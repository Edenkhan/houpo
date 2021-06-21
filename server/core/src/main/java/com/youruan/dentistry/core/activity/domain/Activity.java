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
    private String title;
    /**
     * 链接地址
     */
    private String linkUrl;
    /**
     * 活动图片
     */
    private String imageUrl;
    /**
     * 活动内容
     */
    private String content;
    /**
     * 报名人数
     */
    private Integer numberOfEntries;
    /**
     * 发布状态 0-未发布 1-已发布
     */
    private Integer releaseStatus;
    /**
     * 发布状态[未发布]
     */
    public static final int RELEASE_STATUS_CLOSE = 0;
    /**
     * 发布状态[已发布]
     */
    public static final int RELEASE_STATUS_OPEN = (RELEASE_STATUS_CLOSE + 1);
    /**
     * 报名状态 0-关闭 1-开启
     */
    private Integer enrollStatus;
    /**
     * 报名状态[关闭]
     */
    public static final int ENROLL_STATUS_CLOSE = 0;
    /**
     * 报名状态[开启]
     */
    public static final int ENROLL_STATUS_OPEN = (ENROLL_STATUS_CLOSE + 1);
    /**
     * 最近编辑人id
     */
    private Long recentId;


}
