package com.course.httpclient;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;


public class MyPostHttpClient {
    @Test
    public void testGet() throws IOException {
        HttpClient client = new DefaultHttpClient();
        String uri = "http://localhost:8893/get";
        HttpGet httpGet = new HttpGet(uri);
        HttpResponse response = client.execute(httpGet);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }


}
