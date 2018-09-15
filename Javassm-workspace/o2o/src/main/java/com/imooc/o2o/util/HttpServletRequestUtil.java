package com.imooc.o2o.util;

import javax.servlet.http.HttpServletRequest;
/**
 * Controller层与View层密不可分，需要接受View层传递过来的信息，
 * 我们使用SSM框架的话，传递的请求信息都存在HttpServletRequest中。
 *  因此需要先封装一个工具类来获取HttPServletRequest中的值。
 * @author xf
 *
 */
public class HttpServletRequestUtil {
	public static int getInt(HttpServletRequest request ,String key) {
		try {
			return Integer.decode(request.getParameter(key));
		}catch(Exception e) {
			return -1;
		}
	}
	
	public static Long getLong(HttpServletRequest request ,String key) {
		try {
			return Long.valueOf(request.getParameter(key));
		}catch(Exception e) {
			return -1L;
		}
	}
	
	public static Double getDouble(HttpServletRequest request ,String key) {
		try {
			return Double.valueOf(request.getParameter(key));
		}catch(Exception e) {
			return -1d;
		}
	}
	
	public static boolean getBoolean(HttpServletRequest request ,String key) {
		try {
			return Boolean.valueOf(request.getParameter(key));
		}catch(Exception e) {
			return false;
		}
	}
	
	public static String getString(HttpServletRequest request ,String key) {
		try {
			String result = request.getParameter(key);
				byte[] b = result.getBytes("ISO-8859-1");
				result = new String(b,"utf-8");

			if(result != null) {
				result = result.trim();
				//trim():去掉字符串首尾空格
			}
			if("".equals(result)) {
				result = null;
			}
			return result;
		}catch(Exception e) {
			return null;
		}
	}
}
