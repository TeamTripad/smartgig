package com.smartgig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smartgig.constants.AppConstants;
import com.smartgig.constants.DBConstants;

@Controller
@RequestMapping(value = "/admin")
public class SmartGIGEcommerceAdminController implements AppConstants, DBConstants{
	@RequestMapping(value = "/AssociatedWordForPersonality", method = RequestMethod.GET)
	public ModelAndView assiociatedWords() {
		System.out.println("assiociatedWords()");
		ModelAndView model = new ModelAndView();
		
		model.setViewName(PATH_ECOMMERCE_ADMIN+"AssociatedWordForPersonality");
		return model;
	}
	
	@RequestMapping(value = "/associatedWords/add", method = RequestMethod.GET)
	public ModelAndView assiociatedWordsAdd() {
		System.out.println("assiociatedWordsAdd()");
		ModelAndView model = new ModelAndView();
		
		model.setViewName(PATH_ECOMMERCE_ADMIN+"AssociatedWordForPersonality");
		return model;
	}
	
}
