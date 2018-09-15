package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductImg;


/**
* @author xf
* @version 2018年8月21日 上午11:52:39
*/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest extends BaseTest{
	@Autowired 
	private ProductImgDao productImgDao;
	@Test
	public void testABatchInsertProductImg() throws Exception{
		//productId为1的商品里添加两个详情图片记录
		ProductImg productImg = new ProductImg();
		productImg.setProductId(1L);
		productImg.setImgAddr("test");
		productImg.setImgDesc("测试图片地址1");
		productImg.setPriority(20);
		productImg.setCreateTime(new Date());
		ProductImg productImg1 = new ProductImg();
		productImg1.setProductId(1L);
		productImg1.setImgAddr("test1");
		productImg1.setImgDesc("测试图片地址2");
		productImg1.setPriority(201);
		productImg1.setCreateTime(new Date());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg);
		productImgList.add(productImg1);
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2, effectedNum);
	}
	
	@Test
	public void testBDeleteImgByProductId() throws Exception {
		long productId = 1L;
		int effectedNum = productImgDao.deleteProductImgByProductId(productId);
		assertEquals(2, effectedNum);
	}
	
}