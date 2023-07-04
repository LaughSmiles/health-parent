package com.health.items.service;

import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Permission;

import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:Permission业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface PermissionService {

    /***
     * Permission多条件分页查询
     * @param permission
     * @param page
     * @param size
     * @return
     */
    PageInfo<Permission> findPage(Permission permission, int page, int size);

    /***
     * Permission分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Permission> findPage(int page, int size);

    /***
     * Permission多条件搜索方法
     * @param permission
     * @return
     */
    List<Permission> findList(Permission permission);

    /***
     * 删除Permission
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Permission数据
     * @param permission
     */
    void update(Permission permission);

    /***
     * 新增Permission
     * @param permission
     */
    void add(Permission permission);

    /**
     * 根据ID查询Permission
     * @param id
     * @return
     */
     Permission findById(Integer id);

    /***
     * 查询所有Permission
     * @return
     */
    List<Permission> findAll();
}
