package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.ShopCategory;

/**
* @author xf
* @version 2018年8月1日 下午2:53:04
*/
public interface ShopCategoryDao {
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition")
	ShopCategory ShopCategoryCondition);
}
