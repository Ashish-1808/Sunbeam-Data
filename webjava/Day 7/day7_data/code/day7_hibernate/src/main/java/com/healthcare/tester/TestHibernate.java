package com.healthcare.tester;

import org.hibernate.*;
import static com.healthcare.utils.HibernateUtils.getSessionFactory;

public class TestHibernate {
	public static void main(String[] args) {
		try (SessionFactory sf = getSessionFactory()) {
			System.out.println("Hibernate up & running - " + sf);
		} // JVM - sf.close() -> DBCP cleaned up auto.
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
