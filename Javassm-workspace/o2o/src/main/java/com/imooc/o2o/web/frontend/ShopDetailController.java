package com.imooc.o2o.web.frontend;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.service.ProductCategoryService;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.HttpServletRequestUtil;

/**
* @author xf
* @version 2018年9月11日 上午11:59:03
*/
@Controller
@RequestMapping(value = "/frontend")
public class ShopDetailController {
	@Autowired 
	private ShopService shopService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;
/**
 * 获取店铺信息以及该店铺下面的商品类别列表
 * @param request
 * @return
 */
	@RequestMapping(value = "/listshopdetailpageinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listShopDetailPageInfo(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//获取前台传过来的shopId
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if(shopId != -1) {
			//获取店铺id为shopId的店铺信息
			Shop shop  = shopService.getByShopId(shopId);
			//获取店铺下的商品类别列表
			List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryList(shopId);
			modelMap.put("shop", shop);
			modelMap.put("productCategoryList", productCategoryList );
			modelMap.put("success", true);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty shopId");
		}
		return modelMap;
	}
	/**
	 * 依据查询条件分页列出该店铺下面的所有的商品(商品名（模糊），商品状态，店铺id,商品类别 )
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/listproductsbyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listproductsByShop(HttpServletRequest request) throws UnsupportedEncodingException{
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//获取页码
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		//获取一页需要显示的条数
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		//获取店铺id
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if((pageIndex > -1) && (pageSize > -1) && (shopId > -1)) {
			//尝试获取模糊查找的商品名
			String productName = HttpServletRequestUtil.getString(request, "productName");
			//尝试获取商品类别Id
			Long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
			//按照传入的查询条件以及分页信息返回相应商品列表以及总数
			Product productCondition = compactProductSearch(productName, shopId, productCategoryId);
			ProductExecution pe = productService.queryProductListById(productCondition, pageIndex, pageSize);
			modelMap.put("productList", pe.getProductList());
			modelMap.put("count", pe.getCount());
			modelMap.put("success", true);
			
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex");
		}
		return modelMap;
		
	}
	/**
	 * 组合查询条件，并按条件封装到ProductCondition对象返回
	 * @param productName
	 * @param shopId
	 * @param productCategoryId
	 * @return
	 */
	private Product compactProductSearch(String productName, Long shopId, Long productCategoryId) {
		Product productCondition = new Product();
		Shop shop = new Shop();
		shop.setShopId(shopId);
		productCondition.setShop(shop);
		if(productName != null) {
			//查询名字里包含productName的店铺列表
			productCondition.setProductName(productName);
		}
		if(productCategoryId != -1L) {
			//查询某个商品类别下面的商品列表
			ProductCategory productCategory = new ProductCategory();
			productCategory.setProductCategoryId(productCategoryId);
			productCondition.setProductCategory(productCategory);
		}
		//只允许选出状态为上架的商品
		productCondition.setEnableStatus(1);
		return productCondition;
	}
}
