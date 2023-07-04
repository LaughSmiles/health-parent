package com.health.items.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
/****
 * @Author:shenkunlin
 * @Description:Checkitem构建
 * @Date 2019/6/14 19:13
 *****/
@ApiModel(description = "Checkitem",value = "Checkitem")
@Table(name="tb_checkitem")
public class Checkitem implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "code")
	private String code;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "name")
	private String name;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "sex")
	private String sex;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "age")
	private String age;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "price")
	private String price;//

	@ApiModelProperty(value = "查检项类型,分为检查和检验两种",required = false)
    @Column(name = "type")
	private String type;//查检项类型,分为检查和检验两种

	@ApiModelProperty(value = "",required = false)
    @Column(name = "attention")
	private String attention;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "remark")
	private String remark;//



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public String getCode() {
		return code;
	}

	//set方法
	public void setCode(String code) {
		this.code = code;
	}
	//get方法
	public String getName() {
		return name;
	}

	//set方法
	public void setName(String name) {
		this.name = name;
	}
	//get方法
	public String getSex() {
		return sex;
	}

	//set方法
	public void setSex(String sex) {
		this.sex = sex;
	}
	//get方法
	public String getAge() {
		return age;
	}

	//set方法
	public void setAge(String age) {
		this.age = age;
	}
	//get方法
	public String getPrice() {
		return price;
	}

	//set方法
	public void setPrice(String price) {
		this.price = price;
	}
	//get方法
	public String getType() {
		return type;
	}

	//set方法
	public void setType(String type) {
		this.type = type;
	}
	//get方法
	public String getAttention() {
		return attention;
	}

	//set方法
	public void setAttention(String attention) {
		this.attention = attention;
	}
	//get方法
	public String getRemark() {
		return remark;
	}

	//set方法
	public void setRemark(String remark) {
		this.remark = remark;
	}


}
