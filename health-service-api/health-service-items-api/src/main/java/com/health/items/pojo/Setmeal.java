package com.health.items.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Setmeal构建
 * @Date 2019/6/14 19:13
 *****/
@ApiModel(description = "Setmeal",value = "Setmeal")
@Table(name="tb_setmeal")
public class Setmeal implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "name")
	private String name;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "code")
	private String code;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "helpCode")
	private String helpCode;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "sex")
	private String sex;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "age")
	private String age;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "price")
	private String price;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "remark")
	private String remark;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "attention")
	private String attention;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "img")
	private String img;//

	private List<Checkgroup> checkgroups;


	public List<Checkgroup> getCheckgroups() {
		return checkgroups;
	}

	public void setCheckgroups(List<Checkgroup> checkgroups) {
		this.checkgroups = checkgroups;
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
	public String getName() {
		return name;
	}

	//set方法
	public void setName(String name) {
		this.name = name;
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
	public String getHelpCode() {
		return helpCode;
	}

	//set方法
	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
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
	public String getRemark() {
		return remark;
	}

	//set方法
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getImg() {
		return img;
	}

	//set方法
	public void setImg(String img) {
		this.img = img;
	}


}
