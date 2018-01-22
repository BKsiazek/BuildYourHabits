package buildyourhabits.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

import java.util.List;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Service;

import buildyourhabits.configuration.JPAUtility;
import buildyourhabits.models.Habit;
import buildyourhabits.models.User;

@Service
public class HabitService {
	
	public void addHabit(Habit habit) {
		
		/*SessionFactory sf = HibernateService.createSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		session.save(habit);
		
		session.getTransaction().commit();
		session.close();
		sf.close();*/
		
		EntityManager entityManager = JPAUtility.getEntityManager();	
		entityManager.getTransaction().begin();
		
		entityManager.persist(habit);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public void deleteHabit(int id) {
		
		/*SessionFactory sf = HibernateService.createSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		session.createQuery("delete from Habit where habitID=:id").setParameter("id", id).executeUpdate();
		
		session.getTransaction().commit();
		session.close();
		sf.close();*/
		
		EntityManager entityManager = JPAUtility.getEntityManager();	
		entityManager.getTransaction().begin();
		
		Habit habit = entityManager.find(Habit.class, id);
		entityManager.remove(habit);
		
		entityManager.getTransaction().commit();
		entityManager.close();	
	}
	
	public List<Habit> retrieveHabits(User user) {
		
		/*SessionFactory sf = HibernateService.createSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		List<Habit> habits = session.createQuery("from Habit where owner=:owner").setParameter("owner", user).getResultList();
		
		session.getTransaction().commit();
		session.close();
		sf.close();*/
		
		
		EntityManager entityManager = JPAUtility.getEntityManager();	
		entityManager.getTransaction().begin();
		
		@SuppressWarnings("unchecked")
		List<Habit> habits = entityManager.createQuery("from Habit where owner=:owner").setParameter("owner", user).getResultList();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return habits;
	}
	
	public Habit retrieveHabit(int id) {
		
		/*SessionFactory sf = HibernateService.createSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		Habit habit = session.get(Habit.class, id);
		
		session.getTransaction().commit();
		session.close();
		sf.close();*/
		
		EntityManager entityManager = JPAUtility.getEntityManager();	
		entityManager.getTransaction().begin();
		
		Habit habit = entityManager.find(Habit.class, id);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return habit;
	}
	
	public void updateHabit(Habit habitUpdated) {
		
		/*SessionFactory sf = HibernateService.createSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		session.update(habit);
		
		session.getTransaction().commit();
		session.close();
		sf.close();*/
		
		EntityManager entityManager = JPAUtility.getEntityManager();	
		entityManager.getTransaction().begin();
		
		Habit habit = entityManager.find(Habit.class, habitUpdated.getHabitID());
		
		habit.setDescription(habitUpdated.getDescription());
		habit.setStartDate(habitUpdated.getStartDate());
		habit.setTargetDate(habitUpdated.getTargetDate());
		
		habit = entityManager.merge(habit);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public int getDaysToEnd(Habit habit) {
		
		LocalDateTime habitTargetDate = habit.getTargetDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		return (int) DAYS.between(LocalDateTime.now(), habitTargetDate.plusDays(1));
	}
	
	public int getHabitCompletionRate(Habit habit) {
		
		LocalDateTime habitStartDate = habit.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime habitTargetDate = habit.getTargetDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		int daysFromStartToEnd = (int) DAYS.between(habitStartDate, habitTargetDate);
		int daysFromStartToNow = (int) DAYS.between(habitStartDate, LocalDateTime.now());

		return ((int)((float)daysFromStartToNow/(float)daysFromStartToEnd * 100f));
	}
	
	public String convertFromLocalDateTimeToString(LocalDateTime date) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		return date.format(formatter);
	}
	
	public LocalDateTime convertFromStringToLocalDateTime(String s) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		return LocalDateTime.parse(s, formatter);
	}
	
	public void addOrRemoveCurrentDayFromSuccessfulDays(int id) {
		
		EntityManager entityManager = JPAUtility.getEntityManager();	
		entityManager.getTransaction().begin();
		
		Habit habit = entityManager.find(Habit.class, id);
				
		if(!isTodaySuccessful(id)) {
			habit.succesfulDays.add(convertFromLocalDateTimeToString(LocalDateTime.now()));
			habit.setToday(true);
		} else {
			habit.succesfulDays.remove(convertFromLocalDateTimeToString(LocalDateTime.now()));
			habit.setToday(false);
		}
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	
	public Boolean isTodaySuccessful(int id) {
		
		Boolean today = false;
		
		EntityManager entityManager = JPAUtility.getEntityManager();	
		entityManager.getTransaction().begin();
		
		Habit habit = entityManager.find(Habit.class, id);
		
		if(habit.succesfulDays.contains(convertFromLocalDateTimeToString(LocalDateTime.now())))
			today = true;
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return today;
	}
	
	public int getHabitSuccessRate(int id) {
		
		int result;
		
		EntityManager entityManager = JPAUtility.getEntityManager();	
		entityManager.getTransaction().begin();
		
		Habit habit = entityManager.find(Habit.class, id);
		
		LocalDateTime habitStartDate = habit.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		int daysFromStartToNow = (int) DAYS.between(habitStartDate, LocalDateTime.now());

		if(daysFromStartToNow == 0) daysFromStartToNow++;
		
		result = (int)((float)habit.succesfulDays.size() / (float)daysFromStartToNow * 100f);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return result;
	}
	
	public int getHabitCurrentStreak(int id) {
		
		int streak = 0;
		
		EntityManager entityManager = JPAUtility.getEntityManager();	
		entityManager.getTransaction().begin();
		
		Habit habit = entityManager.find(Habit.class, id);
		
		LocalDateTime startDate = habit.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime current = LocalDateTime.now();
		
		do {
			if(!habit.succesfulDays.contains(convertFromLocalDateTimeToString(current)))
				break;
			else {
				streak++;
				current = current.minusDays(1);
			}
			
		} while(!(startDate.getDayOfYear() > current.getDayOfYear() && startDate.getYear() == current.getYear()));

		entityManager.getTransaction().commit();
		entityManager.close();
		
		return streak;
	}
	
	public Boolean hasHabitStarted(int id) {
		
		Boolean result;
		
		EntityManager entityManager = JPAUtility.getEntityManager();	
		entityManager.getTransaction().begin();
		
		Habit habit = entityManager.find(Habit.class, id);
		
		LocalDateTime startDate = habit.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime today = LocalDateTime.now();
		
		if(startDate.isBefore(today) || (startDate.getDayOfYear() == today.getDayOfYear() && startDate.getYear() == today.getYear()))
			result = true;
		else result = false;
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return result;
	}
	
	public int getHabitLongestStreak(int id) {
		
		int longestStreak = 0;
		int currentStreak = 0;
		
		EntityManager entityManager = JPAUtility.getEntityManager();	
		entityManager.getTransaction().begin();
		
		Habit habit = entityManager.find(Habit.class, id);
		
		LocalDateTime startDate = habit.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime current = LocalDateTime.now();
		
		
		
		do {
			if(!habit.succesfulDays.contains(convertFromLocalDateTimeToString(current))) {
				if(currentStreak > longestStreak)
					longestStreak = currentStreak;
				
				currentStreak = 0;
			} else currentStreak++;
			
			current = current.minusDays(1);
			
		} while(!(startDate.getDayOfYear() > current.getDayOfYear() && startDate.getYear() == current.getYear()));
		
		if(currentStreak > longestStreak)
			longestStreak = currentStreak;
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return longestStreak;
	}
}
