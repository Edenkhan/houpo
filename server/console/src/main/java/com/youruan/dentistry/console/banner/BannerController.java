package com.youruan.dentistry.console.banner;

import com.google.common.collect.ImmutableMap;
import com.youruan.dentistry.console.banner.form.BannerAddForm;
import com.youruan.dentistry.console.banner.form.BannerEditForm;
import com.youruan.dentistry.console.banner.form.BannerListForm;
import com.youruan.dentistry.console.base.interceptor.RequiresPermission;
import com.youruan.dentistry.core.banner.domain.Banner;
import com.youruan.dentistry.core.banner.query.BannerQuery;
import com.youruan.dentistry.core.banner.service.BannerService;
import com.youruan.dentistry.core.banner.vo.ExtendedBanner;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.base.storage.UploadFile;
import com.youruan.dentistry.core.base.utils.BeanMapUtils;
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
@RequestMapping("/banner")
public class BannerController {

    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @PostMapping("/add")
    @RequiresPermission(value = "banner.banner.add", description = "轮播图-添加")
    public ResponseEntity<?> add(BannerAddForm form) {
        Banner banner = bannerService.create(
                form.getBannerName(),
                form.getImageUrl(),
                form.getLinkUrl(),
                form.getStatus());
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("id", banner.getId())
                .build());
    }

    @PostMapping("/edit")
    @RequiresPermission(value = "banner.banner.edit", description = "轮播图-修改")
    public ResponseEntity<?> edit(BannerEditForm form) {
        Banner banner = bannerService.get(form.getId());
        bannerService.update(
                banner,
                form.getBannerName(),
                form.getImageUrl(),
                form.getLinkUrl(),
                form.getStatus());
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("id", banner.getId())
                .build());
    }
    

    @GetMapping("/list")
    @RequiresPermission(value = "banner.banner.list",description = "轮播图-列表")
    public ResponseEntity<?> list(BannerListForm form) {
        BannerQuery qo = form.buildQuery();
        Pagination<ExtendedBanner> pagination = bannerService.query(qo);
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("data", BeanMapUtils.pick(pagination.getData(),
                        "id","createdDate","bannerName","imageUrl","linkUrl","status"))
                .put("rows",pagination.getRows())
                .build());
    }

    @GetMapping("/get")
    @RequiresPermission(value = "banner.banner.get", description = "轮播图-获取")
    public ResponseEntity<?> get(Long id) {
        Banner banner = bannerService.get(id);
        return ResponseEntity.ok(BeanMapUtils.pick(banner,
                "bannerName","linkUrl","status","imageUrl"));
    }


    @PostMapping("/upload")
    @RequiresPermission(value = "banner.banner.upload",description = "轮播图-上传")
    public ResponseEntity<?> upload(MultipartFile file) throws IOException {
        UploadFile uploadFile = new UploadFile();
        uploadFile.setOriginalFilename(file.getOriginalFilename());
        uploadFile.setInputStream(file.getInputStream());
        uploadFile.setSize(file.getSize());
        String imageUrl = bannerService.upload(uploadFile);
        Map<String,String> resultMap = new HashMap<>();
        System.out.println("imageUrl = "+imageUrl);
        resultMap.put("imageUrl",imageUrl);
        return ResponseEntity.ok(resultMap);
    }
    

}
