package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @RestController是@ResponseBody和@Controller的组合注解
 */
@RestController
@Api(value = "/",description = "这是我的全部POST方法")
@RequestMapping("/vi")
public class MyPostMethod {
    //用来存储cookies信息
    private static Cookie cookie;

    //用户登陆成功获取到cookies，然后再访问其他接口获取列表

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登陆接口，成功获取cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "username",required = true) String username,
                        @RequestParam(value = "password",required = true) String password){
        if(username.equals("zhangsan")&&password.equals("123456")){
            cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜你登陆成功了";
        }
        return "用户名或者密码错误！";
    }
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request, @RequestBody User u){
        //获取Cookies

        Cookie[] cookies = request.getCookies();
        //验证Cookies是否合法
        for(Cookie c:cookies){
            if(c.getName().equals("login")&&
            c.getValue().equals("true")&&
            u.getUsername().equals("zhangsan")&&
            u.getPassword().equals("123456")){
                User user = new User();
                user.setName("lisi");
                user.setAge("18");
                user.setSex("man");
                return  user.toString();
            }
        }
        return "参数不合法";
    }
}
