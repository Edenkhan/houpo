package com.youruan.dentistry.core.wx.menu.service.impl;

import com.alibaba.fastjson.JSON;
import com.youruan.dentistry.core.wx.base.constant.WxConstant;
import com.youruan.dentistry.core.wx.base.utils.HttpClientUtils;
import com.youruan.dentistry.core.wx.menu.domain.OneMenu;
import com.youruan.dentistry.core.wx.menu.domain.ViewButton;
import com.youruan.dentistry.core.wx.menu.domain.WxMenu;
import com.youruan.dentistry.core.wx.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

@Service
public class BasicMenuService implements MenuService {

    @Value("${wx.domain}")
    private String domain;

    @Override
    public void define() {

        try {
            //获取响应内容
            String json = HttpClientUtils.doGet(WxConstant.ACCESS_TOKEN_URL);
            Map<String, String> resultMap = JSON.parseObject(json, Map.class);
            //定义click类型菜单
//            ClickButton clickButton = new ClickButton();
//            clickButton.setType("click");
//            clickButton.setName("测试click");
//            clickButton.setKey("rselfmenu_0_0");
            //定义view类型菜单
            ViewButton viewButton = new ViewButton();
            viewButton.setType("view");
            viewButton.setName("授权登录");
            String encodeUrl = URLEncoder.encode(domain + WxConstant.REDIRECT_URI, "UTF-8");
            viewButton.setUrl(String.format(WxConstant.BASIC_OAUTH_URL,encodeUrl));
            //定义1级菜单
            OneMenu oneMenu = new OneMenu();
            oneMenu.setName("厚朴人才");
            oneMenu.setSub_button(Arrays.asList(/*clickButton,*/viewButton));
            //定义微信公众号菜单
            WxMenu wxMenu = new WxMenu();
            wxMenu.setButton(Arrays.asList(oneMenu));
            HttpClientUtils.doPostJson(WxConstant.CREATE_MENU_URL+resultMap.get("access_token"),JSON.toJSONString(wxMenu));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
