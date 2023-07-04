package com.health.items.feign;
import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Setmeal;
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
@RequestMapping("/setmeal")
public interface SetmealFeign {

    @GetMapping("/getSetmealReport")
    public Result getSetmealReport();


    /***
     * Setmeal分页条件搜索实现
     * @param setmeal
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) Setmeal setmeal, @PathVariable(name = "page")  int page
            , @PathVariable(name = "size")  int size);

    /***
     * Setmeal分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable(name = "page")  int page, @PathVariable(name = "size")  int size);

    /***
     * 多条件搜索品牌数据
     * @param setmeal
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<Setmeal>> findList(@RequestBody(required = false) Setmeal setmeal);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable(name = "id") Integer id);

    /***
     * 新增Setmeal数据
     * @param setmeal
     * @return
     */
    @PostMapping
    Result add(@RequestBody Setmeal setmeal, @RequestParam(required = false) Integer[] checkgroupIds);

    /***
     * 根据ID查询Setmeal数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Setmeal> findById(@PathVariable(name = "id") Integer id);

    /***
     * 查询Setmeal全部数据
     * @return
     */
    @GetMapping
    Result<List<Setmeal>> findAll();

    @GetMapping("/findCheckGroupIds/{id}")
    Result findCheckGroupIds(@PathVariable Integer id);

    /***
     * 修改Setmeal数据
     */
    @PutMapping
    Result update(@RequestBody Setmeal setmeal, @RequestParam(required = false) Integer[] checkGroupIds);
}
