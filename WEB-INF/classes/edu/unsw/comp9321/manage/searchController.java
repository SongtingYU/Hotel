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
 * Servlet implementation class searchController
 */
@WebServlet("/manage/searchController")
public class searchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private databaseDAOImpl cast;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public searchController() {
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
		String content = request.getParameter("content");
		String keyword = request.getParameter("keyword");
		System.out.println("searchControllor: content = " + content + "keyword" + keyword);
		try {
			if (actionName == null) {
				destinationPage = "404NotFound.jsp";
			} else if (actionName.equals("searchBooking")) {
				searchLogic.searchBooking(request, response, cast);
			} else if (actionName.equals("getoriginalBookingRoom")) {
				searchLogic.getoriginalBookingRoom(request, response, cast);
			} else if (actionName.equals("getOtherAvailableRooms")){
				System.out.println("searchControllor: getOtherAvailableRooms = " + actionName);
				searchLogic.getOtherAvailableRooms(request, response, cast);
			} else if (actionName.equals("changeRoom")){
				System.out.println("searchControllor: getOtherAvailableRooms = " + actionName);
				searchLogic.changeRoom(request, response, cast);
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + destinationPage);
			dispatcher.forward(request, response);

		} catch (Exception e) {

		}
	}
}
