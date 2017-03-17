package com.nerdapplabs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    
	@RequestMapping(value = "/register")
	public String registration() {
		     return "register";
	}
}
