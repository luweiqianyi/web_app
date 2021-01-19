package com.example.webforandroid.controller;

import com.example.webforandroid.model.User;
import com.example.webforandroid.result.Result;
import com.example.webforandroid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    @ResponseBody
    public String register(@RequestParam String phone, @RequestParam String password){
        User user = new User(phone, password);
        int nResult = userService.addUser(user);
        if(0 == nResult){
            return Result.REGISTER_FAILED.toString();
        }
        return Result.REGISTER_SUCCESS.toString();
    }
}
