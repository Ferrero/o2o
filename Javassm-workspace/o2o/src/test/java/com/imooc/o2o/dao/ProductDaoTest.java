package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.entity.Shop;
import com.mysql.fabric.xmlrpc.base.Array;

/**
* @author xf
* @version 2018年8月21日 下午3:20:04
*/
public class ProductDaoTest extends BaseTest {
	@Autowired
	private ProductDao productDao; 
	@Autowired
	private ProductImgDao productImgDao;
	@Test
	@Ignore
	public void testInsertProduct() {
		Shop shop = new Shop();
		shop.setShopId(1L);
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(5L);
		//初始化商品实例并添加进shopId为1的店铺里，同时商品类别为5
		Product product1 = new Product();
		product1.setProductName("测试");
		product1.setProductDesc("描述");
		product1.setImgAddr("土豆网");
		product1.setNormalPrice("原价");
		product1.setPromotionPrice("折扣价");
		product1.setPriority(20);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setEnableStatus(1);
		product1.setShop(shop);
		product1.setProductCategory(productCategory);
		Product product2 = new Product();
		product2.setProductName("test");
		product2.setProductDesc("description");
		product2.setImgAddr("tudou.com");
		product2.setNormalPrice("normalPrice");
		product2.setPromotionPrice("promotionPrice");
		product2.setPriority(10);
		product2.setCreateTime(new Date());
		product2.setLastEditTime(new Date());
		product2.setEnableStatus(1);
		product2.setShop(shop);
		product2.setProductCategory(productCategory);
		//判断是否添加成功
		int effectedNum = productDao.insertProduct(product1);
		assertEquals(1,effectedNum);
		effectedNum = productDao.insertProduct(product2);
		assertEquals(1,effectedNum);
	} 
	
	@Test
	@Ignore
	public void testQueryProductByProductId() throws Exception{
		Long productId = 4L;
		Product product1= productDao.queryProductById(productId);
		assertEquals("好一二三", product1.getProductName());
		System.out.println("商品名称："+ product1.getProductName() +".."+ "详情图片地址： "+ product1.getProductImgList().get(0).getImgAddr());
	
		ProductImg  productImg1 = new ProductImg();
		productImg1.setCreateTime(new Date());
		productImg1.setImgAddr("www.baidu.com");
		productImg1.setImgDesc("baidu");
		productImg1.setPriority(23);
		productImg1.setProductId(2L);
		
		
		ProductImg  productImg2 = new ProductImg();
		productImg2.setCreateTime(new Date());
		productImg2.setImgAddr("www.souhu");
		productImg2.setImgDesc("souhu");
		productImg2.setPriority(12);
		productImg2.setProductId(2L);
		
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2, effectedNum);
		
		Product product2 = productDao.queryProductById(2L);
		assertEquals(2, product2.getProductImgList().size());
		System.out.println(product2.getProductImgList().get(0).getImgDesc());
		//删除这两个新增的详情图片
		effectedNum = productImgDao.deleteProductImgByProductId(2L);
		assertEquals(2,effectedNum);
		
	}
	
	@Test
	@Ignore
	public void testUpdateProduct() throws Exception{
		Shop shop = new Shop();
		shop.setShopId(1L);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(5L);
		Product product = new Product();
		product.setProductId(1L);
		product.setShop(shop);
//		product.setImgAddr("www.youku.com");
		product.setProductCategory(pc);
		product.setProductName("优酷");
		int effectedNum = productDao.updateProduct(product);
		assertEquals(1, effectedNum);
		
	}
	@Test
	@Ignore
	public void testGetProductListById() {
		Product productCondition = new Product();
		//分页查询， 预期返回三条结果
		List<Product> prodcutList = productDao.getProductListById(productCondition, 0, 3);
		assertEquals(3, prodcutList.size());
		//查询商品总数
		int count = productDao.queryProductCount(productCondition);
		assertEquals(22, count);
		
		//使用商品名称模糊查询，预期返回两条结果
		productCondition.setProductName("优");
		List<Product> productList = productDao.getProductListById(productCondition, 0, 5);
		assertEquals(3, productList.size());
		
		count = productDao.queryProductCount(productCondition);
		assertEquals(3, count);
	}
	
	@Test
	public void testUpdateProductCategory() {
		int effectedNum = productDao.updateProductCategoryToNull(56L);
		assertEquals(1, effectedNum);
	}
}
