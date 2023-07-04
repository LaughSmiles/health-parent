package com.health.items.feign;

import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Member;
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
@RequestMapping("/member")
public interface MemberFeign {

    @GetMapping("/getMemberReport")
    public Result getMemberReport();


    @PostMapping("/login")
    public Result login(@RequestBody Map map);

    /***
     * Member分页条件搜索实现
     * @param member
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) Member member, @PathVariable(name = "page")  int page
            , @PathVariable(name = "size")  int size);

    /***
     * Member分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable(name = "page")  int page, @PathVariable(name = "size")  int size);

    /***
     * 多条件搜索品牌数据
     * @param member
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<Member>> findList(@RequestBody(required = false) Member member);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable(name = "id") Integer id);

    /***
     * 修改Member数据
     * @param member
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Member member,@PathVariable(name = "id") Integer id);

    /***
     * 新增Member数据
     * @param member
     * @return
     */
    @PostMapping
    Result add(@RequestBody Member member);

    /***
     * 根据ID查询Member数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Member> findById(@PathVariable(name = "id") Integer id);

    /***
     * 查询Member全部数据
     * @return
     */
    @GetMapping
    Result<List<Member>> findAll();
}
