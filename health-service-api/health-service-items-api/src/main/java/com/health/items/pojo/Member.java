package com.health.items.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
/****
 * @Author:shenkunlin
 * @Description:Member构建
 * @Date 2019/6/14 19:13
 *****/
@ApiModel(description = "Member",value = "Member")
@Table(name="tb_member")
public class Member implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "fileNumber")
	private String fileNumber;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "name")
	private String name;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "sex")
	private String sex;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "idCard")
	private String idCard;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "phoneNumber")
	private String phoneNumber;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "regTime")
	private Date regTime;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "password")
	private String password;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "email")
	private String email;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "birthday")
	private Date birthday;//

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
	public String getFileNumber() {
		return fileNumber;
	}

	//set方法
	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
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
	public String getIdCard() {
		return idCard;
	}

	//set方法
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	//get方法
	public String getPhoneNumber() {
		return phoneNumber;
	}

	//set方法
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	//get方法
	public Date getRegTime() {
		return regTime;
	}

	//set方法
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	//get方法
	public String getPassword() {
		return password;
	}

	//set方法
	public void setPassword(String password) {
		this.password = password;
	}
	//get方法
	public String getEmail() {
		return email;
	}

	//set方法
	public void setEmail(String email) {
		this.email = email;
	}
	//get方法
	public Date getBirthday() {
		return birthday;
	}

	//set方法
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
