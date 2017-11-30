package buildyourhabits.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Service;

import buildyourhabits.models.User;

@Service
public class UserService {
	
	public void addUserToDatabase(User user) {

		Configuration conf = new Configuration().configure().addAnnotatedClass(User.class);
		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
		SessionFactory sf = conf.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(user);
		tx.commit();
		session.close();
	}
}
