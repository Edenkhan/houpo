package com.youruan.dentistry.portal.wx.menu;

import com.youruan.dentistry.core.wx.menu.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 自定义公众号菜单
     */
    @GetMapping("/define")
    public void define() {
        menuService.define();
    }

}
