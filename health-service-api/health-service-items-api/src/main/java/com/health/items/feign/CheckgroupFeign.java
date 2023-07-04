package com.health.items.feign;
import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Checkgroup;
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
@RequestMapping("/checkgroup")
public interface CheckgroupFeign {

    /***
     * Checkgroup分页条件搜索实现
     * @param checkgroup
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) Checkgroup checkgroup, @PathVariable(name = "page")  int page
            , @PathVariable(name = "size")  int size);

    /***
     * Checkgroup分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable(name = "page")  int page, @PathVariable(name = "size")  int size);

    /***
     * 多条件搜索品牌数据
     * @param checkgroup
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<Checkgroup>> findList(@RequestBody(required = false) Checkgroup checkgroup);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable(name = "id") Integer id);

    /***
     * 修改Checkgroup数据
     * @param checkgroup
     * @return
     */
    @PutMapping
    Result update(@RequestBody Checkgroup checkgroup,@RequestParam(required = false) Integer[] checkitemIds);

    /***
     * 新增Checkgroup数据
     * @param checkgroup
     * @return
     */
    @PostMapping
    Result add(@RequestParam(required = false) Integer[] checkitemIds,@RequestBody Checkgroup checkgroup);

    /***
     * 根据ID查询Checkgroup数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Checkgroup> findById(@PathVariable(name = "id") Integer id);

    /***
     * 查询Checkgroup全部数据
     * @return
     */
    @GetMapping
    Result<List<Checkgroup>> findAll();


    @GetMapping("/findCheckItemIds/{id}")
    Result findCheckItemIds(@PathVariable Integer id);

}
