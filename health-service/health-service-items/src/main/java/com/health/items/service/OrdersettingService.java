package com.health.items.service;

import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Ordersetting;

import java.util.List;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:Ordersetting业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface OrdersettingService {

    /***
     * Ordersetting多条件分页查询
     * @param ordersetting
     * @param page
     * @param size
     * @return
     */
    PageInfo<Ordersetting> findPage(Ordersetting ordersetting, int page, int size);

    /***
     * Ordersetting分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Ordersetting> findPage(int page, int size);

    /***
     * Ordersetting多条件搜索方法
     * @param ordersetting
     * @return
     */
    List<Ordersetting> findList(Ordersetting ordersetting);

    /***
     * 删除Ordersetting
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Ordersetting数据
     * @param ordersetting
     */
    void update(Ordersetting ordersetting);

    /***
     * 新增Ordersetting
     * @param ordersetting
     */
    void add(Ordersetting ordersetting);


    void add(List<Ordersetting> list);


    /**
     * 根据ID查询Ordersetting
     * @param id
     * @return
     */
     Ordersetting findById(Integer id);

    /***
     * 查询所有Ordersetting
     * @return
     */
    List<Ordersetting> findAll();


    public List<Map> getOrderSettingByMonth(String date);


    //根据日期插入
    void insertByDate(String number, String date);


}
