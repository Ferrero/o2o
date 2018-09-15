package com.imooc.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**主要用来解析路由并转发到相应的html中
 * 
* @author xf
* @version 2018年7月26日 下午9:13:23
*/
@Controller
@RequestMapping(value = "/shopadmin",method = {RequestMethod.GET})
public class shopAdminController {
	@RequestMapping(value = "/shopoperation")
	public String shopOperation() {
		//跳转至店铺注册、编辑页面
		return "shop/shopOperation";
	}
	@RequestMapping(value = "/shoplist",method = {RequestMethod.GET})
	public String shoplist() {
		//转发至店铺列表页面
		return "shop/shopList";
	}
	@RequestMapping(value = "/shopmanagement",method = {RequestMethod.GET})
	public String shopmanagement() {
		//跳转至店铺管理页面
		return "shop/shopmanagement";
	}
	@RequestMapping(value = "/productcategorymanagement",method = {RequestMethod.GET})
	public String productcategorymanagement() {
		//转发至商品类别管理页面
		return "shop/productcategorylist";
	}
	@RequestMapping(value = "/productoperation", method = RequestMethod.GET)
	public String productOperation() {
		//跳转至商品添加/编辑页面
		return "shop/productoperation";
	}
	@RequestMapping(value = "/productmanagement")
	public String productManagement() {
		return "shop/productmanagement";
	}
}
