package com.youruan.dentistry.portal.wx.oauth;

import com.youruan.dentistry.core.wx.oauth.domain.UserLogin;
import com.youruan.dentistry.core.wx.oauth.domain.WxUserInfo;
import com.youruan.dentistry.core.wx.oauth.service.OAuthService;
import com.youruan.dentistry.portal.base.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthService oauthService;
    private final HttpServletResponse response;

    public OAuthController(OAuthService oauthService, HttpServletResponse response) {
        this.oauthService = oauthService;
        this.response = response;
    }

    @Value("${wx.domain}")
    private String domain;

    /**
     * 用户授权，回调地址
     */
    @GetMapping("/callback")
    public void callback(String code, String state) throws IOException {
        WxUserInfo wxUserInfo = oauthService.callback(code, state);
        UserLogin userLogin = oauthService.register(wxUserInfo);
        if(!userLogin.getLocked()) {
            SessionUtils.login(userLogin.getId());
        }
        response.sendRedirect(domain);
    }

}
