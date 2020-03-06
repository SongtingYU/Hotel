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
 * Servlet implementation class adminLogic
 */
@WebServlet("/adminLogic")
public class adminLogic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public adminLogic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static boolean adminLogin(HttpServletRequest request, HttpServletResponse response, databaseDAOImpl cast)
			throws IOException {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		Gson gson = new Gson();

		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		System.out.println("pass here 1 ");
		try {
			BufferedReader reader = request.getReader();
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				sb.append(temp);
			}
			System.out.println("sb = " + sb);
			userDTO user = gson.fromJson(sb.toString(), userDTO.class);
			user = cast.getIdentity(user);
			if (user == null) {
				out.write("login failed");
				out.close();
				return false;
			} else {
				if (user.getUser_type() != 3 && user.getUser_type() != 2) {
					out.write("login failed");
					out.close();
					return false;
				}
				HttpSession session = request.getSession();
				session.setAttribute("userSession", user);
				out.write("login successed");
				out.close();
				System.out.println("user = " + user.getUser_name() + "bbbbb");
				return true;
			}

		} catch (Exception e) {
			out.write("login failed");
			out.close();
			return false;
		}
	}

	public static void roomCheckIn(HttpServletRequest request, HttpServletResponse response, databaseDAOImpl cast)
			throws IOException {
		StringBuffer roomFormBuffer = new StringBuffer();
		Gson gson = new Gson();
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		try {
			BufferedReader reader = request.getReader();
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				roomFormBuffer.append(temp);
			}

			roomDTO room = gson.fromJson(roomFormBuffer.toString(), roomDTO.class);

			cast.roomCheckInDTO(room);
			out.write("succeed");
			out.close();

		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}

	}

	public static void bookingCheckin(HttpServletRequest request, HttpServletResponse response, databaseDAOImpl cast)
			throws IOException {
		StringBuffer bookingBuffer = new StringBuffer();
		Gson gson = new Gson();
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();

		try {
			BufferedReader reader = request.getReader();
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				bookingBuffer.append(temp);
			}

			System.out.println("roomFormBuffer = " + bookingBuffer);

			bookingDTO booking = gson.fromJson(bookingBuffer.toString(), bookingDTO.class);
			System.out.println("room1: " + booking.getBooking_id() + booking.getService_status() + "??");

			cast.bookingCheckin(booking);
			out.write("succeed");
			out.close();

		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}
	}

	public static void bookingDelete(HttpServletRequest request, HttpServletResponse response, databaseDAOImpl cast)
			throws IOException {
		StringBuffer bookingBuffer = new StringBuffer();
		Gson gson = new Gson();
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();

		try {
			BufferedReader reader = request.getReader();
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				bookingBuffer.append(temp);
			}

			System.out.println("roomFormBuffer = " + bookingBuffer);

			bookingDTO booking = gson.fromJson(bookingBuffer.toString(), bookingDTO.class);
			System.out.println("room1: " + booking.getBooking_id() + booking.getService_status() + "??");

			cast.bookingDelete(booking);
			out.write("succeed");
			out.close();

		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}
	}

	public static void roomCheckOut(HttpServletRequest request, HttpServletResponse response, databaseDAOImpl cast)
			throws IOException {
		// TODO Auto-generated method stub
		StringBuffer roomFormBuffer = new StringBuffer();
		Gson gson = new Gson();
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();

		try {
			BufferedReader reader = request.getReader();
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				roomFormBuffer.append(temp);
			}

			System.out.println("roomFormBuffer = " + roomFormBuffer);

			roomDTO room = gson.fromJson(roomFormBuffer.toString(), roomDTO.class);
			System.out.println("room1: " + room.getRoom_id() + room.getStatus() + "??");

			cast.roomCheckOutDTO(room);
			out.write("succeed");
			out.close();

		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}

	}

	public static void getAvailableRooms(HttpServletRequest request, HttpServletResponse response, databaseDAOImpl cast)
			throws IOException {
		response.setCharacterEncoding("utf8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		try {
			HttpSession session = request.getSession();
			userDTO user = (userDTO) session.getAttribute("userSession");
			if (user.getUser_type() != 3 && user.getUser_type() != 2) {
				out.write("Not authorized");
				out.close();
				return;
			}

			Gson gson = new Gson();
			ArrayList<roomDTO> rooms = cast.getAvailableRoomsDAO(user);
			String g = gson.toJson(rooms);
			out.write(g);
			out.close();
		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}
	}

	public static void getmaintainingRooms(HttpServletRequest request, HttpServletResponse response,
			databaseDAOImpl cast) throws IOException {
		response.setCharacterEncoding("utf8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		try {
			HttpSession session = request.getSession();
			userDTO user = (userDTO) session.getAttribute("userSession");
			if (user.getUser_type() != 3 && user.getUser_type() != 2) {
				out.write("Not authorized");
				out.close();
				return;
			}

			Gson gson = new Gson();
			ArrayList<roomDTO> rooms = cast.getmaintainingRoomsDTO(user);
			String g = gson.toJson(rooms);
			out.write(g);
			out.close();
		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}
	}

	public static void getoccupiedRooms(HttpServletRequest request, HttpServletResponse response, databaseDAOImpl cast)
			throws IOException {
		response.setCharacterEncoding("utf8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		try {
			HttpSession session = request.getSession();
			userDTO user = (userDTO) session.getAttribute("userSession");
			if (user.getUser_type() != 3 && user.getUser_type() != 2) {
				out.write("Not authorized");
				out.close();
				return;
			}

			Gson gson = new Gson();
			ArrayList<roomDTO> rooms = cast.getoccupiedRoomsDAO(user);
			String g = gson.toJson(rooms);
			out.write(g);
			out.close();
		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}
	}

	public static void getbookingRooms(HttpServletRequest request, HttpServletResponse response, databaseDAOImpl cast)
			throws IOException {
		response.setCharacterEncoding("utf8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		try {
			HttpSession session = request.getSession();
			userDTO user = (userDTO) session.getAttribute("userSession");
			if (user.getUser_type() != 3 && user.getUser_type() != 2) {
				out.write("Not authorized");
				out.close();
				return;
			}

			Gson gson = new Gson();
			ArrayList<roomDTO> rooms = cast.getbookingRoomsDAO(user);
			String g = gson.toJson(rooms);
			out.write(g);
			out.close();
		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}
	}

	public static void getuserBooking(HttpServletRequest request, HttpServletResponse response, databaseDAOImpl cast)
			throws IOException {
		response.setCharacterEncoding("utf8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		try {
			HttpSession session = request.getSession();
			userDTO user = (userDTO) session.getAttribute("userSession");
			if (user.getUser_type() != 3 && user.getUser_type() != 2) {
				out.write("Not authorized");
				out.close();
				return;
			}

			Gson gson = new Gson();
			ArrayList<bookingDTO> bookings = cast.getuserBookingDAO(user);
			String g = gson.toJson(bookings);

			out.write(g);
			out.close();
		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}
	}

}
