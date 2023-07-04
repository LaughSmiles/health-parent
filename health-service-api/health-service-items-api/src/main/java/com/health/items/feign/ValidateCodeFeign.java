package com.health.items.feign;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="items")
@RequestMapping("/validateCode")
public interface ValidateCodeFeign {

    //体检预约时发送手机验证码
    @PostMapping("/send4Order/{telephone}")
    Result send4Order(@PathVariable String telephone);

    //Applogin
    @PostMapping("/send4Login/{telephone}")
    public Result send4Login(@PathVariable String telephone);
}
