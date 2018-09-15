package com.imooc.o2o.web.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.AreaService;
import com.imooc.o2o.service.ShopCategoryService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.HttpServletRequestUtil;

/**
* @author xf
* @version 2018年9月9日 下午2:49:30
*/
@Controller
@RequestMapping("/frontend")
public class ShopListController {
	@Autowired
	private AreaService areaService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private ShopService shopService;
	/**
	 * 返回商品列表页里的ShopCategory列表（二级或者一级），一级区域列表信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "listshopspageinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listshopspageinfo(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//试着从前端请求中获取parentId
		Long parentId = HttpServletRequestUtil.getLong(request, "parentId");
		List<ShopCategory> shopCategoryList = null;
		if(parentId != -1L) {
		//如果parentId存在，则取出一级ShopCategory下的二级shopCategory列表	
			try {
				ShopCategory shopCategoryCondition = new ShopCategory();
				ShopCategory parent = new ShopCategory();
				parent.setShopCategoryId(parentId);
				shopCategoryCondition.setParent(parent);
			    shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
			}catch(Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}else {
			try {
				//如果parentId不存在，则取出所有一级ShopCategory（用户在首页选择的是全部商品列表）
				 shopCategoryList = shopCategoryService.getShopCategoryList(null);
			}catch(Exception e){
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}
		modelMap.put("shopCategoryList", shopCategoryList);
		List<Area> areaList = null;
		try {
			//获取区域列表信息
			 areaList = areaService.getAreaList();
			 modelMap.put("areaList", areaList);
			 modelMap.put("success", true);
		}catch(Exception e){
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	//获取指定查询条件下的店铺列表
	@RequestMapping(value = "listshops", method = RequestMethod.GET)
	@ResponseBody 
	private Map<String, Object> listshops(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//获取页码
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		//获取一页需要显示的数据条数
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		//非空判断
		if(pageIndex > -1 && pageSize >-1) {
			//试着获取一级类别id
			Long parentId = HttpServletRequestUtil.getLong(request, "parentId");
			//试着或取二级类别id
			Long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
			//试着获取区域Id
			Integer areaId = HttpServletRequestUtil.getInt(request, "areaId");
			//试着获取模糊查询的名字
			String shopName = HttpServletRequestUtil.getString(request, "shopName");
			System.out.println("shopName : " + shopName);
			//获取组合之后的查询条件
			Shop shopCondition = CompactShopcondition4Search(parentId, shopCategoryId, areaId, shopName);
			ShopExecution se = shopService.getShopList(shopCondition, pageIndex, pageSize);
			modelMap.put("shopList", se.getShopList());
			modelMap.put("count", se.getCount());
			modelMap.put("success", true);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex");
		}
		return modelMap;
	}

	private Shop CompactShopcondition4Search(Long parentId, Long shopCategoryId, Integer areaId, String shopName) {
		Shop shopCondition = new Shop();
		if(parentId != -1L) {
			//查询某一个一级ShopCategory下面所有的二级ShopCategory里面的店铺列表
			ShopCategory parent = new ShopCategory();
			parent.setShopCategoryId(parentId);
			ShopCategory child = new ShopCategory();
			child.setParent(parent);
			shopCondition.setShopCategory(child);
		}
		if(shopCategoryId != -1L) {
			//查询某一个二级ShopCategory下的店铺列表
			ShopCategory shopCategory = new ShopCategory();
			shopCategory.setShopCategoryId(shopCategoryId);
			shopCondition.setShopCategory(shopCategory);
		}
		if(areaId != -1L) {
			//查询位于某个区域id下的店铺列表
			Area area = new Area();
			area.setAreaId(areaId);
			shopCondition.setArea(area);
		}
		if(shopName != null) {
			//查询某个名字里面包含shaoName的店铺列表（模糊查询）
			shopCondition.setShopName(shopName);
		}
		//前端展示店铺都是审核成功的店铺
		shopCondition.setEnableStatus(1);
		return shopCondition;
	}
}
