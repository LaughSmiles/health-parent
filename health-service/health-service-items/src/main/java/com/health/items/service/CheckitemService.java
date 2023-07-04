package com.health.items.service;

import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Checkitem;

import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:Checkitem业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface CheckitemService {

    /***
     * Checkitem多条件分页查询
     * @param checkitem
     * @param page
     * @param size
     * @return
     */
    PageInfo<Checkitem> findPage(Checkitem checkitem, int page, int size);

    /***
     * Checkitem分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Checkitem> findPage(int page, int size);

    /***
     * Checkitem多条件搜索方法
     * @param checkitem
     * @return
     */
    List<Checkitem> findList(Checkitem checkitem);

    /***
     * 删除Checkitem
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Checkitem数据
     * @param checkitem
     */
    void update(Checkitem checkitem);

    /***
     * 新增Checkitem
     * @param checkitem
     */
    void add(Checkitem checkitem);

    /**
     * 根据ID查询Checkitem
     * @param id
     * @return
     */
     Checkitem findById(Integer id);

    /***
     * 查询所有Checkitem
     * @return
     */
    List<Checkitem> findAll();
}
