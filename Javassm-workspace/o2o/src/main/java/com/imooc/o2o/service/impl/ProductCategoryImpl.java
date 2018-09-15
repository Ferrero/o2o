package com.imooc.o2o.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.ProductCategoryDao;
import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.enums.ProductCategoryStateEnum;
import com.imooc.o2o.service.ProductCategoryService;

import exceptions.ProductCategoryException;

/**
* @author xf
* @version 2018年8月10日 下午10:19:35
*/
@Service
public class ProductCategoryImpl implements ProductCategoryService {
	@Autowired 
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ProductDao prodcutDao;
	
	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		return productCategoryDao.queryProductCategoryList(shopId);
	}

	@Override
	@Transactional
	/**
	 *  使用@Transactional控制事务
	 */
	public ProductCategoryExecution addProductCategoryList(List<ProductCategory> productCategoryList)
			throws ProductCategoryException {
		//先判断该集合是否存在，再判断集合中是否有元素
		if(productCategoryList != null && productCategoryList.size() >0) {
			try {
				int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if(effectedNum <= 0) {
					throw new ProductCategoryException("店铺类别创建失败");
				}else {
					//调用ProductCategoryExecutionc创建成功的构造器，返回具体的结果状态及状态标识(例如：1,创建成功)
					//以及productCategoryList(controller层没有调用到)
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS, productCategoryList);
				}
			}catch(Exception e) {
				throw new ProductCategoryException("batchAddrProductCategory error(批量添加商品类别失败)：" + e.getMessage());
			}
		}else {
			//EMPTY_LIST：添加的集合数少于1
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

	@Override
	@Transactional
	//采用事务管理的好处在于，当你第一步成功的话，不急于提交，而是等到第二步成功后才一起提交，
	//如果第二步失败的话，那么第一步也就不提交了，这样便能达到回滚的目的
	
	/**
	 * 1.解除tb_prodcut里的商品与该productCategoryId的关联
	 * 2.删除该productCategory
	 */
	public ProductCategoryExecution deleteProductCategory(Long productCategoryId, Long shopId) throws ProductCategoryException {
		 // TODO 第一步 因为商品类别跟商品建立了联系，所以需要先将该商品类别下的商品的类别Id置为空
		try {
			int effectedNum = prodcutDao.updateProductCategoryToNull(productCategoryId);
			if(effectedNum < 0) {
				throw new ProductCategoryException("商品类别更新失败");
			}
		}catch(Exception e) {
			throw new ProductCategoryException("deleteProductCategory error:" + e.getMessage());
			
		}

        // 第二步 删除该商品类别
		try {
		    int effectedNum = productCategoryDao.deleteProductcategory(productCategoryId, shopId);
			if(effectedNum > 0) {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}else {
				throw new ProductCategoryException("商品类别删除失败");
			}
		}catch(Exception e) {
			throw new ProductCategoryException("deleteProductCategory error:(商品类别删除失败)" + e.getMessage());
		}
	}

}
