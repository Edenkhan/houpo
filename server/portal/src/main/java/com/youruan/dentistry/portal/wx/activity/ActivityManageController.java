package com.youruan.dentistry.portal.wx.activity;

import com.google.common.collect.ImmutableMap;
import com.youruan.dentistry.core.activity.domain.Activity;
import com.youruan.dentistry.core.activity.query.ActivityQuery;
import com.youruan.dentistry.core.activity.service.ActivityService;
import com.youruan.dentistry.core.activity.vo.ActivitySignUp;
import com.youruan.dentistry.core.activity.vo.ExtendedActivity;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.base.utils.BeanMapUtils;
import com.youruan.dentistry.core.enroll.service.EnrollService;
import com.youruan.dentistry.core.user.domain.RegisteredUser;
import com.youruan.dentistry.portal.base.interceptor.RequiresAuthentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activityManage")
public class ActivityManageController {

    private final ActivityService activityService;
    private final EnrollService enrollService;

    public ActivityManageController(ActivityService activityService, EnrollService enrollService) {
        this.activityService = activityService;
        this.enrollService = enrollService;
    }

    @GetMapping("/list")
    @RequiresAuthentication
    public ResponseEntity<?> list() {
        ActivityQuery query = new ActivityQuery();
        query.setReleaseStatus(Activity.RELEASE_STATUS_OPEN);
        Pagination<ExtendedActivity> pagination = activityService.query(query);
        return ResponseEntity.ok(ImmutableMap.builder().put("data", BeanMapUtils.pick(pagination.getData(),
                "id","title","imageUrl","releaseStatus","enrollStatus"))
                .put("rows",pagination.getRows())
                .build());
    }

    @PostMapping("/get")
    @RequiresAuthentication
    public ResponseEntity<?> get(RegisteredUser user,Long id) {
        Activity activity = activityService.get(id);
        int count = enrollService.countByUserIdAndActivityId(user.getId(),id);
        ActivitySignUp activitySignUp = new ActivitySignUp();
        if(count > 0) {
            //已报名
            activitySignUp.setState(ActivitySignUp.ENROLL_OK);
        }else{
            activitySignUp.setState(ActivitySignUp.ENROLL_NOT);
        }
        return ResponseEntity.ok(ImmutableMap.builder().put("activityManage",
                BeanMapUtils.pick(activity,"id","title","createdDate","content","enrollStatus"))
                .put("activitySignUp",BeanMapUtils.pick(activitySignUp,"state"))
                .build());
    }

}
