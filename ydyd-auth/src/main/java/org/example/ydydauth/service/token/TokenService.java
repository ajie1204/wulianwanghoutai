package org.example.ydydauth.service.token;

import org.example.nacosspringcloudcommoncore.constant.CacheConstants;
import org.example.nacosspringcloudcommoncore.utils.JwtUtil;


import org.example.ydydauth.service.redis.RedisService;
import org.example.ydydauth.utils.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 *
 * @author ruoyi
 */
@Component
public class TokenService {
    @Autowired
    private RedisService redisService;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private final static long expireTime = CacheConstants.EXPIRATION;

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    private final static Long MILLIS_MINUTE_TEN = CacheConstants.REFRESH_TIME * MILLIS_MINUTE;

    /**
     * 创建令牌
     */
    public String createToken(String openid) {

        String token = JwtUtil.createJwt(UUID.randomUUID().toString(), "user", null);
        //将token存入redis中
        redisService.setCacheObject(openid,token,expireTime, TimeUnit.MINUTES);
        return token;
    }



}