package com.youruan.dentistry.core.activity.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ActivitySignUp {

    /**
     * 用户报名状态
     */
    private Integer state;
    /**
     * 状态[未报名]
     */
    public static final int ENROLL_NOT = 0;
    /**
     * 状态[已报名]
     */
    public static final int ENROLL_OK = ENROLL_NOT + 1;

}
