package com.imooc.o2o.enums;

public enum ProductCategoryStateEnum {
	SUCCESS(1, "创建成功"), INNER_ERROR(-1001, "操作失败"), EMPTY_LIST(-1002, "添加数少于1");

	private int state;

	private String stateInfo;

	private ProductCategoryStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	//该方法效果：假如index为1，那么返回的便为SUCCESS
	public static ProductCategoryStateEnum stateOf(int index) {
		for (ProductCategoryStateEnum state : values()) {
			//values() 返回枚举的所有枚举值,这个方法会返回包括所有枚举变量的数组
			if (state.getState() == index) {
				//System.out.println(state);
				return state;
			}
			
		}
		return null;
	}
/*	//測試
	public static void main(String[] args) {
		ProductCategoryStateEnum[] values = ProductCategoryStateEnum.values();
		for (ProductCategoryStateEnum pcs : values) {
			System.out.println(pcs + "--" + pcs.getState() + "--"
					+ pcs.getStateInfo());
			System.out.println("=============");
		}
		//輸出：SUCCESS--1--创建成功
		//=============
		//INNER_ERROR---1001--操作失败
		//=============
		//EMPTY_LIST---1002--添加数少于1
		//=============
		ProductCategoryStateEnum pcs = ProductCategoryStateEnum.stateOf(-1001);
		System.out.println(pcs);//輸出：INNER_ERROR
		System.out.println(ProductCategoryStateEnum.SUCCESS);//輸出：SUCCESS
	}*/
}
