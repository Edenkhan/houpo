package com.youruan.dentistry.core.wx.access.service.impl;

import com.youruan.dentistry.core.wx.base.utils.CommonUtil;
import com.youruan.dentistry.core.wx.access.service.AccessService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicAccessService implements AccessService {


    @Override
    public String access(String signature, String timestamp, String nonce, String echostr) {
        return checkSignature(signature,timestamp,nonce) ? echostr : null;
    }

    private boolean checkSignature(String signature,String timestamp,String nonce){
        /**
         * 验证微信签名
         */
        String token = "GHOST";
        List<String> strList = CommonUtil.dictSort(token, timestamp, nonce);
        String s = CommonUtil.listToString(strList);
        String finalStr = CommonUtil.sha1(s, "utf-8");
        return signature.equals(finalStr);
    }
}
