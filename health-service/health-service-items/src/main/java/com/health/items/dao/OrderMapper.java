package com.health.items.dao;
import com.health.items.pojo.Order;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:Order的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface OrderMapper extends Mapper<Order> {
    
    @Select("select count(id) from tb_order where orderDate = #{today}")
    Integer findOrderCountByDate(String today);

    @Select("select count(id) from tb_order where orderDate >= #{thisWeekMonday}")
    Integer findOrderCountAfterDate(String thisWeekMonday);

    @Select("select count(id) from tb_order where orderDate = #{value} and orderStatus = '已到诊'")
    Integer findVisitsCountByDate(String value);

    @Select("select count(id) from tb_order where orderDate >= #{value} and orderStatus = '已到诊'")
    Integer findVisitsCountAfterDate(String value);

    @Select("select \n" +
            "  s.name,\n" +
            "\tcount(o.id) setmeal_count,\n" +
            "\tcount(o.id)/(select count(id) from tb_order) proportion\n" +
            " from tb_order o inner join tb_setmeal s on s.id = o.setmeal_id\n" +
            " group by o.setmeal_id\n" +
            " order by setmeal_count desc\n" +
            "limit 0,4;")
    List<Map> findHotSetmeal();
}
