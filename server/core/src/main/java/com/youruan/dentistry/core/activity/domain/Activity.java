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
     * 活动标题
     */
    private String eventTitle;
    /**
     * 活动图片
     */
    private String eventImage;
    /**
     * 活动内容
     */
    private String eventContent;
    /**
     * 报名人数
     */
    private Integer numberOfEntries;
    /**
     * 发布状态 0-未发布 1-已发布
     */
    private Integer releaseStatus;
    /**
     * 报名状态 0-关闭 1-开启
     */
    private Integer enrollStatus;
    /**
     * 最近编辑人
     */
    private String recentEditor;


}
