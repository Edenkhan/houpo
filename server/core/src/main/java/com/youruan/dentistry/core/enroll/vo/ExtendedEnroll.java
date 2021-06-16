package com.youruan.dentistry.core.enroll.vo;

import com.youruan.dentistry.core.enroll.domain.Enroll;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExtendedEnroll extends Enroll {

    private final static long serialVersionUID = 1L;

    /**
     * 姓名
     */
    private String realName;
    /**
     * 电话
     */
    private String phoneNumber;

}
