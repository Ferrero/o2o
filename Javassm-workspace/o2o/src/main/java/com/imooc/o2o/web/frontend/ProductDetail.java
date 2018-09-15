package com.imooc.o2o.web.frontend;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.o2o.entity.Product;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.HttpServletRequestUtil;

/**
* @author xf
* @version 2018年9月11日 下午9:19:54
*/
@Controller
@RequestMapping(value = "/frontend")
public class ProductDetail {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/listproductdetailpageinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> modelMap(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Long productId = HttpServletRequestUtil.getLong(request, "productId");
		if(productId != 1L) {
			Product product = productService.queryProductById(productId);
			modelMap.put("product", product);
			modelMap.put("success", true);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "productId is empty");
		}
		return modelMap;
	}
}
