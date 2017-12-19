package buildyourhabits.services;

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
	
	public void updateHabit(Habit habit) {
		
		/*SessionFactory sf = HibernateService.createSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		session.update(habit);
		
		session.getTransaction().commit();
		session.close();
		sf.close();*/
		
		EntityManager entityManager = JPAUtility.getEntityManager();	
		entityManager.getTransaction().begin();
		
		habit = entityManager.merge(habit);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
}
