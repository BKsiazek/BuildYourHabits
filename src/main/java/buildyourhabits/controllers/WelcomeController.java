package buildyourhabits.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showWelcomePage() {		
				
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean isAdmin = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		
		if(isAdmin)
			return "redirect:admin";
		
		return "welcome";
	}
	
	
	
	
	//TODO testing
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "test";
	}
	
	
	
}


//TODO usuwanie usera i jego habitów
//TODO usuwanie swojego konta
//TODO blokowanie dostêpu do panelu admina dla userów