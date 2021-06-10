package com.youruan.dentistry.core.user.vo;

import com.youruan.dentistry.core.base.domain.BasicDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserAllInfoVo extends BasicDomain {
    //基本信息
    /**
     * 微信用户昵称
     */
    private String nickname;
    /**
     * 用户真实姓名
     */
    private String realName;
    /**
     * 年龄
     */
    private String age;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 手机号
     *
     */
    private String phoneNumber;
    /**
     * 是否已锁定
     *
     */
    private Boolean locked;
    /**
     * 微信用户唯一id
     */
    private String openid;
    /**
     * 头像
     */
    private String avatar;

    //其他信息
    /**
     * 毕业院校
     */
    private String school;
    /**
     * 所学专业
     */
    private String major;
    /**
     * 学历水平
     */
    private Integer education;
    /**
     * 期望从事职业
     */
    private String job;
    /**
     * 期望就业地址
     */
    private String area;

}
