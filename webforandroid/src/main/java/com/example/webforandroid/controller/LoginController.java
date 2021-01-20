package com.example.webforandroid.controller;

import com.example.webforandroid.model.User;
import com.example.webforandroid.result.Result;
import com.example.webforandroid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserService userService;


    /**
     * 用户登录走HTTPS方式，浏览器输入：“https://localhost:8081/login?phone=15700080001&password=12345678”
     * 用户登录走HTTP方式，浏览器输入：“http://localhost:8080/login?phone=15700080001&password=12345678”
     * TomcatConfig.java中配置了HTTP的8080端口的请求都会重定向到8081端口中,所以HTTP方式在请求后会自动转成
     * https://localhost:8081/login?phone=15700080001&password=12345678
     * 常见两类错误：
     * 1. 通过“http://localhost:8081/login?phone=15700080001&password=12345678”访问,没有配置过http+8081端口访问方式
     * 所以，访问不了是正常现象，提示 Bad Request This combination of host and port requires TLS
     * 2. 通过“https://localhost:8080/login?phone=15700080001&password=12345678”访问,https+8080也没有配置过
     * 提示错误：此网站无法提供安全连接
     * 原因分析：因为将安全连接指定给了8081端口(在application.properties中配置的)
     * @param phone
     * @param password
     * @return
     */
    @GetMapping("/login")
    @ResponseBody
    String login(@RequestParam String phone, @RequestParam String password){
        User user = userService.getUserByPhone(phone);
        System.out.println(user);
        if((!password.isEmpty()) && user!=null && password.equals(user.getPassword())){
            return Result.LOGIN_SUCCESS.toString();
        }
        return Result.LOGIN_ERROR.toString();
    }

    @GetMapping("/allUsers")
    @ResponseBody
    String allUsers(){
        List<User> users = userService.getAllUsers();
        StringBuilder sb = new StringBuilder();
        for(User user : users){
            sb.append(user.toString()).append("<br>");
        }
        return sb.toString();
    }

    /**
     * 本工程中已经配置了httpscertificate.p12证书并在appLication.properties中加以配置
     * 以下通过URL(https://localhost:8081/hello)进行访问，看能否以https方式访问成功
     * 测试结果：成功访问
     * @return
     */
    @GetMapping("/hello")
    @ResponseBody
    String hello(){
        return "hello";
    }
}
