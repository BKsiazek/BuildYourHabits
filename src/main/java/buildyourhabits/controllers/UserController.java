package buildyourhabits.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import buildyourhabits.models.User;
import buildyourhabits.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/sign-up", method = RequestMethod.GET)
	public String showSignUpPage(ModelMap model) {
		model.addAttribute("user", new User());
		
		return "user";
	}
	
	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public String signUp(@Valid User user, BindingResult result) {
		
		if(result.hasErrors())
			return "user";
		
		user.setRole("ROLE_USER");
		service.createUser(user);
		
		return "redirect:login";
	}
}
