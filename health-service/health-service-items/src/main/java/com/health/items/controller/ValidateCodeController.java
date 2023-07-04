package com.health.items.controller;


import constant.RedisMessageConstant;
import entity.Result;
import entity.StatusCode;
import entity.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Autowired
    private RedisTemplate redisTemplate;

    //体检预约时发送手机验证码
    @PostMapping("/send4Order/{telephone}")
    public Result send4Order(@PathVariable String telephone){
        //生成4位数验证码
        Integer code = ValidateCodeUtils.generateValidateCode(4);

        //todo 因为申请不到短信认证，这里保存到redis后，直接将code返回给前端页面

        //将验证码保存到redis中 并设置过期时间为5种
        redisTemplate.delete(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        redisTemplate.boundSetOps(telephone + RedisMessageConstant.SENDTYPE_ORDER).add(String.valueOf(code));
        redisTemplate.expire(telephone + RedisMessageConstant.SENDTYPE_ORDER, 5, TimeUnit.MINUTES);

        return new Result(true, StatusCode.OK, "验证码设置成功",code);
    }


    //Applogin
    @PostMapping("/send4Login/{telephone}")
    public Result send4Login(@PathVariable String telephone){
        //生成4位数验证码
        Integer code = ValidateCodeUtils.generateValidateCode(4);

        //todo 因为申请不到短信认证，这里保存到redis后，直接将code返回给前端页面
        //将验证码保存到redis中 并设置过期时间为5种
        redisTemplate.delete(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        redisTemplate.boundSetOps(telephone + RedisMessageConstant.SENDTYPE_LOGIN).add(String.valueOf(code));
        redisTemplate.expire(telephone + RedisMessageConstant.SENDTYPE_LOGIN, 5, TimeUnit.MINUTES);

        return new Result(true, StatusCode.OK, "验证码设置成功",code);
    }

}
