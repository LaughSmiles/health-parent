package com.health.app.controller;


import com.health.items.feign.ValidateCodeFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Autowired
    private ValidateCodeFeign validateCodeFeign;

    //体检预约时发送手机验证码
    @PostMapping("/send4Order")
    public Result send4Order(@RequestParam String telephone){
        return validateCodeFeign.send4Order(telephone);
    }

    //Applogin
    @PostMapping("/send4Login")
    public Result send4Login(@RequestParam String telephone) {
        return validateCodeFeign.send4Login(telephone);
    }
}
