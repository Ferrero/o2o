package com.imooc.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dao.ProductImgDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PageCalculator;
import com.imooc.o2o.util.PathUtil;

import exceptions.ProductOperationException;

/**
 * @author xf
 * @version 2018年8月21日 下午10:12:22
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	ProductImgDao productImgDao;

	@Override
	@Transactional
	/*
	 * 1.处理缩略图，获取缩略图相对路径并赋值给product
	 * 2.往tb_product中写入商品信息，一旦创建成功，便自动将创建成功的商品productId赋值给我们传入的product参数
	 * 3.结合productId批量处理商品的详情图 
	 * 4.将商品详情图列表插入tb_product_img中
	 */
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationException {
		// 空值判断(product实例不为空，product归属于哪个店铺下，必须得表明)
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			// 给商品设置默认属性
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			// 默认为上架的状态
			product.setEnableStatus(1);
			// 若商品缩略图不为空则添加
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				// 创建商品信息
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建商品失败");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品失败：" + e.getMessage());
			}
			// 若商品详情图不为空则批量添加
			if (productImgList != null && productImgList.size() > 0) {
				addProductImgList(product, productImgList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		}else {
			//传参为空则返回空值错误信息
			return new ProductExecution(ProductStateEnum.NULL_PRODUCT);
		}
	}
	
	/**
	 * 将商品缩略图添加到特定的shopId目录下，并将thumbnailAddr属性设置给product
	 * @param product
	 * @param thumbnail
	 */
	
	private void addThumbnail(Product product, ImageHolder thumbnail) {
		//获取缩略图存储的子路径，根据shopId来区分（"upload/item/shop/" + shopId + "/"）
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		//添加水印到指定子路径，并返回缩略图相对路径
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		// 将相对路径设置给product，数据库tb_product中的img_addr字段存储的便为相对路径[upload/item/shop/1/2018082310101962804.jpg]
		product.setImgAddr(thumbnailAddr);
	}
	
	/**
	 * 批量添加商品详情图片到用户相对文件夹下，并将其批量添加到数据库中
	 * @param product
	 * @param productImgList
	 */
	private void addProductImgList(Product product, List<ImageHolder> productImgHolderList ) {
		//获取图片存储路径，这里直接存放在相应店铺的文件夹下
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		//遍历图片一次性处理，并添加进productImg实体类里
		for(ImageHolder  imageHolder : productImgHolderList) {
			// 生成图片详情的图片,大一些，并且不添加水印，所以另外写了一个方法，基本和generateThumbnails相似
			String relativeAdrr = ImageUtil.generateNormalImg(imageHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(relativeAdrr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		//如果确实有图片需要添加的，就执行批量添加操作
		if(productImgList.size() > 0) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if(effectedNum <= 0) {
				throw new ProductOperationException("创建详情图片失败");
				}
			}catch(Exception e) {
				throw new ProductOperationException("创建详情图片失败" + e.getMessage());
			}
		}	
	}

	@Override
	public Product queryProductById(Long productId)  {
		return productDao.queryProductById(productId); 
	}

	@Override
	@Transactional
	/**
	 * 1.若缩略图参数有值，则处理缩略图
	 * 若原先商品存在缩略图则先删除再添加新图，之后获取缩略图相对路径并赋值给product
	 * 2.若商品图片列表参数有值，对商品详情图片进行同样的操作
	 * 3.将tb_product_img下面的该商品原先的商品详情图记录全部清除
	 * 4.更新tb_product_img 以及tb_product的信息
	 */
	public ProductExecution updateProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationException {
			//空值判断
			if(product != null && product.getShop() != null && product.getShop().getShopId() != null) {
				//给商品设置默认属性
				product.setLastEditTime(new Date());
				//若传入的商品缩略图不为空，并且原有缩略图不为空，则删除原有缩略图并添加
					if(thumbnail.getImage() != null && thumbnail.getImageName() != null 
							&& !"".equals(thumbnail.getImageName())) {
						Product tempProduct = productDao.queryProductById(product.getProductId());
						if(tempProduct.getImgAddr() != null) {
							ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
						}
						addThumbnail(product, thumbnail);
					}
					//如果有新存入的商品详情图，则将原先的删除，并添加新的图片
					if(productImgList != null && productImgList.size() > 0) {
							deleteProductImgList(product.getProductId());
						addProductImgList(product, productImgList);
					}
					try {
						int effectedNum = productDao.updateProduct(product);
						//更新商品信息
						if(effectedNum <= 0) {
							throw new ProductOperationException("更新商品失败");
						}else {
							return new ProductExecution(ProductStateEnum.SUCCESS, product);
						}
					}catch(Exception e) {
						throw new ProductOperationException("更新商品失败" + e.toString());
					}
			}else {
				return new ProductExecution(ProductStateEnum.NULL_PRODUCT);
			}
}

	private void deleteProductImgList(Long productId) {
		//根据productId获取原有的图片
		List<ProductImg> productImageList = productImgDao.queryProductImgByproductId(productId);
		//干掉原来的图片
		for (ProductImg productImg : productImageList) {
			ImageUtil.deleteFileOrPath(productImg.getImgAddr());
		}
		//删除数据库里原有图片的信息
		productImgDao.deleteProductImgByProductId(productId);
		
	}

	@Override
	public ProductExecution queryProductListById(Product productCondition, int pageIndex, int pageSize) {
		//页码转化成数据库的行码，并调用dao层取回指定页码的商品列表
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productDao.getProductListById(productCondition, rowIndex, pageSize);
		//基于同样的查询条件返回该查询条件下的商品总数
		int count = productDao.queryProductCount(productCondition);
		ProductExecution pe = new ProductExecution();
		pe.setProductList(productList);
		pe.setCount(count);
		return pe;
	}


	
}
