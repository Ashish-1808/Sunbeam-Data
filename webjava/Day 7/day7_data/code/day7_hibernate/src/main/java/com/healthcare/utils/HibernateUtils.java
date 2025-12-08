package com.healthcare.utils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static SessionFactory sessionFactory;
	static {
		sessionFactory=new Configuration() //empty config
				.configure() //loads the config from hibernate.cfg.xml
				.buildSessionFactory();
				
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	

}
