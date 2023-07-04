package com.health.items.dao;

import com.health.items.pojo.Checkitem;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Checkitem的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface CheckitemMapper extends Mapper<Checkitem> {
    //根据检查项id，去查询和检查组的关联个数
    @Select("SELECT count(*) FROM tb_checkgroup_checkitem where checkitem_id = #{id};")
    Integer findCheckgoupRelateCountByid(Integer id);

    @Select("SELECT c.* FROM tb_checkitem c, tb_checkgroup_checkitem cg where c.id = cg.checkitem_id " +
            "and cg.checkgroup_id = #{id}")
    List<Checkitem> selectCheckItemByCheckGourpId(Integer id);
}
