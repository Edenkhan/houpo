package com.youruan.dentistry.portal.wx.enroll;

import com.google.common.collect.ImmutableMap;
import com.youruan.dentistry.core.base.utils.BeanMapUtils;
import com.youruan.dentistry.core.base.utils.StreamUtils;
import com.youruan.dentistry.core.base.wxpay.sdk.WXPayUtil;
import com.youruan.dentistry.core.enroll.domain.Enroll;
import com.youruan.dentistry.core.enroll.service.EnrollService;
import com.youruan.dentistry.core.enroll.vo.EnrollActivityVo;
import com.youruan.dentistry.core.user.domain.RegisteredUser;
import com.youruan.dentistry.portal.base.interceptor.RequiresAuthentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enroll")
public class EnrollController {

    private final EnrollService enrollService;

    public EnrollController(EnrollService enrollService) {
        this.enrollService = enrollService;
    }

    @PostMapping("/list")
    @RequiresAuthentication
    public ResponseEntity<?> list(RegisteredUser user) {
        List<EnrollActivityVo> enrollActivityVoList = enrollService.listByUser(user.getId());
        return ResponseEntity.ok(ImmutableMap.builder().put("data", BeanMapUtils.pick(enrollActivityVoList,
                "id","createdDate","type","title","linkUrl","imageUrl","content","price","orderStatus"))
                .build());
    }


    @PostMapping("/query")
    @RequiresAuthentication
    public ResponseEntity<?> query(RegisteredUser user, Integer type) {
        Enroll enroll = enrollService.queryOne(user.getId(),type);
        return ResponseEntity.ok(BeanMapUtils.pick(enroll,"id","orderStatus"));
    }

    /**
     * 职场百分百报名
     */
    @PostMapping("/workplaceAdd")
    @RequiresAuthentication
    public ResponseEntity<?> workplaceAdd(RegisteredUser user, String ip) {
        Enroll enroll = enrollService.workplaceEnroll(user);
        return wxPay(user,enroll,ip);
    }

    /**
     * 就业直通车报名
     */
    @PostMapping("/employmentAdd")
    @RequiresAuthentication
    public ResponseEntity<?> employmentAdd(RegisteredUser user) {
        enrollService.employmentEnroll(user);
        return ResponseEntity.ok().build();
    }

    /**
     * 支付
     */
    @PostMapping("/pay")
    @RequiresAuthentication
    public ResponseEntity<?> pay(Long id, RegisteredUser user,String ip) {
        Enroll enroll = enrollService.get(id);
        return wxPay(user,enroll,ip);
    }

    /**
     * 微信支付
     */
    private ResponseEntity<?> wxPay(RegisteredUser user, Enroll enroll, String ip) {
        enrollService.deleteExpiredPrepayId(enroll,2);
        String prepayId = enrollService.placeOrder(user, enroll,ip);
        Map<String,String> resultMap = enrollService.payHandle(prepayId);
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("id",enroll.getId())
                .put("result", resultMap)
                .build());
    }



    /**
     * 微信支付回调地址 修改订单状态
     */
    @PostMapping("/notify")
    public ResponseEntity<?> notify_(HttpServletRequest request) {
        System.out.println("***************** notify *****************");
        try {
            String xml = StreamUtils.readStream(request.getInputStream());
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            Enroll enroll = enrollService.getByOrderNo(resultMap.get("out_trade_no"));
            if(enroll!=null && enroll.getOrderStatus()==Enroll.ORDER_STATUS_OK) {
                return ResponseEntity.ok("订单已支付");
            }else{
                enrollService.setOrderStatus(enroll);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }

}
