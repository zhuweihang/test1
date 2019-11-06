package com.course.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyHttpClient {
    private String url;
    private ResourceBundle bundle;
    //存储cookies信息
    CookieStore store;
    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void httpGet() throws IOException {
        HttpClient client = new DefaultHttpClient();
        String uri = bundle.getString("get.cookies.uri");
        String testUrl = this.url+uri;
        HttpGet get = new HttpGet(testUrl);
        HttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //获取cookies信息
        this.store = ((DefaultHttpClient) client).getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for(Cookie cookie:cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name = "+name+";cookie value = "+value);
        }

    }

    @Test(dependsOnMethods = {"httpGet"})
    public void getWithCookies() throws IOException {
        HttpClient client = new DefaultHttpClient();
        String uri = bundle.getString("get.with.cookies.uri");
        String testUrl = this.url+uri;
        HttpGet get = new HttpGet(testUrl);
        //设置请求的cookies信息
        ((DefaultHttpClient) client).setCookieStore(this.store);
        HttpResponse response = client.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = "+statusCode);
        if(statusCode == 200){
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        }
    }
}
