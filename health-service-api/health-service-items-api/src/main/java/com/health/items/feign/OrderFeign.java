package com.health.items.feign;
import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Order;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/18 13:58
 *****/
@FeignClient(name="items")
@RequestMapping("/order")
public interface OrderFeign {


    @PostMapping("/submit")
    public Result submitOrder(@RequestBody Map map);

    /***
     * Order分页条件搜索实现
     * @param order
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) Order order, @PathVariable(name = "page")  int page
            , @PathVariable(name = "size")  int size);

    /***
     * Order分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable(name = "page")  int page, @PathVariable(name = "size")  int size);

    /***
     * 多条件搜索品牌数据
     * @param order
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<Order>> findList(@RequestBody(required = false) Order order);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable(name = "id") Integer id);

    /***
     * 修改Order数据
     * @param order
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Order order,@PathVariable(name = "id") Integer id);

    /***
     * 新增Order数据
     * @param order
     * @return
     */
    @PostMapping
    Result add(@RequestBody Order order);

    /***
     * 根据ID查询Order数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Order> findById(@PathVariable(name = "id") Integer id);

    /***
     * 查询Order全部数据
     * @return
     */
    @GetMapping
    Result<List<Order>> findAll();
}
