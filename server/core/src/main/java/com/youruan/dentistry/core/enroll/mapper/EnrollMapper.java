package com.youruan.dentistry.core.enroll.mapper;

import com.youruan.dentistry.core.enroll.domain.Enroll;
import com.youruan.dentistry.core.enroll.query.EnrollQuery;
import com.youruan.dentistry.core.enroll.vo.ExtendedEnroll;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnrollMapper {

    /**
     * 查询报名信息总条目
     */
    int count(EnrollQuery qo);

    /**
     * 条件分页查询 报名信息
     */
    List<ExtendedEnroll> query(EnrollQuery qo);

    /**
     * 根据用户id和活动id
     * 查询是否有报名信息
     */
    int countByUserIdAndActivityId(Long userId, Long activityId);

    /**
     * 用户报名
     */
    int add(Enroll enroll);

    /**
     * 查询用户订单状态
     */
    Enroll queryOne(Long userId, Integer type);

    /**
     * 检查用户是否报名职业百分百，或就业直通车
     */
    int countByUserIdAndType(Long userId, Integer type);

    /**
     * 查询报名记录
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
}
