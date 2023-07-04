package com.health.app.controller;

import com.health.items.feign.SetmealFeign;
import com.health.items.pojo.Setmeal;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/setmeal")
@ResponseBody
@CrossOrigin
@Controller
public class SetmealWebController {

    @Autowired
    private SetmealFeign setmealFeign;

    @GetMapping("/getSetmeal")
    public Result<List<Setmeal>> findAll(){
        return setmealFeign.findAll();
    }

    @GetMapping("/findById")
    public Result<Setmeal> findById(@RequestParam Integer id){
        return setmealFeign.findById(id);
    }
}
