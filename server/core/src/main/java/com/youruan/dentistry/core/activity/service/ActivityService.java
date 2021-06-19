package com.youruan.dentistry.core.activity.service;

import com.youruan.dentistry.core.activity.domain.Activity;
import com.youruan.dentistry.core.activity.query.ActivityQuery;
import com.youruan.dentistry.core.activity.vo.ExtendedActivity;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.base.storage.UploadFile;

import java.util.List;

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
    Activity create(String title, String imageUrl, String content, Integer enrollStatus, Integer releaseStatus, Long recentId);

    /**
     * 修改活动
     */
    void update(Activity activity, String title, String imageUrl, String content, Integer enrollStatus, Integer releaseStatus, Long recentId);
    /**
     * 上传活动
     */
    String upload(UploadFile uploadFile);

    /**
     * 活动表更新报名人数
     */
    void updateNumberOfEntries(Long activityId);

    /**
     * 选择设置 用户活动报名情况
     */
    void pickSet(List<Long> activityIds, List<ExtendedActivity> extendedActivityList);

    /**
     * 获取所有活动信息
     */
    List<ExtendedActivity> list();
}
