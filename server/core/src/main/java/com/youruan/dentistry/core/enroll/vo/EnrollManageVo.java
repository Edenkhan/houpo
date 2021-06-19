package com.youruan.dentistry.core.enroll.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class EnrollManageVo {

    private Integer type;
    private String title;
    private String img;
    private String desc;
    private String date;
    private String src;
    private BigDecimal price;
    private String text;

}
