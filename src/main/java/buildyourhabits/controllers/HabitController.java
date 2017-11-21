package buildyourhabits.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import buildyourhabits.models.Habit;
import buildyourhabits.services.HabitService;

@Controller
public class HabitController {
	
	@Autowired
	HabitService service;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@RequestMapping(value = "/list-habits", method = RequestMethod.GET)
	public String showListOfHabits(ModelMap model) {
		model.addAttribute("habits", service.retrieveHabits("Bartek"));
		return "list-habits";
	}
	
	@RequestMapping(value = "/add-habit", method = RequestMethod.GET)
	public String showHabitPage(ModelMap model) {
		model.addAttribute("habit", new Habit(0, "Bartek", "", new Date(), false));
		return "habit";
	}
	
	@RequestMapping(value = "/add-habit", method = RequestMethod.POST)
	public String addHabit(@Valid Habit habit, BindingResult result) {
		
		if(result.hasErrors())
			return "habit";
		
		service.addHabit("Bartek", habit.getDescription(), habit.getTargetDate(), false);
		return "redirect:list-habits";
	}
	
	@RequestMapping(value = "/update-habit", method = RequestMethod.GET)
	public String updateHabitGET(ModelMap model, @RequestParam int id) {
		Habit habit = service.retrieveHabit(id);
		model.addAttribute("habit", habit);
		return "habit";
	}
	
	@RequestMapping(value = "/update-habit", method = RequestMethod.POST)
	public String updateHabitPOST(@Valid Habit habit, BindingResult result) {
		
		if(result.hasErrors())
			return "habit";
		
		habit.setUser("Bartek");
		
		service.updateHabit(habit);
		
		return "redirect:list-habits";
	}
	
	@RequestMapping(value = "/delete-habit", method = RequestMethod.GET)
	public String deleteHabit(@RequestParam int id) {
		service.deleteHabit(id);
		return "redirect:list-habits";
	}
}
