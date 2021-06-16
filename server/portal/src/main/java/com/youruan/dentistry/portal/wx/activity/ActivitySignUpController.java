package com.youruan.dentistry.portal.wx.activity;

import com.google.common.collect.ImmutableMap;
import com.youruan.dentistry.core.enroll.domain.Enroll;
import com.youruan.dentistry.core.enroll.service.EnrollService;
import com.youruan.dentistry.core.user.domain.RegisteredUser;
import com.youruan.dentistry.core.user.service.RegisteredUserService;
import com.youruan.dentistry.portal.base.interceptor.RequiresAuthentication;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activitySignUp")
public class ActivitySignUpController {

    private final EnrollService enrollService;
    private final RegisteredUserService userService;

    public ActivitySignUpController(EnrollService enrollService, RegisteredUserService userService) {
        this.enrollService = enrollService;
        this.userService = userService;
    }

    @PostMapping("/add")
    @RequiresAuthentication
    public ResponseEntity<?> add(RegisteredUser user, Long id) {
        Assert.isTrue(userService.checkCompleteInfo(user),"请先完善信息");
        Enroll enroll = enrollService.create(user.getId(), id,Enroll.TYPE_GENERAL);
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("id", enroll.getId())
                .build());
    }
}
