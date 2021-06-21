package com.youruan.dentistry.core.enroll.vo;

import com.youruan.dentistry.core.enroll.domain.Enroll;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollActivityVo extends Enroll {

    private String title;
    private String linkUrl;
    private String imageUrl;
    private String content;

}
