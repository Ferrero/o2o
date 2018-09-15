package com.imooc.o2o.service;

import java.util.List;

import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;

import exceptions.ProductCategoryException;

/**
* @author xf
* @version 2018年8月10日 下午10:15:34
*/

public interface ProductCategoryService {
	/**
	 * 查询制定某个店铺下的所有商品类别信息
	 * @param long shopId
	 * @return List<ProductCategory>
	 */
	List<ProductCategory> getProductCategoryList(long shopId); 
	/**
	 * @Title: addProductCategoryList
	 * @Description: 批量插入ProductCategory
	 * @param productCategoryList
	 * @throws ProductCategoryException
	 * @return: ProductCategoryExecution
	 */
	ProductCategoryExecution addProductCategoryList(List<ProductCategory> productCategoryList)
	throws ProductCategoryException;
	/**
     * @Title: deleteProductCategory
     * @Description: TODO 需要先将该商品目录下的商品的类别Id置为空，然后再删除该商品目录， 因此需要事务控制
     * (因为该商品类别下可能已经挂载商品了，所以先将商品类别的Id置为空才是合理的)
     * @param productCategoryId
     * @param shopId
     * @throws ProductCategoryOperationException
     * @return: ProductCategoryExecution
     */
	ProductCategoryExecution deleteProductCategory(Long ProductCategoryId, Long shopId ) throws ProductCategoryException;
}
