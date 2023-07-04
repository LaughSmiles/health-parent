package com.health.items.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.health.items.dao.CheckgroupMapper;
import com.health.items.pojo.Checkgroup;
import com.health.items.service.CheckgroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:Checkgroup业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
@Slf4j
public class CheckgroupServiceImpl implements CheckgroupService {

    @Autowired
    private CheckgroupMapper checkgroupMapper;


    /**
     * Checkgroup条件+分页查询
     * @param checkgroup 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Checkgroup> findPage(Checkgroup checkgroup, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(checkgroup);
        //执行搜索
        return new PageInfo<Checkgroup>(checkgroupMapper.selectByExample(example));
    }

    /**
     * Checkgroup分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Checkgroup> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Checkgroup>(checkgroupMapper.selectAll());
    }

    /**
     * Checkgroup条件查询
     * @param checkgroup
     * @return
     */
    @Override
    public List<Checkgroup> findList(Checkgroup checkgroup){
        //构建查询条件
        Example example = createExample(checkgroup);
        //根据构建的条件查询数据
        return checkgroupMapper.selectByExample(example);
    }


    /**
     * Checkgroup构建查询对象
     * @param checkgroup
     * @return
     */
    public Example createExample(Checkgroup checkgroup){
        Example example=new Example(Checkgroup.class);
        Example.Criteria criteria = example.createCriteria();
        if(checkgroup!=null){
            //
            if(!StringUtils.isEmpty(checkgroup.getId())){
                    criteria.andEqualTo("id",checkgroup.getId());
            }
            //
            if(!StringUtils.isEmpty(checkgroup.getCode())){
                    criteria.andEqualTo("code",checkgroup.getCode());
            }
            //
            if(!StringUtils.isEmpty(checkgroup.getName())){
                    criteria.andLike("name","%"+checkgroup.getName()+"%");
            }
            //
            if(!StringUtils.isEmpty(checkgroup.getHelpCode())){
                    criteria.andEqualTo("helpCode",checkgroup.getHelpCode());
            }
            //
            if(!StringUtils.isEmpty(checkgroup.getSex())){
                    criteria.andEqualTo("sex",checkgroup.getSex());
            }
            //
            if(!StringUtils.isEmpty(checkgroup.getRemark())){
                    criteria.andEqualTo("remark",checkgroup.getRemark());
            }
            //
            if(!StringUtils.isEmpty(checkgroup.getAttention())){
                    criteria.andEqualTo("attention",checkgroup.getAttention());
            }
        }
        return example;
    }




    /**
     * 增加Checkgroup
     * @param checkgroup
     */
    @Override
    public void add(Checkgroup checkgroup){
        checkgroupMapper.insert(checkgroup);
    }

    /**
     * 根据ID查询Checkgroup
     * @param id
     * @return
     */
    @Override
    public Checkgroup findById(Integer id){
        return  checkgroupMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Checkgroup全部数据
     * @return
     */
    @Override
    public List<Checkgroup> findAll() {
        return checkgroupMapper.selectAll();
    }

    @Override
    public void add(Integer[] checkitemIds, Checkgroup checkgroup) {
        checkgroupMapper.insert(checkgroup);

        if(checkitemIds != null && checkitemIds.length > 0){
            Arrays.stream(checkitemIds).forEach((s) -> {
                checkgroupMapper.insertCheckgroupRalatedCheckItemById(checkgroup.getId(), s);
            });
        }
    }

    /**
     * 修改Checkgroup
     * @param checkgroup
     */
    @Override
    public void update(Checkgroup checkgroup ,Integer[] checkitemIds){
        checkgroupMapper.updateByPrimaryKey(checkgroup);
        //删除所有关联
        checkgroupMapper.deleteCheckGourpRalatedCheckItemByCheckGourpId(checkgroup.getId());
        //添加检查项数据

        if(checkitemIds != null && checkitemIds.length > 0){
            Arrays.stream(checkitemIds).forEach((s) -> {
                checkgroupMapper.insertCheckgroupRalatedCheckItemById(checkgroup.getId(), s);
            });
        }
    }

    @Override
    public Integer[] findCheckItemids(Integer id) {
        return checkgroupMapper.getCheckItemsIdsByCheckGroupId(id);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        //如果被检查套餐管理，不能删除
        Integer raletedCount = checkgroupMapper.getCheckGroupRaletedSetmealCountByCheckGroupId(id);
        if(raletedCount > 0){
            throw new RuntimeException("当前检查组被检查套餐管理");
        }

        checkgroupMapper.deleteByPrimaryKey(id);
        //删除与检查项的关联
        checkgroupMapper.deleteCheckGourpRalatedCheckItemByCheckGourpId(id);
    }
}
