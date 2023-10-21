package com.belence.tacos.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 控制器
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
