package com.health.items.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Checkgroup构建
 * @Date 2019/6/14 19:13
 *****/
@ApiModel(description = "Checkgroup",value = "Checkgroup")
@Table(name="tb_checkgroup")
public class Checkgroup implements Serializable{

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
    @Column(name = "helpCode")
	private String helpCode;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "sex")
	private String sex;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "remark")
	private String remark;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "attention")
	private String attention;//


	private List<Checkitem> checkitemList;



	public Checkgroup() {
	}

	public Checkgroup(Integer id, String code, String name, String helpCode, String sex, String remark, String attention) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.helpCode = helpCode;
		this.sex = sex;
		this.remark = remark;
		this.attention = attention;
	}


	public List<Checkitem> getCheckitemList() {
		return checkitemList;
	}

	public void setCheckitemList(List<Checkitem> checkitemList) {
		this.checkitemList = checkitemList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	@Override
	public String toString() {
		return "Checkgroup{" +
				"id=" + id +
				", code='" + code + '\'' +
				", name='" + name + '\'' +
				", helpCode='" + helpCode + '\'' +
				", sex='" + sex + '\'' +
				", remark='" + remark + '\'' +
				", attention='" + attention + '\'' +
				'}';
	}
}

