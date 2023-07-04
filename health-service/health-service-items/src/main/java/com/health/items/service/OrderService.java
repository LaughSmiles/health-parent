package com.health.items.service;

import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Order;
import entity.Result;

import java.util.List;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:Order业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface OrderService {

    Order findById(Integer id);

    //预约方法
    Result order(Map map) throws Exception;


    /***
     * Order多条件分页查询
     * @param order
     * @param page
     * @param size
     * @return
     */
    PageInfo<Order> findPage(Order order, int page, int size);

    /***
     * Order分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Order> findPage(int page, int size);

    /***
     * Order多条件搜索方法
     * @param order
     * @return
     */
    List<Order> findList(Order order);

    /***
     * 删除Order
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Order数据
     * @param order
     */
    void update(Order order);

    /***
     * 新增Order
     * @param order
     */
    void add(Order order);

    /***
     * 查询所有Order
     * @return
     */
    List<Order> findAll();
}
