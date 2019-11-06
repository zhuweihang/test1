package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {

    @RequestMapping(value = "/get/return/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获得cookies信息",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);//响应信息中添加cookies信息
        return "恭喜获得cookies信息成功";

    }

    /**
     *
     * @param request 需要客户端携带Cookies信息访问的get请求
     * @return
     */

    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "这是一个携带cookies信息才能访问的get方法",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();//获取请求中的cookies信息
        if (Objects.isNull(cookies)){//判断cookies是否为空
            return "需要携带一个cookies信息才能访问";
        }
        for(Cookie cookie:cookies){//如果cookies不为null,需要判断携带的cookies信息是否符合要求
            if(cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                return "恭喜你携带Cookies信息访问成功";
            }
        }
        return "你必须携带cookies信息来";
    }

    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第一种实现方式 url:key=value&key=value
     * 模拟获取商品列表
     */
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "模拟获取商品列表的第一种实现方法",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",2);
        myList.put("衬衫",300);
        return myList;
    }
    /**
     * 第二种写法：
     * url:ip:port/get/with/param/10/20
     */
    @RequestMapping(value = "/get/with/param/{start}/{end}")
    @ApiOperation(value = "模拟获取商品列表的第二种实现方式",httpMethod = "GET")
    public Map getMyList(@PathVariable Integer start,
                         @PathVariable Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",2);
        myList.put("衬衫",300);
        return myList;
    }
}
