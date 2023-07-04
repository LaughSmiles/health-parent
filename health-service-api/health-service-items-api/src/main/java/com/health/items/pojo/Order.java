package com.health.items.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
/****
 * @Author:shenkunlin
 * @Description:Order构建
 * @Date 2019/6/14 19:13
 *****/
@ApiModel(description = "Order",value = "Order")
@Table(name="tb_order")
public class Order implements Serializable{
	public static final String ORDERTYPE_TELEPHONE = "电话预约";
	public static final String ORDERTYPE_WEIXIN = "微信预约";
	public static final String ORDERSTATUS_YES = "已到诊";
	public static final String ORDERSTATUS_NO = "未到诊";

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "员会id",required = false)
    @Column(name = "member_id")
	private Integer memberId;//员会id

	private Member member;

		@ApiModelProperty(value = "约预日期",required = false)
    @Column(name = "orderDate")
	private Date orderDate;//约预日期

	@ApiModelProperty(value = "约预类型 电话预约/微信预约",required = false)
    @Column(name = "orderType")
	private String orderType;//约预类型 电话预约/微信预约

	@ApiModelProperty(value = "预约状态（是否到诊）",required = false)
    @Column(name = "orderStatus")
	private String orderStatus;//预约状态（是否到诊）

	@ApiModelProperty(value = "餐套id",required = false)
    @Column(name = "setmeal_id")
	private Integer setmealId;//餐套id

	private Setmeal setmeal;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Setmeal getSetmeal() {
		return setmeal;
	}

	public void setSetmeal(Setmeal setmeal) {
		this.setmeal = setmeal;
	}

	public Order() {
	}

	public Order(Integer id, Integer memberId, Date orderDate, String orderType, String orderStatus, Integer setmealId) {
		this.id = id;
		this.memberId = memberId;
		this.orderDate = orderDate;
		this.orderType = orderType;
		this.orderStatus = orderStatus;
		this.setmealId = setmealId;
	}

	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public Integer getMemberId() {
		return memberId;
	}

	//set方法
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	//get方法
	public Date getOrderDate() {
		return orderDate;
	}

	//set方法
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	//get方法
	public String getOrderType() {
		return orderType;
	}

	//set方法
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	//get方法
	public String getOrderStatus() {
		return orderStatus;
	}

	//set方法
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	//get方法
	public Integer getSetmealId() {
		return setmealId;
	}

	//set方法
	public void setSetmealId(Integer setmealId) {
		this.setmealId = setmealId;
	}


}
