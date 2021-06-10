package com.youruan.dentistry.portal.wx.activity;

import com.google.common.collect.ImmutableMap;
import com.youruan.dentistry.core.activity.domain.Activity;
import com.youruan.dentistry.core.activity.query.ActivityQuery;
import com.youruan.dentistry.core.activity.service.ActivityService;
import com.youruan.dentistry.core.activity.vo.ExtendedActivity;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.base.utils.BeanMapUtils;
import com.youruan.dentistry.portal.base.interceptor.RequiresAuthentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activityManage")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/list")
    @RequiresAuthentication
    public ResponseEntity<?> list() {
        ActivityQuery query = new ActivityQuery();
        query.setReleaseStatus(Activity.RELEASE_STATUS_OPEN);
        Pagination<ExtendedActivity> pagination = activityService.query(query);
        return ResponseEntity.ok(ImmutableMap.builder().put("data", BeanMapUtils.pick(pagination,
                "id","title","imageUrl","opened","registered")).build());
    }

}
