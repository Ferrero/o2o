package com.imooc.o2o.service;

import java.util.List;

import com.imooc.o2o.entity.ShopCategory;

/**
* @author xf
* @version 2018年8月1日 下午5:15:18
*/
public interface ShopCategoryService {
	/**
	 * 根据查询条件获取商品列表
	 * @param shopCategoryCondition
	 * @return
	 */
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
