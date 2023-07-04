package com.health.items.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:User构建
 * @Date 2019/6/14 19:13
 *****/
@ApiModel(description = "User",value = "User")
@Table(name="tb_user")
public class User implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "birthday")
	private Date birthday;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "gender")
	private String gender;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "username")
	private String username;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "password")
	private String password;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "remark")
	private String remark;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "station")
	private String station;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "telephone")
	private String telephone;//

	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
	public Date getBirthday() {
		return birthday;
	}

	//set方法
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	//get方法
	public String getGender() {
		return gender;
	}

	//set方法
	public void setGender(String gender) {
		this.gender = gender;
	}
	//get方法
	public String getUsername() {
		return username;
	}

	//set方法
	public void setUsername(String username) {
		this.username = username;
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
	public String getRemark() {
		return remark;
	}

	//set方法
	public void setRemark(String remark) {
		this.remark = remark;
	}
	//get方法
	public String getStation() {
		return station;
	}

	//set方法
	public void setStation(String station) {
		this.station = station;
	}
	//get方法
	public String getTelephone() {
		return telephone;
	}

	//set方法
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


}
