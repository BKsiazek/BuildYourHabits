package buildyourhabits.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import buildyourhabits.services.UserService;

@Controller
public class AdminPanelController {
	
	@Autowired
	UserService service;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String showAdminPanel(ModelMap model) {
		
		model.addAttribute("users", service.retrieveAllUsers());
		
		return "admin-panel";
	}
	
	@RequestMapping(value = "/admin/delete-user", method = RequestMethod.GET)
	public String deleteUser(@RequestParam int id) {
		service.deleteUser(id);
		
		return "redirect:../admin";
	}	
}
