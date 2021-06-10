package com.youruan.dentistry.console.activity.form;

import lombok.Getter;
import lombok.Setter;

/**
 * 修改轮播图表单对象
 */
@Setter
@Getter
public class ActivityEditForm {

    private Long id;
    private String title;
    private String imageUrl;
    private String content;
    private Integer enrollStatus;
    private Integer releaseStatus;

}
