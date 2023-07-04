package com.health.items.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
/****
 * @Author:shenkunlin
 * @Description:Menu构建
 * @Date 2019/6/14 19:13
 *****/
@ApiModel(description = "Menu",value = "Menu")
@Table(name="tb_menu")
public class Menu implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "name")
	private String name;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "linkUrl")
	private String linkUrl;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "path")
	private String path;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "priority")
	private Integer priority;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "icon")
	private String icon;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "description")
	private String description;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "parentMenuId")
	private Integer parentMenuId;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "level")
	private Integer level;//



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
	public String getLinkUrl() {
		return linkUrl;
	}

	//set方法
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	//get方法
	public String getPath() {
		return path;
	}

	//set方法
	public void setPath(String path) {
		this.path = path;
	}
	//get方法
	public Integer getPriority() {
		return priority;
	}

	//set方法
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	//get方法
	public String getIcon() {
		return icon;
	}

	//set方法
	public void setIcon(String icon) {
		this.icon = icon;
	}
	//get方法
	public String getDescription() {
		return description;
	}

	//set方法
	public void setDescription(String description) {
		this.description = description;
	}
	//get方法
	public Integer getParentMenuId() {
		return parentMenuId;
	}

	//set方法
	public void setParentMenuId(Integer parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	//get方法
	public Integer getLevel() {
		return level;
	}

	//set方法
	public void setLevel(Integer level) {
		this.level = level;
	}


}
