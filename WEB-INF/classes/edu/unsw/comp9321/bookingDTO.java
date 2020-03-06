package edu.unsw.comp9321;

public class bookingDTO {
	private int booking_id;
	private int user_id;
	private int booking_room_id;
	private float booking_confirm_price;
	private String check_in_date;
	private String check_out_date;
	private int extra_bed;
	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

	private int PIN;
	private String service_status;
	private String booking_time;
	private String room_type;

	public int getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getBooking_room_id() {
		return booking_room_id;
	}

	public void setBooking_room_id(int booking_room_id) {
		this.booking_room_id = booking_room_id;
	}

	public float getBooking_confirm_price() {
		return booking_confirm_price;
	}

	public void setBooking_confirm_price(float booking_confirm_price) {
		this.booking_confirm_price = booking_confirm_price;
	}

	public String getCheck_in_date() {
		return check_in_date;
	}

	public void setCheck_in_date(String check_in_date) {
		this.check_in_date = check_in_date;
	}

	public String getCheck_out_date() {
		return check_out_date;
	}

	public void setCheck_out_date(String check_out_date) {
		this.check_out_date = check_out_date;
	}

	public int getExtra_bed() {
		return extra_bed;
	}

	public void setExtra_bed(int extra_bed) {
		this.extra_bed = extra_bed;
	}

	public int getPIN() {
		return PIN;
	}

	public void setPIN(int pIN) {
		PIN = pIN;
	}

	public String getService_status() {
		return service_status;
	}

	public void setService_status(String service_status) {
		this.service_status = service_status;
	}

	public String getBooking_time() {
		return booking_time;
	}

	public void setBooking_time(String booking_time) {
		this.booking_time = booking_time;
	}

}
