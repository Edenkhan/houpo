package com.youruan.dentistry.core.activity.service;

import com.youruan.dentistry.core.activity.domain.Activity;
import com.youruan.dentistry.core.activity.query.ActivityQuery;
import com.youruan.dentistry.core.activity.vo.ExtendedActivity;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.base.storage.UploadFile;

public interface ActivityService {

    /**
     * 条件分页查询-活动
     */
    Pagination<ExtendedActivity> query(ActivityQuery qo);

    /**
     * 根据id获取活动信息
     */
    Activity get(Long id);

    /**
     * 添加活动
     */
    Activity create(String bannerName, String imageUrl, String linkUrl, Integer status);

    /**
     * 修改活动
     */
    void update(Activity banner, String bannerName, String imageUrl, String linkUrl, Integer status);

    /**
     * 上传活动
     */
    String upload(UploadFile uploadFile);
}
