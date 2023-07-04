package com.health.items.controller;

import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Checkgroup;
import com.health.items.service.CheckgroupService;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/
@Api(value = "CheckgroupController")
@RestController
@RequestMapping("/checkgroup")
@CrossOrigin
public class CheckgroupController {

    @Autowired
    private CheckgroupService checkgroupService;

    /***
     * Checkgroup分页条件搜索实现
     * @param checkgroup
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Checkgroup条件分页查询",notes = "分页条件查询Checkgroup方法详情",tags = {"CheckgroupController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Checkgroup对象",value = "传入JSON数据",required = false) Checkgroup checkgroup, @PathVariable  int page, @PathVariable  int size){
        //调用CheckgroupService实现分页条件查询Checkgroup
        PageInfo<Checkgroup> pageInfo = checkgroupService.findPage(checkgroup, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Checkgroup分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Checkgroup分页查询",notes = "分页查询Checkgroup方法详情",tags = {"CheckgroupController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用CheckgroupService实现分页查询Checkgroup
        PageInfo<Checkgroup> pageInfo = checkgroupService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param checkgroup
     * @return
     */
    @ApiOperation(value = "Checkgroup条件查询",notes = "条件查询Checkgroup方法详情",tags = {"CheckgroupController"})
    @PostMapping(value = "/search" )
    public Result<List<Checkgroup>> findList(@RequestBody(required = false) @ApiParam(name = "Checkgroup对象",value = "传入JSON数据",required = false) Checkgroup checkgroup){
        //调用CheckgroupService实现条件查询Checkgroup
        List<Checkgroup> list = checkgroupService.findList(checkgroup);
        return new Result<List<Checkgroup>>(true,StatusCode.OK,"查询成功",list);
    }



    /***
     * 根据ID查询Checkgroup数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Checkgroup根据ID查询",notes = "根据ID查询Checkgroup方法详情",tags = {"CheckgroupController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Checkgroup> findById(@PathVariable Integer id){
        //调用CheckgroupService实现根据主键查询Checkgroup
        Checkgroup checkgroup = checkgroupService.findById(id);
        return new Result<Checkgroup>(true,StatusCode.OK,"查询成功",checkgroup);
    }

    /***
     * 查询Checkgroup全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Checkgroup",notes = "查询所Checkgroup有方法详情",tags = {"CheckgroupController"})
    @GetMapping
    public Result<List<Checkgroup>> findAll(){
        //调用CheckgroupService实现查询所有Checkgroup
        List<Checkgroup> list = checkgroupService.findAll();
        return new Result<List<Checkgroup>>(true, StatusCode.OK,"查询成功",list) ;
    }

    /***
     * 新增Checkgroup数据
     * @param checkgroup
     * @return
     */
    @PostMapping
    public Result add(@RequestParam(required = false) Integer[] checkitemIds,@RequestBody Checkgroup checkgroup){
        checkgroupService.add(checkitemIds,checkgroup);
        return new Result(true,StatusCode.OK,"添加成功");
    }


    /***
     * 修改Checkgroup数据
     * @param checkgroup
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Checkgroup checkgroup,@RequestParam(required = false) Integer[] checkitemIds){
        //调用CheckgroupService实现修改Checkgroup
        checkgroupService.update(checkgroup, checkitemIds);
        return new Result(true,StatusCode.OK,"修改成功");
    }


    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        try {
            checkgroupService.delete(id);
        }catch (RuntimeException e){
            return new Result(false, StatusCode.ERROR, e.getMessage());
        }catch (Exception e){
            return new Result(false, StatusCode.ERROR, e.getMessage());
        }

        return new Result(true,StatusCode.OK,"删除成功");
    }

    @GetMapping("/findCheckItemIds/{id}")
    public Result findCheckItemIds(@PathVariable Integer id){
        Integer[] checkItemids = checkgroupService.findCheckItemids(id);
        return new Result(true, StatusCode.OK, "查询成功",checkItemids);
    }
}
