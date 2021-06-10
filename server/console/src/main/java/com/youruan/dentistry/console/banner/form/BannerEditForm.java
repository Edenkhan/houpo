package com.youruan.dentistry.console.banner.form;

import lombok.Getter;
import lombok.Setter;

/**
 * 修改轮播图表单对象
 */
@Setter
@Getter
public class BannerEditForm {

    private Long id;
    private String name;
    private String linkUrl;
    private Integer status;
    private String imageUrl;

}
