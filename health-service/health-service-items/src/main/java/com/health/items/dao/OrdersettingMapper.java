package com.health.items.dao;

import com.health.items.pojo.Ordersetting;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Ordersetting的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface OrdersettingMapper extends Mapper<Ordersetting> {
    @Select("SELECT * FROM tb_ordersetting where orderDate BETWEEN #{start} and #{end}")
    List<Ordersetting> getOrdersettingByBetweenDatatime(String start, String end);

}
