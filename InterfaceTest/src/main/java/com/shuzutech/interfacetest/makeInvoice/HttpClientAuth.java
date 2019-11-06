package com.shuzutech.interfacetest.makeInvoice;

import com.shuzutech.interfacetest.Md5;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class HttpClientAuth {

    /**
     * 零税率标识非空开票测试用例，即lslbs为1、2、3
     * @throws Exception
     */

    @Test
    public void makeInvoiceYouhui() throws Exception {
        String body = new ReadFile().readFile();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String APPID = "00ca44d59d6c121bdd567053fac8dcda";
        String access_token = GetAccessToken.authentication1();
        System.out.println("access_token:"+access_token);
        String http = body + date + access_token;
        String s = Md5.EncoderByMd5(http);
        System.out.println(s);
        String url = "http://testservice.shuzutech.com:8081/invoice/business";
        //声明一个client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(url);
        //添加body信息
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

    /**
     * 正常税率开票，开票税率为0.06
     */
    @Test
    public void makeInvoiceZhengchangKaipiao() throws IOException, NoSuchAlgorithmException {
        String body = "";
        String string = null;
        /**
         * 查找文件XML文件
         */
        File file = new File("D:\\Space\\InterfaceAutoTest\\InterfaceTest\\src\\main\\resources\\kaipiao\\zhengchangshuilvkaipiao.xml");
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
    /**
     * 正常税率开票，开票税率为0.13
     */
    @Test
    public void ZhengchangKaipiao1() throws IOException, NoSuchAlgorithmException {
        String body = "";
        String string = null;
        /**
         * 查找文件XML文件
         */
        File file = new File("D:\\Space\\InterfaceAutoTest\\InterfaceTest\\src\\main\\resources\\kaipiao\\zhengchangkaipiao1.xml");
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
        String APPID = "4b7d1de85228569bf413eb0decf08747";
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
    /**
     * 正常税率开票，开票税率为0.16
     */
    @Test
    public void ZhengchangKaipiao2() throws IOException, NoSuchAlgorithmException {
        String body = "";
        String string = null;
        /**
         * 查找文件XML文件
         */
        File file = new File("D:\\IdeaProjects\\InterfaceAutoTest\\InterfaceTest\\src\\main\\resources\\kaipiao\\zhengchangkaipiao2.xml");
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

    /**
     * 税率不存在，例如税率为0.2，开票
     */
    @Test
    public void InexistenceRate() throws IOException, NoSuchAlgorithmException {
        String body = "";
        String string = null;
        /**
         * 查找文件XML文件
         */
        File file = new File("D:\\IdeaProjects\\InterfaceAutoTest\\InterfaceTest\\src\\main\\resources\\kaipiao\\inexistenceRate.xml");
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

    /**
     * 优惠政策开票：yhzcbs  0表示未使用、1表示使用
     * yhzcbs使用时zzstsgl（增值税特殊管理）不能为空，且值要正确
     */
    @Test
    public void PreferentialMakeInvoice() throws IOException, NoSuchAlgorithmException {
        String body = "";
        String string = null;
        /**
         * 查找文件XML文件
         */
        File file = new File("D:\\Space\\InterfaceAutoTest\\InterfaceTest\\src\\main\\resources\\kaipiao\\youhuikaipiao.xml");
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
        String APPID = "013e96f0810f74ea0d12b67fcf33acfd";
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

    /**
     * 折扣行开票
     */
    @Test
    public void Discount() throws IOException, NoSuchAlgorithmException {
        String body = "";
        String string = null;
        /**
         * 查找文件XML文件
         */
        File file = new File("D:\\Space\\InterfaceAutoTest\\InterfaceTest\\src\\main\\resources\\kaipiao\\discount.xml");
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



    /**
     * 测试多行商品开票
     * 1.多行商品包含多种税率
     * 2.包含折扣商品、有折扣为0商品
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */


    @Test
    public void makeInvoiceduohang() throws IOException, NoSuchAlgorithmException {
        String body = "";
        String string = null;
        /**
         * 查找文件XML文件
         */
        File file = new File("D:\\Space\\InterfaceAutoTest\\InterfaceTest\\src\\main\\resources\\kaipiao\\duohangshangpin.xml");
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
