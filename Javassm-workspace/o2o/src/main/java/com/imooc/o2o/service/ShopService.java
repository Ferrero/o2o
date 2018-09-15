package com.imooc.o2o.service;


import java.io.InputStream;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;

import exceptions.ShopOperationException;




public interface ShopService {
	/**
	 * 根据shopCondition分页返回相应店铺的列表
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
	/**
	 * 通过店铺Id获取店铺信息
	 * @param shopId
	 * @return ShopExecution
	 */
	Shop getByShopId(long shopId);
	
	/**
	 * 更新店铺信息，包括对图片的处理
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return ShopExecution
	 */
	//	ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;
	//重构
	ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
//1.插入店铺信息->2.返回店铺Id->3.根据店铺Id创建存储图片的文件夹->4.把文件夹的地址更新回店铺信息中   只要有一步出错 就要事务回滚
	//ShopExecution addShop(Shop shop,File shopImg);
	//重构
	//ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;
	//再次重构
	ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}
