package com.youruan.dentistry.console.user;

import com.google.common.collect.ImmutableMap;
import com.youruan.dentistry.console.base.interceptor.RequiresPermission;
import com.youruan.dentistry.console.user.form.UserAddForm;
import com.youruan.dentistry.console.user.form.UserEditForm;
import com.youruan.dentistry.console.user.form.UserListForm;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.base.utils.BeanMapUtils;
import com.youruan.dentistry.core.user.domain.RegisteredUser;
import com.youruan.dentistry.core.user.query.RegisteredUserQuery;
import com.youruan.dentistry.core.user.service.RegisteredUserService;
import com.youruan.dentistry.core.user.vo.UserAllInfoVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final RegisteredUserService registeredUserService;

    public UserController(RegisteredUserService registeredUserService) {
        this.registeredUserService = registeredUserService;
    }

    @PostMapping("/add")
    @RequiresPermission(value = "user.user.add", description = "用户-添加")
    public ResponseEntity<?> add(UserAddForm form) {
        RegisteredUser user = registeredUserService.create(
                form.getPhoneNumber(),
                form.getLocked());
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("id", user.getId())
                .build());
    }

    @PostMapping("/edit")
    @RequiresPermission(value = "user.user.edit", description = "用户-修改")
    public ResponseEntity<?> edit(UserEditForm form) {
        RegisteredUser user = registeredUserService.get(form.getId());
        registeredUserService.update(
                user,
                form.getPhoneNumber(),
                form.getLocked());
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("id", user.getId())
                .build());
    }

    @GetMapping("/list")
    @RequiresPermission(value = "user.user.list", description = "用户-列表")
    public ResponseEntity<?> list(UserListForm form) {
        RegisteredUserQuery qo = form.buildQuery();
        Pagination<UserAllInfoVo> pagination = registeredUserService.queryAll(qo);
        System.out.println("all = "+pagination.getData());
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("data", BeanMapUtils.pick(pagination.getData(),
                        "id", "createdDate","realName",
                        "age","gender", "phoneNumber", "locked",
                        "school","major","education","job","area"
                        ))
                .put("rows", pagination.getRows())
                .build());
    }

    @PostMapping("/get")
    @RequiresPermission(value = "user.user.get", description = "用户-获取")
    public ResponseEntity<?> get(@RequestParam("id") Long id) {
        RegisteredUser user = registeredUserService.get(id);
        return ResponseEntity.ok(BeanMapUtils.pick(user, "id", "no", "createdDate", "phoneNumber",  "locked"));
    }

}
