package com.imooc.MavenDemo.service;

import java.util.List;

import com.imooc.MavenDemo.entity.Score;

/**
* @author xf
* @version 2018年7月27日 上午11:32:08
*/
public interface ScoreService {
	List<Score> queryScore(Score score);
	void insertScore();
}
