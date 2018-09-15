package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * 分页查询店铺，可输入的条件有：店铺名(模糊),店铺状态,店铺类别,区域Id,owner
	 * @param shopCondition 商品（查询）条件
	 * @param rowIndex 从第几行开始取数据
	 * @param pageSize 返回的行数
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
			@Param("rowIndex") int rowIndex,@Param("pageSize") int pageSize);
	/**
	 * （根据该查询条件）返回对应店铺总数
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
	/**
	 * 通过shop id 查询店铺
	 * @param shopId
	 * @return shop
	 */
	Shop queryByShopId(Long shopId);
	/**
	 * 新增店铺
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	
	/**
	 * 更新店铺信息
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
}
