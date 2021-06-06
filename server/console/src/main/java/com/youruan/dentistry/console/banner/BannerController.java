package com.youruan.dentistry.console.banner;

import com.google.common.collect.ImmutableMap;
import com.youruan.dentistry.console.banner.form.BannerListForm;
import com.youruan.dentistry.console.base.interceptor.RequiresPermission;
import com.youruan.dentistry.core.banner.query.BannerQuery;
import com.youruan.dentistry.core.banner.service.BannerService;
import com.youruan.dentistry.core.banner.vo.ExtendedBanner;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.base.utils.BeanMapUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
public class BannerController {

    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping("/list")
    @RequiresPermission(value = "banner.banner.list",description = "轮播图-列表")
    public ResponseEntity<?> list(BannerListForm form) {
        BannerQuery qo = form.buildQuery();
        Pagination<ExtendedBanner> pagination = bannerService.query(qo);
        return ResponseEntity.ok(ImmutableMap.builder()
                .put("data", BeanMapUtils.pick(pagination.getData(),
                        "id","createdDate","bannerName","imageUrl","linkUrl","no","status"))
                .put("rows",pagination.getRows())
                .build());
    }

}
