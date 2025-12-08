package com.healthcare.tester;

import org.hibernate.*;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;

import static com.healthcare.utils.HibernateUtils.getSessionFactory;

import java.time.LocalDate;
import java.util.Scanner;

public class RegisterUser {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); 
				SessionFactory sf = getSessionFactory()) {
			//create dao instance
			UserDao userDao=new UserDaoImpl();
			System.out.println("Enter User details - firstName,  lastName, "
					+ "dob,  email ,password,  phone,regAmount, userRole");
			//create user class instance
			User user=new User(sc.next(), sc.next(), LocalDate.parse(sc.next()), sc.next(), sc.next(), sc.next(),sc.nextInt(), UserRole.valueOf(sc.next().toUpperCase()));
			//invoke dao's method
			System.out.println(userDao.signUp(user));
		} // JVM - sf.close() -> DBCP cleaned up auto.
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
