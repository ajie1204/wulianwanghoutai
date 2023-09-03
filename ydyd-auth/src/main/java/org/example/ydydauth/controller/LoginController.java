package org.example.ydydauth.controller;

import com.alibaba.fastjson.JSONObject;


import com.github.kevinsawicki.http.HttpRequest;
import org.example.nacosspringcloudcommonentity.customer.Customer;
import org.example.nacosspringcloudcommonentity.vo.Response;

import org.example.ydydauth.service.feign.CustomerService;
import org.example.ydydauth.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * @author 31477
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    CustomerService customerService;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public Response<Customer> login(@RequestBody JSONObject jsonObject){

        JSONObject loginInfo = jsonObject.getJSONObject("loginInfo");
        String code=loginInfo.getString("code");
        JSONObject userInfo=loginInfo.getJSONObject("userInfo");
        String name=userInfo.getString("nickName");
        String avatarUrl=userInfo.getString("avatarUrl");

        String requestCode="96320714";
        Date d = new Date();



        // 小程序唯一标识 (在微信小程序管理后台获取)
        String wxspAppid = "wxb861e81144cf018d";
        // 小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "0233b08ac27c0d3dca53c73b178da6a3";
        // 授权（必填）
        String grant_type = "authorization_code";

        ///1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        // 请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        // 发送请求
        String sr = HttpRequest.get("https://api.weixin.qq.com/sns/jscode2session?"+params).body();


        // 解析相应内容（转换成json对象）
        JSONObject jsonResponse = JSONObject.parseObject(sr);
        // 获取会话密钥（session_key）
        String session_key = jsonResponse.get("session_key").toString();
        // 获取openId
        String openid= jsonResponse.getString("openid");





        //如果用户不存在，注册  存在登录
        if(customerService.getCustomerByOpenid(openid)==null){
            Customer firstParent = customerService.getCustomerByRequestCode(requestCode);
            Customer newCustomer=new Customer();
            newCustomer.setOpenid(openid);
            newCustomer.setAvatar(avatarUrl);
            newCustomer.setName(name);
            newCustomer.setFirstParent(firstParent.getId());
            newCustomer.setSecondParent(firstParent.getFirstParent());
            newCustomer.setCreatedAt(d);
            newCustomer.setRequestCode("123456");
            customerService.insertCustomer(newCustomer);
        }

        //产生新的token
        String token = tokenService.createToken(openid);
        Customer customer = customerService.getCustomerByOpenid(openid);

        return Response.createSuccessResponse("token:"+token,customer);

    }



}
