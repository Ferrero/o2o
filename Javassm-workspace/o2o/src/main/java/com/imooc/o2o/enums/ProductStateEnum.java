package com.imooc.o2o.enums;
/**
* @author xf
* @version 2018年8月21日 下午5:50:58
*/
public enum ProductStateEnum {
	OFFLINE(0,"下架"),SUCCESS(1,"创建成功"),INNER_ERROR(-1001,"内部系统错误" )
	,NULL_PRODUCT(-1002,"商品信息为空"),NUll_PRODUCTID(-1003,"商品id为空");
	private int state;
	private String stateInfo;
	
	private ProductStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
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
	
	
	
}
