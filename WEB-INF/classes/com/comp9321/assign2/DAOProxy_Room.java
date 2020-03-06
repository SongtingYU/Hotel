package com.comp9321.assign2;

import java.util.ArrayList;

public class DAOProxy_Room implements interface_Room_DAO {

	private DatabaseConnection DBconnect = null;
	private interface_Room_DAO uDAO = null;

	public DAOProxy_Room() {
		try {
			this.DBconnect = new DatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.uDAO = new DAOImpl_Room(this.DBconnect.getConnection());
	}

	@Override
	public ArrayList<Room_Bean> RoomList() throws Exception {
		ArrayList<Room_Bean> roomList = null;
		try {
			roomList = this.uDAO.RoomList();
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return roomList;
	}

	@Override
	public ArrayList<Integer> FindId_ByRoomType(String room_type) throws Exception {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			idList = this.uDAO.FindId_ByRoomType(room_type);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return idList;
	}

	@Override
	public ArrayList<Integer> FindId_ByPrice(float price) throws Exception {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			idList = this.uDAO.FindId_ByPrice(price);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return idList;
	}

	@Override
	public ArrayList<Integer> FindId_ByCity(String city) throws Exception {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			idList = this.uDAO.FindId_ByCity(city);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return idList;
	}

	@Override
	public ArrayList<Integer> FindId_ByAddress(String address) throws Exception {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			idList = this.uDAO.FindId_ByAddress(address);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return idList;
	}

	@Override
	public ArrayList<Integer> FindId_ByPreview(String preview) throws Exception {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			idList = this.uDAO.FindId_ByPreview(preview);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return idList;
	}

	@Override
	public ArrayList<Integer> FindId_ByStatus(String status) throws Exception {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			idList = this.uDAO.FindId_ByStatus(status);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return idList;
	}

	@Override
	public Room_Bean FindById(int id) throws Exception {
		Room_Bean room = null;
		try {
			room = this.uDAO.FindById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return room;
	}

	@Override
	public boolean Booking_Room(int id) throws Exception {
		boolean flag = false;
		try {
			flag = this.uDAO.Booking_Room(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return flag;
	}

}
