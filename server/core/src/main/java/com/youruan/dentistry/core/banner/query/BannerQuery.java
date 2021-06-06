package com.youruan.dentistry.core.banner.query;

import com.youruan.dentistry.core.base.query.QueryCondition;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 轮播图条件查询
 */
@Getter
@Setter
public class BannerQuery extends QueryCondition {


    protected Long[] ids;
    protected Date startCreatedDate;
    protected Date endCreatedDate;
    protected String bannerName;
    protected String likeBannerName;
    protected Boolean status;

    public void setOrderById(int keyword) {
        setOrderBy("id", keyword);
    }

    public int getOrderById() {
        return getOrderByKeyword("id");
    }

    public void setOrderByCreatedDate(int keyword) {
        setOrderBy("createdDate", keyword);
    }

    public int getOrderByCreatedDate() {
        return getOrderByKeyword("createdDate");
    }



}
