package com.youruan.dentistry.core.activity.service.impl;

import com.youruan.dentistry.core.activity.domain.Activity;
import com.youruan.dentistry.core.activity.mapper.ActivityMapper;
import com.youruan.dentistry.core.activity.query.ActivityQuery;
import com.youruan.dentistry.core.activity.service.ActivityService;
import com.youruan.dentistry.core.activity.vo.ExtendedActivity;
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
public class BasicActivityService implements ActivityService {

    private final ActivityMapper activityMapper;
    private final DiskFileStorage diskFileStorage;

    public BasicActivityService(ActivityMapper activityMapper, DiskFileStorage diskFileStorage) {
        this.activityMapper = activityMapper;
        this.diskFileStorage = diskFileStorage;
    }

    @Override
    public Pagination<ExtendedActivity> query(ActivityQuery qo) {
        int rows = activityMapper.count(qo);
        List<ExtendedActivity> datas = ( (rows == 0) ? new ArrayList<>() : activityMapper.query(qo) );
        return new Pagination<>(rows,datas);
    }

    @Override
    public Activity get(Long id) {
        return activityMapper.get(id);
    }

    @Override
    public Activity create(String eventTitle, String eventImage, String eventContent, Integer enrollStatus, Integer releaseStatus) {
        checkActivity(eventTitle,eventImage,eventContent,enrollStatus,releaseStatus);
        if(checkAddEventTitle(eventTitle)) {
            throw new RuntimeException("添加失败,活动标题重复");
        }
        Activity activity = new Activity();
        activity.setEventTitle(eventTitle);
        activity.setEventImage(eventImage);
        activity.setEventContent(eventContent);
        activity.setEnrollStatus(enrollStatus);
        activity.setReleaseStatus(releaseStatus);
        return add(activity);
    }

    /**
     * 检查添加活动时，活动标题是否重复
     * 重复返回 true
     */
    private boolean checkAddEventTitle(String eventTitle) {
        ActivityQuery qo = new ActivityQuery();
        qo.setEventTitle(eventTitle);
        int count = activityMapper.count(qo);
        return count > 0;
    }

    /**
     * 判断活动属性是否为空
     */
    private void checkActivity(String eventTitle, String eventImage, String eventContent, Integer enrollStatus, Integer releaseStatus) {
        Assert.notNull(eventTitle,"活动标题不能为空");
        Assert.notNull(eventImage,"活动图片不能为空");
        Assert.notNull(eventContent,"活动内容不能为空");
        Assert.notNull(enrollStatus,"报名状态不能为空");
        Assert.notNull(releaseStatus,"发布状态不能为空");
    }

    private Activity add(Activity activity) {
        activity.setCreatedDate(new Date());
        activityMapper.add(activity);
        return activity;
    }

    @Override
    public void update(Activity activity, String eventTitle, String eventImage, String eventContent, Integer enrollStatus, Integer releaseStatus, String recentEditor) {
        checkActivity(eventTitle,eventImage,eventContent,enrollStatus,releaseStatus);
        Assert.notNull(activity, "必须提供活动");
        if(checkUpdateEventTitle(activity,eventTitle)) {
            throw new RuntimeException("修改失败,活动标题重复");
        }
        activity.setEventTitle(eventTitle);
        activity.setEventImage(eventImage);
        activity.setEventContent(eventContent);
        activity.setEnrollStatus(enrollStatus);
        activity.setReleaseStatus(releaseStatus);
        activity.setRecentEditor(recentEditor);
        update(activity);
    }

    /**
     * 检查修改活动时，活动标题是否重复
     * 重复返回 true
     */
    private boolean checkUpdateEventTitle(Activity activity, String eventTitle) {
        ActivityQuery qo = new ActivityQuery();
        qo.setEventTitle(eventTitle);
        int count = activityMapper.count(qo);
        return count > 0 && !activity.getEventTitle().equals(eventTitle);
    }

    @Override
    public String upload(UploadFile uploadFile) {
        try {
            return diskFileStorage.store(uploadFile,"activity");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void update(Activity activity) {
        int affected = activityMapper.update(activity);
        if (affected == 0) {
            throw new OptimismLockingException("version!!");
        }
        activity.setVersion((activity.getVersion()+ 1));
    }
}
