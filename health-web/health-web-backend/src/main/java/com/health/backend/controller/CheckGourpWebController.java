package com.health.backend.controller;

import com.github.pagehelper.PageInfo;
import com.health.items.feign.CheckgroupFeign;
import com.health.items.pojo.Checkgroup;
import entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/checkgroup")
@CrossOrigin
@Slf4j
public class CheckGourpWebController {

    @Autowired
    private CheckgroupFeign checkgroupFeign;


     /***
     * 新增Checkgroup数据
     * @param checkgroup
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestParam(required = false) Integer[] checkitemIds,@RequestBody Checkgroup checkgroup){
        return checkgroupFeign.add(checkitemIds,checkgroup);
    }


     /***
     * Checkgroup分页条件搜索实现
     */
    @PostMapping(value = "/findPage" )
    public Result<PageInfo> findPage(@RequestBody Map map){
        Integer currentPage = (Integer)map.get("currentPage");
        Integer pageSize = (Integer) map.get("pageSize");
        String queryString = (String) map.get("queryString");

        Checkgroup checkgroup = new Checkgroup();
        checkgroup.setName(queryString);
        return checkgroupFeign.findPage(checkgroup, currentPage, pageSize);
    }


     /***
     * 根据ID查询Checkgroup数据
     * @param id
     * @return
     */
    @GetMapping("/id")
    public Result<Checkgroup> findById(@RequestParam Integer id){
        return checkgroupFeign.findById(id);
    }

    /***
     * 修改Checkgroup数据
     */
    @PutMapping("/edit")
    public Result update(@RequestBody Checkgroup checkgroup,@RequestParam(required = false) Integer[] checkitemIds){
        return checkgroupFeign.update(checkgroup, checkitemIds);
    }



    @DeleteMapping(value = "/delete" )
    public Result delete(@RequestParam Integer id){
        return checkgroupFeign.delete(id);
    }


    /***
     * 查询Checkgroup全部数据
     * @return
     */
    @GetMapping("/findAll")
    public Result<List<Checkgroup>> findAll(){
        return checkgroupFeign.findAll();
    }


    @GetMapping("/findCheckItemIds")
    public Result findCheckItemIds(@RequestParam Integer id){
        return checkgroupFeign.findCheckItemIds(id);
    }
}
