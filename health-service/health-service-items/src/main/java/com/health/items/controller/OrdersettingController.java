package com.health.items.controller;

import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Ordersetting;
import com.health.items.service.OrdersettingService;
import com.health.items.utils.POIUtils;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/
@Api(value = "OrdersettingController")
@RestController
@RequestMapping("/ordersetting")
@CrossOrigin
public class OrdersettingController {

    @Autowired
    private OrdersettingService ordersettingService;

    //根据日期插入
    @GetMapping("/editNumberByDate")
    public Result insertBydate(@RequestParam String number, @RequestParam String orderDate){
        ordersettingService.insertByDate(number, orderDate);
        return new Result<>(true, StatusCode.OK,"插入成功");
    }

    //文件上传，实现预约设置数据批量导入
    @PostMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile){
        try {
            List<String[]> list = POIUtils.readExcel(excelFile);//使用POI解析表格数据
            List<Ordersetting> data = new ArrayList<>();
            for (String[] strings : list) {
                String orderDate = strings[0];
                String number = strings[1];
                Ordersetting orderSetting = new Ordersetting(new Date(orderDate),Integer.parseInt(number));
                data.add(orderSetting);
            }
            //通过dubbo远程调用服务实现数据批量导入到数据库
            ordersettingService.add(data);
            return new Result(true, StatusCode.OK,"上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            //文件解析失败
            return new Result(false, StatusCode.ERROR, "上传失败");
        }
    }

    @GetMapping("/getOrderSettingByMonth/{date}")
    public Result getOrderSettingByMonth(@PathVariable String date){//参数格式为：2019-03
        List<Map> list = ordersettingService.getOrderSettingByMonth(date);
        return new Result(true, StatusCode.OK, "查询成功",list);
    }

    /***
     * Ordersetting分页条件搜索实现
     * @param ordersetting
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Ordersetting条件分页查询",notes = "分页条件查询Ordersetting方法详情",tags = {"OrdersettingController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Ordersetting对象",value = "传入JSON数据",required = false) Ordersetting ordersetting, @PathVariable  int page, @PathVariable  int size){
        //调用OrdersettingService实现分页条件查询Ordersetting
        PageInfo<Ordersetting> pageInfo = ordersettingService.findPage(ordersetting, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Ordersetting分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Ordersetting分页查询",notes = "分页查询Ordersetting方法详情",tags = {"OrdersettingController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用OrdersettingService实现分页查询Ordersetting
        PageInfo<Ordersetting> pageInfo = ordersettingService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param ordersetting
     * @return
     */
    @ApiOperation(value = "Ordersetting条件查询",notes = "条件查询Ordersetting方法详情",tags = {"OrdersettingController"})
    @PostMapping(value = "/search" )
    public Result<List<Ordersetting>> findList(@RequestBody(required = false) @ApiParam(name = "Ordersetting对象",value = "传入JSON数据",required = false) Ordersetting ordersetting){
        //调用OrdersettingService实现条件查询Ordersetting
        List<Ordersetting> list = ordersettingService.findList(ordersetting);
        return new Result<List<Ordersetting>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Ordersetting根据ID删除",notes = "根据ID删除Ordersetting方法详情",tags = {"OrdersettingController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用OrdersettingService实现根据主键删除
        ordersettingService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Ordersetting数据
     * @param ordersetting
     * @param id
     * @return
     */
    @ApiOperation(value = "Ordersetting根据ID修改",notes = "根据ID修改Ordersetting方法详情",tags = {"OrdersettingController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Ordersetting对象",value = "传入JSON数据",required = false) Ordersetting ordersetting,@PathVariable Integer id){
        //设置主键值
        ordersetting.setId(id);
        //调用OrdersettingService实现修改Ordersetting
        ordersettingService.update(ordersetting);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Ordersetting数据
     * @param ordersetting
     * @return
     */
    @ApiOperation(value = "Ordersetting添加",notes = "添加Ordersetting方法详情",tags = {"OrdersettingController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Ordersetting对象",value = "传入JSON数据",required = true) Ordersetting ordersetting){
        //调用OrdersettingService实现添加Ordersetting
        ordersettingService.add(ordersetting);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Ordersetting数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Ordersetting根据ID查询",notes = "根据ID查询Ordersetting方法详情",tags = {"OrdersettingController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Ordersetting> findById(@PathVariable Integer id){
        //调用OrdersettingService实现根据主键查询Ordersetting
        Ordersetting ordersetting = ordersettingService.findById(id);
        return new Result<Ordersetting>(true,StatusCode.OK,"查询成功",ordersetting);
    }

    /***
     * 查询Ordersetting全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Ordersetting",notes = "查询所Ordersetting有方法详情",tags = {"OrdersettingController"})
    @GetMapping
    public Result<List<Ordersetting>> findAll(){
        //调用OrdersettingService实现查询所有Ordersetting
        List<Ordersetting> list = ordersettingService.findAll();
        return new Result<List<Ordersetting>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
