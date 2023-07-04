package com.health.app.controller;


import com.health.items.feign.OrderFeign;
import com.health.items.pojo.Order;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderWebController {

    @Autowired
    private OrderFeign orderFeign;

    /**
     * 体检预约
     * @param map
     * @return
     */
    @PostMapping("/web/submit")
    public Result submitOrder(@RequestBody Map map){
        return orderFeign.submitOrder(map);
    }

    @PostMapping("/findById")
    public Result<Order> findById(@RequestParam Integer id){
        //调用OrderService实现根据主键查询Order
        return orderFeign.findById(id);
    }

}
