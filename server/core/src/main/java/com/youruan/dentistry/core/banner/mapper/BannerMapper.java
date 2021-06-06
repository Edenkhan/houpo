package com.youruan.dentistry.core.banner.mapper;

import com.youruan.dentistry.core.banner.query.BannerQuery;
import com.youruan.dentistry.core.banner.vo.ExtendedBanner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {

    /**
     * 查询轮播图总条目
     */
    int count(BannerQuery qo);

    /**
     * 条件分页查询 轮播图
     */
    List<ExtendedBanner> query(BannerQuery qo);
}
