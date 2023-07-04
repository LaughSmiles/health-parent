package com.health.items.dao;

import com.health.items.pojo.Checkgroup;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Checkgroup的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface CheckgroupMapper extends Mapper<Checkgroup> {
    @Insert("insert into tb_checkgroup_checkitem(checkgroup_id, checkitem_id) values(#{checkgroupId},#{checkItemId});")
    void insertCheckgroupRalatedCheckItemById(Integer checkgroupId, Integer checkItemId);

    @Delete("DELETE from tb_checkgroup_checkitem where checkgroup_id = #{checkGoupId}")
    void deleteCheckGourpRalatedCheckItemByCheckGourpId(Integer checkGoupId);

    @Select("select count(*) from tb_setmeal_checkgroup where checkgroup_id = #{id}")
    Integer getCheckGroupRaletedSetmealCountByCheckGroupId(Integer id);

    @Select("select checkitem_id from tb_checkgroup_checkitem where checkgroup_id = #{id}")
    Integer[] getCheckItemsIdsByCheckGroupId(Integer id);

    @Select("select tb_checkgroup.* from tb_checkgroup,tb_setmeal_checkgroup where tb_checkgroup.id = tb_setmeal_checkgroup." +
            "checkgroup_id and tb_setmeal_checkgroup.setmeal_id = 5;")
    List<Checkgroup> selectCheckGourpBySetmealId(Integer id);
}
