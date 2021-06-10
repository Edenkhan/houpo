package com.youruan.dentistry.portal.wx.user.form;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserEditForm {
    private Long id;
    private String realName;
    private Integer age;
    private Integer gender;
    private String school;
    private String major;
    private Integer education;
    private String job;
    private String area;

}
