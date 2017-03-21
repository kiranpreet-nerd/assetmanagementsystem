package com.nerdapplabs;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nerdapplabs.model.RegisterUser;
import com.nerdapplabs.service.UserServiceImplement;

@Controller
public class UserController {
    
	@Autowired
	private UserServiceImplement userservice;
	
	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public String login() {
		   return "login";
	}
	
	@RequestMapping(value = "/login" , method = RequestMethod.POST )
	public String validateLogin(@ModelAttribute RegisterUser registeruser , BindingResult result , String error , Model model) {
		     RegisterUser user = new RegisterUser();
		     userservice.validateLogin(registeruser, result);
		     if (error != null) { 
		           model.addAttribute("error", "email and password invalid") ; 
		        } else {
		           model.addAttribute("message", "logged in successfully");
		        }
		     return "login";
	}
	
	
	@RequestMapping(value = "/register" , method = RequestMethod.GET)
	public String register() {
		     return "register";
	}
	
	@RequestMapping(value = "/register" , method = RequestMethod.POST)
	public String registeration(@ModelAttribute RegisterUser registeruser, BindingResult result) {
		   RegisterUser user = new RegisterUser();
		   userservice.save(registeruser);
		   userservice.validateRegister(registeruser,result);
		   return "redirect:/login";
	}
	
	@RequestMapping(value = "/forgotpassword")
	public String forgotpassword() {
		     return "forgotpassword";
	}
}
