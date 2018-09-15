package com.imooc.o2o.dao;

import java.util.List;

import com.imooc.o2o.entity.ProductImg;

/**
* @author xf
* @version 2018年8月21日 上午10:00:04
*/
public interface ProductImgDao {
	/**
	 * 批量添加商品详情图片
	 * @param productImgList
	 * @return
	 */
	int batchInsertProductImg(List<ProductImg> productImgList);
	

	/**
	 * 根据商品id商品删除详情图片
	 * @param productId
	 * @return
	 */
	int deleteProductImgByProductId(Long productId);
	
	/**
	 * 根据商品id查询商品详情图片列表
	 * @param productId
	 * @return
	 */
	List<ProductImg> queryProductImgByproductId(Long productId);
}
