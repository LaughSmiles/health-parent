package com.health.items.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.health.items.dao.OrdersettingMapper;
import com.health.items.pojo.Ordersetting;
import com.health.items.service.OrdersettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/****
 * @Author:shenkunlin
 * @Description:Ordersetting业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class OrdersettingServiceImpl implements OrdersettingService {

    @Autowired
    private OrdersettingMapper ordersettingMapper;


    /**
     * Ordersetting条件+分页查询
     * @param ordersetting 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Ordersetting> findPage(Ordersetting ordersetting, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(ordersetting);
        //执行搜索
        return new PageInfo<Ordersetting>(ordersettingMapper.selectByExample(example));
    }

    /**
     * Ordersetting分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Ordersetting> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Ordersetting>(ordersettingMapper.selectAll());
    }

    /**
     * Ordersetting条件查询
     * @param ordersetting
     * @return
     */
    @Override
    public List<Ordersetting> findList(Ordersetting ordersetting){
        //构建查询条件
        Example example = createExample(ordersetting);
        //根据构建的条件查询数据
        return ordersettingMapper.selectByExample(example);
    }


    /**
     * Ordersetting构建查询对象
     * @param ordersetting
     * @return
     */
    public Example createExample(Ordersetting ordersetting){
        Example example=new Example(Ordersetting.class);
        Example.Criteria criteria = example.createCriteria();
        if(ordersetting!=null){
            //
            if(!StringUtils.isEmpty(ordersetting.getId())){
                    criteria.andEqualTo("id",ordersetting.getId());
            }
            // 约预日期
            if(!StringUtils.isEmpty(ordersetting.getOrderDate())){
                    criteria.andEqualTo("orderDate",ordersetting.getOrderDate());
            }
            // 可预约人数
            if(!StringUtils.isEmpty(ordersetting.getNumber())){
                    criteria.andEqualTo("number",ordersetting.getNumber());
            }
            // 已预约人数
            if(!StringUtils.isEmpty(ordersetting.getReservations())){
                    criteria.andEqualTo("reservations",ordersetting.getReservations());
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
        ordersettingMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Ordersetting
     * @param ordersetting
     */
    @Override
    public void update(Ordersetting ordersetting){
        ordersettingMapper.updateByPrimaryKey(ordersetting);
    }

    /**
     * 增加Ordersetting
     * @param ordersetting
     */
    @Override
    public void add(Ordersetting ordersetting){
        ordersettingMapper.insert(ordersetting);
    }

    //批量导入预约设置数据
    public void add(List<Ordersetting> list) {
        if(list != null && list.size() > 0){
            for (Ordersetting orderSetting : list) {
                if(orderSetting.getReservations() == null){
                    orderSetting.setReservations(0);
                }

                //判断当前日期是否已经进行了预约设置
                Ordersetting ordersetting = new Ordersetting();
                ordersetting.setOrderDate(orderSetting.getOrderDate());

                Ordersetting select = ordersettingMapper.selectOne(ordersetting);
                if(select != null){
                    //已经进行了预约设置，执行更新操作
                    orderSetting.setId(select.getId());
                    ordersettingMapper.updateByPrimaryKey(orderSetting);
                }else{
                    //没有进行预约设置，执行插入操作
                    ordersettingMapper.insert(orderSetting);
                }
            }
        }
    }

    /**
     * 根据ID查询Ordersetting
     * @param id
     * @return
     */
    @Override
    public Ordersetting findById(Integer id){
        return  ordersettingMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Ordersetting全部数据
     * @return
     */
    @Override
    public List<Ordersetting> findAll() {
        return ordersettingMapper.selectAll();
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String dataStart = date + "-1 00:00:00";
        String dataEnd = date + "-31 00:00:00";

        List<Ordersetting> ordersettings = ordersettingMapper.getOrdersettingByBetweenDatatime(dataStart, dataEnd);
        List<Map> data = new ArrayList<>();
        for (Ordersetting ordersetting : ordersettings) {
            HashMap<String, Integer> ordersettingMap = new HashMap<>();
            ordersettingMap.put("date", ordersetting.getOrderDate().getDate());
            ordersettingMap.put("number", ordersetting.getNumber());
            ordersettingMap.put("reservations", ordersetting.getReservations());
            data.add(ordersettingMap);
        }

        return data;
    }

    @Override
    public void insertByDate(String number, String date) {
        String[] dateInfo = date.split("-");

        Ordersetting ordersetting = new Ordersetting();
        ordersetting.setOrderDate(new Date(Integer.valueOf(dateInfo[0])-1900,Integer.valueOf(dateInfo[1])-1
                , Integer.valueOf(dateInfo[2])));

        Ordersetting existOrNot = ordersettingMapper.selectOne(ordersetting);
        if(existOrNot == null){
            ordersetting.setNumber(Integer.valueOf(number));
            ordersettingMapper.insert(ordersetting);
        }else{
            existOrNot.setNumber(Integer.valueOf(number));
            ordersettingMapper.updateByPrimaryKey(existOrNot);
        }
    }
}
