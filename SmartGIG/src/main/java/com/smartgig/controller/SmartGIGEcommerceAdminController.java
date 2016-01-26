package com.smartgig.controller;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.smartgig.constants.*;
import com.smartgig.database.dao.Admin;
import com.smartgig.database.dao.Category;

import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;

@Controller
@RequestMapping(value = "/admin")
public class SmartGIGEcommerceAdminController implements AppConstants, DBConstants, CFConstants{
	
	/** JZAH 01-21-16 **/
	@RequestMapping(value = {"/home"}, method = RequestMethod.POST)
	public ModelAndView adminLoginToHome( @RequestParam(required = true, value = "username") String u,
									@RequestParam(required = true, value = "password") String p) {
		
		System.out.println("adminLoginToHome(username, password)");
		
		ModelAndView model = new ModelAndView();
		//AUTHENTICATE ADMIN
		//1. get from db (usa ra ka admin nga gehset nato for the min time)
		//>>TODO>> db function boolean isAdmin(username, password){}
		
		//DUMMY DATA ONLY
		Admin admin = DUMMY_ADMIN;
		
		//2. authenticate {(2.a:success), (2.b:failed)}
		if(admin.getUname().contentEquals(u) && admin.getPasswd().contentEquals(DigestUtils.sha512Hex(p))){
			model.addObject("msg", "none");
			model.setViewName(PATH_ECOMMERCE_ADMIN+"home");
			//DUMMY DATA ONLY
			//ArrayList<Category> categoryList = DUMMY_CATEGORY_LIST;
			model.addObject("state","0");
			model.addObject("admin", admin);
			//model.addObject("categoryList", new ArrayList<Category>());//NO CATEGORIES
			model.addObject("categoryList", DUMMY_CATEGORY_LIST);
		}else{//set error message (eh-alert ra tani; redirect to e-commerce home to retry)
			model.addObject("msg", "Administrator login failed. Username and password doesn't match, please try again.");
			model.setViewName(PATH_ECOMMERCE+"home");
		}
		
		return model;
	}
	
	@RequestMapping(value = {"/home"}, method = RequestMethod.GET)
	public ModelAndView adminDashboard( @RequestParam(required = true, value = "adminId") int adminId) {
		
		System.out.println("adminDashboard(adminId)");
		
		ModelAndView model = new ModelAndView();
		//1. get from db (usa ra ka admin nga gehset nato for the min time)
		//>>TODO>> db function Admin getAdminByAdminId(int adminId)
		
		//DUMMY DATA ONLY
		Admin admin = DUMMY_ADMIN;
		
		//2. authenticate {(2.a:success), (2.b:failed)}
		if(admin !=null){
			model.addObject("msg", "none");
			//DUMMY DATA ONLY
			//ArrayList<Category> categoryList = new ArrayList<>();
			model.addObject("state", "1");
			model.addObject("categoryList", DUMMY_CATEGORY_LIST);
			model.setViewName(PATH_ECOMMERCE_ADMIN+"home");
		}else{//set error message (eh-alert ra tani; redirect to e-commerce home to retry)
			model.addObject("msg", "You are not logged in as administrator.");
			model.setViewName(PATH_ECOMMERCE+"home");
		}
		
		return model;
	}
	/** CATEGORY CRUD START**/
	/** ADD NEW CATEGORY **/
	@RequestMapping(value = "/category/add", method = RequestMethod.POST)
	public ModelAndView categoryAdd( @RequestParam(required = true, value = "catName") String catName) {
		
		System.out.println("categoryAdd()");
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("msg", "none");
		model.addObject("state", 1);
		model.addObject("categoryList", DUMMY_CATEGORY_LIST);
		model.setViewName(PATH_ECOMMERCE_ADMIN+"home");
		return model;
	}
	
	/** GO THROUGH A MAIN CATEGORY **/
	@RequestMapping(value = "/category/main/view", method = RequestMethod.GET)
	public ModelAndView viewMainCategory( @RequestParam(required = true, value = "catId") int catId) {
		
		System.out.println("categoryAdd()");
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("msg", "none");
		model.addObject("state", 2);
		model.addObject("subCategoryList", DUMMY_SUB_CATEGORY_LIST);
		model.setViewName(PATH_ECOMMERCE_ADMIN+"home");
		return model;
	}
	/** GO THROUGH A SUBCATEGORY **/
	/**  **/
	/** CATEGORY CRUD END**/
//	@RequestMapping(value = "/loginTemp")
//	public ModelAndView adminLoginTemp() {
//		System.out.println("adminLoginTemp()");
//		
//		ModelAndView model = new ModelAndView();
//		model.setViewName(PATH_ECOMMERCE_ADMIN+"home");
//		return model;
//	}
	/** JZAH 01-21-16 **/
	
	/** TRIXIE 01-21-16 **/
	@RequestMapping(value = "/home")
	public ModelAndView home() {
		System.out.println("admin : home()");
		
		ModelAndView model = new ModelAndView();
		model.setViewName(PATH_ECOMMERCE_ADMIN+"home");
		return model;
	}
	/** TRIXIE 01-21-16 **/
}