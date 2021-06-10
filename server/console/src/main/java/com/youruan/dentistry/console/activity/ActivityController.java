package com.youruan.dentistry.console.activity;

import com.google.common.collect.ImmutableMap;
import com.youruan.dentistry.console.activity.form.ActivityAddForm;
import com.youruan.dentistry.console.activity.form.ActivityEditForm;
import com.youruan.dentistry.console.activity.form.ActivityListForm;
import com.youruan.dentistry.console.base.interceptor.RequiresPermission;
import com.youruan.dentistry.core.activity.domain.Activity;
import com.youruan.dentistry.core.activity.query.ActivityQuery;
import com.youruan.dentistry.core.activity.service.ActivityService;
import com.youruan.dentistry.core.activity.vo.ExtendedActivity;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.base.storage.UploadFile;
import com.youruan.dentistry.core.base.utils.BeanMapUtils;
import com.youruan.dentistry.core.platform.domain.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityService activityService;
    
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/add")
    @RequiresPermission(value = "activity.activity.add", description = "活动-添加")
    public ResponseEntity<?> add(ActivityAddForm form,Employee employee) {
        Activity activity = activityService.create(
                form.getTitle(),
                form.getImageUrl(),
                form.getContent(),
                form.getEnrollStatus(),
                form.getReleaseStatus(),
                employee.getId());
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("id", activity.getId())
                .build());
    }

    @PostMapping("/edit")
    @RequiresPermission(value = "activity.activity.edit", description = "活动-修改")
    public ResponseEntity<?> edit(ActivityEditForm form, Employee employee) {
        Activity activity = activityService.get(form.getId());
        activityService.update(
                activity,
                form.getTitle(),
                form.getImageUrl(),
                form.getContent(),
                form.getEnrollStatus(),
                form.getReleaseStatus(),
                employee.getId());
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("id", activity.getId())
                .build());
    }
    

    @GetMapping("/list")
    @RequiresPermission(value = "activity.activity.list",description = "活动-列表")
    public ResponseEntity<?> list(ActivityListForm form) {
        ActivityQuery qo = form.buildQuery();
        Pagination<ExtendedActivity> pagination = activityService.query(qo);
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("data", BeanMapUtils.pick(pagination.getData(),
                        "id","createdDate","lastModifiedDate","recentEditor","title","numberOfEntries","enrollStatus","releaseStatus"))
                .put("rows",pagination.getRows())
                .build());
    }

    @GetMapping("/get")
    @RequiresPermission(value = "activity.activity.get", description = "活动-获取")
    public ResponseEntity<?> get(Long id) {
        Activity activity = activityService.get(id);
        return ResponseEntity.ok(BeanMapUtils.pick(activity,
                "title","imageUrl","content","enrollStatus","releaseStatus"));
    }


    @PostMapping("/upload")
    @RequiresPermission(value = "activity.activity.upload",description = "活动-上传")
    public ResponseEntity<?> upload(MultipartFile file) throws IOException {
        UploadFile uploadFile = new UploadFile();
        uploadFile.setOriginalFilename(file.getOriginalFilename());
        uploadFile.setInputStream(file.getInputStream());
        uploadFile.setSize(file.getSize());
        String imageUrl = activityService.upload(uploadFile);
        return ResponseEntity.ok(ImmutableMap.builder().put("imageUrl",imageUrl).build());
    }
    

}
