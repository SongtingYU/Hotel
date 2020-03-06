package com.comp9321.assign2;

import java.util.ArrayList;

public interface interface_Booking_DAO {

	public boolean AddBooking(Booking_Bean booking) throws Exception;

	public boolean DeleteBooking(int booking_id) throws Exception;

	public ArrayList<Booking_Bean> BookingList() throws Exception;

	public ArrayList<Booking_Bean> EffectiveBookingList() throws Exception;

	public ArrayList<Booking_Bean> FreeTimeBookingList(String checkin_time, String checkout_time) throws Exception;

	public boolean Check_Catch_Day(int room_id, String checkin_time, String checkout_time) throws Exception;

	public ArrayList<Booking_Bean> FindCartBookingListBy_User_id(int user_id) throws Exception;

	public boolean CleanCartBookingListBy_User_id(int user_id) throws Exception;

	public boolean UpdateBooking(Booking_Bean booking) throws Exception;

	public ArrayList<Integer> FindId_ByUserId(int user_id) throws Exception;

	public Booking_Bean FindById(int id) throws Exception;

	public ArrayList<Integer> GetPIN() throws Exception;

	public boolean isAvailable(Booking_Bean booking) throws Exception;

}
