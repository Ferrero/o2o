package com.imooc.o2o.web.frontend;
/**
* @author xf
* @version 2018年9月8日 下午1:48:56
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/frontend")
public class FrontendController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	private String index() {
		return "frontend/index";
	}
	/**
	 * 商品列表路由
	 * @return
	 */
	@RequestMapping(value = "shoplist", method = RequestMethod.GET)
	private String showShopList() {
		return "frontend/shoplist";
	}
	/**
	 * 商品详情路由
	 * @return
	 */
	@RequestMapping(value = "/shopdetail", method = RequestMethod.GET)
	private String shopDetail() {
		return "frontend/shopdetail";
	}
	@RequestMapping(value = "productdetail", method = RequestMethod.GET)
	private String productDetail() {
		return "frontend/productdetail";
	}
	
}
