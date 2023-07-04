package com.health.items.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.health.items.dao.CheckitemMapper;
import com.health.items.pojo.Checkitem;
import com.health.items.service.CheckitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:Checkitem业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class CheckitemServiceImpl implements CheckitemService {

    @Autowired
    private CheckitemMapper checkitemMapper;


    /**
     * Checkitem条件+分页查询
     * @param checkitem 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Checkitem> findPage(Checkitem checkitem, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(checkitem);
        //执行搜索
        return new PageInfo<Checkitem>(checkitemMapper.selectByExample(example));
    }

    /**
     * Checkitem分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Checkitem> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Checkitem>(checkitemMapper.selectAll());
    }

    /**
     * Checkitem条件查询
     * @param checkitem
     * @return
     */
    @Override
    public List<Checkitem> findList(Checkitem checkitem){
        //构建查询条件
        Example example = createExample(checkitem);
        //根据构建的条件查询数据
        return checkitemMapper.selectByExample(example);
    }


    /**
     * Checkitem构建查询对象
     * @param checkitem
     * @return
     */
    public Example createExample(Checkitem checkitem){
        Example example=new Example(Checkitem.class);
        Example.Criteria criteria = example.createCriteria();
        if(checkitem!=null){
            //
            if(!StringUtils.isEmpty(checkitem.getId())){
                    criteria.andEqualTo("id",checkitem.getId());
            }
            //
            if(!StringUtils.isEmpty(checkitem.getCode())){
                    criteria.andEqualTo("code",checkitem.getCode());
            }
            //
            if(!StringUtils.isEmpty(checkitem.getName())){
                    criteria.andLike("name","%"+checkitem.getName()+"%");
            }
            //
            if(!StringUtils.isEmpty(checkitem.getSex())){
                    criteria.andEqualTo("sex",checkitem.getSex());
            }
            //
            if(!StringUtils.isEmpty(checkitem.getAge())){
                    criteria.andEqualTo("age",checkitem.getAge());
            }
            //
            if(!StringUtils.isEmpty(checkitem.getPrice())){
                    criteria.andEqualTo("price",checkitem.getPrice());
            }
            // 查检项类型,分为检查和检验两种
            if(!StringUtils.isEmpty(checkitem.getType())){
                    criteria.andEqualTo("type",checkitem.getType());
            }
            //
            if(!StringUtils.isEmpty(checkitem.getAttention())){
                    criteria.andEqualTo("attention",checkitem.getAttention());
            }
            //
            if(!StringUtils.isEmpty(checkitem.getRemark())){
                    criteria.andEqualTo("remark",checkitem.getRemark());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        Integer count = checkitemMapper.findCheckgoupRelateCountByid(id);
        if(count > 0){
            throw new RuntimeException("当前检查项被引用，不可删除");
        }
        checkitemMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Checkitem
     * @param checkitem
     */
    @Override
    public void update(Checkitem checkitem){
        checkitemMapper.updateByPrimaryKey(checkitem);
    }

    /**
     * 增加Checkitem
     * @param checkitem
     */
    @Override
    public void add(Checkitem checkitem){
        checkitemMapper.insert(checkitem);
    }

    /**
     * 根据ID查询Checkitem
     * @param id
     * @return
     */
    @Override
    public Checkitem findById(Integer id){
        return  checkitemMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Checkitem全部数据
     * @return
     */
    @Override
    public List<Checkitem> findAll() {
        return checkitemMapper.selectAll();
    }
}
