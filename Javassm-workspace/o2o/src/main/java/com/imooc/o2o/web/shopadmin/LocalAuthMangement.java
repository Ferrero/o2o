package com.imooc.o2o.web.shopadmin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.service.LocalAuthService;
import com.imooc.o2o.util.HttpServletRequestUtil;

/**
* @author xf
* @version 2018年9月15日 下午5:04:42
*/
@Controller
@RequestMapping(value = "/login")
public class LocalAuthMangement {
	@Autowired
	private LocalAuthService localAuthService;
	
	@RequestMapping(value = "/getUserNameAndPassWord", method = RequestMethod.GET )
	private Map<String, Object> getUserNameAndPassWord(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String userName = HttpServletRequestUtil.getString(request, "userName");
		String password = HttpServletRequestUtil.getString(request, "password");
		return modelMap;
	}

}