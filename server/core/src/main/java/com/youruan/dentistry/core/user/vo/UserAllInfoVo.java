package com.youruan.dentistry.core.user.vo;

import com.youruan.dentistry.core.user.domain.RegisteredUser;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserAllInfoVo extends RegisteredUser {

    /**
     * 毕业院校
     */
    private String graduatedCollege;
    /**
     * 所学专业
     */
    private String major;
    /**
     * 学历水平
     */
    private String education;
    /**
     * 期望从事职业
     */
    private String expectedOccupation;
    /**
     * 期望就业地址
     */
    private String expectedAddress;

}
