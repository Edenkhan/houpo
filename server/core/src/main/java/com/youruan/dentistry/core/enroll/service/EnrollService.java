package com.youruan.dentistry.core.enroll.service;

import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.enroll.domain.Enroll;
import com.youruan.dentistry.core.enroll.query.EnrollQuery;
import com.youruan.dentistry.core.enroll.vo.EnrollActivityVo;
import com.youruan.dentistry.core.enroll.vo.ExtendedEnroll;
import com.youruan.dentistry.core.user.domain.RegisteredUser;

import java.util.List;
import java.util.Map;

public interface EnrollService {

    /**
     * 条件分页查询-报名信息
     */
    Pagination<ExtendedEnroll> query(EnrollQuery qo);

    /**
     * 用户报名
     */
    Enroll baseCreate(RegisteredUser user, Long activityId, Integer type, Integer state);

    /**
     * 查询用户订单状态
     */
    Enroll queryOne(Long userId, Integer type);

    /**
     * 下单
     */
    String placeOrder(RegisteredUser user, Enroll enroll, String ip);

    /**
     * 返回JSAPI调起支付所需参数
     */
    Map<String,String> payHandle(String prepayId);

    /**
     * 检查普通活动，用户是否报名
     */
    int countByUserIdAndActivityId(Long userId, Long activityId);

    /**
     * 查询单条记录
     */
    Enroll get(Long id);

    /**
     * 修改订单状态 已支付
     */
    void setOrderStatus(Enroll enroll);

    /**
     * 根据订单号查询报名信息
     */
    Enroll getByOrderNo(String orderNo);

    /**
     * 查询所有报名信息
     */
    List<ExtendedEnroll> list();

    /**
     * 查询用户报名了哪些活动
     */
    List<Long> getActivityIdsByUserId(Long userId);

    /**
     * 查询当前用户所有报名
     */
    List<EnrollActivityVo> listByUser(Long userId);

    /**
     * 职场百分百报名
     */
    Enroll workplaceEnroll(RegisteredUser user);

    /**
     * 就业直通车报名
     */
    void employmentEnroll(RegisteredUser user);

    /**
     * 普通活动报名
     */
    Enroll activeEnroll(RegisteredUser user, Long activityId);
}
