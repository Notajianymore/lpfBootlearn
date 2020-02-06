package com.zhaohu.example.lpftest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @Value("${name}")
    private String name;

    @Value("${impression}")
    private String impression;

    @Autowired
    private lpf lpf;

    @RequestMapping("hello")
    public String hello(){
        return "Hi this is spring boot\n" + lpf.getAge();
    }

    @GetMapping("/greeting")
    public String getHello(@RequestParam(name = "papa") String name, Model model){
        model.addAttribute("papa",name);
        return "greeting";
    }
}
