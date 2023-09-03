//package org.example.ydyd.vxPay;
//
//import com.alibaba.fastjson.JSONObject;
//import com.hzm.dataplatform.Config.WXConfig;
//import com.hzm.dataplatform.Util.MD5;
//import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
//import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
//import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
//import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
//import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
//import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.util.EntityUtils;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.security.PrivateKey;
//import java.security.Signature;
//import java.util.Base64;
//
//
//public class WXpayUtil{
//
//    private static PrivateKey merchantPrivateKey;
//    private static CloseableHttpClient httpClient;
//
////单例模式
//    static {
//        try {
//            merchantPrivateKey = PemUtil
//                    .loadPrivateKey(new ByteArrayInputStream(WXConfig.privateKey.getBytes("utf-8")));
//            httpClient = WXpayUtil.setup();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private WXpayUtil(){};
//
//    private static CloseableHttpClient setup() throws Exception{
//// 加载商户私钥（privateKey：私钥字符串）
//
//
//        // 加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3密钥）
//        AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
//                new WechatPay2Credentials(WXConfig.mchId, new PrivateKeySigner(WXConfig.mchSerialNo, merchantPrivateKey)), WXConfig.apiV3Key.getBytes("utf-8"));
//
//        // 初始化httpClient
//        httpClient = WechatPayHttpClientBuilder.create()
//                .withMerchant(WXConfig.mchId, WXConfig.mchSerialNo, merchantPrivateKey)
//                .withValidator(new WechatPay2Validator(verifier)).build();
//        return httpClient;
//
//    }
//
//    public  static CloseableHttpClient getHttpClient(){
//        return httpClient;
//    }
//
//    public  static CloseableHttpResponse getResponse(HttpPost httpPost) throws IOException {
//    CloseableHttpResponse response = WXpayUtil.getHttpClient().execute(httpPost);
//        try {
//        int statusCode = response.getStatusLine().getStatusCode();
//        if (statusCode == 200) {
//            System.out.println("success,return body = " + EntityUtils.toString(response.getEntity()));
//        } else if (statusCode == 204) {
//            System.out.println("success");
//        } else {
//            System.out.println("failed,resp code = " + statusCode+ ",return body = " + EntityUtils.toString(response.getEntity()));
//            throw new IOException("request failed");
//        }
//    } finally {
//        response.close();
//    }
//        return response;
//    }
//
//
//    /**
//     * 作用：使用字段appId、timeStamp、nonceStr、package计算得出的签名值
//     * 场景：根据微信统一下单接口返回的 prepay_id 生成调启支付所需的签名值
//
//     * @param pack package
//     * @return
//     * @throws Exception
//     */
//    public  static JSONObject getSign(String pack) throws Exception{
//        JSONObject SignObject =new JSONObject();
//        Long ts = System.currentTimeMillis()/1000;
//        String nonceStr = MD5.uuid();
//        String message = buildMessage(pack,ts,nonceStr);
//        String paySign= sign(message.getBytes("utf-8"));
//        SignObject.put("paySign",paySign);
//        SignObject.put("timeStamp",ts);
//        SignObject.put("nonceStr",nonceStr);
//        return SignObject;
//    }
//    private static String buildMessage(String pack,Long ts,String nonceStr) {
//        return WXConfig.appid + "\n"
//                + ts + "\n"
//                + nonceStr + "\n"
//                + pack + "\n";
//    }
//    private static String sign(byte[] message) throws Exception{
//        Signature sign = Signature.getInstance("SHA256withRSA");
//        //这里需要一个PrivateKey类型的参数，就是商户的私钥。
//        sign.initSign(merchantPrivateKey);
//        sign.update(message);
//        return Base64.getEncoder().encodeToString(sign.sign());
//    }
//
//
//
//
//
//
//
//
//}
