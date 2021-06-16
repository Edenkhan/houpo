package com.youruan.dentistry.core.base.utils;

import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Random;

public class WxAPIV3SignUtils {
    // Authorization: <schema> <token>
    // GET - getToken("GET", httpurl, "")
    // POST - getToken("POST", httpurl, json)

    public static final String sign_type = "HMAC-SHA256";//签名类型，仅支持HMAC-SHA256。示例值：HMAC-SHA256
    private static final String schema = "WECHATPAY2-SHA256-RSA2048";
    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final Random RANDOM = new SecureRandom();

    /**
     *
     * @param appId
     * @param timestamp
     * @param nonceStr
     * @param packageValue
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String getSign(String appId, long timestamp,String nonceStr, String packageValue, String privateKey) {
        try {
            String message = buildMessage(appId, timestamp, nonceStr, packageValue);
            PrivateKey privateKey1 = getPrivateKey(privateKey);
            return sign(message.getBytes("utf-8"),privateKey1);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param message
     * @param privateKey 商户私钥
     * @return
     */
    private static String sign(byte[] message, PrivateKey privateKey) throws Exception{
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privateKey);
        sign.update(message);
        return Base64.getEncoder().encodeToString(sign.sign());
    }

    private static String buildMessage(String appId, long timestamp,String nonceStr, String packageValue) {
        return appId + "\n"
                + timestamp + "\n"
                + nonceStr + "\n"
                + packageValue + "\n";
    }

    /**
     * String转私钥PrivateKey
     * @param key
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取随机字符串 Nonce Str
     *
     * @return String 随机字符串
     */
    public static String generateNonceStr() {
        char[] nonceChars = new char[32];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }

}
