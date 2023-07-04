package com.health.items.service;

import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Checkgroup;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:Checkgroup业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface CheckgroupService {

    /***
     * Checkgroup多条件分页查询
     * @param checkgroup
     * @param page
     * @param size
     * @return
     */
    PageInfo<Checkgroup> findPage(Checkgroup checkgroup, int page, int size);

    /***
     * Checkgroup分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Checkgroup> findPage(int page, int size);

    /***
     * Checkgroup多条件搜索方法
     * @param checkgroup
     * @return
     */
    List<Checkgroup> findList(Checkgroup checkgroup);

    /***
     * 删除Checkgroup
     * @param id
     */
    void delete(Integer id);


    /***
     * 新增Checkgroup
     * @param checkgroup
     */
    void add(Checkgroup checkgroup);

    /**
     * 根据ID查询Checkgroup
     * @param id
     * @return
     */
     Checkgroup findById(Integer id);

    /***
     * 查询所有Checkgroup
     * @return
     */
    List<Checkgroup> findAll();


    void add(Integer[] checkitemIds, Checkgroup checkgroup);

    /***
     * 修改Checkgroup数据
     * @param checkgroup
     */
    void update(Checkgroup checkgroup ,Integer[] checkitemIds);


    Integer[] findCheckItemids(Integer id);
}
