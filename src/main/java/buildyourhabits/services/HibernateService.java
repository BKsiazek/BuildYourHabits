package buildyourhabits.services;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import buildyourhabits.models.Habit;
import buildyourhabits.models.User;

public class HibernateService {
	
	public static SessionFactory createSessionFactory()
	{
		Configuration config = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Habit.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		SessionFactory sf = config.buildSessionFactory(registry);
		
		return sf;
	}
}
