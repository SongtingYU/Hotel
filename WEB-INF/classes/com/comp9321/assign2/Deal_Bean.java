package com.comp9321.assign2;

import java.io.Serializable;

public class Deal_Bean implements Serializable {

	private static final long serialVersionUID = 5006383786353486929L;

	private int deal_id;
	private int room_id;
	private String deal_name;
	private String deal_type;
	private int percentage;
	private String start_date;
	private String end_date;
	private String deal_timestamp;

	public Deal_Bean() {
		super();
	}

	public int getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(int deal_id) {
		this.deal_id = deal_id;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public String getDeal_name() {
		return deal_name;
	}

	public void setDeal_name(String deal_name) {
		this.deal_name = deal_name;
	}

	public String getDeal_type() {
		return deal_type;
	}

	public void setDeal_type(String deal_type) {
		this.deal_type = deal_type;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getDeal_timestamp() {
		return deal_timestamp;
	}

	public void setDeal_timestamp(String deal_timestamp) {
		this.deal_timestamp = deal_timestamp;
	}

}
