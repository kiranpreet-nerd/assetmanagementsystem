package com.nerdapplabs;

import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nerdapplabs.model.User;
import com.nerdapplabs.service.UserServiceImplement;

@Controller
public class UserController {

	@Autowired
	private UserServiceImplement userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome() {
		return "welcome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {

		User user = userService.loginUser(email, password);
		if (user == null) {
			model.addAttribute("loginError", "Error Loggin in , please try again");
			return "login";
		}
		session.setAttribute("loggedInUser", user);
		return "redirect:/welcome";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("loggedInUser");
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView getRegistration() {
		ModelAndView modelandview = new ModelAndView("register");
		return modelandview;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registration(@Valid @ModelAttribute("userform") User user, BindingResult result,
			Map<String, Object> model, Errors errors) {
		ModelAndView modelandview = new ModelAndView("register");
		// String returnVal = "redirect:/login";
		if (result.hasErrors()) {
			return modelandview;
		}
		User tempUser = userService.findByEmail(user.getEmail());
		if (tempUser != null) {
			((Model) model).addAttribute("emailError", "email already registered");
			return modelandview;
		} else if (!(user.getPassword().equals(user.getConfirm()))) {
			errors.rejectValue("confirm", "notmatch.password", "passwords doesnt match");
			return modelandview;
		} else {
			userService.save(user);
		}
		ModelAndView modelview = new ModelAndView("login");
		return modelview;
	}

	@RequestMapping(value = "/forgotpassword")
	public String forgotPassword() {
		return "forgotpassword";
	}

}
