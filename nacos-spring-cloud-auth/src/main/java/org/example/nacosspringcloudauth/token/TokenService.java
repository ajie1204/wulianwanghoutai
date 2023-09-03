package org.example.nacosspringcloudauth.token;


import io.jsonwebtoken.Claims;
import org.example.nacosspringcloudauth.service.UserService;
import org.example.nacosspringcloudauth.utils.IdUtils;
import org.example.nacosspringcloudcommoncore.constant.CacheConstants;
import org.example.nacosspringcloudcommoncore.constant.SecurityConstants;
import org.example.nacosspringcloudcommoncore.security.SecurityUtils;
import org.example.nacosspringcloudcommoncore.utils.JwtUtil;
import org.example.nacosspringcloudcommoncore.utils.JwtUtils;
import org.example.nacosspringcloudcommoncore.utils.ServletUtils;
import org.example.nacosspringcloudcommoncore.utils.StringUtils;
import org.example.nacosspringcloudcommoncore.utils.uuid.UUID;
import org.example.nacosspringcloudcommonentity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.example.nacosspringcloudredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 * 
 * @author ruoyi
 */
@Component
public class TokenService
{


    @Autowired
    private UserService userService;

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
    public Map<String, Object> createToken(User user)
    {
        /*String token = IdUtils.fastUUID();*/
        String account = user.getAccount();

        /*// Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put(SecurityConstants.USER_KEY, token);
        claimsMap.put(SecurityConstants.DETAILS_USER_ID, account);*/

        System.out.println("createToken被调用");

        //生成token，接口返回信息
        Map<String, Object> rspMap = new HashMap<String, Object>();
        String token = JwtUtil.createJwt(UUID.randomUUID().toString(), "user", null);
        rspMap.put("access_token",token);
        //将token存入redis中
        redisService.setCacheObject(account,rspMap.get("access_token"),expireTime,TimeUnit.MINUTES);
        return rspMap;
    }
    //校验token
    public Boolean verifyToken(User user){
        // 获取请求携带的令牌
        String token = user.getToken();
        //校验token
        Claims claims = JwtUtils.parseToken(token);
        String account1 = JwtUtils.getUserId(claims);
        String account2 = user.getAccount();
        if (account1.equals(account2)){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    /*
    public User getLoginUser()
    {
        return getLoginUser(ServletUtils.getRequest());
    }*/

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    /*
    public User getLoginUser(HttpServletRequest request)
    {
        // 获取请求携带的令牌
        String token = SecurityUtils.getToken(request);
        return getLoginUser(token);
    }*/



    //将token存入redis

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    /*
    public User getLoginUser(String token)
    {
        User user = null;
        try
        {
            if (StringUtils.isNotEmpty(token))
            {
                String userkey = JwtUtils.getUserKey(token);
                user = redisService.getCacheObject(getTokenKey(userkey));
                return user;
            }
        }
        catch (Exception e)
        {
        }
        return user;
    }*/

    /**
     * 设置用户身份信息
     */
    /*
    public void setLoginUser(User loginUser)
    {
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken()))
        {
            refreshToken(loginUser);
        }
    }*/

    /**
     * 删除用户缓存信息
     */
    /*
    public void delLoginUser(String token)
    {
        if (StringUtils.isNotEmpty(token))
        {
            String userkey = JwtUtils.getUserKey(token);
            redisService.deleteObject(getTokenKey(userkey));
        }
    }*/

    /**
     * 验证令牌有效期，相差不足120分钟，自动刷新缓存
     *
     * @param loginUser
     */
    /*
    public void verifyToken(User loginUser)
    {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        Date expireTime = loginUser.getExpireTime();
        Date currentTime = new Date();
        long restTime = expireTime.getTime()-currentTime.getTime();
        if ( restTime <= MILLIS_MINUTE_TEN)
        {
            refreshToken(loginUser);
        }
    }
    */

    /**
     * 刷新令牌有效期
     *
     * @param
     */
    public void refreshToken(User user,String token)
    {
        long l = user.getLoginTime().getTime() + expireTime * MILLIS_MINUTE;
        // 根据uuid将loginUser缓存
        /*
        String userKey = getTokenKey(user.getToken());
        redisService.setCacheObject(userKey, user, expireTime, TimeUnit.MINUTES);
        */
        //刷新
        redisService.setCacheObject(user.getAccount(),token,l,TimeUnit.MINUTES);
    }

    private String getTokenKey(String token)
    {
        return ACCESS_TOKEN + token;
    }
}