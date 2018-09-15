package com.imooc.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;

import org.junit.Assert;
import org.junit.Ignore;

/**
* @author xf
* @version 2018年8月23日 下午5:08:29
*/
public class ProductServiceTest extends BaseTest{
	@Autowired
	private ProductService productService;
	
	@Test
	@Ignore
	public void testAddProduct() throws FileNotFoundException {
		// 注意表中的外键关系，确保这些数据在对应的表中的存在
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(5L);
		// 注意表中的外键关系，确保这些数据在对应的表中的存在
		Shop shop = new Shop();
		shop.setShopId(1L);
		 // 构造Product
		Product product = new Product();
		product.setProductName("好一二三");
		product.setProductDesc("测试");
		product.setNormalPrice("33");
		product.setPromotionPrice("88");
		product.setPriority(89);
		product.setShop(shop);
		product.setProductCategory(productCategory);
		// 构造商品图片
		File productFile = new File("C:/image/lanqiao.jpg");
		InputStream is = new FileInputStream(productFile);
		ImageHolder imageHolder = new ImageHolder(productFile.getName(),is);
		//构造商品详情图片
		List<ImageHolder> imageDetailList = new ArrayList<ImageHolder>();
		File productFile1 = new File("C:/image/xiaohuangren.jpg");
		InputStream is1 = new FileInputStream(productFile1);
		ImageHolder imageHolder1 = new ImageHolder(productFile1.getName(),is1);
		
		File productFile2 = new File("C:/image/xiaohuangren_new.jpg");
		InputStream is2 = new FileInputStream(productFile2);
		ImageHolder imageHolder2 = new ImageHolder(productFile1.getName(),is2);
		imageDetailList.add(imageHolder1);
		imageDetailList.add(imageHolder2);
		//调用服务
        ProductExecution pe = productService.addProduct(product, imageHolder, imageDetailList);
        Assert.assertEquals(ProductStateEnum.SUCCESS.getState(),pe.getState());
	}
	
	
	@Test
	public void testUpdateProduct() throws FileNotFoundException {
		//创建shopId为1且productCategory为1的商品实例并给其他成员变量赋值
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(1L);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(5L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductId(21L);
		product.setProductName("优酷");
		product.setProductDesc("这世界很酷");
		
		File file = new File("C:/image/lanqiao.jpg");
		InputStream is = new FileInputStream(file);
		ImageHolder thumbnail = new ImageHolder(file.getName(), is);
		
		File productImg1 = new File("C:/image/lanqiao.jpg");
		InputStream inputStream1 = new FileInputStream(productImg1);
		ImageHolder imageHolder1 = new ImageHolder(productImg1.getName(), inputStream1);
		
		File productImg2 = new File("C:/image/xiaohuangren.jpg");
		FileInputStream inputStream2 = new FileInputStream(productImg2);
		ImageHolder imageHolder2 = new ImageHolder(productImg2.getName(), inputStream2);
		
		List<ImageHolder>productImgList = new ArrayList<ImageHolder>();
		productImgList.add(imageHolder1);
		productImgList.add(imageHolder2);
		
		ProductExecution updateProduct = productService.updateProduct(product, thumbnail, productImgList);
		assertEquals( ProductStateEnum.SUCCESS.getState(), updateProduct.getState());
		
	}

}
