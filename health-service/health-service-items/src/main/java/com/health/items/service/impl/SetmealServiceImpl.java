package com.health.items.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.health.items.dao.CheckgroupMapper;
import com.health.items.dao.CheckitemMapper;
import com.health.items.dao.SetmealMapper;
import com.health.items.pojo.Checkgroup;
import com.health.items.pojo.Checkitem;
import com.health.items.pojo.Setmeal;
import com.health.items.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:Setmeal业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CheckgroupMapper checkgroupMapper;

    @Autowired
    private CheckitemMapper checkitemMapper;

    @Override
    public List<Map<String, String>> findSetmealCount() {
        return setmealMapper.findSetmealCount();
    }

    /**
     * Setmeal条件+分页查询
     * @param setmeal 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Setmeal> findPage(Setmeal setmeal, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(setmeal);
        //执行搜索
        return new PageInfo<Setmeal>(setmealMapper.selectByExample(example));
    }

    /**
     * Setmeal分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Setmeal> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Setmeal>(setmealMapper.selectAll());
    }

    /**
     * Setmeal条件查询
     * @param setmeal
     * @return
     */
    @Override
    public List<Setmeal> findList(Setmeal setmeal){
        //构建查询条件
        Example example = createExample(setmeal);
        //根据构建的条件查询数据
        return setmealMapper.selectByExample(example);
    }


    /**
     * Setmeal构建查询对象
     * @param setmeal
     * @return
     */
    public Example createExample(Setmeal setmeal){
        Example example=new Example(Setmeal.class);
        Example.Criteria criteria = example.createCriteria();
        if(setmeal!=null){
            //
            if(!StringUtils.isEmpty(setmeal.getId())){
                    criteria.andEqualTo("id",setmeal.getId());
            }
            //
            if(!StringUtils.isEmpty(setmeal.getName())){
                    criteria.andLike("name","%"+setmeal.getName()+"%");
            }
            //
            if(!StringUtils.isEmpty(setmeal.getCode())){
                    criteria.andEqualTo("code",setmeal.getCode());
            }
            //
            if(!StringUtils.isEmpty(setmeal.getHelpCode())){
                    criteria.andEqualTo("helpCode",setmeal.getHelpCode());
            }
            //
            if(!StringUtils.isEmpty(setmeal.getSex())){
                    criteria.andEqualTo("sex",setmeal.getSex());
            }
            //
            if(!StringUtils.isEmpty(setmeal.getAge())){
                    criteria.andEqualTo("age",setmeal.getAge());
            }
            //
            if(!StringUtils.isEmpty(setmeal.getPrice())){
                    criteria.andEqualTo("price",setmeal.getPrice());
            }
            //
            if(!StringUtils.isEmpty(setmeal.getRemark())){
                    criteria.andEqualTo("remark",setmeal.getRemark());
            }
            //
            if(!StringUtils.isEmpty(setmeal.getAttention())){
                    criteria.andEqualTo("attention",setmeal.getAttention());
            }
            //
            if(!StringUtils.isEmpty(setmeal.getImg())){
                    criteria.andEqualTo("img",setmeal.getImg());
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
        //删除图片缓存
        Setmeal setmeal = setmealMapper.selectByPrimaryKey(id);
        String img = setmeal.getImg();
        String[] uploadPath = fastdfsPathToStringArray(img);
        redisTemplate.boundSetOps("setmealPicDbResources").remove(JSON.toJSONString(uploadPath));

        setmealMapper.deleteByPrimaryKey(id);
        setmealMapper.deleteSetmealRalatedCheckGroupBySetmealId(id);
    }

    /**
     * 修改Setmeal
     * @param setmeal
     */
    @Override
    public void update(Setmeal setmeal){
        setmealMapper.updateByPrimaryKey(setmeal);
    }

    /**
     * 增加Setmeal
     * @param setmeal
     */
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds){
        setmealMapper.insert(setmeal);

        String img = setmeal.getImg();
        if(img != null && img.length() > 0){
            String[] uploadPath = fastdfsPathToStringArray(img);
            //上传redis服务器
            redisTemplate.boundSetOps("setmealPicDbResources").add(JSON.toJSONString(uploadPath));
        }

        //插入关联
        if(checkgroupIds != null && checkgroupIds.length > 0){
            Arrays.stream(checkgroupIds).forEach((s) -> {
                setmealMapper.insertChecksetmealRalatedCheckgroupById(setmeal.getId(), s);
            });
        }
    }

    public String[] fastdfsPathToStringArray(String path){
        String[] uploadPath = new String[2];
        if(path != null && path.length() > 0){
            //group1
            String[] split = path.split("/");
            uploadPath[0] = split[3];
            //M00/00/00/wKhNhGSZY1eABTXQAANbrSz23ko256.JPG
            int startIndex = path.lastIndexOf("/");
            String filePath = path.substring(startIndex - 9);
            uploadPath[1] = filePath;
        }
        return uploadPath;
    }

    /**
     * 根据ID查询Setmeal
     * @param id
     * @return
     */
    @Override
    public Setmeal findById(Integer id){
        Setmeal setmeal = setmealMapper.selectByPrimaryKey(id);
        List<Checkgroup> checkgroups = checkgroupMapper.selectCheckGourpBySetmealId(setmeal.getId());
        for (Checkgroup checkgroup : checkgroups) {
            List<Checkitem> checkitems = checkitemMapper.selectCheckItemByCheckGourpId(checkgroup.getId());
            checkgroup.setCheckitemList(checkitems);
        }
        setmeal.setCheckgroups(checkgroups);
        return  setmeal;
    }

    /**
     * 查询Setmeal全部数据
     * @return
     */
    @Override
    public List<Setmeal> findAll() {
        List<Setmeal> setmeals = setmealMapper.selectAll();
        for (Setmeal setmeal : setmeals) {
            List<Checkgroup> checkgroups = checkgroupMapper.selectCheckGourpBySetmealId(setmeal.getId());
            for (Checkgroup checkgroup : checkgroups) {
                List<Checkitem> checkitems = checkitemMapper.selectCheckItemByCheckGourpId(checkgroup.getId());
                checkgroup.setCheckitemList(checkitems);
            }
            setmeal.setCheckgroups(checkgroups);
        }
        return setmeals;
    }

    @Override
    public Integer[] findCheckGroupids(Integer id) {
        return setmealMapper.getCheckGroupIdBySetmealId(id);
    }

    @Override
    public void update(Setmeal setmeal, Integer[] checkGroupIds) {
        //判断setmeal中的图片是否被修改过
            //修改了则删除 setmealPicDbResources 中的图片信息
            //添加新的图片缓存
        Setmeal before = setmealMapper.selectByPrimaryKey(setmeal.getId());
        String beforeImg = before.getImg();
        //判断setmeal中的图片是否被修改过
        if(beforeImg != null &&!beforeImg.equals(setmeal.getImg())){
            //修改了则删除 setmealPicDbResources中的图片信息
            String[] beforeUploadPath = fastdfsPathToStringArray(beforeImg);
            redisTemplate.boundSetOps("setmealPicDbResources").remove(JSON.toJSONString(beforeUploadPath));
            //添加新的图片缓存
            String[] afterUploadPath = fastdfsPathToStringArray(setmeal.getImg());
            redisTemplate.boundSetOps("setmealPicDbResources").add(JSON.toJSONString(afterUploadPath));
        }

        //根据id更新setmeal
        setmealMapper.updateByPrimaryKey(setmeal);
        //删除所有与setmeal关联的checkGourp信息
        setmealMapper.deleteSetmealRalatedCheckGroupBySetmealId(setmeal.getId());
        //插入setmeal和checkGroup的关联信息

        if(checkGroupIds != null && checkGroupIds.length >0){
            for (Integer checkGroupId : checkGroupIds) {
                setmealMapper.insertChecksetmealRalatedCheckgroupById(setmeal.getId(), checkGroupId);
            }
        }
    }
}
