package org.example.nacosspringcloudcommoncore.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    //设置token有效期为半小时
    public static final Long JWT_TTL=3600000L;
    //设置密钥的明文 需要安全保护 不要让别人知道
    public static final String JWT_KEY = "aiot";

    /**
     * 创建token
     * @param id  token的唯一标识
     * @param subject 面向的用户(管理员/普通用户)
     * @param ttlMills token的有效期
     * @return
     */
    public static String createJwt(String id, String subject, Long ttlMills) {
        JwtBuilder builder = getJwtBuilder(id, subject, ttlMills);
        return builder.compact();
    }

    /**
     * 创建token 使用自定义的claims
     * claims中可以自由放入如用户Id之类的东西  但不要放入敏感信息
     */
    public static String createJwt(String id, String subject, Long ttlMills, Map<String, Object> claims) {
        JwtBuilder builder = getJwtBuilder(id, subject, ttlMills);
        //自定义的claims
        builder = builder.addClaims(claims);
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String id, String subject, Long ttlMills) {
        if (ttlMills == null) {
            ttlMills = JwtUtil.JWT_TTL;
        }
        //获取当前时间
        Long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //失效时间
        long expMills = nowMillis + ttlMills;
        Date expDate = new Date(expMills);
        //对密钥进行二次加密
        SecretKey secretKey = generalKey();
        return Jwts.builder()
                .setId(id)      //唯一的ID
                .setSubject(subject)        //主题  可以是JSON数据
                .setIssuer("dean")      //签发者
                .setIssuedAt(now)       //签发时间
                .signWith(SignatureAlgorithm.HS256, secretKey)  //使用HS256对称加密算法签名，第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 生成加密后的秘钥  签证最重要的部分
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        //注意：对密钥加密的算法也可以选择其他算法 并不要被别人知道
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析
     */
    public static Claims parseJWT(String jwt)throws Exception{
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}


