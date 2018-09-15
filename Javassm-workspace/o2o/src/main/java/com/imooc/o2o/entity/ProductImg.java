package com.imooc.o2o.entity;

import java.util.Date;
/**
 * 详情图片
 * @author xf
 * @version 2018-07-21
 */
public class ProductImg {
	//详情图片id
	private Long productImgId;
	//图片地址
	private String imgAddr;
	//图片说明
	private String imgDesc;
	//权重
	private Integer priority;
	//创建时间
	private Date createTime;
	//商品id 外键
	private Long productId;
	
	public Long getProductImgId() {
		return productImgId;
	}
	public void setProductImgId(Long productImgId) {
		this.productImgId = productImgId;
	}
	public String getImgAddr() {
		return imgAddr;
	}
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}
	public String getImgDesc() {
		return imgDesc;
	}
	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
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
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
}
