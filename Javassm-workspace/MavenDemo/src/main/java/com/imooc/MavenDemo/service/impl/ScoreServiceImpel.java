package com.imooc.MavenDemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.MavenDemo.entity.Score;
import com.imooc.MavenDemo.mapper.ScoreMapper;
import com.imooc.MavenDemo.service.ScoreService;

/**
* @author xf
* @version 2018年7月27日 上午11:32:51
*/
@Service
public class ScoreServiceImpel implements ScoreService {
	@Autowired
	private ScoreMapper  scoreMapper;
	
	@Override
	public List<Score> queryScore(Score score) {
		return scoreMapper.queryScore(score);
	}

	@Override
	public void insertScore() {
		scoreMapper.insertScore();
		
	}

}
