package com.youruan.dentistry.core.banner.service.impl;

import com.youruan.dentistry.core.banner.domain.Banner;
import com.youruan.dentistry.core.banner.mapper.BannerMapper;
import com.youruan.dentistry.core.banner.query.BannerQuery;
import com.youruan.dentistry.core.banner.service.BannerService;
import com.youruan.dentistry.core.banner.vo.ExtendedBanner;
import com.youruan.dentistry.core.base.exception.OptimismLockingException;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.base.storage.DiskFileStorage;
import com.youruan.dentistry.core.base.storage.UploadFile;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BasicBannerService implements BannerService {

    private final BannerMapper bannerMapper;
    private final DiskFileStorage diskFileStorage;

    public BasicBannerService(BannerMapper bannerMapper, DiskFileStorage diskFileStorage) {
        this.bannerMapper = bannerMapper;
        this.diskFileStorage = diskFileStorage;
    }

    @Override
    public Pagination<ExtendedBanner> query(BannerQuery qo) {
        int rows = bannerMapper.count(qo);
        List<ExtendedBanner> datas = ( (rows == 0) ? new ArrayList<>() : bannerMapper.query(qo) );
        return new Pagination<>(rows,datas);
    }

    @Override
    public Banner get(Long id) {
        return bannerMapper.get(id);
    }

    @Override
    public Banner create(String name, String imageUrl, String linkUrl, Integer status) {
        Banner banner = new Banner();
        banner.setName(name);
        banner.setImageUrl(imageUrl);
        banner.setLinkUrl(linkUrl);
        banner.setStatus(status);
        checkBanner(banner);
        if(checkname(name)) {
            throw new RuntimeException("添加失败,轮播图名称重复");
        }
        return add(banner);
    }

    private boolean checkname(String name) {
        BannerQuery qo = new BannerQuery();
        qo.setName(name);
        int count = bannerMapper.count(qo);
        return count > 0;
    }

    private void checkBanner(Banner banner) {
        Assert.notNull(banner.getName(),"轮播图名称不能为空");
        Assert.notNull(banner.getImageUrl(),"图片不能为空");
        Assert.notNull(banner.getLinkUrl(),"链接地址不能为空");
        Assert.notNull(banner.getStatus(),"轮播图状态不能为空");
    }

    private Banner add(Banner banner) {
        banner.setCreatedDate(new Date());
        bannerMapper.add(banner);
        return banner;
    }

    @Override
    public void update(Banner banner, String name, String imageUrl, String linkUrl, Integer status) {
        Assert.notNull(banner, "必须提供轮播图");
        banner.setName(name);
        banner.setImageUrl(imageUrl);
        banner.setLinkUrl(linkUrl);
        banner.setStatus(status);
        update(banner);
    }

    @Override
    public String upload(UploadFile uploadFile) {
        try {
            return diskFileStorage.store(uploadFile,"banner");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void update(Banner banner) {
        int affected = bannerMapper.update(banner);
        if (affected == 0) {
            throw new OptimismLockingException("version!!");
        }
        banner.setVersion((banner.getVersion()+ 1));
    }
}
