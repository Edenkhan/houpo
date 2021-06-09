package com.youruan.dentistry.portal.wx.user;

import com.google.common.collect.ImmutableMap;
import com.youruan.dentistry.core.base.utils.BeanMapUtils;
import com.youruan.dentistry.core.user.domain.RegisteredUser;
import com.youruan.dentistry.core.user.domain.UserOtherInfo;
import com.youruan.dentistry.core.user.service.RegisteredUserService;
import com.youruan.dentistry.core.user.service.UserOtherInfoService;
import com.youruan.dentistry.core.user.vo.UserAllInfoVo;
import com.youruan.dentistry.portal.base.interceptor.RequiresAuthentication;
import com.youruan.dentistry.portal.base.utils.SessionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    private final RegisteredUserService registeredUserService;
    private final UserOtherInfoService userOtherInfoService;

    public UserController(RegisteredUserService registeredUserService, UserOtherInfoService userOtherInfoService) {
        this.registeredUserService = registeredUserService;
        this.userOtherInfoService = userOtherInfoService;
    }

    @GetMapping("/get")
    @RequiresAuthentication
    public ResponseEntity<?> get() {
        Long userId = SessionUtils.getAuthenticated();
        RegisteredUser userBasicInfo = registeredUserService.get(userId);
        UserOtherInfo userOtherInfo = userOtherInfoService.get(userId);
        UserAllInfoVo userAllInfoVo = new UserAllInfoVo();
        BeanUtils.copyProperties(userBasicInfo,userAllInfoVo);
        BeanUtils.copyProperties(userOtherInfo,userAllInfoVo);
        return ResponseEntity.ok(BeanMapUtils.pick(userAllInfoVo,
                "id","avatar","nickname","phoneNumber"));
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
