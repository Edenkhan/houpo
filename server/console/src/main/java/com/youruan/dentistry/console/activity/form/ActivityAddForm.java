package com.youruan.dentistry.console.activity.form;

import lombok.Getter;
import lombok.Setter;

/**
 * 添加活动表单对象
 */
@Setter
@Getter
public class ActivityAddForm {

    private String title;
    private String imageUrl;
    private String content;
    private Integer enrollStatus;
    private Integer releaseStatus;
}
