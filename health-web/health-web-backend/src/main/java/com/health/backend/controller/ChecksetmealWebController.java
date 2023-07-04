package com.health.backend.controller;

import com.github.pagehelper.PageInfo;
import com.health.items.feign.SetmealFeign;
import com.health.items.pojo.Setmeal;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/setmeal")
@CrossOrigin
public class ChecksetmealWebController {

    @Autowired
    private SetmealFeign setmealFeign;

    @GetMapping("/web/getSetmealReport")
    public Result getSetmealReport(){
        return setmealFeign.getSetmealReport();
    }

    /***
     * 新增Setmeal数据
     * @param setmeal
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, @RequestParam(required = false) Integer[] checkgroupIds){
        return setmealFeign.add(setmeal, checkgroupIds);
    }


    @PostMapping(value = "/findPage" )
    public Result<PageInfo> findPage(@RequestBody(required = false) Map map){
        //调用SetmealService实现分页条件查询Setmeal
        Integer currentPage = (Integer) map.get("currentPage");
        Integer pageSize = (Integer) map.get("pageSize");
        String queryString = (String) map.get("queryString");

        Setmeal setmeal = new Setmeal();
        setmeal.setName(queryString);

        return setmealFeign.findPage(setmeal, currentPage, pageSize);
    }

    @GetMapping("/id")
    public Result<Setmeal> findById(@RequestParam Integer id){
        return setmealFeign.findById(id);
    }

    @GetMapping("/findCheckGroupIds")
    public Result findCheckGroupIds(@RequestParam Integer id){
        return setmealFeign.findCheckGroupIds(id);
    }

    /***
     * 修改Setmeal数据
     */
    @PutMapping("/edit")
    public Result update(@RequestBody Setmeal setmeal, @RequestParam(required = false) Integer[] checkGroupIds){
        //调用CheckgroupService实现修改Checkgroup
        return setmealFeign.update(setmeal, checkGroupIds);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */

    @DeleteMapping(value = "/delete" )
    public Result delete(@RequestParam Integer id){
        return setmealFeign.delete(id);
    }

//    /***
//     * Setmeal分页条件搜索实现
//     * @param setmeal
//     * @param page
//     * @param size
//     * @return
//     */
//    @ApiOperation(value = "Setmeal条件分页查询",notes = "分页条件查询Setmeal方法详情",tags = {"SetmealController"})
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
//            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
//    })
//    @PostMapping(value = "/search/{page}/{size}" )
//    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Setmeal对象",value = "传入JSON数据",required = false) Setmeal setmeal, @PathVariable int page, @PathVariable  int size){
//        //调用SetmealService实现分页条件查询Setmeal
//        PageInfo<Setmeal> pageInfo = setmealService.findPage(setmeal, page, size);
//        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
//    }
//
//    /***
//     * Setmeal分页搜索实现
//     * @param page:当前页
//     * @param size:每页显示多少条
//     * @return
//     */
//    @ApiOperation(value = "Setmeal分页查询",notes = "分页查询Setmeal方法详情",tags = {"SetmealController"})
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
//            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
//    })
//    @GetMapping(value = "/search/{page}/{size}" )
//    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
//        //调用SetmealService实现分页查询Setmeal
//        PageInfo<Setmeal> pageInfo = setmealService.findPage(page, size);
//        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
//    }
//
//    /***
//     * 多条件搜索品牌数据
//     * @param setmeal
//     * @return
//     */
//    @ApiOperation(value = "Setmeal条件查询",notes = "条件查询Setmeal方法详情",tags = {"SetmealController"})
//    @PostMapping(value = "/search" )
//    public Result<List<Setmeal>> findList(@RequestBody(required = false) @ApiParam(name = "Setmeal对象",value = "传入JSON数据",required = false) Setmeal setmeal){
//        //调用SetmealService实现条件查询Setmeal
//        List<Setmeal> list = setmealService.findList(setmeal);
//        return new Result<List<Setmeal>>(true,StatusCode.OK,"查询成功",list);
//    }
//


//
//    /***
//     * 新增Setmeal数据
//     * @param setmeal
//     * @return
//     */
//    @ApiOperation(value = "Setmeal添加",notes = "添加Setmeal方法详情",tags = {"SetmealController"})
//    @PostMapping
//    public Result add(@RequestBody  @ApiParam(name = "Setmeal对象",value = "传入JSON数据",required = true) Setmeal setmeal){
//        //调用SetmealService实现添加Setmeal
//        setmealService.add(setmeal);
//        return new Result(true,StatusCode.OK,"添加成功");
//    }
//

//
//    /***
//     * 查询Setmeal全部数据
//     * @return
//     */
//    @ApiOperation(value = "查询所有Setmeal",notes = "查询所Setmeal有方法详情",tags = {"SetmealController"})
//    @GetMapping
//    public Result<List<Setmeal>> findAll(){
//        //调用SetmealService实现查询所有Setmeal
//        List<Setmeal> list = setmealService.findAll();
//        return new Result<List<Setmeal>>(true, StatusCode.OK,"查询成功",list) ;
//    }

}
