package com.youruan.dentistry.console.enroll;

import com.google.common.collect.ImmutableMap;
import com.youruan.dentistry.console.base.interceptor.RequiresPermission;
import com.youruan.dentistry.console.enroll.form.EnrollListForm;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.base.utils.BeanMapUtils;
import com.youruan.dentistry.core.enroll.query.EnrollQuery;
import com.youruan.dentistry.core.enroll.service.EnrollService;
import com.youruan.dentistry.core.enroll.vo.ExtendedEnroll;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enroll")
public class EnrollController {

    private final EnrollService enrollService;

    public EnrollController(EnrollService enrollService) {
        this.enrollService = enrollService;
    }

    @GetMapping("/list")
    @RequiresPermission(value = "enroll.enroll.list",description = "报名-列表")
    public ResponseEntity<?> list(EnrollListForm form) {
        EnrollQuery qo = form.buildQuery();
        Pagination<ExtendedEnroll> pagination = enrollService.query(qo);
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("data", BeanMapUtils.pick(pagination.getData(),
                        "id","createdDate","orderNo","type","realName","phoneNumber","orderStatus"))
                .put("rows",pagination.getRows())
                .build());
    }

}
