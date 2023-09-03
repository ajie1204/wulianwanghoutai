package org.example.nacosspringcloudauth.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.example.nacosspringcloudcommonentity.DownData;
import org.example.nacosspringcloudcommonentity.LoginLog;
import org.example.nacosspringcloudauth.service.LoginLogService;
import org.example.nacosspringcloudauth.service.UserService;
import org.example.nacosspringcloudauth.token.TokenService;
import org.example.nacosspringcloudcommoncore.constant.UserConstants;
import org.example.nacosspringcloudcommonentity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户控制器
 *
 * @author 31477
 * @date 2023/02/21
 */
@RestController
@RequestMapping("/userAuth")
public class UserController {
     

    /**
     * 用户服务
     */
    @Autowired
    private UserService userService;

    /**
     * 令牌服务
     */
    @Autowired
    private TokenService tokenService;

    /**
     * 日志服务
     */
    @Autowired
    private LoginLogService logService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登录
     *
     * @param user 用户
     * @return {@link Map}<{@link String},{@link Object}>
     */
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody User user){
        System.out.println("1");
        Map<String, Object> map = new HashMap<>();
        //用户登录
        String account = user.getAccount();
        String password = user.getPassword();
        LoginLog log = new LoginLog();
        log.setAccount(account);
        log.setLoginTime(new Date());
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(account,password))
        {
            map.put("result","用户/密码必须填写");
            return map;
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            map.put("result","用户密码不在指定范围(5-20)");
            return map;
        }
        if (account.length()!=11)
        {
            map.put("result","用户名错误");
            return map;
        }
        //用户未注册
        User user1 = userService.find(account);
        if (user1==null){
            map.put("result","用户未注册");
            return map;
        }
        //用户密码错误
        if (user1.getPassword()!=null&&!user1.getPassword().equals(password)){
            log.setMessage("用户密码错误");
            log.setStatus("登录失败");
            logService.add(log);
            map.put("result","用户密码错误");
            map.put("cmd","password");
            return map;
        }
        //登录成功,更新用户登录时间
        user.setLoginTime(new Date());
//        userService.updateLoginTime(user);
        log.setMessage("登录成功");
        log.setStatus("登录成功");
        /*logService.add(log);*/

        //产生新的token，存入redis
        Map<String, Object> tokenMap = tokenService.createToken(user);

        //判断是否携带token
        /*String token = user.getToken();
        if (token==null){
            Map<String, Object> tokenMap = tokenService.createToken(user);
            String accessToken = (String) tokenMap.get("access_token");
            map.put("token",accessToken);
        }else {
            //携带，校验token
            Boolean verifyToken = tokenService.verifyToken(user);
            if (verifyToken){
                //通过,刷新时间
                tokenService.refreshToken(user,token);
            }else {
                //不通过，则生成新的token
                Map<String, Object> newToken = tokenService.createToken(user);
                map.put("token", (String) newToken.get("access_token"));
            }
        }*/

        //将token返回给前端
        map.put("result","登录成功");
        map.put("token",tokenMap.get("access_token"));
        System.out.println("token返回成功");


        // 前端登陆成功
        // 应用平台请求登录thinglinks
        HashMap<String, Object> paramMap = new HashMap<>();
        DownData downData = new DownData();
        paramMap.put("username", downData.getUsername());
        paramMap.put("password", downData.getPassword());


        // 把paramMap转成json格式
        JSONObject json = new JSONObject();
        json.put("jsonTest", JSONObject.toJSONString(paramMap));


        // 创建发送请求并获取响应
        String result = HttpUtil.post("http://111.47.28.118:7106/prod-api/auth/application/login", json.getString("jsonTest"));

        // 取token
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject resultData = jsonObject.getJSONObject("data");
        String access_token = resultData.getString("access_token");

        // 打印 access_token
        System.out.println("access_token: " + access_token);

        //存到redis
        redisTemplate.opsForValue().set("access_token", access_token, 60, TimeUnit.MINUTES);


        return map;
    }

    /**
     * 注册
     *
     * @param user 用户
     * @return {@link Map}<{@link String},{@link Object}>
     *///用户注册
    @PostMapping("/register")
    public Map<String,Object> register(@RequestBody User user){
        Map<String, Object> registerMap = new HashMap<>();
        String account = user.getAccount();
        String password = user.getPassword();
        Date date = new Date();
        if (StringUtils.isAnyBlank(account,password)) {
            registerMap.put("result","用户/密码必须填写");
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            registerMap.put("result","用户密码不在指定范围(5-20)");
        }
        if (account.length()!=11) {
            registerMap.put("result","请输入正确的手机号");
        }
        User user1 = userService.find(user.getAccount());
        if(user1!=null){
            registerMap.put("result","该账号已注册");
        }
        //注册用户
        user.setLoginTime(date);
        user.setRegisterTime(date);
        userService.add(user);
        /*//产生新的token，存入redis
        Map<String, Object> map = tokenService.createToken(user);
        //将token返回给前端

        registerMap.put("token",map.get("access_token"));*/
        //重定向到首页
        registerMap.put("success","注册成功");
        return registerMap;
    }

    /**
     * 忘记pw
     *
     * @param user 用户
     * @return {@link Map}<{@link String},{@link Object}>
     *///忘记密码
    @PostMapping("/forget")
    public Map<String,Object> forgetPW(@RequestBody User user){
        Map<String,Object> map = new HashMap<>();
        //检测该用户是否已注册
        User user1 = userService.find(user.getAccount());
        if (user1==null){
            map.put("result","用户未注册");
            return map;
        }
        //已注册，修改密码
        userService.updatePassword(user);
        map.put("success","用户密码修改成功");
        return map;
    }





    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status 状态
     * @param message 消息内容
     * @return
     */
    /*public void recordLogininfor(String username, String status, String message)
    {
        SysLogininfor logininfor = new SysLogininfor();
        logininfor.setUserName(username);
        logininfor.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        logininfor.setMsg(message);
        // 日志状态
        if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER))
        {
            logininfor.setStatus(Constants.LOGIN_SUCCESS_STATUS);
        }
        else if (Constants.LOGIN_FAIL.equals(status))
        {
            logininfor.setStatus(Constants.LOGIN_FAIL_STATUS);
        }
        remoteLogService.saveLogininfor(logininfor, SecurityConstants.INNER);
    }*/



}

