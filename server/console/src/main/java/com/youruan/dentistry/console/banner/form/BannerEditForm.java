package com.youruan.dentistry.console.banner.form;

import lombok.Setter;

/**
 * 修改轮播图表单对象
 */
@Setter
public class BannerEditForm {

    private Long id;
    private String bannerName;
    private String linkUrl;
    private Boolean status;
    private String imageUrl;

}
