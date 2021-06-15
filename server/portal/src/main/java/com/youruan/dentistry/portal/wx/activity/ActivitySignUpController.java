package com.youruan.dentistry.portal.wx.activity;

import com.google.common.collect.ImmutableMap;
import com.youruan.dentistry.core.enroll.domain.Enroll;
import com.youruan.dentistry.core.enroll.service.EnrollService;
import com.youruan.dentistry.core.user.domain.RegisteredUser;
import com.youruan.dentistry.portal.base.interceptor.RequiresAuthentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activitySignUp")
public class ActivitySignUpController {

    private final EnrollService enrollService;

    public ActivitySignUpController(EnrollService enrollService) {
        this.enrollService = enrollService;
    }

    @PostMapping("/add")
    @RequiresAuthentication
    public ResponseEntity<?> add(RegisteredUser user, Long id) {
        Enroll enroll = enrollService.create(user.getId(), id);
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("id", enroll.getId())
                .build());
    }
}
