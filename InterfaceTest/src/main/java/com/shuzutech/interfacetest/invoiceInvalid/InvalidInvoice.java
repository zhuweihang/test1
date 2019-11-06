package com.shuzutech.interfacetest.invoiceInvalid;

import com.shuzutech.interfacetest.Md5;
import com.shuzutech.interfacetest.makeInvoice.GetAccessToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InvalidInvoice {

    /**
     * 发票作废：专票、普票、卷票
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void invalidInvoice() throws IOException, NoSuchAlgorithmException {
        String body = "";
        String string = null;
        /**
         * 查找文件XML文件
         */
        File file = new File("D:\\Space\\InterfaceAutoTest\\InterfaceTest\\src\\main\\resources\\invalidInvoice\\invalidInvoice.xml");
        if (!file.exists()){
            System.out.println("文件不存在");
        }
        BufferedReader bf = new BufferedReader(new FileReader(file));
        while ((string = bf.readLine())!=null){
            body = body + string;
        }
//            System.out.println(body);
        bf.close();
        //设置日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        String APPID = "00ca44d59d6c121bdd567053fac8dcda";
        String access_token = null;
        access_token = GetAccessToken.authentication1();
        String headerContent = body + date + access_token;
        String s = Md5.EncoderByMd5(headerContent);
        String url = "http://testservice.shuzutech.com:8081/invoice/business";
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        //添加Body信息
        StringEntity entity = new StringEntity(body,"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/x-www-form-urlencoded");
        post.setEntity(entity);

        //设置请求头信息
        post.setHeader("APPID",APPID);
        post.setHeader("Date",date);
        post.setHeader("Content-MD5",s);

        //执行post方法
        HttpResponse response = client.execute(post);
        //获取响应结果
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }
}
