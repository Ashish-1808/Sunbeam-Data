package com.healthcare.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.healthcare.dao.AppointmentDao;
import com.healthcare.dao.AppointmentDaoImpl;
import com.healthcare.entities.Appointment;
import com.healthcare.entities.Patient;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AppointmentServlet
 */
@WebServlet(value = "/appointments", loadOnStartup = 3)
public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AppointmentDao appointmentDao;

	/**
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		try {
			// create dao instance(=dependency)
			appointmentDao = new AppointmentDaoImpl();
		} catch (Exception e) {
			// re throw exc to WC - to inform
			throw new ServletException("err in init " + getClass(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			// clean up
			appointmentDao.cleanUp();
		} catch (Exception e) {
			System.out.println("error " + e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. set resp content type
		response.setContentType("text/html");
		// 2. get Writer
		try (PrintWriter pw = response.getWriter()) {
			// 3. get & parse request params
			long doctorId = Long.parseLong(request.getParameter("doc_id"));
			// String -> LocalDateTime
			LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("appointmentDateTime"));
			// create TS from LocalDateTime
			Timestamp ts = Timestamp.valueOf(dateTime);
			System.out.println(ts);
			// 4. get HttpSession from WC
			HttpSession session = request.getSession();
			System.out.println(session.isNew());// f
			// 4 get patient details from HttpSession
			Patient patient = (Patient) session.getAttribute("patient_details");
			// 5. create appointment entity
			Appointment appointment = new Appointment(ts, doctorId, patient.getPatientId());
			// 6. call dao's method
			String bookingStatus = appointmentDao.bookAppointment(appointment);
			// 7. redirect the client to dashboard in the new request
			response.sendRedirect("patient_dashboard?message=" + bookingStatus);
			/*
			 * Location :patient_dashboard?message=success|failure URI of redirect request -
			 * /ctx_path/patient_dashboard?message=success|failure
			 */

		} catch (Exception e) {
			throw new ServletException("err in servicing " + getClass(), e);
		}
	}
	//http://localhost:8080/day4.1/appointments?id=2 , method=GET
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		try {
		//1. Get request param - appointment id & parse it
		long appointmentId=Long.parseLong(request.getParameter("id"));
		//2. invoke dao's method
		String status = appointmentDao.cancelAppointment(appointmentId);
		//3. redirect the client in the next request to the dashboard
		response.sendRedirect("patient_dashboard?message="+status);
		} catch (Exception e) {
			throw new ServletException("err in do-get"+getClass(), e);
		}
	} 

}
