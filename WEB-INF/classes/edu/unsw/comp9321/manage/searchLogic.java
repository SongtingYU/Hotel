package edu.unsw.comp9321.manage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.unsw.comp9321.bookingDTO;
import edu.unsw.comp9321.databaseDAOImpl;
import edu.unsw.comp9321.userDTO;
import edu.unsw.comp9321.roomDTO;

/**
 * Servlet implementation class searchLogic
 */
@WebServlet("/searchLogic")
public class searchLogic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public searchLogic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void searchBooking(HttpServletRequest request, HttpServletResponse response, databaseDAOImpl cast) throws IOException {
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();	
		
		try {
			HttpSession session = request.getSession();
			userDTO user = (userDTO) session.getAttribute("userSession");
			if (user.getUser_type() != 3 && user.getUser_type() != 2) {
				out.write("Not authorized");
				out.close();
				return;
			}
			
			StringBuffer bookingSearchBuffer = new StringBuffer();
			BufferedReader reader = request.getReader();
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				bookingSearchBuffer.append(temp);
			}

			Gson gson = new Gson();
			JsonElement jelement = gson.fromJson(bookingSearchBuffer.toString(), JsonElement.class);
			JsonObject jobject = jelement.getAsJsonObject();
			
			String content = jobject.get("content").getAsString();
			String keyword = jobject.get("keyword").getAsString();
			
			System.out.println("hello world");
			ArrayList<bookingDTO> bookings =cast.bookingSearchDTO(content, keyword, user);
			String g = gson.toJson(bookings);
			
			session.setAttribute("searchResultSession", bookings);
			
			out.write(g);
			out.close();

		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}	
		
	}

	public static void changeRoom(HttpServletRequest request, HttpServletResponse response, databaseDAOImpl cast)
			throws IOException {
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();	
		
		
		try {
			HttpSession session = request.getSession();
			userDTO user = (userDTO) session.getAttribute("userSession");
			if (user.getUser_type() != 3 && user.getUser_type() != 2) {
				out.write("Not authorized");
				out.close();
				return;
			}
			
			StringBuffer changeRoomBuffer = new StringBuffer();
			BufferedReader reader = request.getReader();
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				changeRoomBuffer.append(temp);
			}

			Gson gson = new Gson();
			JsonElement jelement = gson.fromJson(changeRoomBuffer.toString(), JsonElement.class);
			JsonObject jobject = jelement.getAsJsonObject();
			
			String booking_id = jobject.get("booking_id").getAsString();
			String room_id = jobject.get("room_id").getAsString();
			
			System.out.println("searchLogic.changeRoom(): booking_id = " + booking_id + "room_id" + room_id);
			
			cast.changeRoomDTO(booking_id,room_id,user);
			out.write("succeed");
			out.close();

		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}	
		
		
		
	}
	
	
	
	
	public static void getoriginalBookingRoom(HttpServletRequest request, HttpServletResponse response,
			databaseDAOImpl cast) throws IOException {
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();	
		Gson gson = new Gson();
		
		try {
			HttpSession session = request.getSession();
			userDTO user = (userDTO) session.getAttribute("userSession");
			if (user.getUser_type() != 3 && user.getUser_type() != 2) {
				out.write("Not authorized");
				out.close();
				return;
			}
			ArrayList<bookingDTO> bookings = (ArrayList<bookingDTO>) session.getAttribute("searchResultSession");
			ArrayList<roomDTO> getOriginalRooms =cast.getOriginalRoomsDTO(bookings, user);
			String g = gson.toJson(getOriginalRooms);
			
			out.write(g);
			out.close();

		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}	
	}

	public static void getOtherAvailableRooms(HttpServletRequest request, HttpServletResponse response,
			databaseDAOImpl cast) throws IOException {
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();	
		Gson gson = new Gson();		
		
		try {
			HttpSession session = request.getSession();
			userDTO user = (userDTO) session.getAttribute("userSession");
			if (user.getUser_type() != 3 && user.getUser_type() != 2) {
				out.write("Not authorized");
				out.close();
				return;
			}
			ArrayList<bookingDTO> bookings = (ArrayList<bookingDTO>) session.getAttribute("searchResultSession");
			ArrayList<roomDTO> getOriginalRooms =cast.getOtherAvailableRoomsDTO(bookings, user);
			String g = gson.toJson(getOriginalRooms);
			
			out.write(g);
			out.close();

		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}	
	}

	

}
