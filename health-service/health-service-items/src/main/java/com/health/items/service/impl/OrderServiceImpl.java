package com.health.items.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.health.items.dao.MemberMapper;
import com.health.items.dao.OrderMapper;
import com.health.items.dao.OrdersettingMapper;
import com.health.items.dao.SetmealMapper;
import com.health.items.pojo.Member;
import com.health.items.pojo.Order;
import com.health.items.pojo.Ordersetting;
import com.health.items.pojo.Setmeal;
import com.health.items.service.OrderService;
import constant.MessageConstant;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import utils.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:Order业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private OrdersettingMapper ordersettingMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    public Order findById(Integer id){
        Order order = orderMapper.selectByPrimaryKey(id);
        Member member = memberMapper.selectByPrimaryKey(order.getMemberId());
        order.setMember(member);
        Setmeal setmeal = setmealMapper.selectByPrimaryKey(order.getSetmealId());
        order.setSetmeal(setmeal);
        return order;
    }

    //体检预约
    @Override
    public Result order(Map map) throws Exception {
        //检查当前日期是否进行了预约设置
        String orderDate = (String) map.get("orderDate");
        Date date = DateUtils.parseString2Date(orderDate);

        Ordersetting ordersettingPojo = new Ordersetting();
        ordersettingPojo.setOrderDate(date);
        Ordersetting ordersetting = ordersettingMapper.selectOne(ordersettingPojo);
        if(ordersetting == null){
            return new Result(false, StatusCode.ERROR, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }

        //检查预约日期是否预约已满
        Integer number = ordersetting.getNumber();
        Integer reservations = ordersetting.getReservations();
        if(reservations > number){
            return new Result(false, StatusCode.ERROR, MessageConstant.ORDER_FULL);
        }

        //检查当前用户是否为会员，根据手机号判断
        String telephone = (String) map.get("telephone");
        Member memberPojo = new Member();
        memberPojo.setPhoneNumber(telephone);
        Member member = memberMapper.selectOne(memberPojo);

        //防止重复预约
        if(member != null){
            Integer id = member.getId();
            Integer setmealId = Integer.valueOf((String) map.get("setmealId"));
            Order orderPojo = new Order(null, id, date, null, null, setmealId);
            Order order = orderMapper.selectOne(orderPojo);
            if(order != null){
                return new Result(false, StatusCode.ERROR, MessageConstant.HAS_ORDERED);
            }

        }

        //可以预约，设置预约人数加一
        ordersetting.setReservations(ordersetting.getReservations() + 1);
        ordersettingMapper.updateByPrimaryKey(ordersetting);

        //当前用户不是会员，需要添加到会员表
        if(member == null){
            member = new Member();
            member.setName((String) map.get("name"));
            member.setPhoneNumber(telephone);
            member.setIdCard((String) map.get("idCard"));
            member.setSex((String) map.get("sex"));
            member.setRegTime(new Date());
            memberMapper.insert(member);
        }

        //保存预约信息到预约表
        Order order = new Order();
        order.setMemberId(member.getId());
        order.setOrderDate(date);
        order.setOrderType((String)map.get("orderType"));
        order.setOrderStatus(Order.ORDERSTATUS_NO);
        order.setSetmealId(Integer.valueOf((String) map.get("setmealId")));
        orderMapper.insert(order);

        return new Result(true, StatusCode.OK, MessageConstant.ORDER_SUCCESS, order.getId());
    }

    /**
     * Order条件+分页查询
     * @param order 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Order> findPage(Order order, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(order);
        //执行搜索
        return new PageInfo<Order>(orderMapper.selectByExample(example));
    }

    /**
     * Order分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Order> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Order>(orderMapper.selectAll());
    }

    /**
     * Order条件查询
     * @param order
     * @return
     */
    @Override
    public List<Order> findList(Order order){
        //构建查询条件
        Example example = createExample(order);
        //根据构建的条件查询数据
        return orderMapper.selectByExample(example);
    }


    /**
     * Order构建查询对象
     * @param order
     * @return
     */
    public Example createExample(Order order){
        Example example=new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        if(order!=null){
            //
            if(!StringUtils.isEmpty(order.getId())){
                    criteria.andEqualTo("id",order.getId());
            }
            // 员会id
            if(!StringUtils.isEmpty(order.getMemberId())){
                    criteria.andEqualTo("memberId",order.getMemberId());
            }
            // 约预日期
            if(!StringUtils.isEmpty(order.getOrderDate())){
                    criteria.andEqualTo("orderDate",order.getOrderDate());
            }
            // 约预类型 电话预约/微信预约
            if(!StringUtils.isEmpty(order.getOrderType())){
                    criteria.andEqualTo("orderType",order.getOrderType());
            }
            // 预约状态（是否到诊）
            if(!StringUtils.isEmpty(order.getOrderStatus())){
                    criteria.andEqualTo("orderStatus",order.getOrderStatus());
            }
            // 餐套id
            if(!StringUtils.isEmpty(order.getSetmealId())){
                    criteria.andEqualTo("setmealId",order.getSetmealId());
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
        orderMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Order
     * @param order
     */
    @Override
    public void update(Order order){
        orderMapper.updateByPrimaryKey(order);
    }

    /**
     * 增加Order
     * @param order
     */
    @Override
    public void add(Order order){
        orderMapper.insert(order);
    }


    /**
     * 查询Order全部数据
     * @return
     */
    @Override
    public List<Order> findAll() {
        return orderMapper.selectAll();
    }
}
