package com.health.items.service;

import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:Setmeal业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface SetmealService {





    List<Map<String,String>> findSetmealCount();

    /***
     * Setmeal多条件分页查询
     * @param setmeal
     * @param page
     * @param size
     * @return
     */
    PageInfo<Setmeal> findPage(Setmeal setmeal, int page, int size);

    /***
     * Setmeal分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Setmeal> findPage(int page, int size);

    /***
     * Setmeal多条件搜索方法
     * @param setmeal
     * @return
     */
    List<Setmeal> findList(Setmeal setmeal);

    /***
     * 删除Setmeal
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Setmeal数据
     * @param setmeal
     */
    void update(Setmeal setmeal);

    /***
     * 新增Setmeal
     * @param setmeal
     */
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 根据ID查询Setmeal
     * @param id
     * @return
     */
     Setmeal findById(Integer id);

    /***
     * 查询所有Setmeal
     * @return
     */
    List<Setmeal> findAll();

    Integer[] findCheckGroupids(Integer id);

    /***
     * 修改CheckSetmeal数据
     * @param setmeal
     */
    void update(Setmeal setmeal , Integer[] checkGroupIds);

}
