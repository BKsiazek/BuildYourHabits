package buildyourhabits.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import buildyourhabits.services.HabitService;

@Controller
public class HabitController {
	
	@Autowired
	HabitService service;
	
	@RequestMapping(value = "/list-habits", method = RequestMethod.GET)
	public String showListOfHabits(ModelMap model) {
		model.addAttribute("habits", service.retrieveHabits("Bartek"));
		return "list-habits";
	}
	
	@RequestMapping(value = "/add-habit", method = RequestMethod.GET)
	public String showHabitPage() {
		return "habit";
	}
	
	@RequestMapping(value = "/add-habit", method = RequestMethod.POST)
	public String addHabit(@RequestParam String description) {
		service.addHabit("Bartek", description, new Date(), false);
		return "redirect:list-habits";
	}
}
