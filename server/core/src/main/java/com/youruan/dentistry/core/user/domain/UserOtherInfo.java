package com.youruan.dentistry.core.user.domain;

import com.youruan.dentistry.core.base.domain.BasicDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户其它信息实体类
 */
@Getter
@Setter
@ToString
public class UserOtherInfo extends BasicDomain {

    /**
     * 毕业院校
     */
    private String school;
    /**
     * 所学专业
     */
    private String major;
    /**
     * 学历水平 0: "高中及以下", 1: "大专", 2: "本科", 3: "硕士", 4: "博士及以上"
     */
    private Integer education;
    /**
     * 期望从事职业
     */
    private String job;
    /**
     * 期望就业地址-各地区行政区划代码
     */
    private String area;
    /**
     * 对应 registeruser-id
     */
    private Long userId;

}
