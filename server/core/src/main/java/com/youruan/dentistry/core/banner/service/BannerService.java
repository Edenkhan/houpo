package com.youruan.dentistry.core.banner.service;

import com.youruan.dentistry.core.banner.domain.Banner;
import com.youruan.dentistry.core.banner.query.BannerQuery;
import com.youruan.dentistry.core.banner.vo.ExtendedBanner;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.base.storage.UploadFile;

public interface BannerService {

    /**
     * 条件分页查询-轮播图
     */
    Pagination<ExtendedBanner> query(BannerQuery qo);

    /**
     * 根据id获取轮播图信息
     */
    Banner get(Long id);

    /**
     * 添加轮播图
     */
    Banner create(String bannerName, String imageUrl, String linkUrl, Integer status);

    /**
     * 修改轮播图
     */
    void update(Banner banner, String bannerName, String imageUrl, String linkUrl, Integer status);

    /**
     * 上传轮播图
     */
    String upload(UploadFile uploadFile);
}
