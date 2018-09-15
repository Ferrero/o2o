package com.imooc.o2o.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.Product;

/**
* @author xf
* @version 2018年8月21日 上午9:56:29
*/
public interface ProductDao {
	/**
	 * 插入商品
	 * @param product
	 * @return
	 */
	int insertProduct(Product product);
	/**
	 * 根据商品id获取商品信息
	 * @param productId
	 * @return
	 */
	Product queryProductById(Long productId);
	
	/**
	 * 更新商品信息
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);
	
	
	
	/**商品管理：1.独立实现商品列表展示；2.独立实现商品下架（参考商品编辑）
	 * 查询商品列表并分页。可输入条件有：商品名（模糊），商品状态，店铺id,商品类别
	 * @Param productCondition 商品（查询）条件
	 * @param rowIndex 从第几行开始取数据
	 * @param pageSize 返回的行数
	 */
	List<Product> getProductListById(@Param("productCondition") Product productCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	/**
	 * (按照这个条件查询的所有商品总数)查询对应商品总数
	 * @Param productCondition
	 */
	int queryProductCount(@Param("productCondition") Product productCondition);
	
	/**
	 * 删除商品类别之前，将商品类别Id置为空。置空productCategoryId之后，商品就和productCategoryId失去了联系
	 * @param prodcutCategoryId
	 * @return
	 */
	int updateProductCategoryToNull(long productCategoryId);
	
}
