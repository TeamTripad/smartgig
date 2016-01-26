package com.smartgig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.smartgig.constants.AppConstants;
import com.smartgig.constants.CFConstants;
import com.smartgig.constants.DBConstants;

@Controller
@RequestMapping(value = "/site")
public class SmartGIGSiteController implements AppConstants, DBConstants, CFConstants{

/** HOME PAGE TRAVERSALS*********************************************************/
	@RequestMapping(value = "/page")
	public ModelAndView site() {
		System.out.println("site()");
		
		ModelAndView model = new ModelAndView();
		model.setViewName(PATH_SMARTGIG+"homepage");
		return model;
	}
	@RequestMapping(value = "/home")
	public ModelAndView homeOnly() {
		System.out.println("homeOnly()");
		
		ModelAndView model = new ModelAndView();
		model.addObject("state", "0");//DEFAULT : NOTIFICATION
		model.setViewName(PATH_SMARTGIG+"home");
		return model;
	}
	
	@RequestMapping(value = "/home/notifications")
	public ModelAndView homeNotif() {
		System.out.println("homeNotif()");
		
		ModelAndView model = new ModelAndView();
		model.addObject("state", "1");
		model.setViewName(PATH_SMARTGIG+"home");
		return model;
	}
	@RequestMapping(value = "/home/calendar")
	public ModelAndView homeCalendar() {
		System.out.println("homeCalendar()");
		//insert sa db
		ModelAndView model = new ModelAndView();
		model.addObject("state", "2");
		model.setViewName(PATH_SMARTGIG+"home");
		return model;
	}
	@RequestMapping(value = "/home/contacts")
	public ModelAndView homeContacts() {
		System.out.println("homeContacts()");
		
		ModelAndView model = new ModelAndView();
		model.addObject("state", "3");
		model.setViewName(PATH_SMARTGIG+"home");
		return model;
	}
/** HOME PAGE TRAVERSALS*********************************************************/
	
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