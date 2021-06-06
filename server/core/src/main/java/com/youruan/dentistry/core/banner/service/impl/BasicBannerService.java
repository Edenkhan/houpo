package com.youruan.dentistry.core.banner.service.impl;

import com.youruan.dentistry.core.banner.mapper.BannerMapper;
import com.youruan.dentistry.core.banner.query.BannerQuery;
import com.youruan.dentistry.core.banner.service.BannerService;
import com.youruan.dentistry.core.banner.vo.ExtendedBanner;
import com.youruan.dentistry.core.base.query.Pagination;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasicBannerService implements BannerService {

    private final BannerMapper bannerMapper;

    public BasicBannerService(BannerMapper bannerMapper) {
        this.bannerMapper = bannerMapper;
    }

    @Override
    public Pagination<ExtendedBanner> query(BannerQuery qo) {
        int rows = bannerMapper.count(qo);
        List<ExtendedBanner> datas = ( (rows == 0) ? new ArrayList<>() : bannerMapper.query(qo) );
        return new Pagination<>(rows,datas);
    }
}
