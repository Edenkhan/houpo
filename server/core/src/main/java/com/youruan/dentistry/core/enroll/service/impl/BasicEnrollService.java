package com.youruan.dentistry.core.enroll.service.impl;

import com.alibaba.fastjson.JSON;
import com.youruan.dentistry.core.activity.service.ActivityService;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.base.utils.HttpClientUtils;
import com.youruan.dentistry.core.base.utils.SnowflakeIdWorker;
import com.youruan.dentistry.core.base.utils.WxAPIV3SignUtils;
import com.youruan.dentistry.core.enroll.domain.Enroll;
import com.youruan.dentistry.core.enroll.domain.pay.Amount;
import com.youruan.dentistry.core.enroll.domain.pay.PayParam;
import com.youruan.dentistry.core.enroll.domain.pay.Payer;
import com.youruan.dentistry.core.enroll.domain.pay.PlaceOrderParam;
import com.youruan.dentistry.core.enroll.mapper.EnrollMapper;
import com.youruan.dentistry.core.enroll.query.EnrollQuery;
import com.youruan.dentistry.core.enroll.service.EnrollService;
import com.youruan.dentistry.core.enroll.vo.ExtendedEnroll;
import com.youruan.dentistry.core.user.service.RegisteredUserService;
import com.youruan.dentistry.core.wx.base.constant.WxPayConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BasicEnrollService implements EnrollService {

    private final EnrollMapper enrollMapper;
    private final ActivityService activityService;
    private final RegisteredUserService userService;

    public BasicEnrollService(EnrollMapper enrollMapper, ActivityService activityService, RegisteredUserService userService) {
        this.enrollMapper = enrollMapper;
        this.activityService = activityService;
        this.userService = userService;
    }

    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.pay.mchid}")
    private String mchId;
    @Value("${wx.pay.privateKey}")
    private String privateKey;
    @Value("${wx.domain}")
    private String domain;

    @Override
    public Pagination<ExtendedEnroll> query(EnrollQuery qo) {
        int rows = enrollMapper.count(qo);
        List<ExtendedEnroll> datas = ( (rows == 0) ? new ArrayList<>() : enrollMapper.query(qo) );
        return new Pagination<>(rows,datas);
    }

    @Override
    public int countByUserIdAndActivityId(Long userId, Long activityId) {
        return enrollMapper.countByUserIdAndActivityId(userId,activityId);
    }

    @Override
    @Transactional
    public Enroll create(Long userId, Long activityId, Integer type) {
        Assert.isTrue(!checkEnroll(userId, activityId),"该用户已经报名此活动");
        Enroll enroll = new Enroll();
        String orderNo = SnowflakeIdWorker.getIdWorker();
        enroll.setOrderNo(orderNo);
        enroll.setType(type);
        enroll.setOrderStatus(Enroll.ORDER_STATUS_NOT);
        enroll.setUserId(userId);
        enroll.setActivityId(activityId);
        // 活动表更新报名人数
        activityService.updateNumberOfEntries(activityId);
        return this.add(enroll);
    }

    @Override
    public Enroll queryOne(Long userId, Integer type) {
        Assert.notNull(type,"类型不能为空");
        return enrollMapper.queryOne(userId,type);
    }

    @Override
    public String placeOrder(String orderNo, String openid) {
        PlaceOrderParam param = new PlaceOrderParam();
        param.setAppid(appId);
        param.setDescription("职场百分百");
        param.setMchid(mchId);
        param.setOut_trade_no(orderNo);
        param.setNotify_url(domain + WxPayConstant.NOTIFY_URI);
        param.setAmount(new Amount(1));
        param.setPayer(new Payer(openid));
        return HttpClientUtils.doPostJson(WxPayConstant.PLACE_ORDER_URL, JSON.toJSONString(param));
    }

    @Override
    public PayParam payHandle(String prepayId) {
        PayParam param = new PayParam();
        param.setAppId(appId);
        param.setTimeStamp(System.currentTimeMillis());
        param.setNonceStr(WxAPIV3SignUtils.generateNonceStr());
        param.setPackageValue(prepayId);
        param.setSignType("RSA");
        String paySign = WxAPIV3SignUtils.getSign(param.getAppId(),
                param.getTimeStamp(),
                param.getNonceStr(),
                param.getPackageValue(),
                privateKey);
        param.setPaySign(paySign);
        return param;
    }

    /**
     * 用户报名
     */
    private Enroll add(Enroll enroll) {
        enroll.setCreatedDate(new Date());
        enrollMapper.add(enroll);
        return enroll;
    }

    /**
     * 检查用户是否已报过名
     */
    private boolean checkEnroll(Long userId, Long activityId) {
        int count = countByUserIdAndActivityId(userId, activityId);
        return count > 0;
    }

}
