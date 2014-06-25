package br.com.fluentcode.jaxrs.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import br.com.fluentcode.jaxrs.entity.Product;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	static{
		try{
			Configuration configuration = new Configuration()
			.configure()
			.addAnnotatedClass(Product.class);
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
		            configuration.getProperties()).build();
			
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}catch (HibernateException e) {
			System.err.println("Error on startup session factory");
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}

}
