package com.health.backend.controller;

import com.github.pagehelper.PageInfo;
import com.health.items.feign.CheckitemFeign;
import com.health.items.pojo.Checkitem;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/
@RestController
@RequestMapping("/checkitem")
@CrossOrigin
public class CheckitemWebController {

    @Autowired
    private CheckitemFeign checkitemFeign;

    /***
     * 新增Checkitem数据
     * @param checkitem
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Checkitem checkitem){
        return checkitemFeign.add(checkitem);
    }


    /***
     * Checkitem分页条件搜索实现
//     * @param queryString
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/findPage/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) Map map
            , @PathVariable  int page, @PathVariable int size){
        String queryString = (String) map.get("queryString");

        Checkitem checkitem = new Checkitem();
        checkitem.setName(queryString);
        return checkitemFeign.findPage(checkitem, page, size);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping("delete")
    public Result delete(@RequestParam(required = true)Integer id){
        return checkitemFeign.delete(id);
    }

    /***
     * 根据ID查询Checkitem数据
     * @param id
     * @return
     */
    @GetMapping("/findbyId")
    public Result<Checkitem> findById(@RequestParam(required = true)Integer id){
        return checkitemFeign.findById(id);
    }

    /***
     * 修改Checkitem数据
     * @param checkitem
     */
    @PutMapping(value="edit")
    public Result update(@RequestBody Checkitem checkitem){
        return checkitemFeign.update(checkitem, checkitem.getId());
    }

    /***
     * 查询Checkitem全部数据
     * @return
     */
    @GetMapping("findall")
    public Result<List<Checkitem>> findAll(){
        return checkitemFeign.findAll();
    }




//    /***
//     * Checkitem分页搜索实现
//     * @param page:当前页
//     * @param size:每页显示多少条
//     * @return
//     */
//    @ApiOperation(value = "Checkitem分页查询",notes = "分页查询Checkitem方法详情",tags = {"CheckitemController"})
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
//            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
//    })
//    @GetMapping(value = "/search/{page}/{size}" )
//    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
//        //调用CheckitemService实现分页查询Checkitem
//        PageInfo<Checkitem> pageInfo = checkitemService.findPage(page, size);
//        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
//    }
//
//    /***
//     * 多条件搜索品牌数据
//     * @param checkitem
//     * @return
//     */
//    @ApiOperation(value = "Checkitem条件查询",notes = "条件查询Checkitem方法详情",tags = {"CheckitemController"})
//    @PostMapping(value = "/search" )
//    public Result<List<Checkitem>> findList(@RequestBody(required = false) @ApiParam(name = "Checkitem对象",value = "传入JSON数据",required = false) Checkitem checkitem){
//        //调用CheckitemService实现条件查询Checkitem
//        List<Checkitem> list = checkitemService.findList(checkitem);
//        return new Result<List<Checkitem>>(true,StatusCode.OK,"查询成功",list);
//    }
//
//    /***
//     * 根据ID删除品牌数据
//     * @param id
//     * @return
//     */
//    @ApiOperation(value = "Checkitem根据ID删除",notes = "根据ID删除Checkitem方法详情",tags = {"CheckitemController"})
//    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
//    @DeleteMapping(value = "/{id}" )
//    public Result delete(@PathVariable Integer id){
//        //调用CheckitemService实现根据主键删除
//        checkitemService.delete(id);
//        return new Result(true,StatusCode.OK,"删除成功");
//    }
//

//
//
//

//
//    /***
//     * 查询Checkitem全部数据
//     * @return
//     */
//    @ApiOperation(value = "查询所有Checkitem",notes = "查询所Checkitem有方法详情",tags = {"CheckitemController"})
//    @GetMapping
//    public Result<List<Checkitem>> findAll(){
//        //调用CheckitemService实现查询所有Checkitem
//        List<Checkitem> list = checkitemService.findAll();
//        return new Result<List<Checkitem>>(true, StatusCode.OK,"查询成功",list) ;
//    }
}
