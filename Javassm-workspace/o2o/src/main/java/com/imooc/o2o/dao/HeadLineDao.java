package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.HeadLine;

/**
* @author xf
* @version 2018年9月7日 下午5:16:36
*/
public interface HeadLineDao {
	
	/**
	 * 根据传入的查询条件（头条名查询头条）
	 * @param lineName
	 * @return
	 */
	List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);
}
