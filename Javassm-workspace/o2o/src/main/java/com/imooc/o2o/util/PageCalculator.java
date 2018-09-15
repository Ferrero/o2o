package com.imooc.o2o.util;
/**
* @author xf
* @version 2018年8月8日 下午2:43:12
*/
public class PageCalculator {
	public static int calculateRowIndex(int pageIndex, int pageSize) {
		return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
	}
}
