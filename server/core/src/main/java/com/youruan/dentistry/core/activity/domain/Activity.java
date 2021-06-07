package com.youruan.dentistry.core.activity.domain;

import com.youruan.dentistry.core.base.domain.BasicDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * 活动
 */
@Getter
@Setter
public class Activity extends BasicDomain {

    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 缩略图
     */
    private String imageUrl;
    /**
     * 链接
     */
    private String linkUrl;
    /**
     * 序号
     */
    private Integer no;
    /**
     * 状态 0-停用 1-启用
     */
    private Integer status;


}
