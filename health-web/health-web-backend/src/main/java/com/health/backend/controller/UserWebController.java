package com.health.backend.controller;

import com.health.items.feign.UserFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserWebController {


    @Autowired
    private UserFeign userFeign;

    @GetMapping("/web/getUsername")
    public Result getUsername(){
        return userFeign.getUsername();
    }

    @GetMapping("/web/quit")
    public void quit(HttpServletResponse response) throws IOException {
        //清理cookie
        Cookie cookie = new Cookie("Authorization", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        response.sendRedirect("http://127.0.0.1:9001/pages/login.html");
    }

}

