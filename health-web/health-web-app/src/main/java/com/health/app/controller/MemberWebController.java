package com.health.app.controller;


import com.health.items.feign.MemberFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberWebController {

    @Autowired
    private MemberFeign memberFeign;

    @PostMapping("/web/login")
    public Result login(HttpServletResponse response, @RequestBody Map map){
        String telephone = (String) map.get("telephone");

        Cookie cookie = new Cookie("login_member_telephone",telephone);
        cookie.setPath("/");
        //30分钟
        cookie.setMaxAge(60*30);
        response.addCookie(cookie);

        return memberFeign.login(map);
    }
}
