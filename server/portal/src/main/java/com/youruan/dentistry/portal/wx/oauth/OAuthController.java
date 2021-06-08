package com.youruan.dentistry.portal.wx.oauth;

import com.youruan.dentistry.core.wx.oauth.service.OAuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthService oauthService;

    public OAuthController(OAuthService oauthService) {
        this.oauthService = oauthService;
    }

    /**
     * 用户授权
     */
    @GetMapping("/start")
    public void oauth() {
        oauthService.auth();
    }

    /**
     * 用户授权，回调地址
     */
    @GetMapping("/callback")
    public void callback(String code,String state) {
        oauthService.callback(code,state);
    }

}
