package com.youruan.dentistry.portal.wx.enroll;

import com.google.common.collect.ImmutableMap;
import com.youruan.dentistry.core.base.utils.BeanMapUtils;
import com.youruan.dentistry.core.enroll.domain.Enroll;
import com.youruan.dentistry.core.enroll.service.EnrollService;
import com.youruan.dentistry.core.user.domain.RegisteredUser;
import com.youruan.dentistry.portal.base.interceptor.RequiresAuthentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/enroll")
public class EnrollController {

    private final EnrollService enrollService;

    public EnrollController(EnrollService enrollService) {
        this.enrollService = enrollService;
    }

    @PostMapping("/query")
    @RequiresAuthentication
    public ResponseEntity<?> query(RegisteredUser user, Integer type) {
        Enroll enroll = enrollService.queryOne(user.getId(),type);
        return ResponseEntity.ok(BeanMapUtils.pick(enroll,"id","orderStatus"));
    }

    /**
     * 报名支付下单
     */
    @PostMapping("/add")
    @RequiresAuthentication
    public ResponseEntity<?> add(RegisteredUser user, Integer type, String ip) {
        Enroll enroll = enrollService.create(user.getId(), null,type);
        return getResponseEntity(user, ip, enroll);
    }

    /**
     * 支付
     */
    @PostMapping("/pay")
    @RequiresAuthentication
    public ResponseEntity<?> pay(Long id, RegisteredUser user,String ip) {
        Enroll enroll = enrollService.get(id);
        return getResponseEntity(user, ip, enroll);
    }

    private ResponseEntity<?> getResponseEntity(RegisteredUser user, String ip, Enroll enroll) {
        String prepayId = enrollService.placeOrder(enroll.getOrderNo(), user.getOpenid(),ip);
        Map<String,String> resultMap = enrollService.payHandle(prepayId);
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("id",enroll.getId())
                .put("result", resultMap)
                .build());
    }



    /**
     * 微信支付回调地址
     */
    @GetMapping("/notify")
    public ResponseEntity<?> notify_() {

        return ResponseEntity.ok().build();
    }

}
