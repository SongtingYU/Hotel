package com.comp9321.assign2;

import java.util.ArrayList;

public interface interface_Room_DAO {

	public ArrayList<Room_Bean> RoomList() throws Exception;

	public ArrayList<Integer> FindId_ByRoomType(String room_type) throws Exception;

	public ArrayList<Integer> FindId_ByPrice(float price) throws Exception;

	public ArrayList<Integer> FindId_ByCity(String city) throws Exception;

	public ArrayList<Integer> FindId_ByAddress(String address) throws Exception;

	public ArrayList<Integer> FindId_ByPreview(String preview) throws Exception;

	public ArrayList<Integer> FindId_ByStatus(String status) throws Exception;

	public Room_Bean FindById(int id) throws Exception;

	public boolean Booking_Room(int id) throws Exception;
}
