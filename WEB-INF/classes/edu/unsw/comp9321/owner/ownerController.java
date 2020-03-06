package edu.unsw.comp9321.owner;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.databaseDAOImpl;
import edu.unsw.comp9321.manage.adminLogic;
import edu.unsw.comp9321.owner.ownerLogic;

/**
 * Servlet implementation class ownerController
 */
@WebServlet("/owner/ownerController")
public class ownerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private databaseDAOImpl cast;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ownerController() {
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
		System.out.println(actionName + "!!!!!");
		try {

			if (actionName == null) {
				destinationPage = "404NotFound.jsp";
			} else if (actionName.equals("login")) {
				// System.out.println("actionName = " + actionName + " !!!!!");
				ownerLogic.ownerLogin(request, response, cast);
			} else if (actionName.equals("getHotelsData")) {
				// System.out.println("actionName = " + actionName + " !!!!!");
				ownerLogic.getHotelsData(request, response, cast);
			} else if (actionName.equals("getoccupiedRooms")) {
				System.out.println("actionName = " + actionName + " 2");
				ownerLogic.getHotelOccupiedRooms(request, response, cast);
			} else if (actionName.equals("getavailableRooms")) {
				System.out.println("actionName = " + actionName + " 1");
				ownerLogic.getHotelAvailableRooms(request, response, cast);
			} else if (actionName.equals("getmaintainRooms")) {
				System.out.println("actionName = " + actionName + " 1");
				ownerLogic.getmaintainRooms(request, response, cast);
			} else if (actionName.equals("roomRepair")) {
				System.out.println("actionName = " + actionName + " 1");
				ownerLogic.roomRepair(request, response, cast);
			} else if (actionName.equals("roomFinishMaintain")) {
				System.out.println("actionName = " + actionName + " 1");
				ownerLogic.roomFinishMaintain(request, response, cast);
			} else if (actionName.equals("setHotelSession")) {
				System.out.println("actionName = " + actionName + " 1");
				ownerLogic.setHotelSession(request, response, cast);
			} else if (actionName.equals("addDeal")) {
				System.out.println("actionName = " + actionName + " 1");
				ownerLogic.adddeal(request, response, cast);
			}
			
			//ownerController?action=addDeal
			
			
			

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + destinationPage);
			dispatcher.forward(request, response);
		} catch (

		Exception e) {
		}

	}

}
