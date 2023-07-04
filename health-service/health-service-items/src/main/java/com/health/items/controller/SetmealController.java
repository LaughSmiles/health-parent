package com.health.items.controller;

import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Setmeal;
import com.health.items.service.SetmealService;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/
@Api(value = "SetmealController")
@RestController
@RequestMapping("/setmeal")
@CrossOrigin
public class SetmealController {

    @Autowired
    private SetmealService setmealService;


    @GetMapping("/getSetmealReport")
    public Result getSetmealReport(){
        List<Map<String, String>> list = setmealService.findSetmealCount();

        Map<String,Object> map = new HashMap<>();
        map.put("setmealCount",list);

        List<String> setmealNames = new ArrayList<>();
        for (Map<String, String> m : list) {
            String name = m.get("name");
            setmealNames.add(name);
        }
        return new Result(true, StatusCode.OK, "查询成功",map);
    }



    /***
     * Setmeal分页条件搜索实现
     * @param setmeal
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Setmeal条件分页查询",notes = "分页条件查询Setmeal方法详情",tags = {"SetmealController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Setmeal对象",value = "传入JSON数据",required = false) Setmeal setmeal, @PathVariable  int page, @PathVariable  int size){
        //调用SetmealService实现分页条件查询Setmeal
        PageInfo<Setmeal> pageInfo = setmealService.findPage(setmeal, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Setmeal分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Setmeal分页查询",notes = "分页查询Setmeal方法详情",tags = {"SetmealController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用SetmealService实现分页查询Setmeal
        PageInfo<Setmeal> pageInfo = setmealService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param setmeal
     * @return
     */
    @ApiOperation(value = "Setmeal条件查询",notes = "条件查询Setmeal方法详情",tags = {"SetmealController"})
    @PostMapping(value = "/search" )
    public Result<List<Setmeal>> findList(@RequestBody(required = false) @ApiParam(name = "Setmeal对象",value = "传入JSON数据",required = false) Setmeal setmeal){
        //调用SetmealService实现条件查询Setmeal
        List<Setmeal> list = setmealService.findList(setmeal);
        return new Result<List<Setmeal>>(true,StatusCode.OK,"查询成功",list);
    }




    /***
     * 根据ID查询Setmeal数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Setmeal根据ID查询",notes = "根据ID查询Setmeal方法详情",tags = {"SetmealController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Setmeal> findById(@PathVariable Integer id){
        //调用SetmealService实现根据主键查询Setmeal
        Setmeal setmeal = setmealService.findById(id);
        return new Result<Setmeal>(true,StatusCode.OK,"查询成功",setmeal);
    }

    /***
     * 查询Setmeal全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Setmeal",notes = "查询所Setmeal有方法详情",tags = {"SetmealController"})
    @GetMapping
    public Result<List<Setmeal>> findAll(){
        //调用SetmealService实现查询所有Setmeal
        List<Setmeal> list = setmealService.findAll();
        return new Result<List<Setmeal>>(true, StatusCode.OK,"查询成功",list) ;
    }

    /***
     * 新增Setmeal数据
     * @param setmeal
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Setmeal setmeal, @RequestParam(required = false) Integer[] checkgroupIds){
        //调用SetmealService实现添加Setmeal
        setmealService.add(setmeal, checkgroupIds);
        return new Result(true,StatusCode.OK,"添加成功");
    }


    @GetMapping("/findCheckGroupIds/{id}")
    public Result findCheckGroupIds(@PathVariable Integer id){
        Integer[] checkGroupids = setmealService.findCheckGroupids(id);
        return new Result(true, StatusCode.OK, "查询成功",checkGroupids);
    }

    /***
     * 修改Setmeal数据
     */
    @PutMapping
    public Result update(@RequestBody Setmeal setmeal, @RequestParam(required = false) Integer[] checkGroupIds){
        //调用CheckgroupService实现修改Checkgroup
        setmealService.update(setmeal, checkGroupIds);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用SetmealService实现根据主键删除
        setmealService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

}
