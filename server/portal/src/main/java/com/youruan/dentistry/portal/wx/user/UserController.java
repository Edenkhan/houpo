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
import com.youruan.dentistry.portal.wx.user.form.UserEditForm;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<?> get(RegisteredUser user) {
        RegisteredUser userBasicInfo = registeredUserService.get(user.getId());
        UserOtherInfo userOtherInfo = userOtherInfoService.get(user.getId());
        UserAllInfoVo userAllInfoVo = new UserAllInfoVo();
        BeanUtils.copyProperties(userOtherInfo,userAllInfoVo);
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
    public ResponseEntity<?> profile() {
        Long userId = SessionUtils.getAuthenticated();
        RegisteredUser user = registeredUserService.get(userId);
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("user", BeanMapUtils.pick(user,
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

}
