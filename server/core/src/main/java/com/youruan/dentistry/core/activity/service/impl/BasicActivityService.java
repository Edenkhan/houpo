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
import com.youruan.dentistry.core.enroll.domain.Enroll;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

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
    public Activity create(String title, String imageUrl, String content, Integer enrollStatus, Integer releaseStatus, Long recentId) {
        checkActivity(title,imageUrl,content,enrollStatus,releaseStatus,recentId);
        Assert.isTrue(!checkAddTitle(title),"活动标题重复");
        Activity activity = new Activity();
        activity.setTitle(title);
        activity.setImageUrl(imageUrl);
        activity.setContent(content);
        activity.setEnrollStatus(enrollStatus);
        activity.setReleaseStatus(releaseStatus);
        activity.setRecentId(recentId);
        return add(activity);
    }

    /**
     * 检查添加活动时，活动标题是否重复
     * 重复返回 true
     */
    private boolean checkAddTitle(String title) {
        ActivityQuery qo = new ActivityQuery();
        qo.setTitle(title);
        int count = activityMapper.count(qo);
        return count > 0;
    }

    /**
     * 判断活动属性是否为空
     */
    private void checkActivity(String title, String imageUrl, String content, Integer enrollStatus, Integer releaseStatus, Long recentId) {
        Assert.notNull(title,"活动标题不能为空");
        Assert.notNull(imageUrl,"活动图片不能为空");
        Assert.notNull(content,"活动内容不能为空");
        Assert.notNull(enrollStatus,"报名状态不能为空");
        Assert.notNull(releaseStatus,"发布状态不能为空");
        Assert.notNull(releaseStatus,"发布状态不能为空");
        Assert.notNull(recentId,"最近编辑人id不能为空");
    }

    private Activity add(Activity activity) {
        activity.setCreatedDate(new Date());
        activityMapper.add(activity);
        return activity;
    }

    @Override
    public void update(Activity activity, String title, String imageUrl, String content, Integer enrollStatus, Integer releaseStatus, Long recentId) {
        checkActivity(title,imageUrl,content,enrollStatus,releaseStatus,recentId);
        Assert.notNull(activity, "必须提供活动");
        Assert.isTrue(!checkUpdateTitle(activity,title),"活动标题重复");
        activity.setTitle(title);
        activity.setImageUrl(imageUrl);
        activity.setContent(content);
        activity.setEnrollStatus(enrollStatus);
        activity.setReleaseStatus(releaseStatus);
        activity.setRecentId(recentId);
        update(activity);
    }

    /**
     * 检查修改活动时，活动标题是否重复
     * 重复返回 true
     */
    private boolean checkUpdateTitle(Activity activity, String title) {
        ActivityQuery qo = new ActivityQuery();
        qo.setTitle(title);
        int count = activityMapper.count(qo);
        return count > 0 && !activity.getTitle().equals(title);
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

    @Override
    public void updateNumberOfEntries(Long activityId) {
        Activity activity = activityMapper.get(activityId);
        Assert.notNull(activity,"必须提供活动");
        if(activity.getNumberOfEntries()==null) {
            activity.setNumberOfEntries(1);
        }else{
            activity.setNumberOfEntries(activity.getNumberOfEntries()+1);
        }
        activityMapper.updateNumberOfEntries(activity);
    }


    @Override
    public void pickSet(List<Long> activityIds, List<ExtendedActivity> extendedActivityList) {
        if(!CollectionUtils.isEmpty(activityIds) && !CollectionUtils.isEmpty(extendedActivityList)) {
            for (Long activityId : activityIds) {
                for (ExtendedActivity extendedActivity : extendedActivityList) {
                    if(activityId.equals(extendedActivity.getId())) {
                        extendedActivity.setOrderStatus(Enroll.ORDER_STATUS_OK);
                    }
                }
            }
        }
    }

    @Override
    public List<ExtendedActivity> list() {
        return activityMapper.list();
    }

    private void update(Activity activity) {
        int affected = activityMapper.update(activity);
        if (affected == 0) {
            throw new OptimismLockingException("version!!");
        }
        activity.setVersion((activity.getVersion()+ 1));
    }
}
