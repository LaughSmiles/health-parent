package com.health.items.dao;

import com.health.items.pojo.Setmeal;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:Setmeal的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface SetmealMapper extends Mapper<Setmeal> {
    @Insert("insert into tb_setmeal_checkgroup(setmeal_id, checkgroup_id) values(#{checksetmealId},#{checkgroupId})")
    void insertChecksetmealRalatedCheckgroupById(Integer checksetmealId, Integer checkgroupId);

    @Select("select checkgroup_id from tb_setmeal_checkgroup where setmeal_id = #{id}")
    Integer[] getCheckGroupIdBySetmealId(Integer id);

    @Delete("DELETE from tb_setmeal_checkgroup where setmeal_id = #{setmealId}")
    void deleteSetmealRalatedCheckGroupBySetmealId(Integer setmeal);

    @Select("select s.name,count(o.id) as value\n" +
            "from tb_order o ,tb_setmeal s\n" +
            "where o.setmeal_id = s.id\n" +
            "group by s.name")
    List<Map<String,String>> findSetmealCount();
}
