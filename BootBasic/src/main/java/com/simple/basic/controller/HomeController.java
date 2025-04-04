package com.simple.basic.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {


    @ResponseBody
    @GetMapping("/")
    public String home() {

        //TestVO vo = new TestVO();

        return "home";
    }
}
