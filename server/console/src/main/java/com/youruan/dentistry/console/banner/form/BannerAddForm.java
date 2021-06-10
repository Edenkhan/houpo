package com.youruan.dentistry.console.banner.form;

import lombok.Getter;
import lombok.Setter;

/**
 * 添加轮播图表单对象
 */
@Setter
@Getter
public class BannerAddForm {

    private String name;
    private String linkUrl;
    private Integer status;
    private String imageUrl;

}
