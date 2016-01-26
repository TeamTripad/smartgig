package com.smartgig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.smartgig.recommender.collaborative.CFRecommender;

@Controller
@RequestMapping(value = "/cfr")
public class CFRecommenderController {
	
	@RequestMapping(value = "/test")
	public ModelAndView test() {
		System.out.println("CFRecommenderController --> test()");
		CFRecommender.cfRecommenderTest();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("home");
		return model;
	}
}