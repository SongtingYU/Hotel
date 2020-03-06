package com.comp9321.assign2;

import java.util.ArrayList;

public class DAOProxy_Booking implements interface_Booking_DAO {

	private DatabaseConnection DBconnect = null;
	private interface_Booking_DAO uDAO = null;

	public DAOProxy_Booking() {
		try {
			this.DBconnect = new DatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.uDAO = new DAOImpl_Booking(this.DBconnect.getConnection());
	}

	@Override
	public boolean AddBooking(Booking_Bean booking) throws Exception {
		boolean flag = false;
		try {
			flag = this.uDAO.AddBooking(booking);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return flag;
	}

	@Override
	public boolean DeleteBooking(int booking_id) throws Exception {
		boolean flag = false;
		try {
			flag = this.uDAO.DeleteBooking(booking_id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return flag;
	}

	@Override
	public ArrayList<Booking_Bean> BookingList() throws Exception {
		ArrayList<Booking_Bean> bookingList = null;
		try {
			bookingList = this.uDAO.BookingList();
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return bookingList;
	}

	@Override
	public boolean UpdateBooking(Booking_Bean booking) throws Exception {
		boolean flag = false;
		try {
			flag = this.uDAO.UpdateBooking(booking);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return flag;
	}

	@Override
	public ArrayList<Integer> FindId_ByUserId(int user_id) throws Exception {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			idList = this.uDAO.FindId_ByUserId(user_id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return idList;
	}

	@Override
	public Booking_Bean FindById(int id) throws Exception {
		Booking_Bean booking = null;
		try {
			booking = this.uDAO.FindById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return booking;
	}

	@Override
	public ArrayList<Integer> GetPIN() throws Exception {
		ArrayList<Integer> PINList = null;
		try {
			PINList = this.uDAO.GetPIN();
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return PINList;
	}

	@Override
	public ArrayList<Booking_Bean> EffectiveBookingList() throws Exception {
		ArrayList<Booking_Bean> bookingList = null;
		try {
			bookingList = this.uDAO.EffectiveBookingList();
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return bookingList;
	}

	@Override
	public ArrayList<Booking_Bean> FreeTimeBookingList(String checkin_time, String checkout_time) throws Exception {
		ArrayList<Booking_Bean> bookingList = null;
		try {
			bookingList = this.uDAO.FreeTimeBookingList(checkin_time, checkout_time);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return bookingList;
	}

	@Override
	public ArrayList<Booking_Bean> FindCartBookingListBy_User_id(int user_id) throws Exception {
		ArrayList<Booking_Bean> bookingList = null;
		try {
			bookingList = this.uDAO.FindCartBookingListBy_User_id(user_id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return bookingList;
	}

	@Override
	public boolean CleanCartBookingListBy_User_id(int user_id) throws Exception {
		boolean flag = false;
		try {
			flag = this.uDAO.CleanCartBookingListBy_User_id(user_id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return flag;
	}

	@Override
	public boolean isAvailable(Booking_Bean booking) throws Exception {
		boolean flag = true;
		try {
			flag = this.uDAO.isAvailable(booking);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return flag;
	}

	@Override
	public boolean Check_Catch_Day(int room_id, String checkin_time, String checkout_time) throws Exception {
		boolean flag = false;
		try {
			flag = this.uDAO.Check_Catch_Day(room_id, checkin_time, checkout_time);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return flag;
	}

}
