package com.healthcare.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.healthcare.dao.AppointmentDao;
import com.healthcare.dao.AppointmentDaoImpl;
import com.healthcare.dao.PatientDao;
import com.healthcare.dao.PatientDaoImpl;
import com.healthcare.dto.AppointmentDTO;
import com.healthcare.entities.Patient;
import com.healthcare.entities.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class PatientDashboardServlet
 */
@WebServlet(urlPatterns = "/patient_dashboard", loadOnStartup = 2)
public class PatientDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PatientDao patientDao;
	private AppointmentDao appointmentDao;

	@Override
	public void destroy() {
		try {
			patientDao.cleanUp();
			appointmentDao.cleanUp();
		} catch (Exception e) {
			System.out.println("err " + e);
		}
	}

	@Override
	public void init() throws ServletException {
		try {
			// create daos (dependencies)
			patientDao = new PatientDaoImpl();
			appointmentDao = new AppointmentDaoImpl();
		} catch (Exception e) {
			// to inform WC about init 's failure
			throw new ServletException("err in init of " + getClass(), e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// pw.print("<h5>"+request.getParameter("message")+"</h5>");
			// 3. Get HttpSession from WC
			HttpSession hs = request.getSession(false);
			User userDetails = (User) hs.getAttribute("user_details");
			if (hs != null) {
				// pw.print("<h5> Hello , " + userDetails.getFirstName() + " " +
				// userDetails.getLastName() + " </h5>");
				// 4. get & render patient details
				Patient patient = patientDao.getPatientDetailsFromUserId(userDetails.getUserId());
				// 5. save patient details under Session
				hs.setAttribute("patient_details", patient);
//				pw.print("<h5> Gender " + patient.getGender() + " Blood Group " + patient.getBloodGroup() + " </h5>");
				// 6. get list of apppointments & render
				List<AppointmentDTO> appointments = appointmentDao
						.getUpcomingPatientAppointments(patient.getPatientId());
				// 7. add appoint list under request scope
				request.setAttribute("appointment_list", appointments);
				// forward to view layer
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/patient_dashboard.jsp");
				rd.forward(request, response);
//				for (AppointmentDTO dto : appointments) {
//					pw.print("<h6> ID " + dto.getAppointmentId() + " TS " + dto.getAppointmentDateTime() + " Doc Name "
//							+ dto.getFirstName() + " " + dto.getLastName());
//					// add cancel link
//					pw.print("  <a href='appointments?id=" + dto.getAppointmentId() + "'>Cancel Appointment</a></h6>");
//
//				}
				// 7. render a link - to book appointment
//				pw.print("<h5> <a href='book_appointment.html'>Book Appointment</a></h5>");
//				// 7. render a link - to logout
//				pw.print("<h5> <a href='logout'>Log Out</a></h5>");

			} else {
				// in case of no cookies -no session -
				// redirect the client to context root
				response.sendRedirect(request.getContextPath() + "/?mesg=Accept Cookies first....");
			}

		} catch (Exception e) {
			throw new ServletException("err in servicing " + getClass(), e);
		}
	}

}
