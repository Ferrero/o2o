package com.imooc.o2o.entity;

import java.util.Date;
/**
 * 区域实体类
 * @author xf
 * @version 2018-07-21
 */
public class Area {
	//ID
	private Integer areaId;
	//区域名称
	private String areaName;
	//权重，区域显示的优先级，越大排位越靠前
	private Integer priority;
	//区域创建时间
	private Date createTime;
	//区域更新时间
	private Date lastEditTime;
	
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	
}
