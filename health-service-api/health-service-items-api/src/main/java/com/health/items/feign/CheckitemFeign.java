package com.health.items.feign;
import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Checkitem;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/18 13:58
 *****/
@FeignClient(name="items")
@RequestMapping("/checkitem")
public interface CheckitemFeign {

    /***
     * Checkitem分页条件搜索实现
     * @param checkitem
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) Checkitem checkitem, @PathVariable(name = "page")  int page
            , @PathVariable(name = "size")  int size);

    /***
     * Checkitem分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable(name = "page")  int page, @PathVariable(name = "size")  int size);

    /***
     * 多条件搜索品牌数据
     * @param checkitem
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<Checkitem>> findList(@RequestBody(required = false) Checkitem checkitem);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable(name = "id") Integer id);

    /***
     * 修改Checkitem数据
     * @param checkitem
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Checkitem checkitem,@PathVariable(name = "id") Integer id);

    /***
     * 新增Checkitem数据
     * @param checkitem
     * @return
     */
    @PostMapping
    Result add(@RequestBody Checkitem checkitem);

    /***
     * 根据ID查询Checkitem数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Checkitem> findById(@PathVariable(name = "id") Integer id);

    /***
     * 查询Checkitem全部数据
     * @return
     */
    @GetMapping
    Result<List<Checkitem>> findAll();
}
