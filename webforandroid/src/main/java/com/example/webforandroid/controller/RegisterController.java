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
        int nResult = -1;
        //SQLIntegrityConstraintViolationException 捕获不了,所以用Exception来捕获
//        try {
//            nResult = userService.addUser(user);
//        }catch (SQLIntegrityConstraintViolationException exception){
//            return Result.SERVER_INTERNAL_ERROR.toString();
//        }
        try {
            nResult = userService.addUser(user);
        }catch (Exception exception){
            String error = exception.getCause().toString();
            // 设置造成异常的原因的信息，暂时将造成错误原因呈现给用户，但是不推荐(展现给用户的时候，
            // 不需要调用以下内容:setResult；作为日志记录的时候，可以调用以下代码:setResult)
            Result.SERVER_INTERNAL_ERROR.setResult(error);
            return Result.SERVER_INTERNAL_ERROR.toString();
        }
        if(0 == nResult){
            return Result.REGISTER_FAILED.toString();
        }
        return Result.REGISTER_SUCCESS.toString();
    }
}
