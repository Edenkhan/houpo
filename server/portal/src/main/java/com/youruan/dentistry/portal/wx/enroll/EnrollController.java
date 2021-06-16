package com.youruan.dentistry.portal.wx.enroll;

import com.google.common.collect.ImmutableMap;
import com.youruan.dentistry.core.base.utils.BeanMapUtils;
import com.youruan.dentistry.core.enroll.domain.Enroll;
import com.youruan.dentistry.core.enroll.domain.pay.PayParam;
import com.youruan.dentistry.core.enroll.service.EnrollService;
import com.youruan.dentistry.core.user.domain.RegisteredUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enroll")
public class EnrollController {

    private final EnrollService enrollService;

    public EnrollController(EnrollService enrollService) {
        this.enrollService = enrollService;
    }

    @PostMapping("/query")
    public ResponseEntity<?> query(RegisteredUser user, Integer type) {
        Enroll enroll = enrollService.queryOne(user.getId(),type);
        return ResponseEntity.ok(BeanMapUtils.pick(enroll,"id","orderStatus"));
    }

    /**
     * 报名支付下单
     */
    @PostMapping("/add")
    public ResponseEntity<?> add(RegisteredUser user, Integer type) {
        Enroll enroll = enrollService.create(user.getId(), null,type);
        String prepayId = enrollService.placeOrder(enroll.getOrderNo(),user.getOpenid());
        PayParam param = enrollService.payHandle(prepayId);
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("id",enroll.getId())
                .put("result",BeanMapUtils.pick(param,
                        "appId","timeStamp","signType","nonceStr","packageValue","paySign"))
                .build());
    }

}
