package com.healthcare.pages;

import java.io.IOException;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.entities.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet(value = "/login", loadOnStartup = 1)
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	/**
	 * @see Servlet#init()
	 */
	@Override
	public void init() throws ServletException {
		// 1. create dao instance
		try {
			userDao = new UserDaoImpl();
		} catch (Exception e) {
			/*
			 * Centralized exc handling in Servlets Instead of just catching the exception ,
			 * re - throw it back to WC so that WC aborts the life cycle , in case of init's
			 * failure API - public ServletException(String mesg,Throwable rootCause)
			 */
			throw new ServletException("err in init of - " + getClass(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy() {
		try {
			// dao's clean up
			userDao.cleanUp();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("err in destroy " + getClass(), e);// OPTIONAL !!!!!
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		try  {
			// 3 Read request parameters
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			// 4. invoke user dao's authenticate method
			User user = userDao.authenticateUser(email, password);
			// 5. check for auth status
			if (user == null) {
				// failed login ->  forward the client to view layer(login.jsp) ->highlighted with error message	
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/login.jsp?mesg=Invalid Email or Password");
				rd.forward(request, response);
			} else {
				//6. Get HttpSession from WC
				HttpSession session=request.getSession();	
		        //7  save clnt info under session scope
				session.setAttribute("user_details", user);
				String mesg="Welcome to "+user.getUserRole()+" dashboard";
				// 8. role based authorization
				if (user.getUserRole().equals("ROLE_PATIENT")) {
					response.sendRedirect("patient_dashboard?message="+mesg);
				} else if (user.getUserRole().equals("ROLE_DOCTOR")) {
					response.sendRedirect("doctor_dashboard?message="+mesg);
				} else {
					response.sendRedirect("admin_dashboard?message="+mesg);
				}
				
			}

		} catch (Exception e) {
			// re throw the exc to WC
			throw new ServletException("err in servicing " + getClass(), e);
		}

	}

}
