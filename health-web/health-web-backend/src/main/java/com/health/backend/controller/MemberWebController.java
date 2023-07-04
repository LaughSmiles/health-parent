package com.health.backend.controller;


import com.health.items.feign.MemberFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberWebController {

    @Autowired
    private MemberFeign memberFeign;

    @GetMapping("/web/getMemberReport")
    public Result getMemberReport(){
        return memberFeign.getMemberReport();
    }
}
