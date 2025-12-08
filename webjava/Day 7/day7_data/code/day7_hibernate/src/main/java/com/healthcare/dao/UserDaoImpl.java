package com.healthcare.dao;

import com.healthcare.entities.User;
import org.hibernate.*;
import static com.healthcare.utils.HibernateUtils.getSessionFactory;

public class UserDaoImpl implements UserDao {
	/*
	 * no fields , no ctor , no cleanup !
	 */

	@Override
	public String signUp(User user) {
		String mesg="User registration failed....";
		// Generic steps in Hibernate based DAO
		//1. Get Session from SessionFactory
		/*
		 * API of SessionFactory
		 * public Session getCurrentSession() throws HibernateException
		 * - SF creates and returns a  new Session , only if none exists.
		 * Otherwise return existing Session
		 * 
		 */
		Session session=getSessionFactory().getCurrentSession();//new 
		Session session2=getSessionFactory().getCurrentSession();//existing
		System.out.println(session==session2);//true
		/*
		 * 2. Begin a Transaction
		 * Session API
		 * public org.hibernate.Transaction beginTransaction() throws HibernateException
		 * 
		 */
		Transaction tx=session.beginTransaction();
		try {
			//Session API - CRUD
			/*
			 * Session API
			 * public void  persist(Object transientEntity) throws HibernateException
			 */
			session.persist(user);
			//success -> commit
			tx.commit();
			mesg="User registered successfully , ID="+user.getUserId();
		} catch (RuntimeException e) {
			// failure -> rollback
			if(tx != null)
			{
				tx.rollback();
			}
			//re throw the exception to the caller
			throw e;
		}
		
		return mesg;
	}

}
