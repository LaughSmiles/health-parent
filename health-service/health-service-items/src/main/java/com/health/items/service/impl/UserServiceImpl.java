package com.health.items.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.health.items.dao.RoleMapper;
import com.health.items.dao.UserMapper;
import com.health.items.pojo.Role;
import com.health.items.pojo.User;
import com.health.items.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:User业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * User条件+分页查询
     * @param user 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<User> findPage(User user, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(user);
        //执行搜索
        return new PageInfo<User>(userMapper.selectByExample(example));
    }

    /**
     * User分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<User> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<User>(userMapper.selectAll());
    }

    /**
     * User条件查询
     * @param user
     * @return
     */
    @Override
    public List<User> findList(User user){
        //构建查询条件
        Example example = createExample(user);
        //根据构建的条件查询数据
        return userMapper.selectByExample(example);
    }


    /**
     * User构建查询对象
     * @param user
     * @return
     */
    public Example createExample(User user){
        Example example=new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(user!=null){
            //
            if(!StringUtils.isEmpty(user.getId())){
                    criteria.andEqualTo("id",user.getId());
            }
            //
            if(!StringUtils.isEmpty(user.getBirthday())){
                    criteria.andEqualTo("birthday",user.getBirthday());
            }
            //
            if(!StringUtils.isEmpty(user.getGender())){
                    criteria.andEqualTo("gender",user.getGender());
            }
            //
            if(!StringUtils.isEmpty(user.getUsername())){
                    criteria.andLike("username","%"+user.getUsername()+"%");
            }
            //
            if(!StringUtils.isEmpty(user.getPassword())){
                    criteria.andEqualTo("password",user.getPassword());
            }
            //
            if(!StringUtils.isEmpty(user.getRemark())){
                    criteria.andEqualTo("remark",user.getRemark());
            }
            //
            if(!StringUtils.isEmpty(user.getStation())){
                    criteria.andEqualTo("station",user.getStation());
            }
            //
            if(!StringUtils.isEmpty(user.getTelephone())){
                    criteria.andEqualTo("telephone",user.getTelephone());
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
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改User
     * @param user
     */
    @Override
    public void update(User user){
        userMapper.updateByPrimaryKey(user);
    }

    /**
     * 增加User
     * @param user
     */
    @Override
    public void add(User user){
        userMapper.insert(user);
    }

    /**
     * 根据ID查询User
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id){
        return  userMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询User全部数据
     * @return
     */
    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    public User findByUsername(String name){
        User userPoji = new User();
        userPoji.setUsername(name);
        User user = userMapper.selectOne(userPoji);
        List<Role> roles = roleMapper.findByUserId(user.getId());
        user.setRoles(roles);
        return user;
    }
}
