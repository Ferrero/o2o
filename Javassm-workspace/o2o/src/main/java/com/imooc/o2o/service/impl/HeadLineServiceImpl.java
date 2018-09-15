package com.imooc.o2o.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.o2o.dao.HeadLineDao;
import com.imooc.o2o.entity.HeadLine;
import com.imooc.o2o.service.HeadLineService;

/**
* @author xf
* @version 2018年9月8日 上午11:29:40
*/
@Service
public class HeadLineServiceImpl implements HeadLineService {
	
	@Autowired
	private HeadLineDao headLineDao;
	@Override
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
		
		
		return headLineDao.queryHeadLine(headLineCondition);
	}

}
