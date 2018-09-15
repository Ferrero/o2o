package com.imooc.o2o.dto;

import java.util.List;

import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.enums.ProductCategoryStateEnum;

/**
* @author xf
* 
* @ClassName: ProductCategoryExecution
* 
* @Description: 封装操作ProductCategory的返回结果，包括操作状态和ProductCategory信息
* 
* @version 2018年8月14日 上午11:27:14
*/
public class ProductCategoryExecution {
	//结果状态
	private int state;
	//状态标识
	private String stateInfo;
	// 因为是批量操作,所以使用List
	private List<ProductCategory> productCategoryList;
	
	public ProductCategoryExecution() {
		
	}
	//操作失败时候使用的构造器,仅包含状态和状态描述即可
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum ){
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	//操作成功时候使用的构造器,返回操作状态和ProductCategory集合
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productCategoryList = productCategoryList;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}
	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}
	
/*	//測試
	public static void main(String[] args) {
		ProductCategoryExecution pc = new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
		System.out.println(pc.state + "," +  pc.stateInfo);//1,创建成功
		System.out.println(ProductCategoryStateEnum.SUCCESS);//SUCCESS
		System.out.println(ProductCategoryStateEnum.SUCCESS.getState() + ","
		+ ProductCategoryStateEnum.SUCCESS.getStateInfo());//1,创建成功
	}*/
}
