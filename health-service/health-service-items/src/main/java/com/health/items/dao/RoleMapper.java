package com.health.items.dao;

import com.health.items.pojo.Role;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Role的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface RoleMapper extends Mapper<Role> {

    @Select("SELECT r.* FROM tb_role r,tb_user_role ur where r.id = ur.role_id and ur.user_id = #{id}")
    List<Role> findByUserId(Integer id);
}
