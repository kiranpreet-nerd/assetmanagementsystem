package com.nerdapplabs;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.nerdapplabs.model.Asset;
import com.nerdapplabs.model.AssetRequest;
import com.nerdapplabs.dao.AssetDao;
import com.nerdapplabs.dao.VerificationTokenRepository;
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
	VerificationTokenRepository tokenDao;

	@Autowired
	ApplicationEventPublisher eventPublisher;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private MessageSource messages;

	int count = 0;
	private static final String EMAIL_PATTERN = ".+@+nerdapplabs+.com";
	private String temp;
	private Long tempid;

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

	@RequestMapping(value = "/changePassword{email}", method = RequestMethod.GET)
	public String changePasssword(@ModelAttribute("changepassword") User user) {
		return "changepassword";
	}

	@RequestMapping(value = "/newcompany", method = RequestMethod.GET)
	public String newCompany() {
		return "addcompany";
	}
	
	@RequestMapping(value = "/confirmationLink", method = RequestMethod.GET)
	public String confirmationLink() {
		return "confirmationlink";
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
				session.setAttribute("email", email);
				session.setMaxInactiveInterval(600);
				return "redirect:/users?email=" + email;
			} else if (user.getRole().contains("ROLE_ADMIN")) {
				session.setAttribute("email", email);
				session.setMaxInactiveInterval(600);
				return "redirect:/requestedassets?email=" + email;
			} else if (user.getRole().contains("ROLE_USER")) {
				session.setAttribute("email", email);
				session.setMaxInactiveInterval(600);
				return "redirect:/assetrequest";
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
	public String updateUser(@Valid @ModelAttribute("user") User user, HttpSession session, BindingResult result) {

		if (result.hasErrors()) {
			return "updateuser";
		} else {
			userService.update(user);
			return "redirect:/users";
		}

	}

	@RequestMapping(value = "/users")
	public ModelAndView listUsers(ModelAndView model, HttpSession session) throws IOException {
		
		session.getAttribute("email");
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
	public ModelAndView asset(@Valid @ModelAttribute("asset") Asset asset, HttpServletRequest request, Model model,
			@RequestParam Long id) {

		List<Asset> assetAttributesList = userService.uniqueAttribute();
		List<Asset> registeredAttributesList = userService.registeredAttribute(id);
		for (int i = 0; i < assetAttributesList.size(); i++) {
			if (assetAttributesList.get(i).getId() == id)
				tempid = id;
		}
          if (temp == null) {
			for (int i = 0; i < assetAttributesList.size(); i++) {
				if (assetAttributesList.get(i).getSerialnumber().equals(asset.getSerialnumber())) {
					model.addAttribute("serialError", "Serial number already exist");
					return new ModelAndView("redirect:/asset");
				} else if (assetAttributesList.get(i).getTag().equals(asset.getTag())) {
					model.addAttribute("tagError", "Asset Tag already exist");
					return new ModelAndView("redirect:/asset");
				} else if (assetAttributesList.get(i).getOrdernumber().equals(asset.getOrdernumber())) {
					model.addAttribute("orderError", "Order Number already esist");
					return new ModelAndView("redirect:/asset");
				}
			}
			userService.addAsset(asset);
			ModelAndView modelview = new ModelAndView("redirect:/requestedassets");
			return modelview;
		} else {
			for (int i = 0; i < registeredAttributesList.size(); i++) {
				if (registeredAttributesList.get(i).getSerialnumber().equals(asset.getSerialnumber())) {
					model.addAttribute("serialError", "Serial number already exist");
					return new ModelAndView("redirect:/getAsset?id=" + asset.getId());
				} else if (registeredAttributesList.get(i).getOrdernumber().equals(asset.getOrdernumber())) {
					model.addAttribute("orderError", "Order number already exist");
					return new ModelAndView("redirect:/getAsset?id=" + asset.getId());
				} else if (registeredAttributesList.get(i).getTag().equals(asset.getTag())) {
					model.addAttribute("tagError", "Asset Tag already exist");
					return new ModelAndView("redirect:/getAsset?id=" + asset.getId());
				}
			}
			userService.updateAsset(asset, id);
			return new ModelAndView("redirect:/assetslist");
		}
	}

	@RequestMapping(value = "/requestNotAssign{id}{email}")
	public ModelAndView requestNotAssign(@ModelAttribute("reasonassign") AssetRequest assetrequest) {
		
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("requestnotassigned");
		return modelview;
	}

	@RequestMapping(value = "/requestNotAssigned", method = RequestMethod.POST)
	public ModelAndView requestNotAssigned(@RequestParam Long id, @RequestParam String reason, ModelAndView modelview) {
		
		userService.updateNotAssignedRequest(reason, id);
		List<User> listEmail = service.listEmail();
		modelview.addObject("listEmail", listEmail);
		modelview.setViewName("requestedassets");
		return modelview;
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request,@RequestParam(value = "email") String email) {
		
		//String email = request.getParameter("email");
		userService.softDelete(email);
		return new ModelAndView("redirect:/users");
	}

	@RequestMapping(value = "/deleteCompany", method = RequestMethod.GET)
	public ModelAndView deleteCompany(@RequestParam(value = "id") Long id, @RequestParam(value = "company") String company, Model model) {
		
		List<Asset> listExistedAttributes = userService.existedAttributes();
		for (int i = 0; i < listExistedAttributes.size(); i++) {
			if (listExistedAttributes.get(i).getCompany().equals(company)) {
				model.addAttribute("companyError", "This record cannot be deleted as it exists in asset record");
				return new ModelAndView("listofcompanies");
			}
		}
		userService.deleteCompany(id);
		return new ModelAndView("listofcompanies");
	}

	@RequestMapping(value = "/deleteSupplier", method = RequestMethod.GET)
	public ModelAndView deleteSupplier(@RequestParam(value = "id") Long id, @RequestParam(value = "supplier") String supplier, Model model) {
		
		List<Asset> listExistedAttributes = userService.existedAttributes();
		for (int i = 0; i < listExistedAttributes.size(); i++) {
			if (listExistedAttributes.get(i).getSupplier().equals(supplier)) {
				model.addAttribute("supplierError", "This record cannot be deleted as it exists in asset record");
				return new ModelAndView("listofsuppliers");
			}
		}
		userService.deleteSupplier(id);
		return new ModelAndView("listofsuppliers");
	}

	@RequestMapping(value = "/deleteAssetModel", method = RequestMethod.GET)
	public ModelAndView deleteAssetModel(@ModelAttribute("assetmodel") NewModel newmodel,@RequestParam(value = "id") Long id, @RequestParam(value = "model") String model, Model modelname) {
		
		List<Asset> listExistedAttributes = userService.existedAttributes();
		for (int i = 0; i < listExistedAttributes.size(); i++) {
			if (listExistedAttributes.get(i).getModel().equals(model)) {
				modelname.addAttribute("modelError","This record cannot be deleted as it exists in asset record");
				return new ModelAndView("listofassetmodels");
			}
		}
		userService.deleteAssetModel(id);
		return new ModelAndView("listofassetmodels");
	}

	@RequestMapping(value = "/deleteAccessoryModel", method = RequestMethod.GET)
	public ModelAndView deleteAccessoryModel(@RequestParam(value = "id") Long id, @RequestParam(value = "model") String model, Model modelname) {
		
		List<Asset> listExistedAttributes = userService.existedAttributes();
		for (int i = 0; i < listExistedAttributes.size(); i++) {
			if (listExistedAttributes.get(i).getModel().equals(model)) {
				modelname.addAttribute("modelError", "This record cannot be deleted as it exists in asset record");
				return new ModelAndView("listofaccessorymodels");
			}
		}
		userService.deleteAccessoryModel(id);
		return new ModelAndView("listofaccessorymodels");
	}

	@RequestMapping(value = "/deleteConsumableModel", method = RequestMethod.GET)
	public ModelAndView deleteConsumableModel(@RequestParam(value = "id") Long id, @RequestParam(value = "model") String model, Model modelname) {
		
		List<Asset> listExistedAttributes = userService.existedAttributes();
		for (int i = 0; i < listExistedAttributes.size(); i++) {
			if (listExistedAttributes.get(i).getModel().equals(model)) {
				modelname.addAttribute("modelError", "This record cannot be deleted as it exists in asset record");
				return new ModelAndView("listofconsumablemodels");
			}
		}
		userService.deleteConsumableModel(id);
		return new ModelAndView("listofconsumablemodels");
	}

	@RequestMapping(value = "/deleterequestedassets", method = RequestMethod.GET)
	public ModelAndView deleteRequestedAssets(HttpServletRequest request) {
		
		Long id = (long) Integer.parseInt(request.getParameter("id"));
		userService.deleteAssetRequest(id);
		return new ModelAndView("redirect:/assetrequest");
	}

	@RequestMapping(value = "/deleteAsset", method = RequestMethod.GET)
	public ModelAndView deleteAsset(HttpServletRequest request,@RequestParam(value = "id") Long id) {
		
		//Long id = (long) Integer.parseInt(request.getParameter("id"));
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
			((Model) model).addAttribute("emailError", "Email already registered");
			return modelandview;
		}

		if (!(user.getEmail().isEmpty())) {
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(user.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "domainmatch.email", "Domain name must be @nerdapplabs.com");
				return modelandview;
			}
			//user.setStatus(1);
			//userService.save(user);
			//((Model) model).addAttribute("saveUser","User added successfully");
			//return new ModelAndView("add");
		}

		user.setStatus(1);
		userService.save(user);
		((Model) model).addAttribute("saveUser","User added successfully");
		ModelAndView modelview = new ModelAndView("add");
		return modelview;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registration(@Valid @ModelAttribute("userform") User user, BindingResult result,
			Model model, Errors errors, WebRequest request) {
		
		ModelAndView modelandview = new ModelAndView("register");
		if (!(user.getEmail().isEmpty())) {
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(user.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "domainmatch.email", "Domain name must be @nerdapplabs.com");
				return modelandview;
			}
		}
		User tempUser = userService.findByEmail(user.getEmail());
		if (tempUser != null) {
			 model.addAttribute("emailError", "Email already registered");
			return modelandview;
		}
		user.setStatus(0);
		user.setIs_approved(false);
		userService.save(user);
		
		try {
			String appUrl = request.getContextPath();
			model.addAttribute("confirmationlink","Confirmation link send to your email");
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, appUrl));
		} catch (Exception e) {
			 model.addAttribute("emailError", "Error in sending email");
			return modelandview;
		}
		ModelAndView modelview = new ModelAndView("register");
		return modelview;
	}

	@RequestMapping(value = "/changePassword{email}{newpassword}{oldpassword}", method = RequestMethod.POST)
	public ModelAndView changePassword(@ModelAttribute("changepassword") User user, @RequestParam String newpassword,
			@RequestParam String email, @RequestParam String oldpassword, Model model, Errors errors) {
		
		user = userService.getUser(email);
		if (user.getPassword().contains(oldpassword)) {
			userService.updatePassword(email, newpassword);
			if (user.getRole().contains("ROLE_ADMIN")) {
				return new ModelAndView("redirect:/requestedassets?email=" + email);
			} else if (user.getRole().contains("ROLE_USER")) {
				return new ModelAndView("redirect:/assetrequest");
			} else if(user.getRole().contains("ROlE_SUPER")) {
			return new ModelAndView("redirect:/users?email=" + email);
			} else {
				return new ModelAndView("redirect:/changePassword");
			}
		} else {
			model.addAttribute("passwordError", "Password not registered");
			return new ModelAndView("redirect:/changePassword");
		}

	}

	@RequestMapping(value = "/modelaccessory", method = RequestMethod.POST)
	public ModelAndView addModelAccessory(@ModelAttribute("newmodelaccessory") ModelAccessory modelaccessory,
			Model modelObject) {
		
		List<ModelAccessory> listmodelaccessory = service.listModelAccessory();
		for (int i = 0; i < listmodelaccessory.size(); i++) {
			if (listmodelaccessory.get(i).getModel().equals(modelaccessory.getModel())) {
				modelObject.addAttribute("modelError", "Model name already exsist");
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
				modelObject.addAttribute("modelError", "Model name already exsist");
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
				modelObject.addAttribute("modelError", "Model name already exsist");
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
	public ModelAndView addSupplier(@ModelAttribute("supplier") Supplier supplier, Model model) {
		
		List<Supplier> listsupplier = service.listSupplier();
		for (int i = 0; i < listsupplier.size(); i++) {
			if (listsupplier.get(i).getSupplier().equals(supplier.getSupplier())) {
				model.addAttribute("supplierError", "Supplier already exsist");
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
	public ModelAndView addStatus(@ModelAttribute("status") Status status, Model model) {
		
		List<Status> liststatus = service.listStatus();
		for (int i = 0; i < liststatus.size(); i++) {
			if (liststatus.get(i).getStatus().equals(status.getStatus())) {
				model.addAttribute("statusError", "Status already exsist");
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
	public ModelAndView addCompany(@ModelAttribute("newcompany") Company company, Model model) {
		
		List<Company> listcompany = service.listCompany();
		for (int i = 0; i < listcompany.size(); i++) {
			if (listcompany.get(i).getCompany().equals(company.getCompany())) {
				model.addAttribute("companyError", "Company name already exsist");
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

	@RequestMapping(value = "/listOfCompanies")
	public ModelAndView listOfCompanies(ModelAndView model) throws IOException {
		
		List<Company> listcompany = service.listCompany();
		model.addObject("listcompany", listcompany);
		model.setViewName("listofcompanies");
		return model;
	}

	@RequestMapping(value = "/listOfSuppliers")
	public ModelAndView listOfSuppliers(ModelAndView model) throws IOException {
		
		List<Supplier> listsupplier = service.listSupplier();
		model.addObject("listsupplier", listsupplier);
		model.setViewName("listofsuppliers");
		return model;
	}

	@RequestMapping(value = "/listOfAssetModels")
	public ModelAndView listOfAssetModels(ModelAndView model) throws IOException {
		
		List<NewModel> listmodel = service.listModel();
		model.addObject("listmodel", listmodel);
		model.setViewName("listofassetmodels");
		return model;
	}

	@RequestMapping(value = "/listOfAccessoryModels")
	public ModelAndView listOfAccessoryModels(ModelAndView model) throws IOException {
		
		List<ModelAccessory> listmodelaccessory = service.listModelAccessory();
		model.addObject("listmodelaccessory", listmodelaccessory);
		model.setViewName("listofaccessorymodels");
		return model;
	}

	@RequestMapping(value = "/listOfConsumableModels")
	public ModelAndView listOfConsumableModels(ModelAndView model) throws IOException {
		
		List<ModelConsumable> listmodelconsumable = service.listModelConsumable();
		model.addObject("listmodelconsumable", listmodelconsumable);
		model.setViewName("listofconsumablemodels");
		return model;
	}

	@RequestMapping(value = "/assetrequest", method = RequestMethod.GET)
	public ModelAndView listAssetsRequest(@ModelAttribute("assetrequest") AssetRequest assetrequest, ModelAndView model,
			HttpSession session, Model modelview) throws IOException {
		
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
			Map<String, Object> model, Errors errors, HttpSession session, User user, @RequestParam String assettype,
			@RequestParam String assetname, @RequestParam String windows, Model modelview) {
		
		session.getAttribute("email");
		List<AssetRequest> listAssets = service.listAsset(user);
		List<AssetRequest> listAssetsRequest = service.listAssetsRequest(session);
		ModelAndView view = new ModelAndView("assetrequest");
		List<NewModel> listmodelname = service.listModel();
		view.addObject("listmodelname", listmodelname);
		List<ModelAccessory> listmodelaccessory = service.listModelAccessory();
		view.addObject("listmodelaccessory", listmodelaccessory);
		List<ModelConsumable> listmodelconsumable = service.listModelConsumable();
		view.addObject("listmodelconsumable", listmodelconsumable);
		for (int i = 0; i < listAssets.size(); i++) {
			if (listAssets.get(i).getAssettype().equals(assettype) && listAssets.get(i).getAssetname().equals(assetname)
					&& listAssets.get(i).getWindows().equals(windows)) {
				modelview.addAttribute("typeRegistered", "assettype,assetname and windows already registered");
				view.addObject("listAssetsRequest", listAssetsRequest);
				view.setViewName("assetrequest");
				return view;
			}
		}

		if (result.hasErrors()) {
			listAssetsRequest = service.listAssetsRequest(session);
			view.addObject("listAssetsRequest", listAssetsRequest);
			view.setViewName("assetrequest");
			return view;
		}
		userService.saveAsset(assetrequest, user);
		listAssetsRequest = service.listAssetsRequest(session);
		view.addObject("listAssetsRequest", listAssetsRequest);
		view.setViewName("assetrequest");
		return view;
	}

	@RequestMapping(value = "/statusassetslist{email}", method = RequestMethod.GET)
	public ModelAndView statusAsset(@ModelAttribute("user") User user, ModelAndView modelview, HttpSession session,
			AssetRequest assetrequest) throws IOException {
		
		user = userService.getUser(user.getEmail());
		List<AssetRequest> listAssets = service.listAsset(user);
		modelview.addObject("listAssets", listAssets);
		modelview.setViewName("statusassetslist");
		return modelview;
	}

	@RequestMapping(value = "/statusCheckRequest{email}{id}", method = RequestMethod.GET)
	public ModelAndView CheckRequest(@ModelAttribute("asset") Asset asset, @RequestParam Long id,
			@RequestParam String email, ModelAndView model, AssetRequest assetrequest, User user) throws IOException {
		
		List<Asset> listAssets = service.listAssets();
		model.addObject("listAssets", listAssets);
		model.setViewName("assetslist");
		return model;
	}

	@RequestMapping(value = "/statusCheckRequest{email}{id}", method = RequestMethod.POST)
	public ModelAndView statusCheckRequest(@ModelAttribute("asset") Asset asset, @RequestParam Long id,
			@RequestParam String email, ModelAndView model, AssetRequest assetrequest, User user) throws IOException {
		
		List<Asset> listAssets = service.listAssets();
		model.addObject("listAssets", listAssets);
		model.setViewName("assetslist");
		return model;
	}

	ModelAndView modelview;

	@RequestMapping(value = "/statusAssignRequest", method = RequestMethod.GET)
	public @ResponseBody ModelAndView statusAssignRequest(@ModelAttribute("asset") Asset asset,
			@RequestParam("id") Long id, @RequestParam("quantity") String quantity, @RequestParam("email") String email,
			@RequestParam("serialnumber") String serialnumber) throws IOException {

		User user;
		user = userService.getUser(email);
		asset = userService.getAsset(id);
		List<AssetRequest> listAsset = service.listAsset(user);
		List<Asset> listAssets = service.listAssets();
		for (int i = 0; i < listAsset.size(); i++) {
			if (listAsset.get(i).getId() == id)
				tempid = id;
		}
		for (int i = 0; i < listAssets.size(); i++) {
			if (listAssets.get(i).getSerialnumber().contentEquals(serialnumber)	)
				temp = serialnumber;
		}
		if (temp != null) {
			userService.updateAssetQuantity(quantity, serialnumber);
			if (tempid != null) {
				userService.updateAssetRequestQuantity(quantity, id);
			}
			listAssets = service.listAssets();
			modelview.addObject("listAssets", listAssets);
			modelview.setViewName("assetslist");
			return modelview;
		}
		listAssets = service.listAssets();
		modelview.addObject("listAssets", listAssets);
		modelview.setViewName("assetslist");
		return modelview;
	}

	@RequestMapping(value = "/statusRecieveRequest{id}{email}", method = RequestMethod.GET)
	public ModelAndView statusRecieveRequest(@ModelAttribute("user") User user, ModelAndView modelview,
			AssetRequest assetrequest, @RequestParam Long id, @RequestParam String email) throws IOException {
		user = userService.getUser(email);
        List<AssetRequest> listAssets = service.listAsset(user);
		for (int i = 0; i < listAssets.size(); i++) {
			if (listAssets.get(i).getId() == id)
				tempid = id;
		}
		if (tempid != null) {
			userService.updateRecieveRequest(assetrequest, id);
			listAssets = service.listAsset(user);
			modelview.addObject("listAssets", listAssets);
			modelview.setViewName("statusassetslist");
			return modelview;
		}
		modelview.addObject("listAssets", listAssets);
		modelview.setViewName("statusassetslist");
		return modelview;
	}

	@RequestMapping(value = "/requestedassetslist{email}")
	public ModelAndView listAssets(@ModelAttribute("user") User user, ModelAndView modelview, HttpSession session)
			throws IOException {
		session.getAttribute("email");
		user = userService.getUser(user.getEmail());
		List<AssetRequest> listAssets = service.listAsset(user);
		modelview.addObject("listAssets", listAssets);
		modelview.setViewName("requestedassetslist");
		return modelview;
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
		user.setIs_approved(true);
		userService.save(user);
		return "redirect:/confirmationLink";
	}

	@RequestMapping(value = "/requestedassets{email}")
	public ModelAndView listEmail(ModelAndView model, HttpSession session) throws IOException {

		session.getAttribute("email");
		List<User> listEmail = service.listEmail();
		model.addObject("listEmail", listEmail);
		model.setViewName("requestedassets");
		return model;

	}

	@RequestMapping(value = "/listOfApprovalUsers")
	public ModelAndView listOfRegisteredUsers(@ModelAttribute("user") User user,ModelAndView model) throws IOException {

		List<User> listApprovalUsers = service.listApprovalUsers();
		model.addObject("listApprovalUsers", listApprovalUsers);
		model.setViewName("approvaluserslist");
		return model;

	}

	@RequestMapping(value = "/assetslist")
	public ModelAndView listAssets(@ModelAttribute("asset") Asset asset, ModelAndView model, AssetRequest assetrequest,
			User user) throws IOException {
		List<NewModel> listmodelname = service.listModel();
		model.addObject("listmodelname", listmodelname);
		List<ModelAccessory> listmodelaccessory = service.listModelAccessory();
		model.addObject("listmodelaccessory", listmodelaccessory);
		List<ModelConsumable> listmodelconsumable = service.listModelConsumable();
		model.addObject("listmodelconsumable", listmodelconsumable);
		List<Asset> listAssets = service.listAssets();
		model.addObject("listAssets", listAssets);
		model.setViewName("assetslist");
		return model;
    }
	
	@RequestMapping(value = "/approveUser{email}", method = RequestMethod.GET)
	public ModelAndView approveUser(@RequestParam String email, Model model) throws MessagingException {
		userService.sendApprovalEmail(email);
		model.addAttribute("approveUser","Approval is sent to User's mail");
		userService.updateRegisteredStatus(email);
		return new ModelAndView("approvaluserslist");

	}
	
	@RequestMapping(value = "/rejectUser", method = RequestMethod.GET)
	public ModelAndView rejectUser(@RequestParam(value = "email") String email,Model model){
		
		int result = userService.deleteToken(email);
		if(result == 0) {
			model.addAttribute("tokenError","User cannot be deleted as token still exists");
			return new ModelAndView("approvaluserslist");
		}
		userService.deleteFakeUser(email);
		return new ModelAndView("approvaluserslist");

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
			model.addAttribute("emailError", "Password send successfully to your email");
			return "forgotpassword";
		} else if (tempuser == null) {
			model.addAttribute("emailError", "Valid email required");
			return "forgotpassword";
		} else if (tempuser.getStatus() == 0) {
			System.out.println("email not registered");
			model.addAttribute("emailError", "Email not registered");
			return "forgotpassword";
		} else {
			return "forgotpassword";
		}
	}
}
