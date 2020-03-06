package bean;

import java.util.ArrayList;

import com.comp9321.assign2.Booking_Bean;

public class Row {
	private int booking_id;
	private int user_id;
	private int booking_room_id;
	private float booking_confirm_price;
	private String check_in_date;
	private String check_out_date;
	private int extra_bed;
	private int PIN;
	private int booking_room_number;
	private String booking_time;
	private String hotel_name;
	private String room_type;
	private String city;
	private String service_status;
	
	public String getService_status() {
		return service_status;
	}

	public void setService_status(String service_status) {
		this.service_status = service_status;
	}
	
	public void sethotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public String gethotel_name() {
		return hotel_name;
	}
	
	public void setroom_type(String room_type) {
		this.room_type = room_type;
	}
	public String getroom_type() {
		return room_type;
	}
	
	public void setcity(String city) {
		this.city = city;
	}
	public String getcity() {
		return city;
	}
	
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	public int getBooking_id() {
		return booking_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getUser_id() {
		return user_id;
	}
	
	public void setBooking_room_id(int booking_room_id) {
		this.booking_room_id = booking_room_id;
	}
	public int getBooking_room_id() {
		return booking_room_id;
	}
	
	public void setCheck_in_date(String check_in_date) {
		this.check_in_date = check_in_date;
	}
	public String getCheck_in_date() {
		return check_in_date;
	}
	
	public void setCheck_out_date(String check_out_date) {
		this.check_out_date = check_out_date;
	}
	public String getCheck_out_date() {
		return check_out_date;
	}
	
	public void setExtra_bed(int extra_bed) {
		this.extra_bed = extra_bed;
	}
	public int getExtra_bed() {
		return extra_bed;
	}
	
	public void setBooking_room_number(int booking_room_number) {
		this.booking_room_number = booking_room_number;
	}
	public int getBooking_room_number() {
		return booking_room_number;
	}
	
	public void setPIN(int PIN) {
		this.PIN = PIN;
	}
	public int getPIN() {
		return PIN;
	}
	
	public void setBooking_time(String booking_time) {
		this.booking_time = booking_time;
	}
	public String getBooking_time() {
		return booking_time;
	}
	
	public void setBooking_confirm_price(float booking_confirm_price) {
		this.booking_confirm_price = booking_confirm_price;
	}
	public float getBooking_confirm_price() {
		return booking_confirm_price;
	}
}
