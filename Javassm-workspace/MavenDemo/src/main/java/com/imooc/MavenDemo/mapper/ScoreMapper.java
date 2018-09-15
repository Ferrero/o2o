package com.imooc.MavenDemo.mapper;

import java.util.List;

import com.imooc.MavenDemo.entity.Score;

/**
* @author xf
* @version 2018年7月27日 上午10:52:48
*/
public interface ScoreMapper {
	
	List<Score> queryScore(Score score);
	void insertScore();
}
