package com.youruan.dentistry.portal.wx.banner;

import com.youruan.dentistry.core.banner.domain.Banner;
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

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        BannerQuery query = new BannerQuery();
        query.setStatus(Banner.BANNER_STATUS_OPEN);
        Pagination<ExtendedBanner> pagination = bannerService.query(query);
        return ResponseEntity.ok(BeanMapUtils.pick(pagination.getData(),"id","linkUrl","imageUrl"));
    }

}
