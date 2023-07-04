package com.health.items.controller;

import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Checkitem;
import com.health.items.service.CheckitemService;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/
@Api(value = "CheckitemController")
@RestController
@RequestMapping("/checkitem")
@CrossOrigin
public class CheckitemController {

    @Autowired
    private CheckitemService checkitemService;

    /***
     * Checkitem分页条件搜索实现
     * @param checkitem
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Checkitem条件分页查询",notes = "分页条件查询Checkitem方法详情",tags = {"CheckitemController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Checkitem对象",value = "传入JSON数据",required = false) Checkitem checkitem, @PathVariable  int page, @PathVariable  int size){
        //调用CheckitemService实现分页条件查询Checkitem
        PageInfo<Checkitem> pageInfo = checkitemService.findPage(checkitem, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Checkitem分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Checkitem分页查询",notes = "分页查询Checkitem方法详情",tags = {"CheckitemController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用CheckitemService实现分页查询Checkitem
        PageInfo<Checkitem> pageInfo = checkitemService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param checkitem
     * @return
     */
    @ApiOperation(value = "Checkitem条件查询",notes = "条件查询Checkitem方法详情",tags = {"CheckitemController"})
    @PostMapping(value = "/search" )
    public Result<List<Checkitem>> findList(@RequestBody(required = false) @ApiParam(name = "Checkitem对象",value = "传入JSON数据",required = false) Checkitem checkitem){
        //调用CheckitemService实现条件查询Checkitem
        List<Checkitem> list = checkitemService.findList(checkitem);
        return new Result<List<Checkitem>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Checkitem根据ID删除",notes = "根据ID删除Checkitem方法详情",tags = {"CheckitemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        try{
            //调用CheckitemService实现根据主键删除
            checkitemService.delete(id);
        }catch (RuntimeException e){
            return new Result(false, StatusCode.ERROR, e.getMessage());
        }

        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Checkitem数据
     * @param checkitem
     * @param id
     * @return
     */
    @ApiOperation(value = "Checkitem根据ID修改",notes = "根据ID修改Checkitem方法详情",tags = {"CheckitemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Checkitem对象",value = "传入JSON数据",required = false) Checkitem checkitem,@PathVariable Integer id){
        //设置主键值
        checkitem.setId(id);
        //调用CheckitemService实现修改Checkitem
        checkitemService.update(checkitem);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Checkitem数据
     * @param checkitem
     * @return
     */
    @ApiOperation(value = "Checkitem添加",notes = "添加Checkitem方法详情",tags = {"CheckitemController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Checkitem对象",value = "传入JSON数据",required = true) Checkitem checkitem){
        //调用CheckitemService实现添加Checkitem
        checkitemService.add(checkitem);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Checkitem数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Checkitem根据ID查询",notes = "根据ID查询Checkitem方法详情",tags = {"CheckitemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Checkitem> findById(@PathVariable Integer id){
        //调用CheckitemService实现根据主键查询Checkitem
        Checkitem checkitem = checkitemService.findById(id);
        return new Result<Checkitem>(true,StatusCode.OK,"查询成功",checkitem);
    }

    /***
     * 查询Checkitem全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Checkitem",notes = "查询所Checkitem有方法详情",tags = {"CheckitemController"})
    @GetMapping
    public Result<List<Checkitem>> findAll(){
        //调用CheckitemService实现查询所有Checkitem
        List<Checkitem> list = checkitemService.findAll();
        return new Result<List<Checkitem>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
