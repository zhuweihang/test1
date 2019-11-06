package com.shuzutech.interfacetest.testCase;

import com.shuzutech.Openplatform.Encrypt;

import java.net.URLEncoder;

public class ThirdInvoiceIncoming {
    public static void main(String[] args) {
        String appId = "d7a5606175ee6ea89a6a43e518908c16";
        String accessToken = "075f79d625527fdb3e1c141d26326ebf";  //"dda0637f5f66505555d2485d90103b84";
        String returnUrl = "http://www.baidu.com";
//        String params = "&staffMobile=13599999999&companyName=第三方发票进件测试商户一&contact=庞测试商户一&mobile=15951880600&taxNo=100000015951880600&outMerchantId=P0001&channelId=40";
        String params = "&staffMobile=13599999999&companyName=第三方发票进件测试商户BW081701&contact=测试商户BW081701&mobile=12100081701&taxNo=BW081701&outMerchantId=BW081701&channelId=40";
//        String params = "&staffMobile=13548484848&companyName=第三方发票进件测试商户XJ071509&contact=测试商户XJ071509&mobile=15100000369&taxNo=XJ071509&outMerchantId=XJ150009&channelId=602";
        String encryptMsg = new Encrypt().aesEncrypt(params, accessToken);
        String encryptMsg_urlEncode = URLEncoder.encode(encryptMsg);
        String url = "http://106.14.193.154:8084/third/invoice/registration?appId="+(appId)+"&encryptMsg="+(encryptMsg_urlEncode)+"&returnURL="+(returnUrl);
//        String url = "http://112.74.173.171:8082/third/invoice/registration?appId="+(appId)+"&encryptMsg="+(encryptMsg_urlEncode)+"&returnURL="+(returnUrl);
        System.out.println(encryptMsg);
        System.out.println(encryptMsg_urlEncode);
        System.out.println(url);
    }
}
