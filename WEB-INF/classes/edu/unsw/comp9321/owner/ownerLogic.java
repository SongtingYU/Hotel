package edu.unsw.comp9321.owner;

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
import edu.unsw.comp9321.hotelDTO;
import edu.unsw.comp9321.userDTO;
import edu.unsw.comp9321.roomDTO;

/**
 * Servlet implementation class ownerLogic
 */
@WebServlet("/ownerLogic")
public class ownerLogic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ownerLogic() {
		super();
	}

	public static boolean ownerLogin(HttpServletRequest request, HttpServletResponse response, databaseDAOImpl cast)
			throws IOException {
		StringBuffer sb = new StringBuffer();
		Gson gson = new Gson();

		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
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
				if (user.getUser_type() != 3) {
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

	public static void getHotelsData(HttpServletRequest request, HttpServletResponse response, databaseDAOImpl cast)
			throws IOException {
		response.setCharacterEncoding("utf8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		try {
			HttpSession session = request.getSession();
			userDTO user = (userDTO) session.getAttribute("userSession");
			if (user.getUser_type() != 3) {
				out.write("Not authorized");
				out.close();
				return;
			}

			Gson gson = new Gson();
			ArrayList<hotelDTO> hotels = cast.getHotelStatisticDAO(user);
			String g = gson.toJson(hotels);
			out.write(g);
			out.close();

		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}

	}

	public static void getHotelOccupiedRooms(HttpServletRequest request, HttpServletResponse response,
			databaseDAOImpl cast) throws IOException {
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();

		try {
			HttpSession session = request.getSession();
			userDTO user = (userDTO) session.getAttribute("userSession");
			if (user.getUser_type() != 3) {
				out.write("Not authorized");
				out.close();
				return;
			}
			StringBuffer hotelSearchBuffer = new StringBuffer();
			BufferedReader reader = request.getReader();
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				hotelSearchBuffer.append(temp);
			}
			System.out.println(hotelSearchBuffer);
			Gson gson = new Gson();
			JsonElement jelement = gson.fromJson(hotelSearchBuffer.toString(), JsonElement.class);
			JsonObject jobject = jelement.getAsJsonObject();
			String hotel_name = jobject.get("hotel_name").getAsString();
			hotel_name = hotel_name.replaceAll("%20", " ");
			session.setAttribute("searchHotel", hotel_name);

			ArrayList<roomDTO> rooms = cast.getoccupiedRoomsWithHotelNameDAO(user, hotel_name);
			String g = gson.toJson(rooms);
			out.write(g);
			out.close();

		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}

	}

	public static void setHotelSession(HttpServletRequest request, HttpServletResponse response, databaseDAOImpl cast)
			throws IOException {
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();

		try {
			HttpSession session = request.getSession();
			userDTO user = (userDTO) session.getAttribute("userSession");
			if (user.getUser_type() != 3) {
				out.write("Not authorized");
				out.close();
				return;
			}
			StringBuffer hotelBuffer = new StringBuffer();
			BufferedReader reader = request.getReader();
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				hotelBuffer.append(temp);
			}
			Gson gson = new Gson();
			hotelDTO hotel = gson.fromJson(hotelBuffer.toString(), hotelDTO.class);
			session.setAttribute("selectedHotel", hotel.getHotel_name());

			out.write("success");

		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}
	}

	public static void getHotelAvailableRooms(HttpServletRequest request, HttpServletResponse response,
			databaseDAOImpl cast) throws IOException {
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();

		try {
			HttpSession session = request.getSession();
			userDTO user = (userDTO) session.getAttribute("userSession");
			if (user.getUser_type() != 3) {
				out.write("Not authorized");
				out.close();
				return;
			}
			StringBuffer hotelSearchBuffer = new StringBuffer();
			BufferedReader reader = request.getReader();
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				hotelSearchBuffer.append(temp);
			}
			Gson gson = new Gson();
			JsonElement jelement = gson.fromJson(hotelSearchBuffer.toString(), JsonElement.class);
			JsonObject jobject = jelement.getAsJsonObject();
			String hotel_name = jobject.get("hotel_name").getAsString();
			hotel_name = hotel_name.replaceAll("%20", " ");
			session.setAttribute("searchHotel", hotel_name);

			ArrayList<roomDTO> rooms = cast.getavailableRoomsWithHotelNameDAO(user, hotel_name);
			String g = gson.toJson(rooms);
			out.write(g);
			out.close();

		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}
	}

	public static void getmaintainRooms(HttpServletRequest request, HttpServletResponse response, databaseDAOImpl cast)
			throws IOException {
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();

		try {
			HttpSession session = request.getSession();
			userDTO user = (userDTO) session.getAttribute("userSession");
			if (user.getUser_type() != 3) {
				out.write("Not authorized");
				out.close();
				return;
			}
			StringBuffer hotelSearchBuffer = new StringBuffer();
			BufferedReader reader = request.getReader();
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				hotelSearchBuffer.append(temp);
			}
			Gson gson = new Gson();
			JsonElement jelement = gson.fromJson(hotelSearchBuffer.toString(), JsonElement.class);
			JsonObject jobject = jelement.getAsJsonObject();
			String hotel_name = jobject.get("hotel_name").getAsString();
			hotel_name = hotel_name.replaceAll("%20", " ");
			session.setAttribute("searchHotel", hotel_name);

			ArrayList<roomDTO> rooms = cast.getmaintainRoomsWithHotelNameDAO(user, hotel_name);
			String g = gson.toJson(rooms);
			out.write(g);
			out.close();

		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}

	}

	public static void roomRepair(HttpServletRequest request, HttpServletResponse response, databaseDAOImpl cast)
			throws IOException {
		StringBuffer roomRepariBuffer = new StringBuffer();
		Gson gson = new Gson();
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		try {
			BufferedReader reader = request.getReader();
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				roomRepariBuffer.append(temp);
			}

			roomDTO room = gson.fromJson(roomRepariBuffer.toString(), roomDTO.class);

			cast.roomRepairDTO(room);
			out.write("succeed");
			out.close();

		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}

	}

	public static void roomFinishMaintain(HttpServletRequest request, HttpServletResponse response,
			databaseDAOImpl cast) throws IOException {
		StringBuffer roomFinishMaintainBuffer = new StringBuffer();
		Gson gson = new Gson();
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		try {
			BufferedReader reader = request.getReader();
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				roomFinishMaintainBuffer.append(temp);
			}

			roomDTO room = gson.fromJson(roomFinishMaintainBuffer.toString(), roomDTO.class);

			cast.roomFinishMaintainDTO(room);
			out.write("succeed");
			out.close();

		} catch (Exception e) {
			out.write("failed");
			e.printStackTrace();
			out.close();
		}
	}

	public static void adddeal(HttpServletRequest request, HttpServletResponse response, databaseDAOImpl cast)
			throws IOException {
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		try {
			HttpSession session = request.getSession();
			userDTO user = (userDTO) session.getAttribute("userSession");
			if (user.getUser_type() != 3) {
				out.write("Not authorized");
				out.close();
				return;
			}
			StringBuffer addDealBuffer = new StringBuffer();
			BufferedReader reader = request.getReader();
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				addDealBuffer.append(temp);
			}
			Gson gson = new Gson();
			JsonElement jelement = gson.fromJson(addDealBuffer.toString(), JsonElement.class);
			JsonObject jobject = jelement.getAsJsonObject();

			String deal_name = jobject.get("deal_name").getAsString();
			String deal_type = jobject.get("deal_type").getAsString();
			String Single = jobject.get("Single").getAsString();
			String Twin = jobject.get("Twin").getAsString();
			String Queen = jobject.get("Queen").getAsString();
			String Executive = jobject.get("Executive").getAsString();
			String Suite = jobject.get("Suite").getAsString();
			String percentage = jobject.get("percentage").getAsString();
			String start_date = jobject.get("start_date").getAsString();
			String end_date = jobject.get("end_date").getAsString();
			String hotel_name = (String) session.getAttribute("selectedHotel");

			// XXX
			ArrayList<roomDTO> hotelRoomList = cast.getHotelRoomListDAO(hotel_name);

			if (deal_name.isEmpty() || deal_type.isEmpty() || percentage.isEmpty() || start_date.isEmpty()
					|| end_date.isEmpty() || hotel_name.isEmpty()) {
				System.out.println("failed add ");
				out.write("failed");
				out.close();

			} else {

				if (Single.equals("true")) {
					for (int i = 0; i < hotelRoomList.size(); i++) {
						if (hotelRoomList.get(i).getRoom_type().equals("single")) {
							cast.addSpecialForRoom(hotelRoomList.get(i).getRoom_id(), deal_name, deal_type, percentage,
									start_date, end_date);
						}
					}
				}
				if (Twin.equals("true")) {
					for (int i = 0; i < hotelRoomList.size(); i++) {
						if (hotelRoomList.get(i).getRoom_type().equals("twin")) {
							cast.addSpecialForRoom(hotelRoomList.get(i).getRoom_id(), deal_name, deal_type, percentage,
									start_date, end_date);
						}
					}
				}
				if (Queen.equals("true")) {
					for (int i = 0; i < hotelRoomList.size(); i++) {
						if (hotelRoomList.get(i).getRoom_type().equals("queen")) {
							cast.addSpecialForRoom(hotelRoomList.get(i).getRoom_id(), deal_name, deal_type, percentage,
									start_date, end_date);
						}
					}
				}
				if (Executive.equals("true")) {
					for (int i = 0; i < hotelRoomList.size(); i++) {
						if (hotelRoomList.get(i).getRoom_type().equals("executive")) {
							cast.addSpecialForRoom(hotelRoomList.get(i).getRoom_id(), deal_name, deal_type, percentage,
									start_date, end_date);
						}
					}
				}
				if (Suite.equals("true")) {
					for (int i = 0; i < hotelRoomList.size(); i++) {
						if (hotelRoomList.get(i).getRoom_type().equals("suite")) {
							cast.addSpecialForRoom(hotelRoomList.get(i).getRoom_id(), deal_name, deal_type, percentage,
									start_date, end_date);
						}
					}
				}
				System.out.println("success add ");
				out.write("success");
				out.close();
			}
		} catch (Exception e) {
			System.out.println("failed add ");
			out.write("failed");
			e.printStackTrace();
			out.close();
		}

	}

}
