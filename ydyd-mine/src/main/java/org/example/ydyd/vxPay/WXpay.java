//package org.example.ydyd.vxPay;
//
//import com.alibaba.fastjson.JSONObject;
//import com.hzm.SortService.Util.Factory.OrderFactory;
//import com.hzm.SortService.Util.OrderIDGenerate;
//import com.hzm.SortService.Util.WXpayUtil;
//import com.hzm.SortService.dao.OrderDao;
//import com.hzm.dataplatform.Config.WXConfig;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.util.EntityUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("wxpay")
//public class WXpay {
//    @Autowired
//    private OrderDao orderDao;
//
//    @RequestMapping(value = "order",method = RequestMethod.POST)
//    public String CreateOrder(@RequestBody String reqjson) throws Exception{
//        //取openid
//        JSONObject reqObject = JSONObject.parseObject(reqjson);
//        System.out.println(reqjson);
//        //异常逻辑判断
//        if (reqObject.getString("openid").isEmpty() || reqObject.getString("month").isEmpty())
//        {
//            return "请求数据异常";
//        }
//        String openid = reqObject.getString("openid");
//        Integer month = reqObject.getInteger("month");
//        String description = reqObject.getString("description");
//        Integer HouseID = reqObject.getInteger("HouseID");
//        //请求URL
//        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");
//        String orderID= OrderIDGenerate.getOrderNum();
//        JSONObject resjson = new JSONObject();
//
//        JSONObject payer = new JSONObject();
//        payer.put("openid",openid);
//
//        JSONObject amount = new JSONObject();
//        //价格计算公式
//        int Price = month;
//        amount.put("total", Price);
//        amount.put("currency","CNY");
//        // 请求body参数
//        resjson.put("amount",amount);
//        resjson.put("payer",payer);
//        resjson.put("appid", WXConfig.appid);
//        resjson.put("mchid", WXConfig.mchId);
//        resjson.put("description",description);
//
//        resjson.put("notify_url","https://localhost:80/wxpay/notify");//待修改
//        resjson.put("out_trade_no", orderID);
//        //房间编号
//        resjson.put("goods_tag",HouseID.toString());
//
//        //存订单号
//        orderDao.OrderGenerate(OrderFactory.OrderFactory(orderID,HouseID,openid,Price,month));
//
//        StringEntity entity = new StringEntity(resjson.toString(),"utf-8");
//        entity.setContentType(WXConfig.APPLICATION_JSON);
//        httpPost.setEntity(entity);
//        httpPost.setHeader("Accept", WXConfig.APPLICATION_JSON);
//
//        //完成签名并执行请求
//        System.out.println(month);
//        CloseableHttpResponse response = WXpayUtil.getResponse(httpPost);
//        String prepay_id = JSONObject.parseObject(EntityUtils.toString(response.getEntity())).getString("prepay_id");
//        JSONObject sign= WXpayUtil.getSign("prepay_id="+prepay_id);
//        sign.put("package","prepay_id="+prepay_id);
//        return sign.toString();
//
//    }
//
//    @RequestMapping("test/{test}")
//    public String test(@PathVariable String test) throws Exception{
//        System.out.println(test);
//        return "test";
//    }
//
//
//    public void CloseOrder(String orderID) throws Exception {
//
//        //请求URL
//        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/"+orderID+"/close");
//        //请求body参数
//        JSONObject reqjson = new JSONObject();
//        reqjson.put("mchid",WXConfig.mchId);
//
//        StringEntity entity = new StringEntity(reqjson.toString(),"utf-8");
//        entity.setContentType("application/json");
//        httpPost.setEntity(entity);
//        httpPost.setHeader("Accept", "application/json");
//
//        //完成签名并执行请求
//        CloseableHttpResponse response = WXpayUtil.getResponse(httpPost);
//    }
//
//
//}
