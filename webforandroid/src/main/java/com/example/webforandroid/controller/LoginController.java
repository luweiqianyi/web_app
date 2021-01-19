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

    // 返回值必须为String类型
    @GetMapping("/login")
    @ResponseBody
    String login(@RequestParam String phone, @RequestParam String password){
        User user = userService.getUserByPhone(phone);
        System.out.println(user);
        if((!password.isEmpty()) && password.equals(user.getPassword())){
            return Result.LOGIN_SUCCESS.toString();
        }
        return Result.LOGIN_ERROR.toString();
    }

    @GetMapping("/hello")
    @ResponseBody
    String hello(){
        return "Hello";
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
}
