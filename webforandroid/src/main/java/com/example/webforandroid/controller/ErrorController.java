package com.example.webforandroid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorController {
    //https://www.cnblogs.com/Lyn4ever/p/12757947.html(集成自定义错误页面:暂时先不做)
//    @GetMapping("/error")
//    @ResponseBody
//    public String error(){
//        return " Customized Error Page!";
//    }
}
