package com.youruan.dentistry.core.wx.oauth.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLogin {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 是否被锁定
     */
    private Boolean locked;

}
