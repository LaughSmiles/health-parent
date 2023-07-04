package com.health.items.controller;

import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Permission;
import com.health.items.service.PermissionService;
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
@Api(value = "PermissionController")
@RestController
@RequestMapping("/permission")
@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /***
     * Permission分页条件搜索实现
     * @param permission
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Permission条件分页查询",notes = "分页条件查询Permission方法详情",tags = {"PermissionController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Permission对象",value = "传入JSON数据",required = false) Permission permission, @PathVariable  int page, @PathVariable  int size){
        //调用PermissionService实现分页条件查询Permission
        PageInfo<Permission> pageInfo = permissionService.findPage(permission, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Permission分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Permission分页查询",notes = "分页查询Permission方法详情",tags = {"PermissionController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用PermissionService实现分页查询Permission
        PageInfo<Permission> pageInfo = permissionService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param permission
     * @return
     */
    @ApiOperation(value = "Permission条件查询",notes = "条件查询Permission方法详情",tags = {"PermissionController"})
    @PostMapping(value = "/search" )
    public Result<List<Permission>> findList(@RequestBody(required = false) @ApiParam(name = "Permission对象",value = "传入JSON数据",required = false) Permission permission){
        //调用PermissionService实现条件查询Permission
        List<Permission> list = permissionService.findList(permission);
        return new Result<List<Permission>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Permission根据ID删除",notes = "根据ID删除Permission方法详情",tags = {"PermissionController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用PermissionService实现根据主键删除
        permissionService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Permission数据
     * @param permission
     * @param id
     * @return
     */
    @ApiOperation(value = "Permission根据ID修改",notes = "根据ID修改Permission方法详情",tags = {"PermissionController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Permission对象",value = "传入JSON数据",required = false) Permission permission,@PathVariable Integer id){
        //设置主键值
        permission.setId(id);
        //调用PermissionService实现修改Permission
        permissionService.update(permission);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Permission数据
     * @param permission
     * @return
     */
    @ApiOperation(value = "Permission添加",notes = "添加Permission方法详情",tags = {"PermissionController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Permission对象",value = "传入JSON数据",required = true) Permission permission){
        //调用PermissionService实现添加Permission
        permissionService.add(permission);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Permission数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Permission根据ID查询",notes = "根据ID查询Permission方法详情",tags = {"PermissionController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Permission> findById(@PathVariable Integer id){
        //调用PermissionService实现根据主键查询Permission
        Permission permission = permissionService.findById(id);
        return new Result<Permission>(true,StatusCode.OK,"查询成功",permission);
    }

    /***
     * 查询Permission全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Permission",notes = "查询所Permission有方法详情",tags = {"PermissionController"})
    @GetMapping
    public Result<List<Permission>> findAll(){
        //调用PermissionService实现查询所有Permission
        List<Permission> list = permissionService.findAll();
        return new Result<List<Permission>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
