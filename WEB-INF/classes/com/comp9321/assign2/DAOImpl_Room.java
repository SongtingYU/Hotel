package com.comp9321.assign2;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DAOImpl_Room implements interface_Room_DAO {

	private Connection DBconnect = null;
	private PreparedStatement uPreState = null;

	public DAOImpl_Room(Connection DBconnect) {
		this.DBconnect = DBconnect;
	}

	@Override
	public ArrayList<Room_Bean> RoomList() throws Exception {
		ArrayList<Room_Bean> roomList = new ArrayList<Room_Bean>();
		try {
			String sql = "SELECT * FROM room_tbl";
			this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
			ResultSet getR = this.uPreState.executeQuery();
			Room_Bean room = null;
			while (getR.next()) {
				room = new Room_Bean();
				room.setRoom_id(getR.getInt(1));
				room.setHotel_name(getR.getString(2));
				room.setManager_id(getR.getInt(3));
				room.setOwner_id(getR.getInt(4));
				room.setRoom_type(getR.getString(5));
				room.setPrice(getR.getFloat(6));
				room.setCity(getR.getString(7));
				room.setAddress(getR.getString(8));
				room.setPreview(getR.getString(9));
				room.setStatus(getR.getString(10));
				room.setImg_url(getR.getString(11));
				roomList.add(room);
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
		return roomList;
	}

	@Override
	public ArrayList<Integer> FindId_ByRoomType(String room_type) throws Exception {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			String sql = "SELECT room_id FROM room_tbl WHERE room_type=?";
			this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
			this.uPreState.setString(1, room_type);
			ResultSet getR = this.uPreState.executeQuery();
			while (getR.next()) {
				int id = getR.getInt(1);
				idList.add(id);
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
		return idList;
	}

	@Override
	public ArrayList<Integer> FindId_ByPrice(float price) throws Exception {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			String sql = "SELECT room_id FROM room_tbl WHERE price=?";
			this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
			this.uPreState.setFloat(1, price);
			ResultSet getR = this.uPreState.executeQuery();
			while (getR.next()) {
				int id = getR.getInt(1);
				idList.add(id);
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
		return idList;
	}

	@Override
	public ArrayList<Integer> FindId_ByCity(String city) throws Exception {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			String sql = "SELECT room_id FROM room_tbl WHERE city=?";
			this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
			this.uPreState.setString(1, city);
			ResultSet getR = this.uPreState.executeQuery();
			while (getR.next()) {
				int id = getR.getInt(1);
				idList.add(id);
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
		return idList;
	}

	@Override
	public ArrayList<Integer> FindId_ByAddress(String address) throws Exception {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			String sql = "SELECT room_id FROM room_tbl WHERE address=?";
			this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
			this.uPreState.setString(1, address);
			ResultSet getR = this.uPreState.executeQuery();
			while (getR.next()) {
				int id = getR.getInt(1);
				idList.add(id);
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
		return idList;
	}

	@Override
	public ArrayList<Integer> FindId_ByPreview(String preview) throws Exception {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			String sql = "SELECT room_id FROM room_tbl WHERE preview=?";
			this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
			this.uPreState.setString(1, preview);
			ResultSet getR = this.uPreState.executeQuery();
			while (getR.next()) {
				int id = getR.getInt(1);
				idList.add(id);
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
		return idList;
	}

	@Override
	public ArrayList<Integer> FindId_ByStatus(String status) throws Exception {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			String sql = "SELECT room_id FROM room_tbl WHERE status=?";
			this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
			this.uPreState.setString(1, status);
			ResultSet getR = this.uPreState.executeQuery();
			while (getR.next()) {
				int id = getR.getInt(1);
				idList.add(id);
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
		return idList;
	}

	@Override
	public Room_Bean FindById(int id) throws Exception {
		Room_Bean room = null;
		try {
			String sql = "SELECT * FROM room_tbl WHERE room_id=?";
			this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
			this.uPreState.setInt(1, id);
			ResultSet getR = this.uPreState.executeQuery();
			if (getR.next()) {
				room = new Room_Bean();
				room.setRoom_id(getR.getInt(1));
				room.setHotel_name(getR.getString(2));
				room.setManager_id(getR.getInt(3));
				room.setOwner_id(getR.getInt(4));
				room.setRoom_type(getR.getString(5));
				room.setPrice(getR.getFloat(6));
				room.setCity(getR.getString(7));
				room.setAddress(getR.getString(8));
				room.setPreview(getR.getString(9));
				room.setStatus(getR.getString(10));
				room.setImg_url(getR.getString(11));
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
		return room;
	}

	@Override
	public boolean Booking_Room(int id) throws Exception {
		boolean flag = false;
		try {
			String sql = "UPDATE room_tbl SET status=? WHERE room_id=?";
			this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
			this.uPreState.setString(1, "booking");
			this.uPreState.setInt(2, id);

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
}
