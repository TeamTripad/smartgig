package com.smartgig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartgig.facebook.process.FacebookData;

@Controller
@RequestMapping("/fbconnect")
public class FacebookConnect {

	@RequestMapping(value="/auth/callback", method = RequestMethod.GET)
	public String login(@RequestParam(value="code") String code){
		System.out.println("FacebookConnect --> login()");
		if (code == null || code.equals("")) {
			throw new RuntimeException(
					"ERROR: Didn't get code parameter in callback.");
		}
		else{
			FacebookData data = new FacebookData();
			data.userDetails(code);
			data.userPosts(code);
//			data.userLikes(code);
			return "home";
		}
	}
	public String processData(){
		
		return null;
	}	
}
