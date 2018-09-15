package com.imooc.o2o.web.frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.o2o.entity.HeadLine;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.HeadLineService;
import com.imooc.o2o.service.ShopCategoryService;

/**
* @author xf
* @version 2018年9月8日 上午11:36:09
*/

//frontend:中文译为前端
@Controller
@RequestMapping("/frontend")
//响应主页的请求
public class MainPageController {
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private HeadLineService headLineService;
	
	/**
	 * 初始化前端展示系统的主页信息，包括获取一级店铺类别列表以及头条列表
	 */
	@RequestMapping(value = "/listmainpageinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listMainPageInfo(){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		try{
			//获取一级店铺类别列表（即parentId为空的shopCategory【父级ID为空即表示当前为一级类别列表】）
			 shopCategoryList = shopCategoryService.getShopCategoryList(null);
			modelMap.put("shopCategoryList", shopCategoryList);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		
		List<HeadLine> headLineList = new ArrayList<HeadLine>();
		try {
			//获取状态为可用（1）的头条列表
			HeadLine headLine = new HeadLine();
			headLine.setEnableStatus(1);
			headLineList = headLineService.getHeadLineList(headLine);
			modelMap.put("headLineList", headLineList);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		
		modelMap.put("success", true);
		return modelMap;
	}

}
