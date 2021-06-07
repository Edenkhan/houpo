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
    private String bannerName;
    private String linkUrl;
    private Integer status;
    private String imageUrl;

}
