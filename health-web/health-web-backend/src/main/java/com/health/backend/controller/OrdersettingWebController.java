package com.health.backend.controller;


import com.health.items.feign.OrdersettingFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/ordersetting")
@CrossOrigin
public class OrdersettingWebController {

    @Autowired
    private OrdersettingFeign ordersettingFeign;

    //文件上传，实现预约设置数据批量导入
    @PostMapping("/excel/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile){
        return ordersettingFeign.upload(excelFile);
    }

    @GetMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(@RequestParam String date){//参数格式为：2019-03
        return ordersettingFeign.getOrderSettingByMonth(date);
    }

    //根据日期插入
    @PostMapping("/editNumberByDate")
    public Result insertBydate(@RequestBody Map map){
        String number = (String) map.get("number");
        String orderDate = (String) map.get("orderDate");
        return ordersettingFeign.insertBydate(number, orderDate);
    }

}
