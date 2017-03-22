package com.nerdapplabs;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.nerdapplabs.model.User;
import com.nerdapplabs.service.UserServiceImplement;

@Controller
public class UserController {

	@Autowired
	private UserServiceImplement userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Map<String, Object> model) {
		User user = new User();
		model.put("userform", user);
		return "login";
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome() {
		   return "welcome";
	}

	/*
	 * @RequestMapping(value = "/login" , method = RequestMethod.POST ) public
	 * String validateLogin(@ModelAttribute User registerUser , BindingResult
	 * result , String error , Model model) {
	 * userService.validateLogin(registerUser, result); if (error != null) {
	 * model.addAttribute("error", "email and password invalid") ; } else {
	 * model.addAttribute("message", "logged in successfully"); } return
	 * "login"; }
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String validateLogin(@Valid @ModelAttribute("userform") User user, BindingResult result,
			Map<String, Object> model) {
		if (result.hasErrors()) {
			return "login";
		}
		userService.save(user);
		return "redirect:/welcome";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registeration(@Valid @ModelAttribute("userform") User user, BindingResult result,
			Map<String, Object> model) {
		if (result.hasErrors()) {
			return "register";
		}
		userService.save(user);
		return "redirect:/login";
	}

	@RequestMapping(value = "/forgotpassword")
	public String forgotPassword() {
		return "forgotpassword";
	}
}
