package com.shuzutech.interfacetest.makeInvoice;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAccessToken {
    /**
     * 获取AccessToken
     * @return
     * @throws IOException
     */
    public static  String authentication1() throws IOException {
        String result;//用来存放结果，并打印出来
        String url = "http://testservice.shuzutech.com:8081/invoice/token?&appId=4b7d1de85228569bf413eb0decf08747&appSecret=3ae3ce9644be221d8c2008ff9ad24e8a96536b3e";
        HttpClient client = new DefaultHttpClient();
        //这个是用来执行get方法的
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);//需要捕获异常
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        String str=null;
        String str1 = "<access_token>";
        String str2 = "</access_token>";
        str = result.substring(result.indexOf(str1)+str1.length(),result.indexOf(str2));
        System.out.println(result);
        System.out.println("====================================");
        System.out.println(str);
        return str;

    }

//    public static void main(String[] args) throws IOException {
//        GetAccessToken accessToken1 = new GetAccessToken();
//        accessToken1.authentication1();
//    }


}
