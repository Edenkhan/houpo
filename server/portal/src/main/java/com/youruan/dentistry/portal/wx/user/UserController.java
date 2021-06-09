package com.youruan.dentistry.portal.wx.user;

import com.google.common.collect.ImmutableMap;
import com.youruan.dentistry.core.base.utils.BeanMapUtils;
import com.youruan.dentistry.core.user.domain.RegisteredUser;
import com.youruan.dentistry.core.user.service.RegisteredUserService;
import com.youruan.dentistry.portal.base.interceptor.RequiresAuthentication;
import com.youruan.dentistry.portal.base.utils.SessionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    private final RegisteredUserService registeredUserService;

    public UserController(RegisteredUserService registeredUserService) {
        this.registeredUserService = registeredUserService;
    }


    @GetMapping("/get")
    @RequiresAuthentication
    public ResponseEntity<?> get() {
        Long userId = SessionUtils.getAuthenticated();
        RegisteredUser user = registeredUserService.get(userId);
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("user", BeanMapUtils.pick(user,
                        "id","avatar","nickname","phoneNumber"))
                .build());
    }

    /**
     * 获取微信用户个人信息
     */
    @GetMapping("/profile")
    @RequiresAuthentication
    public ResponseEntity<?> profile() {
        Long userId = SessionUtils.getAuthenticated();
        RegisteredUser user = registeredUserService.get(userId);
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("user", BeanMapUtils.pick(user,
                        "id","avatar","nickname","phoneNumber"))
                .build());
    }

}
