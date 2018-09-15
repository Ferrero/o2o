package com.imooc.o2o.enums;
/**
 *在实现Service层之前 需要考虑
 *在操作Shop时候 会有个操作状态（成功或者失败） 并且需要返回给Controller层
 *创建Enum类ShopStateEnum
 *用来表示店铺的状态
 * @author Rong
 *
 */
public enum ShopStateEnum {
	CHECK(0,"审核中"),OFFLINE(-1,"非法店铺"),SUCCESS(1,"操作成功"),
	PASS(2,"通过认证"),INNER_ERROR(-1001,"内部系统错误"),
	NULL_SHOPID(-1002,"shopId为空"),NULL_SHOP(-1003,"shop信息为空");
	private int state;
	private String stateInfo;
	
	//在枚举类中构造器是私有的， 不希望第三方来改Enum的值
	private ShopStateEnum(int state,String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	/**
	 * 依据传入的state返回相应的enum值;
	 */
	public static ShopStateEnum stateOf(int state) {
		for(ShopStateEnum stateEnum : values()) {
			//values() 返回枚举的所有枚举值
			if(stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
	}
	public int getState() {
		return state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	
}
