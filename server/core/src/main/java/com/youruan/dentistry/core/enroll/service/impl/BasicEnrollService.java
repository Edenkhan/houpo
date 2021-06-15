package com.youruan.dentistry.core.enroll.service.impl;

import com.youruan.dentistry.core.activity.domain.Activity;
import com.youruan.dentistry.core.activity.mapper.ActivityMapper;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.base.utils.SnowflakeIdWorker;
import com.youruan.dentistry.core.enroll.domain.Enroll;
import com.youruan.dentistry.core.enroll.mapper.EnrollMapper;
import com.youruan.dentistry.core.enroll.query.EnrollQuery;
import com.youruan.dentistry.core.enroll.service.EnrollService;
import com.youruan.dentistry.core.enroll.vo.ExtendedEnroll;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BasicEnrollService implements EnrollService {

    private final EnrollMapper enrollMapper;
    private final ActivityMapper activityMapper;

    public BasicEnrollService(EnrollMapper enrollMapper, ActivityMapper activityMapper) {
        this.enrollMapper = enrollMapper;
        this.activityMapper = activityMapper;
    }

    @Override
    public Pagination<ExtendedEnroll> query(EnrollQuery qo) {
        int rows = enrollMapper.count(qo);
        List<ExtendedEnroll> datas = ( (rows == 0) ? new ArrayList<>() : enrollMapper.query(qo) );
        return new Pagination<>(rows,datas);
    }

    @Override
    public int countByUserIdAndActivityId(Long userId, Long activityId) {
        return enrollMapper.countByUserIdAndActivityId(userId,activityId);
    }

    @Override
    public Enroll create(Long userId, Long activityId) {
        Assert.isTrue(!checkEnroll(userId, activityId),"该用户已经报名此活动");
        Enroll enroll = new Enroll();
        enroll.setOrderNo(SnowflakeIdWorker.getIdWorker());
        enroll.setType(Enroll.TYPE_GENERAL);
        enroll.setOrderStatus(Enroll.ORDER_STATUS_NOT);
        enroll.setUserId(userId);
        enroll.setActivityId(activityId);
        // 活动表更新报名人数
        this.updateNumberOfEntries(activityId);
        return this.add(enroll);
    }

    /**
     * 活动表更新报名人数
     */
    private void updateNumberOfEntries(Long activityId) {
        Activity activity = activityMapper.get(activityId);
        Assert.notNull(activity,"必须提供活动");
        if(activity.getNumberOfEntries()==null) {
            activity.setNumberOfEntries(1);
        }else{
            activity.setNumberOfEntries(activity.getNumberOfEntries()+1);
        }
        activityMapper.updateNumberOfEntries(activity);
    }

    /**
     * 用户报名
     */
    private Enroll add(Enroll enroll) {
        enroll.setCreatedDate(new Date());
        enrollMapper.add(enroll);
        return enroll;
    }

    /**
     * 检查用户是否已报过名
     */
    private boolean checkEnroll(Long userId, Long activityId) {
        int count = countByUserIdAndActivityId(userId, activityId);
        return count > 0;
    }

}
