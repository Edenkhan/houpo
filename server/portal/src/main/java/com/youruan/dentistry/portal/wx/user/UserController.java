package com.youruan.dentistry.portal.wx.user;

import com.google.common.collect.ImmutableMap;
import com.youruan.dentistry.core.base.utils.BeanMapUtils;
import com.youruan.dentistry.core.message.SmsVerificationProperties;
import com.youruan.dentistry.core.message.domain.SmsVerification;
import com.youruan.dentistry.core.message.service.SmsVerificationService;
import com.youruan.dentistry.core.user.domain.RegisteredUser;
import com.youruan.dentistry.core.user.domain.UserOtherInfo;
import com.youruan.dentistry.core.user.service.RegisteredUserService;
import com.youruan.dentistry.core.user.service.UserOtherInfoService;
import com.youruan.dentistry.core.user.vo.UserAllInfoVo;
import com.youruan.dentistry.portal.base.interceptor.RequiresAuthentication;
import com.youruan.dentistry.portal.wx.user.form.BindPhoneForm;
import com.youruan.dentistry.portal.wx.user.form.UserEditForm;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
public class UserController {

    private final RegisteredUserService registeredUserService;
    private final UserOtherInfoService userOtherInfoService;
    private final SmsVerificationService verificationService;
    private final SmsVerificationProperties verificationProperties;

    public UserController(RegisteredUserService registeredUserService, UserOtherInfoService userOtherInfoService, SmsVerificationService verificationService, SmsVerificationProperties verificationProperties) {
        this.registeredUserService = registeredUserService;
        this.userOtherInfoService = userOtherInfoService;
        this.verificationService = verificationService;
        this.verificationProperties = verificationProperties;
    }

    @GetMapping("/get")
    @RequiresAuthentication
    public ResponseEntity<?> get(RegisteredUser user) {
        RegisteredUser userBasicInfo = registeredUserService.get(user.getId());
        UserOtherInfo userOtherInfo = userOtherInfoService.get(user.getId());
        UserAllInfoVo userAllInfoVo = new UserAllInfoVo();
        if(userOtherInfo!=null) {
            BeanUtils.copyProperties(userOtherInfo,userAllInfoVo);
        }
        BeanUtils.copyProperties(userBasicInfo,userAllInfoVo);
        return ResponseEntity.ok(BeanMapUtils.pick(userAllInfoVo,
                "id","avatar","nickname","realName","age",
                "gender","major","school","education","job","area"));
    }

    /**
     * 获取微信用户个人信息
     */
    @GetMapping("/profile")
    @RequiresAuthentication
    public ResponseEntity<?> profile(RegisteredUser user) {
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("user", BeanMapUtils.pick(
                        registeredUserService.get(user.getId()),
                        "id","avatar","nickname","phoneNumber"))
                .build());
    }

    /**
     * 修改用户个人信息
     */
    @PostMapping("/edit")
    @RequiresAuthentication
    public ResponseEntity<?> edit(UserEditForm form) {
        RegisteredUser user = registeredUserService.get(form.getId());
        Assert.notNull(user,"必须提供用户");
        BeanUtils.copyProperties(form,user);
        registeredUserService.modify(user,
                form.getMajor(),
                form.getSchool(),
                form.getEducation(),
                form.getJob(),
                form.getArea());
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("user", BeanMapUtils.pick(user,
                        "id","avatar","nickname","phoneNumber"))
                .build());
    }

    /**
     * 检查用户资料是否完善
     */
    @PostMapping("/check")
    @RequiresAuthentication
    public ResponseEntity<?> check(RegisteredUser user) {
        return ResponseEntity.ok(ImmutableMap.builder()
        .put("check",registeredUserService.checkCompleteInfo(user))
        .build());
    }

    /**
     * 发送验证码
     */
    @PostMapping("/sendLoginVerifyCode")
    @RequiresAuthentication
    public ResponseEntity<?> sendLoginVerifyCode(String phoneNumber, HttpServletRequest request) {
        String requestIp = request.getRemoteAddr();
        System.out.println("ip = " + requestIp);
        verificationService.sendLogin(phoneNumber, request.getRemoteAddr());
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("intervalSeconds",verificationProperties.getIntervalInSeconds())
                .build());
    }

    /**
     * 绑定手机号
     */
    @PostMapping("/bind")
    @RequiresAuthentication
    public ResponseEntity<?> bind(BindPhoneForm form,RegisteredUser user) {
        SmsVerification smsVerification = verificationService.getByPhoneLastCode(form.getPhone());
        registeredUserService.bindPhone(smsVerification,form.getPhone(),form.getVerificationCode(),user.getId());
        return ResponseEntity.ok().build();
    }

}
