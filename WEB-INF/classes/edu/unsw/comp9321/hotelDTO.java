package edu.unsw.comp9321;

public class hotelDTO {
	private String hotel_name;
	private int available_room_number;
	private int occupied_room_number;
	private int booking_room_number;
	private int maintaining_room_number;

	
	public hotelDTO() {
		super();
		available_room_number = 0;
		occupied_room_number = 0;
		booking_room_number = 0;
		maintaining_room_number = 0;
	}

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	public int getAvailable_room_number() {
		return available_room_number;
	}

	public void setAvailable_room_number(int available_room_number) {
		this.available_room_number = available_room_number;
	}

	public int getOccupied_room_number() {
		return occupied_room_number;
	}

	public void setOccupied_room_number(int occupied_room_number) {
		this.occupied_room_number = occupied_room_number;
	}

	public int getBooking_room_number() {
		return booking_room_number;
	}

	public void setBooking_room_number(int booking_room_number) {
		this.booking_room_number = booking_room_number;
	}

	public int getMaintaining_room_number() {
		return maintaining_room_number;
	}

	public void setMaintaining_room_number(int maintaining_room_number) {
		this.maintaining_room_number = maintaining_room_number;
	}

}
