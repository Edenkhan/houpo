package com.youruan.dentistry.core.wx.menu.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 封装菜单信息
 */
@Getter
@Setter
public class WxMenu {
    private List<OneMenu> button;
}
