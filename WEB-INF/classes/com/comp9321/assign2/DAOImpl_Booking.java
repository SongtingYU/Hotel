package com.comp9321.assign2;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DAOImpl_Booking implements interface_Booking_DAO {

	private Connection DBconnect = null;
	private PreparedStatement uPreState = null;

	public DAOImpl_Booking(Connection DBconnect) {
		this.DBconnect = DBconnect;
	}

	@Override
	public boolean AddBooking(Booking_Bean booking) throws Exception {
		boolean flag = false;
		try {
			String sql = "SELECT booking_id FROM booking_tbl";
			this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
			ResultSet getR = this.uPreState.executeQuery();
			getR.last();
			int booking_id = getR.getRow() + 1;

			sql = "INSERT INTO booking_tbl(booking_id,user_id,booking_room_id,booking_confirm_price,check_in_date,check_out_date,extra_bed,PIN,service_status,booking_time) VALUES (?,?,?,?,?,?,?,?,?,?)";
			this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);

			this.uPreState.setInt(1, booking_id);
			this.uPreState.setInt(2, booking.getUser_id());
			this.uPreState.setInt(3, booking.getBooking_room_id());
			this.uPreState.setFloat(4, booking.getBooking_confirm_price());
			this.uPreState.setString(5, booking.getCheck_in_date());
			this.uPreState.setString(6, booking.getCheck_out_date());
			this.uPreState.setInt(7, booking.getExtra_bed());
			this.uPreState.setInt(8, booking.getPIN());
			this.uPreState.setString(9, booking.getService_status());
			this.uPreState.setString(10, booking.getBooking_time());
			if (this.uPreState.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (this.uPreState != null) {
				try {
					this.uPreState.close();
				} catch (Exception e) {
					throw e;
				}
			}
		}
		return flag;
	}

	@Override
	public boolean DeleteBooking(int booking_id) throws Exception {
		boolean flag = false;
		try {
			String sql = "DELETE FROM booking_tbl WHERE booking_id=?";
			this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
			this.uPreState.setInt(1, booking_id);
			if (this.uPreState.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (this.uPreState != null) {
				try {
					this.uPreState.close();
				} catch (Exception e) {
					throw e;
				}
			}
		}
		return flag;
	}

	@Override
	public ArrayList<Booking_Bean> BookingList() throws Exception {
		ArrayList<Booking_Bean> bookingList = new ArrayList<Booking_Bean>();
		String sql = "SELECT * FROM booking_tbl";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
		ResultSet getR = this.uPreState.executeQuery();
		Booking_Bean booking = null;
		while (getR.next()) {
			booking = new Booking_Bean();
			booking.setBooking_id(getR.getInt(1));
			booking.setUser_id(getR.getInt(2));
			booking.setBooking_room_id(getR.getInt(3));
			booking.setBooking_confirm_price(getR.getFloat(4));
			booking.setCheck_in_date(getR.getString(5));
			booking.setCheck_out_date(getR.getString(6));
			booking.setExtra_bed(getR.getInt(7));
			booking.setPIN(getR.getInt(8));
			booking.setService_status(getR.getString(9));
			booking.setBooking_time(getR.getString(10));
			bookingList.add(booking);
		}
		this.uPreState.close();
		return bookingList;
	}

	@Override
	public boolean UpdateBooking(Booking_Bean booking) throws Exception {
		boolean flag = false;
		try {
			String sql = "UPDATE booking_tbl SET user_id=?,booking_room_type=?,booking_confirm_price=?,check_in_date=?,check_out_date=?,extra_bed=?,PIN=?,booking_room_number=?,booking_time=? WHERE booking_id=?";
			this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
			this.uPreState.setInt(1, booking.getUser_id());
			this.uPreState.setInt(2, booking.getBooking_room_id());
			this.uPreState.setFloat(3, booking.getBooking_confirm_price());
			this.uPreState.setString(4, booking.getCheck_in_date());
			this.uPreState.setString(5, booking.getCheck_out_date());
			this.uPreState.setInt(6, booking.getExtra_bed());
			this.uPreState.setInt(7, booking.getPIN());
			this.uPreState.setString(8, booking.getService_status());
			this.uPreState.setString(9, booking.getBooking_time());
			this.uPreState.setInt(10, booking.getBooking_id());
			if (this.uPreState.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (this.uPreState != null) {
				try {
					this.uPreState.close();
				} catch (Exception e) {
					throw e;
				}
			}
		}
		return flag;
	}

	@Override
	public ArrayList<Integer> FindId_ByUserId(int user_id) throws Exception {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		String sql = "SELECT booking_id FROM booking_tbl WHERE user_id=?";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
		this.uPreState.setInt(1, user_id);
		ResultSet getR = this.uPreState.executeQuery();
		while (getR.next()) {
			int id = getR.getInt(1);
			idList.add(id);
		}
		this.uPreState.close();
		return idList;
	}

	@Override
	public Booking_Bean FindById(int id) throws Exception {
		Booking_Bean booking = null;
		String sql = "SELECT * FROM booking_tbl WHERE booking_id=?";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
		this.uPreState.setInt(1, id);
		ResultSet getR = this.uPreState.executeQuery();
		if (getR.next()) {
			booking = new Booking_Bean();
			booking.setBooking_id(getR.getInt(1));
			booking.setUser_id(getR.getInt(2));
			booking.setBooking_room_id(getR.getInt(3));
			booking.setBooking_confirm_price(getR.getFloat(4));
			booking.setCheck_in_date(getR.getString(5));
			booking.setCheck_out_date(getR.getString(6));
			booking.setExtra_bed(getR.getInt(7));
			booking.setPIN(getR.getInt(8));
			booking.setService_status(getR.getString(9));
			booking.setBooking_time(getR.getString(10));
		}
		this.uPreState.close();
		return booking;
	}

	@Override
	public ArrayList<Integer> GetPIN() throws Exception {
		ArrayList<Integer> PINList = new ArrayList<Integer>();
		String sql = "SELECT PIN FROM booking_tbl";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);

		ResultSet getR = this.uPreState.executeQuery();
		while (getR.next()) {
			PINList.add(getR.getInt(1));
		}
		return PINList;
	}

	@Override
	public ArrayList<Booking_Bean> EffectiveBookingList() throws Exception {
		ArrayList<Booking_Bean> bookingList = new ArrayList<Booking_Bean>();
		String sql = "SELECT * FROM booking_tbl WHERE service_status='booking' AND PIN>=1000000 AND user_id<>0";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
		ResultSet getR = this.uPreState.executeQuery();
		Booking_Bean booking = null;
		while (getR.next()) {
			booking = new Booking_Bean();
			booking.setBooking_id(getR.getInt(1));
			booking.setUser_id(getR.getInt(2));
			booking.setBooking_room_id(getR.getInt(3));
			booking.setBooking_confirm_price(getR.getFloat(4));
			booking.setCheck_in_date(getR.getString(5));
			booking.setCheck_out_date(getR.getString(6));
			booking.setExtra_bed(getR.getInt(7));
			booking.setPIN(getR.getInt(8));
			booking.setService_status(getR.getString(9));
			booking.setBooking_time(getR.getString(10));
			bookingList.add(booking);
		}
		this.uPreState.close();
		return bookingList;
	}

	@Override
	public ArrayList<Booking_Bean> FreeTimeBookingList(String checkin_time, String checkout_time) throws Exception {
		ArrayList<Booking_Bean> bookingList = new ArrayList<Booking_Bean>();
		String sql = "SELECT * FROM booking_tbl WHERE service_status='booking' AND PIN>=1000000 AND user_id<>0 AND (check_in_date>? OR check_out_date<?)";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
		this.uPreState.setString(1, checkout_time);
		this.uPreState.setString(2, checkin_time);
		ResultSet getR = this.uPreState.executeQuery();
		Booking_Bean booking = null;
		while (getR.next()) {
			booking = new Booking_Bean();
			booking.setBooking_id(getR.getInt(1));
			booking.setUser_id(getR.getInt(2));
			booking.setBooking_room_id(getR.getInt(3));
			booking.setBooking_confirm_price(getR.getFloat(4));
			booking.setCheck_in_date(getR.getString(5));
			booking.setCheck_out_date(getR.getString(6));
			booking.setExtra_bed(getR.getInt(7));
			booking.setPIN(getR.getInt(8));
			booking.setService_status(getR.getString(9));
			booking.setBooking_time(getR.getString(10));
			bookingList.add(booking);
		}
		this.uPreState.close();
		return bookingList;
	}

	@Override
	public ArrayList<Booking_Bean> FindCartBookingListBy_User_id(int user_id) throws Exception {
		ArrayList<Booking_Bean> bookingList = new ArrayList<Booking_Bean>();
		String sql = "SELECT * FROM booking_tbl WHERE user_id=? AND PIN=0";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
		this.uPreState.setInt(1, user_id);
		ResultSet getR = this.uPreState.executeQuery();
		Booking_Bean booking = null;
		while (getR.next()) {
			booking = new Booking_Bean();
			booking.setBooking_id(getR.getInt(1));
			booking.setUser_id(getR.getInt(2));
			booking.setBooking_room_id(getR.getInt(3));
			booking.setBooking_confirm_price(getR.getFloat(4));
			booking.setCheck_in_date(getR.getString(5));
			booking.setCheck_out_date(getR.getString(6));
			booking.setExtra_bed(getR.getInt(7));
			booking.setPIN(getR.getInt(8));
			booking.setService_status(getR.getString(9));
			booking.setBooking_time(getR.getString(10));
			bookingList.add(booking);
		}
		this.uPreState.close();
		return bookingList;
	}

	@Override
	public boolean CleanCartBookingListBy_User_id(int user_id) throws Exception {
		boolean flag = true;

		String sql = "SELECT booking_id FROM booking_tbl WHERE user_id=? AND PIN=0";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
		this.uPreState.setInt(1, user_id);
		ResultSet getR = this.uPreState.executeQuery();
		ArrayList<Integer> clear_list = new ArrayList<Integer>();
		while (getR.next()) {
			int clear_booking_id = getR.getInt(1);
			clear_list.add(clear_booking_id);
		}

		sql = "UPDATE booking_tbl SET user_id=0 WHERE booking_id=?";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
		for (int i : clear_list) {
			this.uPreState.setInt(1, i);
			if (this.uPreState.executeUpdate() <= 0) {
				flag = false;
			}
		}

		this.uPreState.close();
		return flag;
	}

	@Override
	public boolean isAvailable(Booking_Bean booking) throws Exception {
		boolean flag = true;

		String sql = "SELECT booking_id FROM booking_tbl WHERE booking_room_id=? AND ((check_in_date<=? AND check_out_date>=?) OR (check_in_date<=? AND check_out_date>=?)) AND service_status<>'checkout'";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
		this.uPreState.setInt(1, booking.getBooking_room_id());
		this.uPreState.setString(2, booking.getCheck_in_date());
		this.uPreState.setString(3, booking.getCheck_in_date());
		this.uPreState.setString(4, booking.getCheck_out_date());
		this.uPreState.setString(5, booking.getCheck_out_date());
		ResultSet getR = this.uPreState.executeQuery();
		if (getR.next()) {
			flag = false;
		}

		this.uPreState.close();
		return flag;
	}

	@Override
	public boolean Check_Catch_Day(int room_id, String checkin_time, String checkout_time) throws Exception {
		boolean flag = false;

		String sql = "SELECT booking_id FROM booking_tbl WHERE booking_room_id=? AND ((check_in_date<=? AND check_out_date>=?) OR (check_in_date<=? AND check_out_date>=?)) AND service_status<>'checkout'";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
		this.uPreState.setInt(1, room_id);
		this.uPreState.setString(2, checkin_time);
		this.uPreState.setString(3, checkin_time);
		this.uPreState.setString(4, checkout_time);
		this.uPreState.setString(5, checkout_time);
		ResultSet getR = this.uPreState.executeQuery();
		if (getR.next()) {
			flag = true;
		}

		this.uPreState.close();

		return flag;
	}

}
