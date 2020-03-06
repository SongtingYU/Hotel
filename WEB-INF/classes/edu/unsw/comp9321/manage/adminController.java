package edu.unsw.comp9321.manage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.databaseDAOImpl;

/**
 * Servlet implementation class adminController
 */
@WebServlet("/manage/adminController")
public class adminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private databaseDAOImpl cast;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public adminController() {
		super();
		try {
			cast = new databaseDAOImpl();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("failed");
		}
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		dispatchRequests(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		dispatchRequests(request, response);
	}

	private void dispatchRequests(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String destinationPage = "";
		String actionName = request.getParameter("action");
		System.out.println(actionName + "!!!!!");
		try {
			if (actionName == null) {
				destinationPage = "404NotFound.jsp";
			} else if (actionName.equals("login")) {
				// System.out.println("actionName = " + actionName + " !!!!!");
				adminLogic.adminLogin(request, response, cast);
			} else if (actionName.equals("getAvailableRooms")) {
				System.out.println("actionName = " + actionName + " 1");
				adminLogic.getAvailableRooms(request, response, cast);
			} else if (actionName.equals("getoccupiedRooms")) {
				System.out.println("actionName = " + actionName + " 2");
				adminLogic.getoccupiedRooms(request, response, cast);
			} else if (actionName.equals("getbookingRooms")) {
				System.out.println("actionName = " + actionName + " 3");
				adminLogic.getbookingRooms(request, response, cast);
			} else if (actionName.equals("roomCheckIn")) {
				System.out.println("actionName = " + actionName + " 4");
				adminLogic.roomCheckIn(request, response, cast);
			} else if (actionName.equals("roomCheckOut")) {
				System.out.println("actionName = " + actionName + " 5");
				adminLogic.roomCheckOut(request, response, cast);
			} else if (actionName.equals("getuserBooking")) {
				System.out.println("actionName = " + actionName + " 6");
				adminLogic.getuserBooking(request, response, cast);
			} else if (actionName.equals("bookingCheckin")) {
				System.out.println("actionName = " + actionName + " 7");
				adminLogic.bookingCheckin(request, response, cast);
			} else if (actionName.equals("bookingDelete")) {
				System.out.println("actionName = " + actionName + " 8");
				adminLogic.bookingDelete(request, response, cast);
			} else if (actionName.equals("getmaintainingRooms")) {
				System.out.println("actionName = " + actionName + " 8");
				adminLogic.getmaintainingRooms(request, response, cast);
			}
			
			

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + destinationPage);
			dispatcher.forward(request, response);

		} catch (Exception e) {

		}

	}

}
