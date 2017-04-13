package com.nerdapplabs;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.nerdapplabs.registerEvent.OnRegistrationCompleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import com.nerdapplabs.model.User;
import com.nerdapplabs.model.VerificationToken;
import com.nerdapplabs.service.*;


@Controller
public class UserController {

	@Autowired
	private UserServiceImplement userService;

	@Autowired
	UserService service;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	 @Autowired
	    private MessageSource messages;
	  
	int count = 0;
	private static final String EMAIL_PATTERN = ".+@+nerdapplabs+.com";
	 private static final String STRING_PATTERN = "[a-zA-Z]+";
	 private static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z])[a-zA-Z0-9!@#$%^&*]{6,20}$";
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
	
	String email1 = "abc";
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model, HttpServletRequest request) {
        
		User user = userService.loginUser(email, password);
	  try {
		if (user == null) {
			model.addAttribute("loginError", "Error Loggin in , please try again");
			return "login";
		}
		session.setAttribute("loggedInUser", user);
		return "redirect:/users";
	  } catch (Exception e) {
		  throw new RuntimeException(e);
	  }
		
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
        		 errors.rejectValue("password", "special.password", "password should contain atleast one capital letter, one numeric value , one small letter ,special symbol and password length must be between 6 to 20 characters");
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
			Map<String, Object> model, Errors errors, WebRequest request) {
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
            		 errors.rejectValue("password", "special.password", "password should contain atleast one numeric value,special symbol,one small letter, one capital letter and password length must be between 6 to 20 characters");
            		 return modelandview;
                 } 
               }
		
		user.setStatus(0);
		userService.save(user);
		try {
			 String appUrl = request.getContextPath();
	         eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, appUrl));
		} catch (Exception e) {
			((Model) model).addAttribute("emailError", "email already registered");
			 return modelandview; 
		}
       
		ModelAndView modelview = new ModelAndView("redirect:/login");
		return modelview;
	}
	

	@RequestMapping(value = "/registrationconfirm", method = RequestMethod.GET)
	public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {
		//Locale locale = request.getLocale();
		
		VerificationToken verificationToken = userService.getVerificationToken(token);
		if (verificationToken == null) {
	        String message = messages.getMessage("auth.message.invalidToken", null, null);
	        model.addAttribute("message", message);
	        return "redirect:/baduser";
	    }
		
		User user = verificationToken.getUser();
	    Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	        String messageValue = messages.getMessage("auth.message.expired", null, null);
	        model.addAttribute("message", messageValue);
	        return "redirect:/baduser";
	        
	    } 
	    
	    user.setStatus(1); 
        userService.save(user); 
        return "redirect:/login";
		
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
	public String recoverPassword(@ModelAttribute("passwordform") User user, Model model, @RequestParam String email) {
		System.out.println("in controller");
		User tempuser = userService.findByEmail(user.getEmail());
		if(tempuser != null && tempuser.getStatus() == 1) {
		   userService.sendEmail(email);
		   model.addAttribute("emailError","email successfully send");
		   return "forgotpassword";
		} else if(tempuser == null ){
			model.addAttribute("emailError", "valid email required");
			return "forgotpassword";
		} else if(tempuser.getStatus() == 0) {
			System.out.println("email not registered");
			model.addAttribute("emailError", "email not registered");
			return "forgotpassword";
		} else {
			return "forgotpassword";
		}
	}

	
}
