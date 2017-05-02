package com.nerdapplabs;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.validation.Valid;
import com.nerdapplabs.registerEvent.OnRegistrationCompleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.nerdapplabs.model.Asset;
import com.nerdapplabs.model.AssetRequest;
import com.nerdapplabs.dao.AssetDao;
import com.nerdapplabs.model.*;
import com.nerdapplabs.service.*;

@Controller
@SessionAttributes("email")
public class UserController {

	@Autowired
	private UserServiceImplement userService;

	@Autowired
	UserService service;
	

	@Autowired
	AssetDao assetDao;

	@Autowired
	ApplicationEventPublisher eventPublisher;

	@Autowired
	private MessageSource messages;

	int count = 0;
	private static final String EMAIL_PATTERN = ".+@+nerdapplabs+.com";
	private static final String STRING_PATTERN = "[a-zA-Z]+";
	private static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z])[a-zA-Z0-9!@#$%^&*]{6,20}$";

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/asset", method = RequestMethod.GET)
	public ModelAndView asset() {
			ModelAndView model = new ModelAndView();
			
			    List<NewModel> listmodelname = service.listModel();
				model.addObject("listmodelname", listmodelname);
				List<Company> listcompany = service.listCompany();
				model.addObject("listcompany", listcompany);
				List<Supplier> listsupplier = service.listSupplier();
				model.addObject("listsupplier", listsupplier);
				List<Status> liststatus = service.listStatus();
				model.addObject("liststatus", liststatus);
				 model.setViewName("asset");
					return model;
			   
	}
	
	@RequestMapping(value = "/newmodel", method = RequestMethod.GET)
	public String newModelAccessory() {
		return "addmodel";
	}
	
	@RequestMapping(value = "/newcompany", method = RequestMethod.GET)
	public String newCompany() {
		return "addcompany";
	}
	
	@RequestMapping(value = "/newmodelaccessory", method = RequestMethod.GET)
	public String newModelConsumable() {
		return "addmodelaccessory";
	}

	@RequestMapping(value = "/newmodelconsumable", method = RequestMethod.GET)
	public String newModel() {
		return "addmodelconsumable";
	}


	@RequestMapping(value = "/newsupplier", method = RequestMethod.GET)
	public String newSupplier() {
		return "addsupplier";
	}

	@RequestMapping(value = "/newstatus", method = RequestMethod.GET)
	public String newStatus() {
		return "addstatus";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user, @RequestParam String email, @RequestParam String password, HttpSession session,
			Model model, HttpServletRequest request) {

		user = userService.loginUser(email, password);
		try {
			if (user == null) {
				model.addAttribute("loginError", "Error Loggin in , please try again");
				return "login";
			}

			if (user.getRole().contains("ROLE_SUPER")) {
				session.setAttribute("loggedInUser", user);
				return "redirect:/users";
			} else if (user.getRole().contains("ROLE_ADMIN")) {
				session.setAttribute("email", email);
				return "redirect:/requestedassets";
			} else if (user.getRole().contains("ROLE_USER")) {
				session.setAttribute("email", email);
				return "redirect:/requested";
			} else {
				return "login";

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("loggedInUser");
		session.removeAttribute("email");
		session.setMaxInactiveInterval(10);
		return "login";
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

	@RequestMapping(value = "/users")
	public ModelAndView listUsers(ModelAndView model) throws IOException {

		List<User> listUsers = service.list();
		model.addObject("listUsers", listUsers);
		model.setViewName("userslist");

		return model;
	}
	
	@RequestMapping(value = "/getUser{email}")
	public String view(@RequestParam String email, Model model) {
		model.addAttribute("user", userService.getUser(email));
		return "updateuser";
	}

	@RequestMapping(value = "/editasset{id}", method = RequestMethod.GET)
	public ModelAndView updateAsset() {
			ModelAndView model = new ModelAndView();
			
			    List<NewModel> listmodelname = service.listModel();
				model.addObject("listmodelname", listmodelname);
				List<Company> listcompany = service.listCompany();
				model.addObject("listcompany", listcompany);
				List<Supplier> listsupplier = service.listSupplier();
				model.addObject("listsupplier", listsupplier);
				List<Status> liststatus = service.listStatus();
				model.addObject("liststatus", liststatus);
				 model.setViewName("updateasset");
					return model;
			   
	}
	
	@RequestMapping(value = "/getAsset{id}")
	public ModelAndView EditAsset( Model model,HttpServletRequest request) {
		
		Long id = (long) Integer.parseInt(request.getParameter("id"));
		model.addAttribute("updateasset", userService.getAsset(id));
		ModelAndView modelview = new ModelAndView();
		
		List<NewModel> listmodelname = service.listModel();
		modelview.addObject("listmodelname", listmodelname);
		List<Supplier> listsupplier = service.listSupplier();
		modelview.addObject("listsupplier", listsupplier);
		List<Status> liststatus = service.listStatus();
		modelview.addObject("liststatus", liststatus);
		modelview.setViewName("updateasset");
		return modelview;
	}
	
	@RequestMapping(value = "/updateasset", method = RequestMethod.POST)
	public String updateAsset(@ModelAttribute("updateasset") Asset asset, HttpServletRequest request) {

		    Long id = (long) Integer.parseInt(request.getParameter("id"));
			userService.updateAsset(asset,id);
			return "redirect:/assetslist";
	

	}

	@RequestMapping(value = "/requestedassetslist{email}")
	public ModelAndView listAssets(@ModelAttribute("user") User user, @RequestParam String email,
			ModelAndView modelview) throws IOException {
		user = userService.getUser(email);
		List<AssetRequest> listAssets = service.listAsset(user);
		modelview.addObject("listAssets", listAssets);
		modelview.setViewName("requestedassetslist");
		return modelview;

	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) {
		String email = request.getParameter("email");
		userService.softDelete(email);
		return new ModelAndView("redirect:/users");
	}
	
	@RequestMapping(value = "/deleterequestedassets", method = RequestMethod.GET)
	public ModelAndView deleteRequestedAssets(HttpServletRequest request) {
		 Long id = (long) Integer.parseInt(request.getParameter("id"));
		userService.deleteAssetRequest(id);
		return new ModelAndView("redirect:/requested");
	}
	
	@RequestMapping(value = "/deleteAsset", method = RequestMethod.GET)
	public ModelAndView deleteAsset(HttpServletRequest request) {
		Long id = (long) Integer.parseInt(request.getParameter("id"));
		userService.deleteAsset(id);
		return new ModelAndView("redirect:/assetslist");
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
		if (result.hasErrors()) {
			return modelandview;
		}
		User tempUser = userService.findByEmail(user.getEmail());
		if (tempUser != null) {
			((Model) model).addAttribute("emailError", "email already registered");
			return modelandview;
		}

		if (!(user.getEmail().isEmpty())) {
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(user.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "domainmatch.email", "domain name must be @nerdapplabs.com");
				return modelandview;
			}
		if (!(user.getPassword().isEmpty())) {
				Pattern patternPassword = Pattern.compile(PASSWORD_PATTERN);
				Matcher matcherPassword = patternPassword.matcher(user.getPassword());
				if (!matcherPassword.matches()) {
					errors.rejectValue("password", "special.password",
							"password should contain atleast one capital letter, one numeric value , one small letter ,special symbol and password length must be between 6 to 20 characters");
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
		User tempUser = userService.findByEmail(user.getEmail());
		if (tempUser != null) {
			((Model) model).addAttribute("emailError", "email already registered");
			return modelandview;
		}

		if (!(user.getEmail().isEmpty())) {
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(user.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "domainmatch.email", "domain name must be @nerdapplabs.com");
				return modelandview;
			}
		}
		if (!(user.getPassword().isEmpty())) {
			Pattern patternPassword = Pattern.compile(PASSWORD_PATTERN);
			Matcher matcherPassword = patternPassword.matcher(user.getPassword());
			if (!matcherPassword.matches()) {
				errors.rejectValue("password", "special.password",
						"password should contain atleast one numeric value,special symbol,one small letter, one capital letter and password length must be between 6 to 20 characters");
				return modelandview;
			}
		}

		user.setStatus(0);
		userService.save(user);
		try {
			String appUrl = request.getContextPath();
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, appUrl));
		} catch (Exception e) {
			((Model) model).addAttribute("emailError", "error in sending email");
			return modelandview;
		}

		ModelAndView modelview = new ModelAndView("redirect:/login");
		return modelview;
	}
	
	@RequestMapping(value = "/asset", method = RequestMethod.POST)
	public ModelAndView Asset(@ModelAttribute("asset") Asset asset, HttpServletRequest request, Model model,@RequestParam String serialnumber,@RequestParam String tag, @RequestParam String ordernumber) {
		
		List<Asset> assetAttributesList = userService.uniqueAttribute();
		for(int i=0; i < assetAttributesList.size(); i++) {
			if(assetAttributesList.get(i).getSerialnumber().equals(serialnumber)){
			model.addAttribute("serialError","serial number already exsist");
			return new ModelAndView("redirect:/asset");
			} else if(assetAttributesList.get(i).getTag().equals(tag)) {
				model.addAttribute("tagError","Asset Tag already exsist");
				return new ModelAndView("redirect:/asset");
			} else if(assetAttributesList.get(i).getOrdernumber().equals(ordernumber)) {
				model.addAttribute("orderError","Order Number already exsist");
				return new ModelAndView("redirect:/asset");
			}
		}
		userService.addAsset(asset);
		ModelAndView modelview = new ModelAndView("redirect:/requestedassets");
		return modelview;
	}
	
	@RequestMapping(value = "/modelaccessory", method = RequestMethod.POST)
	public ModelAndView addModelAccessory(@ModelAttribute("newmodelaccessory") ModelAccessory modelaccessory) {
		userService.addModelAccessory(modelaccessory);
		ModelAndView model = new ModelAndView("redirect:/listmodelaccessory");
		return model;
	}
	
	@RequestMapping(value = "/modelconsumable", method = RequestMethod.POST)
	public ModelAndView addModelConsumable(@ModelAttribute("newmodelconsumable") ModelConsumable modelconsumable) {
		userService.addModelConsumable(modelconsumable);
		ModelAndView model = new ModelAndView("redirect:/listmodelconsumable");
		return model;
	}

	@RequestMapping(value = "/newmodel", method = RequestMethod.POST)
	public ModelAndView addModel(@ModelAttribute("newmodel") NewModel newmodel) {
		userService.addModel(newmodel);
		ModelAndView model = new ModelAndView("redirect:/listmodel");
		return model;
	}

	@RequestMapping(value = "/listmodel")
	public ModelAndView listModel(ModelAndView model) throws IOException {
		List<NewModel> listmodel = service.listModel();
		model.addObject("listmodel", listmodel);
		ModelAndView modelview = new ModelAndView("redirect:/asset");
		return modelview;
 }
	
	@RequestMapping(value = "/listmodelaccessory")
	public ModelAndView listModelAccessory(ModelAndView model) throws IOException {
		List<ModelAccessory> listmodelaccessory = service.listModelAccessory();
		model.addObject("listmodelaccessory", listmodelaccessory);
		ModelAndView modelview = new ModelAndView("redirect:/asset");
		return modelview;
 }
	
	@RequestMapping(value = "/listmodelconsumable")
	public ModelAndView listModelConsumable(ModelAndView model) throws IOException {
		List<ModelConsumable> listmodelconsumable = service.listModelConsumable();
		model.addObject("listmodelconsumable", listmodelconsumable);
		ModelAndView modelview = new ModelAndView("redirect:/asset");
		return modelview;
 }
	
	
	@RequestMapping(value = "/newsupplier", method = RequestMethod.POST)
	public ModelAndView addSupplier(@ModelAttribute("supplier") Supplier supplier) {
		userService.addSupplier(supplier);
		ModelAndView model = new ModelAndView("redirect:/listsupplier");
		return model;
	}
	
	@RequestMapping(value = "/listsupplier")
	public ModelAndView listSupplier(ModelAndView model) throws IOException {
		List<Supplier> listsupplier = service.listSupplier();
		model.addObject("listsupplier", listsupplier);
		ModelAndView modelview = new ModelAndView("redirect:/asset");
		return modelview;
	}
	
	@RequestMapping(value = "/newstatus", method = RequestMethod.POST)
	public ModelAndView addStatus(@ModelAttribute("status") Status status) {
		userService.addStatus(status);
		ModelAndView model = new ModelAndView("redirect:/liststatus");
		return model;
	}
	
	@RequestMapping(value = "/liststatus")
	public ModelAndView listStatus(ModelAndView model) throws IOException {
		List<Status> liststatus = service.listStatus();
		model.addObject("liststatus", liststatus);
		ModelAndView modelview = new ModelAndView("redirect:/asset");
		return modelview;
	}
	
	@RequestMapping(value = "/newcompany", method = RequestMethod.POST)
	public ModelAndView addCompany(@ModelAttribute("newcompany") Company company) {
		userService.addCompany(company);
		ModelAndView model = new ModelAndView("redirect:/listcompany");
		return model;
	}
	
	@RequestMapping(value = "/listcompany")
	public ModelAndView listCompany(ModelAndView model) throws IOException {
		List<Company> listcompany = service.listCompany();
		model.addObject("listcompany", listcompany);
		ModelAndView modelview = new ModelAndView("redirect:/asset");
		return modelview;
	}

	@RequestMapping(value = "/requested", method = RequestMethod.GET)
	public ModelAndView listAssetsRequest(ModelAndView model, HttpSession session, Model modelview) throws IOException {
		session.getAttribute("email");
		List<AssetRequest> listAssetsRequest = service.listAssetsRequest(session);
		model.addObject("listAssetsRequest", listAssetsRequest);
		model.setViewName("assetrequest");
		return model;
	}
	
	@RequestMapping(value = "/assetrequest", method = RequestMethod.POST)
	public ModelAndView requestAsset(@RequestParam String email,
			@Valid @ModelAttribute("assetrequest") AssetRequest assetrequest, BindingResult result,
			Map<String, Object> model, Errors errors, HttpSession session, User user) {
		session.getAttribute("email");
        ModelAndView view = new ModelAndView("assetrequest");
		if (result.hasErrors()) {
			List<AssetRequest> listAssetsRequest = service.listAssetsRequest(session);
			view.addObject("listAssetsRequest", listAssetsRequest);
			view.setViewName("assetrequest");
			return view;
		}
		userService.saveAsset(assetrequest, user);
		List<AssetRequest> listAssetsRequest = service.listAssetsRequest(session);
		view.addObject("listAssetsRequest", listAssetsRequest);
		view.setViewName("assetrequest");
		return view;

	}

	@RequestMapping(value = "/registrationconfirm", method = RequestMethod.GET)
	public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {

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

	@RequestMapping(value = "/requestedassets")
	public ModelAndView listEmail(ModelAndView model) throws IOException {

		List<User> listEmail = service.listEmail();
		model.addObject("listEmail", listEmail);
		model.setViewName("requestedassets");
		return model;

	}
	
	@RequestMapping(value = "/assetslist")
	public ModelAndView listAssets(ModelAndView model) throws IOException {

		List<Asset> listAssets = service.listAssets();
		model.addObject("listAssets", listAssets);
		model.setViewName("assetslist");
		return model;

	}

	@RequestMapping(value = "/forgotpassword")
	public String forgotPassword() {
		return "forgotpassword";
	}

	@RequestMapping(value = "/recoverypassword", method = RequestMethod.POST)
	public String recoverPassword(@ModelAttribute("passwordform") User user, Model model, @RequestParam String email) {
		System.out.println("in controller");
		User tempuser = userService.findByEmail(user.getEmail());
		if (tempuser != null && tempuser.getStatus() == 1) {
			userService.sendEmail(email);
			model.addAttribute("emailError", "email send successfully");
			return "forgotpassword";
		} else if (tempuser == null) {
			model.addAttribute("emailError", "valid email required");
			return "forgotpassword";
		} else if (tempuser.getStatus() == 0) {
			System.out.println("email not registered");
			model.addAttribute("emailError", "email not registered");
			return "forgotpassword";
		} else {
			return "forgotpassword";
		}
	}

}
