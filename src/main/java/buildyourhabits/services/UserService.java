package buildyourhabits.services;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import buildyourhabits.configuration.JPAUtility;
import buildyourhabits.models.User;

@Service
public class UserService {
	
	public void createUser(User user) {

		/*SessionFactory sf = HibernateService.createSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		session.save(user);
		
		session.getTransaction().commit();
		session.close();
		sf.close();*/
		
		EntityManager entityManager = JPAUtility.getEntityManager();	
		entityManager.getTransaction().begin();
		
		entityManager.persist(user);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public User getActiveUser(String username) {
		
		/*SessionFactory sf = HibernateService.createSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		List<User> users = session.createQuery("from User where name=:username").setParameter("username", username).getResultList();
		User activeUser = null;
		
		if(users.size() > 0)
			activeUser = users.get(0);
		
		session.getTransaction().commit();
		session.close();
		sf.close();*/
		
		
		EntityManager entityManager = JPAUtility.getEntityManager();	
		entityManager.getTransaction().begin();
		
		User activeUser = (User)entityManager.createQuery("from User where name=:username").setParameter("username", username).getSingleResult();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return activeUser;
	}
	
}
