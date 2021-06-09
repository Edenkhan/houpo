package com.youruan.dentistry.core.wx.oauth.service.impl;

import com.alibaba.fastjson.JSON;
import com.youruan.dentistry.core.user.domain.RegisteredUser;
import com.youruan.dentistry.core.user.mapper.RegisteredUserMapper;
import com.youruan.dentistry.core.user.query.RegisteredUserQuery;
import com.youruan.dentistry.core.user.vo.UserAllInfoVo;
import com.youruan.dentistry.core.wx.base.constant.WxConstant;
import com.youruan.dentistry.core.wx.base.utils.HttpClientUtils;
import com.youruan.dentistry.core.wx.oauth.domain.WxResult;
import com.youruan.dentistry.core.wx.oauth.domain.WxUserInfo;
import com.youruan.dentistry.core.wx.oauth.service.OAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BasicOAuthService implements OAuthService {

    private final RegisteredUserMapper registeredUserMapper;
    public BasicOAuthService(RegisteredUserMapper registeredUserMapper) {
        this.registeredUserMapper = registeredUserMapper;
    }

    @Override
    public WxUserInfo callback(String code, String state) {
        if(state==null || !state.equals("GHOST")) {
            throw new RuntimeException("回調錯誤");
        }
        String json = HttpClientUtils.doGet(String.format(WxConstant.BASIC_OAUTH_ACCESS_TOKEN_URL,code));
        WxResult wxResult = JSON.parseObject(json, WxResult.class);
        return getWxUserInfo(wxResult.getAccessToken(),wxResult.getOpenid());
    }

    @Override
    public Long register(WxUserInfo wxUserInfo) {
        RegisteredUser registeredUser = this.getByOpenid(wxUserInfo.getOpenid());
        if(registeredUser == null) {
            registeredUser = new RegisteredUser();
            registeredUser.setOpenid(wxUserInfo.getOpenid());
            registeredUser.setNickname(wxUserInfo.getNickname());
            registeredUser.setAvatar(wxUserInfo.getHeadimgurl());
            registeredUser.setCreatedDate(new Date());
            registeredUserMapper.add(registeredUser);
            System.out.println("userid = "+registeredUser.getId());
        }

        return registeredUser.getId();
    }

    /**
     * 根据openid查询用户 并返回
     */
    private RegisteredUser getByOpenid(String openid) {
        RegisteredUserQuery qo = new RegisteredUserQuery();
        qo.setOpenid(openid);
        List<UserAllInfoVo> list = registeredUserMapper.query(qo);
        if(list != null && list.size() > 0) {
            UserAllInfoVo userAllInfoVo = list.get(0);
            RegisteredUser registeredUser = new RegisteredUser();
            BeanUtils.copyProperties(userAllInfoVo,registeredUser);
            return registeredUser;
        }
        return null;
    }


    /**
     * 通过access_token和openid拉取用户信息
     */
    private WxUserInfo getWxUserInfo(String accessToken, String openId) {
        String json = HttpClientUtils.doGet(String.format(WxConstant.BASIC_GET_USERINFO_URL, accessToken, openId));
        return JSON.parseObject(json, WxUserInfo.class);
    }


}
