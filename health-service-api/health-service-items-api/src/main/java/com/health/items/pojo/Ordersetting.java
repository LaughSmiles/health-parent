package com.health.items.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
/****
 * @Author:shenkunlin
 * @Description:Ordersetting构建
 * @Date 2019/6/14 19:13
 *****/
@ApiModel(description = "Ordersetting",value = "Ordersetting")
@Table(name="tb_ordersetting")
public class Ordersetting implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "约预日期",required = false)
    @Column(name = "orderDate")
	private Date orderDate;//约预日期

	@ApiModelProperty(value = "可预约人数",required = false)
    @Column(name = "number")
	private Integer number;//可预约人数

	@ApiModelProperty(value = "已预约人数",required = false)
    @Column(name = "reservations")
	private Integer reservations;//已预约人数


	public Ordersetting() {
	}

	public Ordersetting(Date orderDate, Integer number) {
		this.orderDate = orderDate;
		this.number = number;
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
	public Date getOrderDate() {
		return orderDate;
	}

	//set方法
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	//get方法
	public Integer getNumber() {
		return number;
	}

	//set方法
	public void setNumber(Integer number) {
		this.number = number;
	}
	//get方法
	public Integer getReservations() {
		return reservations;
	}

	@Override
	public String toString() {
		return "Ordersetting{" +
				"id=" + id +
				", orderDate=" + orderDate +
				", number=" + number +
				", reservations=" + reservations +
				'}';
	}

	//set方法
	public void setReservations(Integer reservations) {
		this.reservations = reservations;
	}


}
