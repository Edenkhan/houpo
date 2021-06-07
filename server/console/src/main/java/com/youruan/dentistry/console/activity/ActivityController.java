package com.youruan.dentistry.console.activity;

import com.google.common.collect.ImmutableMap;
import com.youruan.dentistry.console.activity.form.activityAddForm;
import com.youruan.dentistry.console.activity.form.activityEditForm;
import com.youruan.dentistry.console.activity.form.activityListForm;
import com.youruan.dentistry.console.base.interceptor.RequiresPermission;
import com.youruan.dentistry.core.activity.domain.activity;
import com.youruan.dentistry.core.activity.query.activityQuery;
import com.youruan.dentistry.core.activity.service.activityService;
import com.youruan.dentistry.core.activity.vo.Extendedactivity;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.base.storage.UploadFile;
import com.youruan.dentistry.core.base.utils.BeanMapUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    private final activityService activityService;

    @Value("${dentistry.core.base.storage.disk.base-directory}")
    private String baseDirectory;


    public ActivityController(activityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/add")
    @RequiresPermission(value = "activity.activity.add", description = "轮播图-添加")
    public ResponseEntity<?> add(activityAddForm form) {
        activity activity = activityService.create(
                form.getactivityName(),
                form.getImageUrl(),
                form.getLinkUrl(),
                form.getStatus());
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("id", activity.getId())
                .build());
    }

    @PostMapping("/edit")
    @RequiresPermission(value = "activity.activity.edit", description = "轮播图-修改")
    public ResponseEntity<?> edit(activityEditForm form) {
        activity activity = activityService.get(form.getId());
        activityService.update(
                activity,
                form.getactivityName(),
                form.getImageUrl(),
                form.getLinkUrl(),
                form.getStatus());
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("id", activity.getId())
                .build());
    }
    

    @GetMapping("/list")
    @RequiresPermission(value = "activity.activity.list",description = "轮播图-列表")
    public ResponseEntity<?> list(activityListForm form) {
        activityQuery qo = form.buildQuery();
        Pagination<Extendedactivity> pagination = activityService.query(qo);
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("data", BeanMapUtils.pick(pagination.getData(),
                        "id","createdDate","activityName","imageUrl","linkUrl","status"))
                .put("rows",pagination.getRows())
                .build());
    }

    @GetMapping("/get")
    @RequiresPermission(value = "activity.activity.get", description = "轮播图-获取")
    public ResponseEntity<?> get(Long id) {
        activity activity = activityService.get(id);
        return ResponseEntity.ok(BeanMapUtils.pick(activity,
                "activityName","linkUrl","status","imageUrl"));
    }


    @PostMapping("/upload")
    @RequiresPermission(value = "activity.activity.upload",description = "轮播图-上传")
    public ResponseEntity<?> upload(MultipartFile file) throws IOException {
        UploadFile uploadFile = new UploadFile();
        uploadFile.setOriginalFilename(file.getOriginalFilename());
        uploadFile.setInputStream(file.getInputStream());
        uploadFile.setSize(file.getSize());
        String imageUrl = activityService.upload(uploadFile);
        Map<String,String> resultMap = new HashMap<>();
        System.out.println("imageUrl = "+imageUrl);
        resultMap.put("imageUrl",imageUrl);
        return ResponseEntity.ok(resultMap);
    }
    

}
