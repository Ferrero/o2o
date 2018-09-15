package com.imooc.MavenDemo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.MavenDemo.entity.Score;
import com.imooc.MavenDemo.service.ScoreService;

/**
 * @author xf
 * @version 2018年7月27日 下午2:35:43
 */
@Controller
@RequestMapping(value = "score", method = RequestMethod.GET)
//@ResponseBody
public class ScoreController {
	private static final Logger logger = LoggerFactory.getLogger(ScoreController.class);
	@Autowired
	ScoreService scoreService;

	@RequestMapping(value = "/scoreOperation")
	public String scoreOperation() {

		return "/scoreOperation";
	}

	@RequestMapping(value = "/scoreInfoList", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	/*
	 * public ModelAndView query(@RequestParam(value="studentId",
	 * required=false)Integer studentId) { System.out.println(studentId);
	 * ModelAndView m = new ModelAndView("score"); Score score = new Score();
	 * score.setStudentId(studentId); m.addObject("scoreInfoList",
	 * scoreService.queryScore(score)); return m;
	 * 
	 * }
	 */
	@ResponseBody
	public Map<String, Object> query(@RequestParam(value = "studentId", required = false) Integer studentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Score score = new Score();
		score.setStudentId(studentId);
		System.out.println(studentId);
		List<Score> scoreInfoList = new ArrayList<Score>();
		try {
			scoreInfoList = scoreService.queryScore(score);
			map.put("scoreInfoList", scoreInfoList);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;

		}
		return map;
	}
	
	@RequestMapping(value="insert",method = RequestMethod.GET)
	public Map<String,Object> insert(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		return map;
	}
}
