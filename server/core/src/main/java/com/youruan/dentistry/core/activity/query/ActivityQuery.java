package com.youruan.dentistry.core.activity.query;

import com.youruan.dentistry.core.base.query.QueryCondition;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 活动条件查询
 */
@Getter
@Setter
public class ActivityQuery extends QueryCondition {


    protected Long[] ids;
    protected Date startCreatedDate;
    protected Date endCreatedDate;
    protected String title;
    protected String likeTitle;
    protected Integer enrollStatus;
    protected Integer releaseStatus;

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
