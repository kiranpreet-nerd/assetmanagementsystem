package com.nerdapplabs;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
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
import org.springframework.web.bind.annotation.ModelAttribute;
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
	private static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z])[a-zA-Z0-9!@#$%^&*]{6,20}$";

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/asset", method = RequestMethod.GET)
	public ModelAndView asset(@ModelAttribute("asset") Asset asset) {
		ModelAndView model = new ModelAndView();

		List<NewModel> listmodelname = service.listModel();
		model.addObject("listmodelname", listmodelname);
		List<ModelAccessory> listmodelaccessory = service.listModelAccessory();
		model.addObject("listmodelaccessory", listmodelaccessory);
		List<ModelConsumable> listmodelconsumable = service.listModelConsumable();
		model.addObject("listmodelconsumable", listmodelconsumable);
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
				session.setMaxInactiveInterval(600);
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

	@RequestMapping(value = "/getAsset{id}")
	public ModelAndView editAsset(Model model, HttpServletRequest request) {

		Long id = (long) Integer.parseInt(request.getParameter("id"));
		model.addAttribute("asset", userService.getAsset(id));
		ModelAndView modelview = new ModelAndView();

		List<NewModel> listmodelname = service.listModel();
		modelview.addObject("listmodelname", listmodelname);
		List<ModelAccessory> listmodelaccessory = service.listModelAccessory();
		modelview.addObject("listmodelaccessory", listmodelaccessory);
		List<ModelConsumable> listmodelconsumable = service.listModelConsumable();
		modelview.addObject("listmodelconsumable", listmodelconsumable);
		List<Company> listcompany = service.listCompany();
		modelview.addObject("listcompany", listcompany);
		List<Supplier> listsupplier = service.listSupplier();
		modelview.addObject("listsupplier", listsupplier);
		List<Status> liststatus = service.listStatus();
		modelview.addObject("liststatus", liststatus);
		modelview.setViewName("asset");
		return modelview;
	}

	@RequestMapping(value = "/asset", method = RequestMethod.POST)
	public ModelAndView asset(@ModelAttribute("asset") Asset asset, HttpServletRequest request, Model model,
			@RequestParam Long id) {

		List<Asset> assetAttributesList = userService.uniqueAttribute();
		List<Asset> registeredAttributesList = userService.registeredAttribute(id);

		if (asset.getId() != id) {
			for (int i = 0; i < assetAttributesList.size(); i++) {
				if (assetAttributesList.get(i).getSerialnumber().equals(asset.getSerialnumber())) {
					model.addAttribute("serialError", "serial number already exsist");
					return new ModelAndView("redirect:/asset");
				} else if (assetAttributesList.get(i).getTag().equals(asset.getTag())) {
					model.addAttribute("tagError", "Asset Tag already exsist");
					return new ModelAndView("redirect:/asset");
				} else if (assetAttributesList.get(i).getOrdernumber().equals(asset.getOrdernumber())) {
					model.addAttribute("orderError", "Order Number already exsist");
					return new ModelAndView("redirect:/asset");
				}
			}
			userService.addAsset(asset);
			ModelAndView modelview = new ModelAndView("redirect:/requestedassets");
			return modelview;
		} else {
			for (int i = 0; i < registeredAttributesList.size(); i++) {
				if (registeredAttributesList.get(i).getSerialnumber().equals(asset.getSerialnumber())) {
					model.addAttribute("serialError", "serial number already exsist");
					return new ModelAndView("redirect:/getAsset?id=" + asset.getId());
				} else if (registeredAttributesList.get(i).getOrdernumber().equals(asset.getOrdernumber())) {
					model.addAttribute("orderError", "order number already exsist");
					return new ModelAndView("redirect:/getAsset?id=" + asset.getId());
				} else if (registeredAttributesList.get(i).getTag().equals(asset.getTag())) {
					model.addAttribute("tagError", "Asset Tag already exsist");
					return new ModelAndView("redirect:/getAsset?id=" + asset.getId());
				}
			}
			userService.updateAsset(asset, id);
			return new ModelAndView("redirect:/assetslist");
		}
	}

	@RequestMapping(value = "/requestedassetslist{email}")
	public ModelAndView listAssets(@ModelAttribute("user") User user,
			ModelAndView modelview) throws IOException {
		user = userService.getUser(user.getEmail());
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

	@RequestMapping(value = "/modelaccessory", method = RequestMethod.POST)
	public ModelAndView addModelAccessory(@ModelAttribute("newmodelaccessory") ModelAccessory modelaccessory,
			Model modelObject) {
		List<ModelAccessory> listmodelaccessory = service.listModelAccessory();
		for (int i = 0; i < listmodelaccessory.size(); i++) {
			if (listmodelaccessory.get(i).getModel().equals(modelaccessory.getModel())) {
				modelObject.addAttribute("model", "model name already exsist");
				return new ModelAndView("redirect:/modelaccessory");
			}
		}
		userService.addModelAccessory(modelaccessory);
		ModelAndView modelview = new ModelAndView("redirect:/listmodelaccessory");
		return modelview;
	}

	@RequestMapping(value = "/modelconsumable", method = RequestMethod.POST)
	public ModelAndView addModelConsumable(@ModelAttribute("newmodelconsumable") ModelConsumable modelconsumable,
			Model modelObject) {
		List<ModelConsumable> listmodelconsumable = service.listModelConsumable();
		for (int i = 0; i < listmodelconsumable.size(); i++) {
			if (listmodelconsumable.get(i).getModel().equals(modelconsumable.getModel())) {
				modelObject.addAttribute("model", "model name already exsist");
				return new ModelAndView("redirect:/modelconsumable");
			}
		}
		userService.addModelConsumable(modelconsumable);
		ModelAndView modelview = new ModelAndView("redirect:/listmodelconsumable");
		return modelview;
	}

	@RequestMapping(value = "/newmodel", method = RequestMethod.POST)
	public ModelAndView addModel(@ModelAttribute("newmodel") NewModel newmodel, Model modelObject) {
		List<NewModel> listmodel = service.listModel();
		for (int i = 0; i < listmodel.size(); i++) {
			if (listmodel.get(i).getModel().equals(newmodel.getModel())) {
				modelObject.addAttribute("model", "model name already exsist");
				return new ModelAndView("redirect:/newmodel");
			}
		}
		userService.addModel(newmodel);
		ModelAndView modelview = new ModelAndView("redirect:/listmodel");
		return modelview;
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
	public ModelAndView addSupplier(@ModelAttribute("supplier") Supplier supplier,
			Model model) {
		List<Supplier> listsupplier = service.listSupplier();
		for (int i = 0; i < listsupplier.size(); i++) {
			if (listsupplier.get(i).getSupplier().equals(supplier.getSupplier())) {
				model.addAttribute("supplier", "supplier already exsist");
				return new ModelAndView("redirect:/newsupplier");
			}
		}
		userService.addSupplier(supplier);
		ModelAndView modelview = new ModelAndView("redirect:/listsupplier");
		return modelview;
	}

	@RequestMapping(value = "/listsupplier")
	public ModelAndView listSupplier(ModelAndView model) throws IOException {
		List<Supplier> listsupplier = service.listSupplier();
		model.addObject("listsupplier", listsupplier);
		ModelAndView modelview = new ModelAndView("redirect:/asset");
		return modelview;
	}

	@RequestMapping(value = "/newstatus", method = RequestMethod.POST)
	public ModelAndView addStatus(@ModelAttribute("status") Status status,
			Model model) {
		List<Status> liststatus = service.listStatus();
		for (int i = 0; i < liststatus.size(); i++) {
			if (liststatus.get(i).getStatus().equals(status.getStatus())) {
				model.addAttribute("status", "status already exsist");
				return new ModelAndView("redirect:/newstatus");
			}
		}
		userService.addStatus(status);
		ModelAndView modelview = new ModelAndView("redirect:/liststatus");
		return modelview;
	}

	@RequestMapping(value = "/liststatus")
	public ModelAndView listStatus(ModelAndView model) throws IOException {
		List<Status> liststatus = service.listStatus();
		model.addObject("liststatus", liststatus);
		ModelAndView modelview = new ModelAndView("redirect:/asset");
		return modelview;
	}

	@RequestMapping(value = "/newcompany", method = RequestMethod.POST)
	public ModelAndView addCompany(@ModelAttribute("newcompany") Company company,
			Model model) {
		List<Company> listcompany = service.listCompany();
		for (int i = 0; i < listcompany.size(); i++) {
			if (listcompany.get(i).getCompany().equals(company.getCompany())) {
				model.addAttribute("company", "company name already exsist");
				return new ModelAndView("redirect:/newcompany");
			}
		}
		userService.addCompany(company);
		ModelAndView modelview = new ModelAndView("redirect:/listcompany");
		return modelview;
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
		List<NewModel> listmodelname = service.listModel();
		model.addObject("listmodelname", listmodelname);
		List<ModelAccessory> listmodelaccessory = service.listModelAccessory();
		model.addObject("listmodelaccessory", listmodelaccessory);
		List<ModelConsumable> listmodelconsumable = service.listModelConsumable();
		model.addObject("listmodelconsumable", listmodelconsumable);
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
		List<NewModel> listmodelname = service.listModel();
		view.addObject("listmodelname", listmodelname);
		List<ModelAccessory> listmodelaccessory = service.listModelAccessory();
		view.addObject("listmodelaccessory", listmodelaccessory);
		List<ModelConsumable> listmodelconsumable = service.listModelConsumable();
		view.addObject("listmodelconsumable", listmodelconsumable);

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
	public String recoverPassword(@ModelAttribute("passwordform") User user, Model model) {
		User tempuser = userService.findByEmail(user.getEmail());
		if (tempuser != null && tempuser.getStatus() == 1) {
			userService.sendEmail(user.getEmail());
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
