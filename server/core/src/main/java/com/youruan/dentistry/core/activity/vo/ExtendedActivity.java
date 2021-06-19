package com.youruan.dentistry.core.activity.vo;

import com.youruan.dentistry.core.activity.domain.Activity;
import com.youruan.dentistry.core.enroll.domain.Enroll;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExtendedActivity extends Activity {

    private final static long serialVersionUID = 1L;
    /**
     * 最后编辑人
     */
    private String recentEditor;
    /**
     * 是否报名
     */
    private Integer orderStatus;
    /**
     * 普通活动类型
     */
    private Integer type = Enroll.TYPE_GENERAL;
}
