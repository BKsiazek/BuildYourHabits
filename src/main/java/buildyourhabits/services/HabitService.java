package buildyourhabits.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import buildyourhabits.models.Habit;

@Service
public class HabitService {

	private static List<Habit> habits = new ArrayList<Habit>();
	private static int habitCount = 3;

	static {
		habits.add(new Habit(1, "Bartek", "Drink more water", new Date(), false));
		habits.add(new Habit(2, "ZlyBartek", "Drink less water", new Date(), false));
		habits.add(new Habit(3, "Bartek", "Get more sleep", new Date(), false));
		habits.add(new Habit(4, "ZlyBartek", "Sleep less than 5 hours", new Date(), false));
		habits.add(new Habit(5, "ZlyBartek", "Eat sweets at least three times a day", new Date(), false));
		habits.add(new Habit(6, "Bartek", "Work out 3 times per week", new Date(), false));
	}
	
	public void addHabit(String name, String description, Date targetDate, boolean isDone) {
		habits.add(new Habit(++habitCount, name, description, targetDate, isDone));
	}
	
	public void deleteHabit(int id) {
		Iterator<Habit> iterator = habits.iterator();
		while (iterator.hasNext()) {
			Habit habit = iterator.next();
			if (habit.getId() == id) {
				iterator.remove();
			}
		}
	}
	
	public List<Habit> retrieveHabits(String user) {
		List<Habit> filteredHabits = new ArrayList<Habit>();
		for (Habit habit : habits) {
			if (habit.getUser().equals(user))
				filteredHabits.add(habit);
		}
		return filteredHabits;
	}
	
	public Habit retrieveHabit(int id) {
		for (Habit habit : habits) {
			if(habit.getId() == id)
				return habit;
		}
		return null;
	}
	
	public void updateHabit(Habit habit) {
		habits.remove(habit);
		habits.add(habit);
	}
	
}
