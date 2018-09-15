package com.imooc.o2o.service;
/**
* @author xf
* @version 2018年8月21日 下午10:10:19
*/

import java.io.InputStream;
import java.util.List;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;

import exceptions.ProductOperationException;

public interface ProductService {
	/**添加商品信息以及图片处理
	 * 
	 * @param product
	 * @param thumnail
	 * @param thumnailName
	 * @param productImgList
	 * @param productImgNameList
	 * @return
	 */
	/*ProductExecution addProduct(Product product, InputStream thumnail, String thumnailName,
			List<InputStream> productImgList, List<String> productImgNameList);*/
	//thumnail和thumnailName--》缩略图，productImgList，productImgNameList--》商品图片
	//可以合二为一，对代码进行重构
	ProductExecution addProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImgList) throws ProductOperationException;
	
	/**
	 * 根据商品id查询商品信息
	 * @param productId
	 * @return
	 */
	Product  queryProductById(Long productId);
	
	/**
	 * 编辑商品信息
	 * @param product
	 * @param thumbnail
	 * @param productimgList
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution updateProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImgList ) throws ProductOperationException;
	/**
	 * 查询商品列表并分页，可输入的条件为：商品名(模糊)，商品状态， 店铺Id，商品类别（或者四者组合）
	 * @param productCondition 商品查询条件
	 * @param pageIndex 第几页
	 * @param pageSize  每页多少条数据
	 * @return
	 */
	ProductExecution queryProductListById(Product productCondition, int pageIndex, int pageSize);
	
}
