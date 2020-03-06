package com.comp9321.assign2;

public class Factory_DAO {

	public static interface_Booking_DAO GetBookingDAOInstance() {
		return new DAOProxy_Booking();
	}

	public static interface_Deal_DAO GetDealDAOInstance() {
		return new DAOProxy_Deal();
	}

	public static interface_Room_DAO GetRoomDAOInstance() {
		return new DAOProxy_Room();
	}

	public static interface_User_DAO GetUserDAOInstance() {
		return new DAOProxy_User();
	}

}
