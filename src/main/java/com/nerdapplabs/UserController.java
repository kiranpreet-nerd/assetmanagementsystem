package com.nerdapplabs;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	private static final String EMAIL_PATTERN = ".+@+nerdapplabs+.com";
	 private static final String STRING_PATTERN = "[a-zA-Z]+";
	 private static final String PASSWORD_PATTERN = "[A-Z]+[a-z]+[0-9]+[@._#$&!%]";
			//"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			//+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

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
		return "redirect:/login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView getRegistration() {
		ModelAndView modelandview = new ModelAndView("register");
		return modelandview;
	}
	
	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
		
		if (result.hasErrors()) {
			return "updateuser";
		} else {
			userService.update(user);
		    return "redirect:/users";
		}
		
	}
	
	@RequestMapping(value = "/getUser{email}")
	public String view (@RequestParam String email,Model model) {
		
		model.addAttribute("user", userService.getUser(email));
		return "updateuser";
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) {
		String email = request.getParameter("email");
		userService.softDelete(email);
		return new ModelAndView("redirect:/users");
	}
    
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView getAdd() {
		ModelAndView modelandview = new ModelAndView("add");
		return modelandview;
	}
    
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addUser(@Valid @ModelAttribute("adduser") User user, BindingResult result,
			Map<String, Object> model, Errors errors) {
		ModelAndView modelandview = new ModelAndView("add");
		if (result.hasErrors() || user.getPassword().isEmpty() || user.getConfirm().isEmpty()) {
			errors.rejectValue("password", "required.password", "password is required");
			errors.rejectValue("confirm", "required.password", "confirm password is required");
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
		
		if(!(user.getEmail().isEmpty())){
       	 Pattern pattern = Pattern.compile(EMAIL_PATTERN);
       	 Matcher matcher = pattern.matcher(user.getEmail());
       	 if(!matcher.matches()) {
			  errors.rejectValue("email","domainmatch.email", "domain name must be @nerdapplabs.com");
       	 return modelandview;
       	 }
		
       	 
       	 if(!(user.getFirstname().isEmpty())) {
        	 Pattern patternFirstName = Pattern.compile(STRING_PATTERN);
        	 Matcher matcherFirstName = patternFirstName.matcher(user.getFirstname());
        	 if(!matcherFirstName.matches()) {
        		 errors.rejectValue("firstname", "nonchar.firstname", "First name should contain only alphabets");
        		 return modelandview;
             } 
           }
    	 if(!(user.getLastname().isEmpty())) {
        	 Pattern patternLastName = Pattern.compile(STRING_PATTERN);
        	 Matcher matcherLastName = patternLastName.matcher(user.getLastname());
        	 if(!matcherLastName.matches()) {
        		 errors.rejectValue("lastname", "nonchar.lastname", "Last name should contain only alphabets");
        		 return modelandview;
             } 
           }
    	 if(!(user.getPassword().isEmpty())) {
        	 Pattern patternPassword = Pattern.compile(PASSWORD_PATTERN);
        	 Matcher matcherPassword= patternPassword.matcher(user.getPassword());
        	 if(!matcherPassword.matches()) {
        		 errors.rejectValue("password", "special.password", "password should contain atleast one capital letter, one numeric value , one small letter and special symbol");
        		 return modelandview;
             } 
           }
         
       	 user.setStatus(1);
         userService.save(user);
       	 return new ModelAndView("redirect:/users");
		}

		
		user.setStatus(1);
		userService.save(user);
        ModelAndView modelview = new ModelAndView("redirect:/users");
		return modelview;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registration(@Valid @ModelAttribute("userform") User user, BindingResult result,
			Map<String, Object> model, Errors errors) {
		ModelAndView modelandview = new ModelAndView("register");
		if (result.hasErrors() || user.getPassword().isEmpty() || user.getConfirm().isEmpty()) {
			errors.rejectValue("password", "required.password", "password is required");
			errors.rejectValue("confirm", "required.password", "confirm password is required");
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
		
         if(!(user.getEmail().isEmpty())){
        	 Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        	 Matcher matcher = pattern.matcher(user.getEmail());
        	 if(!matcher.matches()) {
			  errors.rejectValue("email","domainmatch.email", "domain name must be @nerdapplabs.com");
        	 return modelandview;
        	 }
        	 if(!(user.getFirstname().isEmpty())) {
            	 Pattern patternFirstName = Pattern.compile(STRING_PATTERN);
            	 Matcher matcherFirstName = patternFirstName.matcher(user.getFirstname());
            	 if(!matcherFirstName.matches()) {
            		 errors.rejectValue("firstname", "nonchar.firstname", "First name should contain only alphabets");
            		 return modelandview;
                 } 
               }
        	 if(!(user.getLastname().isEmpty())) {
            	 Pattern patternLastName = Pattern.compile(STRING_PATTERN);
            	 Matcher matcherLastName = patternLastName.matcher(user.getLastname());
            	 if(!matcherLastName.matches()) {
            		 errors.rejectValue("lastname", "nonchar.lastname", "Last name should contain only alphabets");
            		 return modelandview;
                 } 
               }
        	 if(!(user.getPassword().isEmpty())) {
            	 Pattern patternPassword = Pattern.compile(PASSWORD_PATTERN);
            	 Matcher matcherPassword= patternPassword.matcher(user.getPassword());
            	 if(!matcherPassword.matches()) {
            		 errors.rejectValue("password", "special.password", "password should contain atleast one capital letter, one numeric value , one small letter and special symbol");
            		 return modelandview;
                 } 
               }
             
        	 user.setStatus(1);
     		 userService.save(user);
        	 return new ModelAndView("redirect:/login");
		}
		
		user.setStatus(1);
		userService.save(user);
        ModelAndView modelview = new ModelAndView("redirect:/login");
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
	
	@RequestMapping(value= "/recoverypassword", method = RequestMethod.POST)
	public String recoverPassword(@ModelAttribute("passwordform") User user, Model model) {
		System.out.println("in controller");
		User tempuser = userService.findByEmail(user.getEmail());
		if(tempuser != null && tempuser.getStatus() == 1) {
		   userService.sendEmail(tempuser);
		   model.addAttribute("emailSuccess","email successfully send");
		   return "forgotpassword";
		} else {
			System.out.println("email not registered");
			model.addAttribute("emailError", "email not registered");
			return "forgotpassword";
		}
	}

	
}
