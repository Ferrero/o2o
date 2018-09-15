package com.imooc.o2o.web.shopadmin;
/**
* @author xf
* @version 2018年8月11日 上午10:24:17
*/

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.dto.Result;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductCategoryStateEnum;
import com.imooc.o2o.service.ProductCategoryService;

import exceptions.ProductCategoryException;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
	@Autowired 
	private ProductCategoryService productCategoryService;
	
	@RequestMapping(value="/getproductcategorylist", method = RequestMethod.GET)
	@ResponseBody
/*	自己练习写的代码，缺少必要的判断
	public Map<String, Object> getProductCategoryList(){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		ProductCategory productCategory = new ProductCategory();
		productCategory.setShopId(1L);
		try {
			List<ProductCategory> productCategoryList =productCategoryService.getProductCategoryList(productCategory.getShopId());
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("success", true);
		}catch(Exception e) {
			modelMap.put("success", false);
		}
		return modelMap;
	}*/
	
	private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request){
		// To be removed
		/*Shop shop = new Shop();
		shop.setShopId(1L);
		request.getSession().setAttribute("current", shop);
		*/
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		List<ProductCategory> list = null;
		if(currentShop != null && currentShop.getShopId() > 0) {
			list = productCategoryService.getProductCategoryList(currentShop.getShopId());
			return new Result<List<ProductCategory>>(true, list);
		}else {
			ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
			return new Result<List<ProductCategory>>(false, ps.getStateInfo(),ps.getState());
		}
	}
	
	@RequestMapping(value = "/addproductcategorys", method = RequestMethod.POST)
	@ResponseBody
	//@RequestBody ,自动接收前台批量传过来的productCategoryList
	private Map<String, Object> addproductcategorys(@RequestBody List<ProductCategory> productCategoryList,
	HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		//从session中取出currentShop的值，productCategoryList需要通过shopId标识，以此来表明这些商品类别是在哪个店铺下创建的。
		//之所以不采用前端拼接的方式，是因为前台传的数据可以很随意,所以尽量不依赖前来传来的数据。
			//遍历productCategoryList,给每个商品类别添加Id
			for(ProductCategory pc : productCategoryList) {
				pc.setShopId(currentShop.getShopId());
			}
		//做空值判断
		if(productCategoryList != null && productCategoryList.size() > 0){
			try {
				
				ProductCategoryExecution  pe = productCategoryService.addProductCategoryList(productCategoryList);
				//判断
				if(pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
					//返回success给前台，表明批量新增的数据成功插入到数据库中，而无须把批量新增的list传到前台。(impl返回了list，没有调用到)
					//至于页面上的显示，可以在js的点击提交按钮后再次调用查询方法。（通俗地来说这里只需要把数据丢到数据库，然后告诉前台我丢进去了）
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			}catch(ProductCategoryException e) {
				modelMap.put("success", false);
				modelMap.put("errmsg", e.toString());
				return modelMap;
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少输入一个商品类别");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/removeproductcategory", method = RequestMethod.POST)	
	@ResponseBody
	private Map<String, Object> removeProductCategory(Long productCategoryId, Long shopId, HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if(productCategoryId != null && productCategoryId > 0 ) {
			try {
				Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
				ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId, currentShop.getShopId());
				if(pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMag", pe.getStateInfo());
				}
			}catch(ProductCategoryException e){
				//使用ProductCategoryException去捕获service层抛出的异常，然后将错误信息返回给前端
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				//service层抛出ProductCategoryException自定义异常"实例",这里捕获并调用toString()方法。
				//将会把错误信息(同e.getMessage,如："商品类别删除失败")和异常种类名称(getClass().getName())返回给前端
				return modelMap;
			}
			return modelMap;
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少选择一个商品类别");
		}
		return modelMap;
	}
}
