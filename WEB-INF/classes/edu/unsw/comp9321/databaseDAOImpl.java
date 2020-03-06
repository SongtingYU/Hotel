package edu.unsw.comp9321;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class databaseDAOImpl {

	static Logger logger = Logger.getLogger(databaseDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public databaseDAOImpl() throws SQLException {
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}

	public userDTO getIdentity(userDTO user) throws Exception {
		System.out.println("I'm in get Identity");
		System.out.println("I'm in get Identity" + user.getUser_name());
		try {
			Statement statement = connection.createStatement();
			String query_cast = "SELECT * FROM COMP9321assignment2.user_tbl where user_name = '" + user.getUser_name()
					+ "' and password = '" + user.getPassword() + "';";
			System.out.println("query_cast is " + query_cast);
			ResultSet res = statement.executeQuery(query_cast);
			userDTO thisUser = null;
			while (res.next()) {
				thisUser = makeUser(res);
			}
			statement.close();
			return thisUser;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public ArrayList<roomDTO> getAvailableRoomsDAO(userDTO user) throws SQLException {
		Statement statement = connection.createStatement();
		ArrayList<roomDTO> rooms = new ArrayList<roomDTO>();
		int userType = user.getUser_type();
		try {
			String searchRoom = "";
			if (userType == 2) {
				searchRoom = "SELECT * FROM COMP9321assignment2.room_tbl where manager_id = '" + user.getUser_id()
						+ "' and status like 'available';";
				System.out.println("searchRoom is " + searchRoom);
			} else if (userType == 3) {
				searchRoom = "SELECT * FROM COMP9321assignment2.room_tbl where owner_id = '" + user.getUser_id()
						+ "' and status like 'available';";
				System.out.println("searchRoom is " + searchRoom);
			}
			ResultSet res = statement.executeQuery(searchRoom);

			while (res.next()) {
				roomDTO room = makeRoom(res);
				rooms.add(room);
			}
			statement.close();
			return rooms;
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
	}
	
	public ArrayList<roomDTO> getmaintainingRoomsDTO(userDTO user) throws SQLException {
		Statement statement = connection.createStatement();
		ArrayList<roomDTO> rooms = new ArrayList<roomDTO>();
		int userType = user.getUser_type();
		try {
			String searchRoom = "";
			if (userType == 2) {
				searchRoom = "SELECT * FROM COMP9321assignment2.room_tbl where manager_id = '" + user.getUser_id()
						+ "' and status like 'maintain';";
				System.out.println("searchRoom is " + searchRoom);
			} else if (userType == 3) {
				searchRoom = "SELECT * FROM COMP9321assignment2.room_tbl where owner_id = '" + user.getUser_id()
						+ "' and status like 'maintain';";
				System.out.println("searchRoom is " + searchRoom);
			}
			ResultSet res = statement.executeQuery(searchRoom);

			while (res.next()) {
				roomDTO room = makeRoom(res);
				rooms.add(room);
			}
			statement.close();
			return rooms;
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
	}

	public ArrayList<roomDTO> getoccupiedRoomsDAO(userDTO user) throws SQLException {

		Statement statement = connection.createStatement();
		ArrayList<roomDTO> Occupiedrooms = new ArrayList<roomDTO>();
		int userType = user.getUser_type();
		try {
			String searchRoom = "";
			if (userType == 2) {
				searchRoom = "SELECT * FROM COMP9321assignment2.room_tbl where manager_id = '" + user.getUser_id()
						+ "' and status like 'occupied';";
				System.out.println("searchRoom is " + searchRoom);
			} else if (userType == 3) {
				searchRoom = "SELECT * FROM COMP9321assignment2.room_tbl where owner_id = '" + user.getUser_id()
						+ "' and status like 'occupied';";
				System.out.println("searchRoom is " + searchRoom);
			}
			ResultSet res = statement.executeQuery(searchRoom);

			while (res.next()) {
				roomDTO room = makeRoom(res);
				Occupiedrooms.add(room);
			}
			statement.close();
			return Occupiedrooms;
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}

	}

	public ArrayList<roomDTO> getoccupiedRoomsWithHotelNameDAO(userDTO user, String hotel_name) throws SQLException {
		Statement statement = connection.createStatement();
		ArrayList<roomDTO> Occupiedrooms = new ArrayList<roomDTO>();
		int userType = user.getUser_type();
		try {
			String searchRoom = "";
			if (userType == 3) {
				searchRoom = "SELECT * FROM COMP9321assignment2.room_tbl where owner_id = '" + user.getUser_id()
						+ "' and (status like 'occupied' or status like 'booking') and hotel_name like '" + hotel_name
						+ "';";
				System.out.println("searchRoom is " + searchRoom);
			}
			ResultSet res = statement.executeQuery(searchRoom);

			while (res.next()) {
				roomDTO room = makeRoom(res);
				Occupiedrooms.add(room);
			}
			statement.close();
			return Occupiedrooms;
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
	}

	public ArrayList<roomDTO> getavailableRoomsWithHotelNameDAO(userDTO user, String hotel_name) throws SQLException {
		Statement statement = connection.createStatement();
		ArrayList<roomDTO> Occupiedrooms = new ArrayList<roomDTO>();
		int userType = user.getUser_type();
		try {
			String searchRoom = "";
			if (userType == 3) {
				searchRoom = "SELECT * FROM COMP9321assignment2.room_tbl where owner_id = '" + user.getUser_id()
						+ "' and status like 'available' and hotel_name like '" + hotel_name + "';";
				System.out.println("searchRoom is " + searchRoom);
			}
			ResultSet res = statement.executeQuery(searchRoom);

			while (res.next()) {
				roomDTO room = makeRoom(res);
				Occupiedrooms.add(room);
			}
			statement.close();
			return Occupiedrooms;
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
	}

	public ArrayList<roomDTO> getmaintainRoomsWithHotelNameDAO(userDTO user, String hotel_name) throws SQLException {
		Statement statement = connection.createStatement();
		ArrayList<roomDTO> maintainrooms = new ArrayList<roomDTO>();
		int userType = user.getUser_type();
		try {
			String searchRoom = "";
			if (userType == 3) {
				searchRoom = "SELECT * FROM COMP9321assignment2.room_tbl where owner_id = '" + user.getUser_id()
						+ "' and status like 'maintain' and hotel_name like '" + hotel_name + "';";
				System.out.println("searchRoom is " + searchRoom);
			}
			ResultSet res = statement.executeQuery(searchRoom);

			while (res.next()) {
				roomDTO room = makeRoom(res);
				maintainrooms.add(room);
			}
			statement.close();
			return maintainrooms;
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
	}

	public ArrayList<roomDTO> getbookingRoomsDAO(userDTO user) throws SQLException {
		Statement statement = connection.createStatement();
		ArrayList<roomDTO> bookingRooms = new ArrayList<roomDTO>();
		int userType = user.getUser_type();
		try {
			String searchRoom = "";
			if (userType == 2) {
				searchRoom = "SELECT * FROM COMP9321assignment2.room_tbl where manager_id = '" + user.getUser_id()
						+ "' and status like 'booking';";
				System.out.println("searchRoom is " + searchRoom);
			} else if (userType == 3) {
				searchRoom = "SELECT * FROM COMP9321assignment2.room_tbl where owner_id = '" + user.getUser_id()
						+ "' and status like 'booking';";
				System.out.println("searchRoom is " + searchRoom);
			}
			ResultSet res = statement.executeQuery(searchRoom);

			while (res.next()) {
				roomDTO room = makeRoom(res);
				bookingRooms.add(room);
			}
			statement.close();
			return bookingRooms;
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
	}

	public ArrayList<roomDTO> getOriginalRoomsDTO(ArrayList<bookingDTO> bookings, userDTO user) throws SQLException {
		Statement statement = connection.createStatement();
		ArrayList<roomDTO> OriginalbookingRooms = new ArrayList<roomDTO>();
		int userType = user.getUser_type();
		try {
			for (int i = 0; i < bookings.size(); i++) {
				String getOriginalRooms = "";

				if (userType == 2) {
					getOriginalRooms = "SELECT * FROM COMP9321assignment2.room_tbl where manager_id = '"
							+ user.getUser_id() + "' and status like 'booking' and room_id = '"
							+ bookings.get(i).getBooking_room_id() + "';";
				} else if (userType == 3) {
					getOriginalRooms = "SELECT * FROM COMP9321assignment2.room_tbl where owner_id = '"
							+ user.getUser_id() + "' and status like 'booking' and room_id = '"
							+ bookings.get(i).getBooking_room_id() + "';";
					System.out.println("searchRoom is " + getOriginalRooms);
				}
				ResultSet res = statement.executeQuery(getOriginalRooms);

				while (res.next()) {
					roomDTO room = makeRoom(res);
					OriginalbookingRooms.add(room);
				}
			}

			return OriginalbookingRooms;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public ArrayList<roomDTO> getOtherAvailableRoomsDTO(ArrayList<bookingDTO> bookings, userDTO user)
			throws SQLException {
		Statement statement = connection.createStatement();
		ArrayList<roomDTO> otherAvailableRooms = new ArrayList<roomDTO>();
		int userType = user.getUser_type();

		try {
			for (int i = 0; i < bookings.size(); i++) {
				String getOtherAvailableRooms = "";

				if (userType == 2) {
					getOtherAvailableRooms = "SELECT * FROM COMP9321assignment2.room_tbl where manager_id = '"
							+ user.getUser_id() + "' and status like 'available' and room_id != '"
							+ bookings.get(i).getBooking_room_id() + "' and room_type like '"
							+ bookings.get(i).getRoom_type() + "';";
					System.out.println("getOtherAvailableRooms is kkkkk " + getOtherAvailableRooms);
				} else if (userType == 3) {
					getOtherAvailableRooms = "SELECT * FROM COMP9321assignment2.room_tbl where owner_id = '"
							+ user.getUser_id() + "' and status like 'abailable' and room_id != '"
							+ bookings.get(i).getBooking_room_id() + "' and room_type like '"
							+ bookings.get(i).getRoom_type() + "';";
					System.out.println("searchRoom is " + getOtherAvailableRooms);
				}
				ResultSet res = statement.executeQuery(getOtherAvailableRooms);

				while (res.next()) {
					roomDTO room = makeRoom(res);
					otherAvailableRooms.add(room);
				}
			}
			System.out.println(otherAvailableRooms + "help ");
			return otherAvailableRooms;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public ArrayList<bookingDTO> getuserBookingDAO(userDTO user) throws SQLException {
		Statement statement = connection.createStatement();
		ArrayList<bookingDTO> userbookings = new ArrayList<bookingDTO>();
		int userType = user.getUser_type();

		// SELECT booking_tbl.* FROM COMP9321assignment2.booking_tbl INNER JOIN
		// COMP9321assignment2.room_tbl on booking_tbl.booking_room_id =
		// room_tbl.room_id where manager_id = 2 and service_status like
		// 'booking'

		
		/// XXX pin < 1000000 will not invalid
		try {
			String searchBooking = "";
			if (userType == 2) {
				searchBooking = "SELECT booking_tbl.*, room_tbl.room_type FROM COMP9321assignment2.booking_tbl INNER JOIN "
						+ "COMP9321assignment2.room_tbl on booking_tbl.booking_room_id = room_tbl.room_id "
						+ "where manager_id = " + user.getUser_id() + " and service_status like 'booking' and PIN > 1000000;";

				System.out.println("searchBooking is !!!!???????" + searchBooking);
			} else if (userType == 3) {
				searchBooking = "SELECT booking_tbl.*, room_tbl.room_type FROM COMP9321assignment2.booking_tbl INNER JOIN "
						+ "COMP9321assignment2.room_tbl on booking_tbl.booking_room_id = room_tbl.room_id "
						+ "where owner_id = " + user.getUser_id() + " and service_status like 'booking' and PIN > 1000000;";

				System.out.println("searchBooking is " + searchBooking);
			}
			ResultSet res = statement.executeQuery(searchBooking);

			while (res.next()) {
				bookingDTO booking = makeBooking(res);
				userbookings.add(booking);
			}
			statement.close();
			return userbookings;

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}

	}

	public ArrayList<hotelDTO> getHotelStatisticDAO(userDTO user) throws SQLException {
		Statement statement = connection.createStatement();
		ArrayList<hotelDTO> hotels = new ArrayList<hotelDTO>();
		int userType = user.getUser_type();
		ArrayList<String> inChargeHotelList = new ArrayList<String>();
		System.out.println("databaseDAO: getHotelNameList:!!!!!! 有吗" + inChargeHotelList);

		try {
			inChargeHotelList = getHotelNameList(user);

			for (int i = 0; i < inChargeHotelList.size(); i++) {

				hotelDTO hotel = new hotelDTO();
				String get4TypeRoomCount = "SELECT status, COUNT(status) as number FROM COMP9321assignment2.room_tbl "
						+ "where owner_id = '" + user.getUser_id() + "' and hotel_name like '"
						+ inChargeHotelList.get(i) + "' group by status";

				// SELECT status, COUNT(status) FROM room_tbl where owner_id = 3
				// and
				// hotel_name = 'peter hotel' group by status;
				ResultSet res = statement.executeQuery(get4TypeRoomCount);
				hotel.setHotel_name(inChargeHotelList.get(i));
				while (res.next()) {

					if (res.getString("status").equals("available")) {
						hotel.setAvailable_room_number(res.getInt("number"));
						System.out.println(hotel.getAvailable_room_number());
					} else if (res.getString("status").equals("booking")) {
						hotel.setBooking_room_number(res.getInt("number"));
						System.out.println(hotel.getBooking_room_number());
					} else if (res.getString("status").equals("maintain")) {
						hotel.setMaintaining_room_number(res.getInt("number"));
						System.out.println(hotel.getMaintaining_room_number());
					} else if (res.getString("status").equals("occupied")) {
						hotel.setOccupied_room_number(res.getInt("number"));
						System.out.println(hotel.getOccupied_room_number());
					}
				}
				System.out.println("final" + hotel.getAvailable_room_number());
				hotels.add(hotel);
			}

			return hotels;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	private ArrayList<String> getHotelNameList(userDTO user) throws SQLException {
		Statement statement = connection.createStatement();

		ArrayList<String> inChargeHotelList = new ArrayList<String>();

		try {
			// SELECT distinct hotel_name FROM COMP9321assignment2.room_tbl
			// where owner_id = 3 ;

			String getHotelListByOwnerId = "SELECT distinct hotel_name FROM COMP9321assignment2.room_tbl"
					+ " where owner_id = '" + user.getUser_id() + "';";
			ResultSet res = statement.executeQuery(getHotelListByOwnerId);

			while (res.next()) {

				String temp = res.getString("hotel_name");
				inChargeHotelList.add(temp);
			}
			statement.close();
			return inChargeHotelList;

		} catch (Exception e) {
		}

		return inChargeHotelList;
	}

	public ArrayList<roomDTO> getHotelRoomListDAO(String hotel_name) throws SQLException {
		Statement statement = connection.createStatement();
		ArrayList<roomDTO> hotelRoomList = new ArrayList<roomDTO>();
		try {
			// select * from COMP9321assignment2.room_tbl where hotel_name like
			// 'peter hotel'
			String getHotelRoomList = "select * from COMP9321assignment2.room_tbl where hotel_name like '" + hotel_name
					+ "';";
			ResultSet res = statement.executeQuery(getHotelRoomList);
			System.out.println("databaseDTO : getHotelRoomListDAO" + getHotelRoomList);
			while (res.next()) {
				roomDTO room = makeRoom(res);
				hotelRoomList.add(room);
			}
			statement.close();
			return hotelRoomList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public ArrayList<bookingDTO> bookingSearchDTO(String content, String keyword, userDTO user) throws SQLException {
		Statement statement = connection.createStatement();
		ArrayList<bookingDTO> userbookings = new ArrayList<bookingDTO>();
		int userType = user.getUser_type();
		System.out.println("searchBooking is ttttttttttt" + keyword);
		try {

			String searchBooking = "";
			if (content.isEmpty() || keyword.equals("false")) {
				return userbookings;
			}

			if (userType == 2) {
				if (keyword.equals("PIN")) {
					searchBooking = "SELECT booking_tbl.*, room_tbl.room_type FROM COMP9321assignment2.booking_tbl INNER JOIN "
							+ "COMP9321assignment2.room_tbl on booking_tbl.booking_room_id = room_tbl.room_id "
							+ "where manager_id = " + user.getUser_id()
							+ " and service_status like 'booking' and PIN like " + content + ";";
					System.out.println("searchBooking is 1111111" + searchBooking);
				}
				
				// XXX
				// select booking_tbl.*, user_tbl.user_id, user_tbl.email from
				// user_tbl inner join booking_tbl on booking_tbl.user_id =
				// user_tbl.user_id inner join room_tbl on
				// booking_tbl.booking_room_id = room_tbl.room_id where
				// user_tbl.email like 'vincentywx@hotmail.com' and
				// service_status like 'booking' and room_tbl.manager_id = '2'
				else if (keyword.equals("email")) {
					searchBooking = "SELECT booking_tbl.*, room_tbl.room_type, user_tbl.user_id, user_tbl.email FROM COMP9321assignment2.user_tbl INNER JOIN "
							+ "COMP9321assignment2.booking_tbl on booking_tbl.user_id = user_tbl.user_id inner join room_tbl on booking_tbl.booking_room_id = room_tbl.room_id "
							+ " where user_tbl.email like '" + content
							+ "' and service_status like 'booking' and room_tbl.manager_id = '" + user.getUser_id()
							+ "' and PIN > 1000000;";
					System.out.println("searchBooking is 2222222" + searchBooking);

				} else if (keyword.equals("booking_id")) {

					searchBooking = "SELECT booking_tbl.*, room_tbl.room_type FROM COMP9321assignment2.booking_tbl INNER JOIN "
							+ "COMP9321assignment2.room_tbl on booking_tbl.booking_room_id = room_tbl.room_id "
							+ "where manager_id = " + user.getUser_id()
							+ " and service_status like 'booking' and booking_id = '" + content + "';";

					System.out.println("searchBooking is !!!!" + searchBooking);
				}

				System.out.println("searchBooking is !!!!" + searchBooking);
			} else if (userType == 3) {
				if (keyword.equals("PIN")) {
					searchBooking = "SELECT booking_tbl.*, room_tbl.room_type FROM COMP9321assignment2.booking_tbl INNER JOIN "
							+ "COMP9321assignment2.room_tbl on booking_tbl.booking_room_id = room_tbl.room_id "
							+ "where owner_id = " + user.getUser_id()
							+ " and service_status like 'booking' and PIN like " + content + ";";
					System.out.println("searchBooking is 3333333333" + searchBooking);

				} else if (keyword.equals("email")) {
					searchBooking = "SELECT booking_tbl.*, room_tbl.room_type, user_tbl.user_id, user_tbl.email FROM COMP9321assignment2.user_tbl INNER JOIN "
							+ "COMP9321assignment2.booking_tbl on booking_tbl.user_id = user_tbl.user_id inner join room_tbl on booking_tbl.booking_room_id = room_tbl.room_id "
							+ " where user_tbl.email like '" + content
							+ "' and service_status like 'booking' and room_tbl.owner_id = '" + user.getUser_id()
							+ "' and PIN > 1000000;";
					System.out.println("searchBooking is 2222222" + searchBooking);
				}
				System.out.println("searchBooking is " + searchBooking);
			}
			ResultSet res = statement.executeQuery(searchBooking);

			while (res.next()) {
				bookingDTO booking = makeBooking(res);
				userbookings.add(booking);
			}
			statement.close();
			return userbookings;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void roomCheckInDTO(roomDTO room) throws SQLException {
		Statement statement = connection.createStatement();
		// UPDATE `COMP9321assignment2`.`room_tbl` SET `status`='occupied' WHERE
		// `room_id`='103';
		try {
			String roomCheckInStmt = "UPDATE COMP9321assignment2.room_tbl SET status='occupied' WHERE room_id = '"
					+ room.getRoom_id() + "';";
			statement.executeUpdate(roomCheckInStmt);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void roomRepairDTO(roomDTO room) throws SQLException {
		Statement statement = connection.createStatement();
		// UPDATE `COMP9321assignment2`.`room_tbl` SET `status`='occupied' WHERE
		// `room_id`='103';
		try {
			String roomRepairStmt = "UPDATE COMP9321assignment2.room_tbl SET status='maintain' WHERE room_id = '"
					+ room.getRoom_id() + "';";
			statement.executeUpdate(roomRepairStmt);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void addSpecialForRoom(int room_id, String deal_name, String deal_type, String percentage, String start_date,
			String end_date) throws SQLException {
		Statement statement = connection.createStatement();

		int lastDeal_idNumber = getLastDealNumber();
		lastDeal_idNumber = lastDeal_idNumber + 1;
		try {
			// INSERT INTO `COMP9321assignment2`.`deal_tbl` (`deal_id`,
			// `room_id`, `deal_name`, `deal_type`, `percentage`, `start_date`,
			// `end_date`) VALUES ('7', '201', 'kkkk', 'peak', '30',
			// '2016-07-10', '2016-07-20');

			String updateStmt = "INSERT INTO COMP9321assignment2.deal_tbl (deal_id, "
					+ "room_id, deal_name, deal_type, percentage,start_date,end_date) " + "VALUES ('"
					+ lastDeal_idNumber + "', '" + room_id + "', '" + deal_name + "', '" + deal_type + "', '"
					+ percentage + "', '" + start_date + "', '" + end_date + "');";
			statement.executeUpdate(updateStmt);
			statement.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	private int getLastDealNumber() throws SQLException {
		Statement statement = connection.createStatement();
		int lastDealID = 0;
		try {
			String lastDealnumber = "SELECT deal_id FROM COMP9321assignment2.deal_tbl";
			ResultSet res = statement.executeQuery(lastDealnumber);
			while (res.next()) {
				lastDealID = res.getInt("deal_id");
			}

			return lastDealID;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void roomFinishMaintainDTO(roomDTO room) throws SQLException {
		Statement statement = connection.createStatement();
		// UPDATE `COMP9321assignment2`.`room_tbl` SET `status`='occupied' WHERE
		// `room_id`='103';
		try {
			String roomRepairStmt = "UPDATE COMP9321assignment2.room_tbl SET status='available' WHERE room_id = '"
					+ room.getRoom_id() + "';";
			statement.executeUpdate(roomRepairStmt);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void bookingCheckin(bookingDTO booking) throws SQLException {
		Statement statement = connection.createStatement();
		// UPDATE `COMP9321assignment2`.`booking_tbl` SET
		// `service_status`='checkin' WHERE `booking_id`='3';

		try {

			String bookingCheckinStmt = "UPDATE COMP9321assignment2.booking_tbl SET service_status='checkin' WHERE booking_id = '"
					+ booking.getBooking_id() + "';";
			System.out.println("update bookingcheckinStmt!!!!!!!!!!!!:" + bookingCheckinStmt);
			statement.executeUpdate(bookingCheckinStmt);

			String roomCheckInStmt = "UPDATE COMP9321assignment2.room_tbl SET status='occupied' WHERE room_id = '"
					+ booking.getBooking_room_id() + "';";
			statement.executeUpdate(roomCheckInStmt);

			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void bookingDelete(bookingDTO booking) throws SQLException {
		Statement statement = connection.createStatement();
		// UPDATE `COMP9321assignment2`.`booking_tbl` SET
		// `service_status`='checkin' WHERE `booking_id`='3';
		try {
			String bookingCheckinStmt = "UPDATE COMP9321assignment2.booking_tbl SET service_status='checkout' WHERE booking_id = '"
					+ booking.getBooking_id() + "';";
			statement.executeUpdate(bookingCheckinStmt);

			String roomCheckInStmt = "UPDATE COMP9321assignment2.room_tbl SET status='available' WHERE room_id = '"
					+ booking.getBooking_room_id() + "';";
			statement.executeUpdate(roomCheckInStmt);

			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void changeRoomDTO(String booking_id, String room_id, userDTO user) throws SQLException {
		Statement statement = connection.createStatement();
		int beChangedRoomID = 0;
		try {
			// change two room status
			// SELECT booking_room_id FROM COMP9321assignment2.booking_tbl where
			// booking_id = 3;
			String GetRoomIDStmt = "SELECT booking_room_id FROM COMP9321assignment2.booking_tbl where booking_id = "
					+ booking_id + ";";

			System.out.println("GetRoomIDStmt: " + GetRoomIDStmt);

			ResultSet res = statement.executeQuery(GetRoomIDStmt);
			while (res.next()) {
				beChangedRoomID = res.getInt("booking_room_id");
				System.out.println("databaseDTO.changeRoomDTO: beChangedRoomID:" + beChangedRoomID);
			}

			// UPDATE `COMP9321assignment2`.`room_tbl` SET `status`='available'
			// WHERE `room_id`='103';
			String changeTargetRoomStmt = "UPDATE COMP9321assignment2.room_tbl SET status = 'available' WHERE room_id = '"
					+ beChangedRoomID + "';";
			statement.executeUpdate(changeTargetRoomStmt);

			String changeWillingRoomStmt = "UPDATE COMP9321assignment2.room_tbl SET status = 'booking' WHERE room_id like '"
					+ room_id + "';";
			statement.executeUpdate(changeWillingRoomStmt);

			// change booking status

			// UPDATE `COMP9321assignment2`.`booking_tbl` SET
			// `booking_room_id`='107' WHERE `booking_id`='3';
			String changeRoomStmt = "UPDATE COMP9321assignment2.booking_tbl SET booking_room_id ='" + room_id
					+ "' where booking_id = '" + booking_id + "';";
			statement.executeUpdate(changeRoomStmt);
			System.out.println("databaseDTO.changeRoomDTO: " + changeRoomStmt);

			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void roomCheckOutDTO(roomDTO room) throws SQLException {
		Statement statement = connection.createStatement();
		// UPDATE `COMP9321assignment2`.`room_tbl` SET `status`='occupied' WHERE
		// `room_id`='103';
		try {
			String roomCheckInStmt = "UPDATE COMP9321assignment2.room_tbl SET status='available' WHERE room_id = '"
					+ room.getRoom_id() + "';";
			statement.executeUpdate(roomCheckInStmt);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private bookingDTO makeBooking(ResultSet res) throws SQLException {
		bookingDTO thisBooking = new bookingDTO();
		thisBooking.setBooking_id(res.getInt("booking_id"));
		thisBooking.setUser_id(res.getInt("user_id"));
		thisBooking.setBooking_room_id(res.getInt("booking_room_id"));
		thisBooking.setBooking_confirm_price(res.getFloat("booking_confirm_price"));
		thisBooking.setCheck_in_date(res.getString("check_in_date"));
		thisBooking.setCheck_out_date(res.getString("check_out_date"));
		thisBooking.setExtra_bed(res.getInt("extra_bed"));
		thisBooking.setPIN(res.getInt("PIN"));
		thisBooking.setService_status(res.getString("service_status"));
		thisBooking.setBooking_time(res.getString("booking_time"));
		thisBooking.setRoom_type(res.getString("room_type"));
		return thisBooking;
	}

	private roomDTO makeRoom(ResultSet res) throws SQLException {
		roomDTO thisRoom = new roomDTO();
		thisRoom.setRoom_id(res.getInt("room_id"));
		thisRoom.setHotel_name(res.getString("hotel_name"));
		thisRoom.setManager_id(res.getInt("manager_id"));
		thisRoom.setOwner_id(res.getInt("owner_id"));
		thisRoom.setRoom_type(res.getString("room_type"));
		thisRoom.setPrice(res.getFloat("price"));
		thisRoom.setCity(res.getString("city"));
		thisRoom.setAddress(res.getString("address"));
		thisRoom.setPreview(res.getString("preview"));
		thisRoom.setStatus(res.getString("status"));
		thisRoom.setImg_url(res.getString("img_url"));

		return thisRoom;
	}

	private userDTO makeUser(ResultSet res) throws SQLException {
		userDTO thisUser = new userDTO();
		thisUser.setUser_id(res.getInt("user_id"));
		thisUser.setUser_name(res.getString("user_name"));
		thisUser.setPassword(res.getString("password"));
		thisUser.setNick_name(res.getString("nick_name"));
		thisUser.setFirst_name(res.getString("first_name"));
		thisUser.setLast_name(res.getString("last_name"));
		thisUser.setEmail(res.getString("full_address"));
		thisUser.setCredit_card(res.getString("credit_card"));
		thisUser.setUser_type(res.getInt("user_type"));
		thisUser.setUser_status(res.getString("user_status"));
		thisUser.setReg_date(res.getString("reg_date"));
		return thisUser;
	}

}
