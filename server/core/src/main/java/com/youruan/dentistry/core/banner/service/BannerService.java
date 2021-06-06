package com.youruan.dentistry.core.banner.service;

import com.youruan.dentistry.core.banner.query.BannerQuery;
import com.youruan.dentistry.core.banner.vo.ExtendedBanner;
import com.youruan.dentistry.core.base.query.Pagination;

public interface BannerService {

    /**
     * 条件分页查询-轮播图
     */
    Pagination<ExtendedBanner> query(BannerQuery qo);
}
