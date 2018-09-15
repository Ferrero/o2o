package com.imooc.o2o.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PageCalculator;
import com.imooc.o2o.util.PathUtil;

import exceptions.ShopOperationException;

@Service
public class ShopServiceImpel implements ShopService{
	private static final Logger logger = LoggerFactory.getLogger(ShopServiceImpel.class);
	@Autowired
	private ShopDao shopDao;
	@Override
	//事务支持
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {
		//1.空值判断
		if(shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			//2.给店铺信息赋初始值  (设置人为不能更改的信息)
			//初识店铺都是 0  表示未上架  审核中
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			
			//3.添加店铺信息
			int effectedNum = shopDao.insertShop(shop);
			//4.判断插入店铺是否成功，若不成功，则回滚事务
			//只有当程序抛出RuntimeException的异常或继承RuntimeException类 时，事务才会终止并且回滚，如果只是抛出Exception，事务不会回滚，该提交的依然提交。
			if (effectedNum <= 0) {
				throw new ShopOperationException("店铺创建失败"); 
			}else {
				//5.判断传入的图片是否为空，不为空，则将店铺对应的图片存储到相关目录当中
				if(thumbnail.getImage() != null) {
					//6.存储图片
					try {
						addShopImg(shop,thumbnail);
					}catch(Exception e) {
						logger.error("addShopImg error {} ", e.toString());
						throw new ShopOperationException("addShopImg error :" + e.getMessage());
					}
					//7.更新店铺的图片地址(把文件夹的地址更新回店铺信息中 )
					effectedNum = shopDao.updateShop(shop);
					logger.error("updateShopImg success ", effectedNum);
					System.out.println(effectedNum);
					if(effectedNum <= 0) {
						logger.error("addShopImg error {} ", effectedNum);
						throw new ShopOperationException("更新图片地址失败" );
					}
				}
			}
		}catch(Exception e){
			logger.error("addShopImg error {} ",  e.getMessage());
			throw new ShopOperationException("addShop error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}
	  /**
     * 
     * 
     * @Title: addShopImg
     * 
     * @Description: 根据shopId创建目录,并生成水印图片
     * 
     * @param shop
     * @param shopImg
     * 
     * @return: void
     */
	private void addShopImg(Shop shop, ImageHolder thumbnail) {
		
			//获取各个店铺下的图片的子路径
			String dest = PathUtil.getShopImagePath(shop.getShopId());
			//System.out.println(dest);
			//获取店铺图片的相对路径
			String shopImgAddr = ImageUtil.generateThumbnail(thumbnail,dest);
			shop.setShopImg(shopImgAddr);
	}
	@Override
	public Shop getByShopId(long shopId) {
		return shopDao.queryByShopId(shopId);
	}
	@Override
	public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail)
			throws ShopOperationException {
		if(shop == null || shop.getShopId() ==null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		//1.判断是否需要处理图片
		try{
			if(thumbnail.getImage() != null && thumbnail.getImageName() !=null && !"".equals(thumbnail.getImageName())) {
			Shop tempShop = shopDao.queryByShopId(shop.getShopId());
			logger.error("tempShop error {} ",tempShop);
				if(tempShop.getShopImg() != null) {
				logger.error("获取图片失败",tempShop.getShopImg());
				ImageUtil.deleteFileOrPath(tempShop.getShopImg());
				}
			addShopImg(shop,thumbnail);
			}
		
		//2.更新店铺信息
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		if(effectedNum <= 0) {
			logger.error("222",effectedNum);
			return new ShopExecution(ShopStateEnum.INNER_ERROR);
			
		}else {
			shop = shopDao.queryByShopId(shop.getShopId());
			return new ShopExecution(ShopStateEnum.SUCCESS,shop);
		}
		}catch(Exception e) {
			throw new ShopOperationException("modifyShop error 更新商铺失败：" + e.getMessage());
		}
	}
	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopDao.queryShopCount(shopCondition);
		ShopExecution se = new ShopExecution();
		if(shopList != null) {
			se.setShopList(shopList);
			se.setCount(count);
		}else {
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return se;
	}
	
}
