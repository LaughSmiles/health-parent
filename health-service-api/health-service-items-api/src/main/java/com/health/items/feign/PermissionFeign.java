package com.health.items.feign;
import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Permission;
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
@RequestMapping("/permission")
public interface PermissionFeign {

    /***
     * Permission分页条件搜索实现
     * @param permission
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) Permission permission, @PathVariable(name = "page")  int page
            , @PathVariable(name = "size")  int size);

    /***
     * Permission分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable(name = "page")  int page, @PathVariable(name = "size")  int size);

    /***
     * 多条件搜索品牌数据
     * @param permission
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<Permission>> findList(@RequestBody(required = false) Permission permission);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable(name = "id") Integer id);

    /***
     * 修改Permission数据
     * @param permission
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Permission permission,@PathVariable(name = "id") Integer id);

    /***
     * 新增Permission数据
     * @param permission
     * @return
     */
    @PostMapping
    Result add(@RequestBody Permission permission);

    /***
     * 根据ID查询Permission数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Permission> findById(@PathVariable(name = "id") Integer id);

    /***
     * 查询Permission全部数据
     * @return
     */
    @GetMapping
    Result<List<Permission>> findAll();
}
