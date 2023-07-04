package com.health.items.feign;

import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Ordersetting;
import entity.Result;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/18 13:58
 *****/
@FeignClient(name="items")
@RequestMapping("/ordersetting")
public interface OrdersettingFeign {

    /***
     * Ordersetting分页条件搜索实现
     * @param ordersetting
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) Ordersetting ordersetting, @PathVariable(name = "page")  int page
            , @PathVariable(name = "size")  int size);

    /***
     * Ordersetting分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable(name = "page")  int page, @PathVariable(name = "size")  int size);

    /***
     * 多条件搜索品牌数据
     * @param ordersetting
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<Ordersetting>> findList(@RequestBody(required = false) Ordersetting ordersetting);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable(name = "id") Integer id);

    /***
     * 修改Ordersetting数据
     * @param ordersetting
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Ordersetting ordersetting,@PathVariable(name = "id") Integer id);

    /***
     * 新增Ordersetting数据
     * @param ordersetting
     * @return
     */
    @PostMapping
    Result add(@RequestBody Ordersetting ordersetting);

    /***
     * 根据ID查询Ordersetting数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Ordersetting> findById(@PathVariable(name = "id") Integer id);

    /***
     * 查询Ordersetting全部数据
     * @return
     */
    @GetMapping
    Result<List<Ordersetting>> findAll();


    @GetMapping("/getOrderSettingByMonth/{date}")
    Result getOrderSettingByMonth(@PathVariable String date);


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Headers("Content-Type: multipart/form-data")
    Result upload(@RequestPart("excelFile") MultipartFile excelFile);


    //根据日期插入
    @GetMapping("/editNumberByDate")
    Result insertBydate(@RequestParam String number, @RequestParam String orderDate);
}
