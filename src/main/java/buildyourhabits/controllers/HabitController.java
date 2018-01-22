package buildyourhabits.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import buildyourhabits.models.Habit;
import buildyourhabits.models.User;
import buildyourhabits.services.HabitService;
import buildyourhabits.services.UserService;

@Controller
public class HabitController {

	@Autowired
	private HabitService service;
	
	@Autowired
	private UserService userService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@RequestMapping(value = "/list-habits", method = RequestMethod.GET)
	public String showListOfHabits(ModelMap model) {
		model.addAttribute("habits", service.retrieveHabits(retrieveLoggedInUser()));
		
		return "list-habits";
	}

	private User retrieveLoggedInUser() {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String activeUsername = ((UserDetails)principal).getUsername();
		User activeUser = userService.getActiveUser(activeUsername);
		
		return activeUser;
	}
	
	@RequestMapping(value = "/add-habit", method = RequestMethod.GET)
	public String showAddHabitPage(ModelMap model) {
		model.addAttribute("habit", new Habit(0, null, ""));
		
		return "habit";
	}
	
	@RequestMapping(value = "/add-habit", method = RequestMethod.POST)
	public String addHabit(@Valid Habit habit, BindingResult result) {
		
		if(result.hasErrors())
			return "habit";
		
		habit.setOwner(retrieveLoggedInUser());
		
		service.addHabit(habit);
		return "redirect:list-habits";
	}
	
	@RequestMapping(value = "/update-habit", method = RequestMethod.GET)
	public String showUpdateHabitPage(ModelMap model, @RequestParam int id) {
		Habit habit = service.retrieveHabit(id);
		model.addAttribute("habit", habit);
		return "habit";
	}
	
	@RequestMapping(value = "/update-habit", method = RequestMethod.POST)
	public String updateHabit(@Valid Habit habit, BindingResult result) {
		
		if(result.hasErrors())
			return "habit";
		
		habit.setOwner(retrieveLoggedInUser());
		service.updateHabit(habit);
		
		return "redirect:list-habits";
	}
	
	@RequestMapping(value = "/delete-habit", method = RequestMethod.GET)
	public String deleteHabit(@RequestParam int id) {
		service.deleteHabit(id);
		
		return "redirect:list-habits";
	}	
	
	@RequestMapping(value = "/habit-details", method = RequestMethod.GET)
	public String showHabitDetails(ModelMap model, @RequestParam int id) {
		Habit habit = service.retrieveHabit(id);
		habit.setDaysLeft(service.getDaysToEnd(habit));
		habit.setCompletionRate(service.getHabitCompletionRate(habit));
		habit.setSuccessRate(service.getHabitSuccessRate(id));
		habit.setCurrentStreak(service.getHabitCurrentStreak(id));
		habit.setLongestStreak(service.getHabitLongestStreak(id));
		habit.setToday(service.isTodaySuccessful(id));
		habit.setHasStarted(service.hasHabitStarted(id));
		
		if(habit.getHasStarted() == false)
			habit.setCompletionRate(0);
		
		model.addAttribute("habit", habit);
		
		return "habit-details";
	}
	
	@RequestMapping(value = "/succesful-day", method = RequestMethod.GET)
	public String addSuccesfulDay(@RequestParam int id) {
		
		service.addOrRemoveCurrentDayFromSuccessfulDays(id);
		
		return "redirect:habit-details?id=" + Integer.toString(id);
	}
}
