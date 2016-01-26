package com.smartgig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/site")
public class SmartGIGSiteController {

	@RequestMapping(value = "/test")
	public String testPage() {
		System.out.println("testPage()");
		return "testPage";
	}
	
	@RequestMapping(value = "/")
	public ModelAndView blankpage1() {
		System.out.println("printHome()");
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}
	@RequestMapping(value = "/sample")
	public ModelAndView sample(){
		System.out.println("sample()");
		ModelAndView model = new ModelAndView();
		model.setViewName("sample");
		return model;
	}
	@RequestMapping(value = "/login")
	public ModelAndView login( @RequestParam(required = true, value = "username") String u,
			   					@RequestParam(required = true, value = "password") String p ) {
		System.out.println("login() --> username : "+u+"password : "+p);
		ModelAndView model = new ModelAndView();
		model.addObject("username", u);
		model.addObject("password", p);
		
		model.setViewName("index");
		return model;
	}
	@RequestMapping(value = "/test1")
	public ModelAndView testPage1() {
		System.out.println("testPage()");
		ModelAndView model = new ModelAndView();
		model.setViewName("testPage");
		return model;
	}
}