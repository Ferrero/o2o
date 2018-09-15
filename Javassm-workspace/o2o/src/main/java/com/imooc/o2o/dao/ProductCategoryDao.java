package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.ProductCategory;

/**
* @author xf
* @version 2018年8月10日 下午3:54:28
*/
public interface ProductCategoryDao {
	/**
	 * 批量增加店铺商品类别
	 * @param productCategory
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategory);
	/**
	 * 通过shopId 查询店铺商品类别
	 * @param long shopId
	 * @return List<ProductCategory>
	 */
	List<ProductCategory> queryProductCategoryList(long shopId);
	/**
     * 
     * @Title: deleteProductCategory
     * 
     * @Description: 删除特定shop下的productCategory
     * 
     * @param productCategoryId
     * @param shopId
     * 
     * @return: int
     */
	int deleteProductcategory(@Param(value = "productCategoryId") Long productCategoryId, @Param(value = "shopId") long shopId);


}
