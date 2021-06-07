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
    public Activity create(String activityName, String imageUrl, String linkUrl, Integer status) {
        Activity activity = new Activity();
        activity.setActivityName(activityName);
        activity.setImageUrl(imageUrl);
        activity.setLinkUrl(linkUrl);
        activity.setStatus(status);
        checkactivity(activity);
        if(checkactivityName(activityName)) {
            throw new RuntimeException("添加失败,活动名称重复");
        }
        return add(activity);
    }

    private boolean checkactivityName(String activityName) {
        ActivityQuery qo = new ActivityQuery();
        qo.setActivityName(activityName);
        int count = activityMapper.count(qo);
        return count > 0;
    }

    private void checkactivity(Activity activity) {
        Assert.notNull(activity.getActivityName(),"活动名称不能为空");
        Assert.notNull(activity.getImageUrl(),"图片不能为空");
        Assert.notNull(activity.getLinkUrl(),"链接地址不能为空");
        Assert.notNull(activity.getStatus(),"活动状态不能为空");
    }

    private Activity add(Activity activity) {
        activity.setCreatedDate(new Date());
        activityMapper.add(activity);
        return activity;
    }

    @Override
    public void update(Activity activity, String activityName, String imageUrl, String linkUrl, Integer status) {
        Assert.notNull(activity, "必须提供活动");
        activity.setActivityName(activityName);
        activity.setImageUrl(imageUrl);
        activity.setLinkUrl(linkUrl);
        activity.setStatus(status);
        update(activity);
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
