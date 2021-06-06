package com.youruan.dentistry.console.banner.form;

import lombok.Setter;

/**
 * 添加轮播图表单对象
 */
@Setter
public class BannerAddForm {

    private String bannerName;
    private String linkUrl;
    private Boolean status;
    private String imageUrl;

}
