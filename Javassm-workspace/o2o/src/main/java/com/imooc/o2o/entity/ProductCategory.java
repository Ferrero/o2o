package com.imooc.o2o.entity;

import java.util.Date;
/**
 * 商品类别
 * @author xf
 *
 */
public class ProductCategory {
	//ID
	private Long productCategoryId;
	//店铺id 外键   没有通过shop实体类的方式定义该变量，是因为获取ProductCategory店铺商品类别时，
	//并不需要获取shop实体类除了shopId的信息,相反我们会通过shopId选出对应的商品类别
	private Long shopId;
	//商品类别名称
	private String productCategoryName;
	//权重，优先级
	private Integer priority;
	//创建时间
	private Date createTime;
	
	public Long getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
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

}
