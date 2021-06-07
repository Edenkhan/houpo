package com.youruan.dentistry.console.activity.form;

import lombok.Getter;
import lombok.Setter;

/**
 * 添加活动表单对象
 */
@Setter
@Getter
public class ActivityAddForm {

    private String eventTitle;
    private String eventImage;
    private String eventContent;
    private Integer enrollStatus;
    private Integer releaseStatus;
}
