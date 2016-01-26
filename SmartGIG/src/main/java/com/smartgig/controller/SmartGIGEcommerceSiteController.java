package com.smartgig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.smartgig.constants.*;

@Controller
@RequestMapping(value = "/ecommerce")
public class SmartGIGEcommerceSiteController implements AppConstants, DBConstants, CFConstants{
	
	@RequestMapping(value = "/home")
	public ModelAndView ecommerceHomeOnly() {
		System.out.println("ecommerceHomeOnly()");
		
		ModelAndView model = new ModelAndView();
		model.setViewName(PATH_ECOMMERCE+"home");
		model.addObject("msg", "none");
	
		return model;
	}
}