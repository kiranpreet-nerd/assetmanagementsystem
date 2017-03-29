package com.nerdapplabs;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.nerdapplabs.service.*;

@Controller
public class UserController {

	@Autowired
	private UserServiceImplement userService;

	@Autowired
	UserService service;

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
		return "redirect:/users";
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

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute("user") User user) {
		userService.update(user);
		return new ModelAndView("redirect:/users");
	}

	@RequestMapping(value = "/editUser")
	public ModelAndView editUser(HttpServletRequest request) {
		String email = request.getParameter("email");
		ModelAndView model = new ModelAndView("updateuser");
		User user = userService.edit(email);
		model.addObject("updateForm", user);
		return model;
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) {
		String email = request.getParameter("email");
		userService.delete(email);
		return new ModelAndView("redirect:/users");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registration(@Valid @ModelAttribute("userform") User user, BindingResult result,
			Map<String, Object> model, Errors errors) {
		ModelAndView modelandview = new ModelAndView("register");
		if (result.hasErrors()) {
			return modelandview;
		}
		User tempUser = userService.findByEmail(user.getEmail());
		if (tempUser != null) {
			((Model) model).addAttribute("emailError", "email already registered");
			return modelandview;
		}
		if (!(user.getPassword().equals(user.getConfirm()))) {
			errors.rejectValue("confirm", "notmatch.password", "passwords doesnt match");
			return modelandview;
		}
		userService.save(user);

		ModelAndView modelview = new ModelAndView("login");
		return modelview;
	}

	@RequestMapping(value = "/users")
	public ModelAndView listUsers(ModelAndView model) throws IOException {

		List<User> listUsers = service.list();
		model.addObject("listUsers", listUsers);
		model.setViewName("userslist");

		return model;
	}

	@RequestMapping(value = "/forgotpassword")
	public String forgotPassword() {
		return "forgotpassword";
	}

}
