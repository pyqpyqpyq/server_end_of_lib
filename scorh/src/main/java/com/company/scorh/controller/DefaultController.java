package com.company.scorh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    int a=1;
    @RequestMapping("/")
    public String index(){
        return "page/index.html";
    }
}
