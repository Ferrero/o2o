package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductCategory;

import exceptions.ProductCategoryException;

/**
* @author xf
* @version 2018年8月10日 下午4:49:59
*/


/**
 * 
 * 
 * @ClassName: ProductCategoryTest
 * 
 * @Description: Junit 4.11里增加了指定测试方法执行顺序的特性 .
 * 
 *               测试类的执行顺序可通过对测试类添加注解@FixMethodOrder(value) 来指定,其中value 为执行顺序
 * 
 *               三种执行顺序可供选择：
 * 
 *               默认（MethodSorters.DEFAULT）,
 *               默认顺序由方法名hashcode值来决定，如果hash值大小一致，则按名字的字典顺序确定
 *               由于hashcode的生成和操作系统相关
 *               (以native修饰），所以对于不同操作系统，可能会出现不一样的执行顺序，在某一操作系统上，多次执行的顺序不变
 * 
 *        [*推荐*]按方法名（ MethodSorters.NAME_ASCENDING）,
 *               按方法名称的进行排序，由于是按字符的字典顺序，所以以这种方式指定执行顺序会始终保持一致；
 * 
 *               JVM（MethodSorters.JVM）
 *               按JVM返回的方法名的顺序执行，此种方式下测试方法的执行顺序是不可预测的，即每次运行的顺序可能都不一样
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest {
	@Autowired 
	private ProductCategoryDao productCategoryDao;
	
	@Test
//	@Ignore
	public void testB_QueryProductCategory() throws Exception {
		Long shopId = 1L;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		//assertEquals(3,productCategoryList.size());
		System.out.println("该店铺自定义类别数为：" + productCategoryList.size());
		//assertEquals("店铺商品类别1", productCategoryList.get(0).getProductCategoryName());
	}
	
	@Test
//	@Ignore
	public void testA_InsertProductCategory() throws ProductCategoryException {
		ProductCategory productCategory1 = new ProductCategory();
		productCategory1.setProductCategoryName("纯甄牛奶");
		productCategory1.setPriority(1);
		productCategory1.setShopId(1L);
		productCategory1.setCreateTime(new Date());
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("安慕希酸奶");
		productCategory2.setPriority(2);
		productCategory2.setShopId(1L);
		productCategory2.setCreateTime(new Date());
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory1);
		productCategoryList.add(productCategory2);
		int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
		assertEquals(2, effectedNum);
	}
	
	@Test
	public void testC_DeleteProductCategory() {
		//查询shopId为1L的所有的商品类别
		List<ProductCategory> pcList = productCategoryDao.queryProductCategoryList(1L);
		//遍历循环删除
		for(ProductCategory pc :pcList) {
			if("纯甄牛奶".equals(pc.getProductCategoryName()) || "安慕希酸奶".equals(pc.getProductCategoryName())) {
				int effectedNum = 	productCategoryDao.deleteProductcategory(pc.getProductCategoryId(), pc.getShopId());
				assertEquals(1, effectedNum);
			}
		}
	}
	
	//自己测试练习的代码，但删除操作会影响数据库里的数据，所以老师采用了回环的方式
/*	@Test
	public void testDeleteProductCategory() {
		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryId(25L);
		pc1.setShopId(1L);
		int effectedNum = productCategoryDao.deleteProductcategory(pc1.getProductCategoryId(),pc1.getShopId());
		assertEquals(1,effectedNum);
	}*/

}
