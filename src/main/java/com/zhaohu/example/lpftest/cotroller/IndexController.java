package com.zhaohu.example.lpftest.cotroller;

import com.zhaohu.example.lpftest.mapper.UserMapper;
import com.zhaohu.example.lpftest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = null;
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if("token".equals(name)){
                token = cookie.getValue();
                break;
            }
        }
        if(token != null){
            User user = userMapper.findUserByToken(token);
            if(user != null){
                request.getSession().setAttribute("user",user);
            }
        }
        return "index";
    }
}
