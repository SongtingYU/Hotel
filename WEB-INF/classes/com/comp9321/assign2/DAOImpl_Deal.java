package com.comp9321.assign2;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DAOImpl_Deal implements interface_Deal_DAO {

	private Connection DBconnect = null;
	private PreparedStatement uPreState = null;

	public DAOImpl_Deal(Connection DBconnect) {
		this.DBconnect = DBconnect;
	}

	@Override
	public ArrayList<Deal_Bean> DealList() throws Exception {
		ArrayList<Deal_Bean> dealList = new ArrayList<Deal_Bean>();
		String sql = "SELECT * FROM deal_tbl";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
		ResultSet getR = this.uPreState.executeQuery();
		Deal_Bean deal = null;
		while (getR.next()) {
			deal = new Deal_Bean();
			deal.setDeal_id(getR.getInt(1));
			deal.setRoom_id(getR.getInt(2));
			deal.setDeal_name(getR.getString(3));
			deal.setDeal_type(getR.getString(4));
			deal.setPercentage(getR.getInt(5));
			deal.setStart_date(getR.getString(6));
			deal.setEnd_date(getR.getString(7));
			deal.setDeal_timestamp(getR.getString(8));
			dealList.add(deal);
		}
		this.uPreState.close();
		return dealList;
	}

	@Override
	public ArrayList<Deal_Bean> SpecialDealList() throws Exception {
		ArrayList<Deal_Bean> dealList = new ArrayList<Deal_Bean>();
		String sql = "SELECT * FROM deal_tbl WHERE deal_type='special'";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
		ResultSet getR = this.uPreState.executeQuery();
		Deal_Bean deal = null;
		while (getR.next()) {
			deal = new Deal_Bean();
			deal.setDeal_id(getR.getInt(1));
			deal.setRoom_id(getR.getInt(2));
			deal.setDeal_name(getR.getString(3));
			deal.setDeal_type(getR.getString(4));
			deal.setPercentage(getR.getInt(5));
			deal.setStart_date(getR.getString(6));
			deal.setEnd_date(getR.getString(7));
			deal.setDeal_timestamp(getR.getString(8));
			dealList.add(deal);
		}
		this.uPreState.close();
		return dealList;
	}

	@Override
	public ArrayList<Deal_Bean> Special_DealList(int room_id, String date) throws Exception {
		ArrayList<Deal_Bean> dealList = new ArrayList<Deal_Bean>();
		String sql = "SELECT * FROM deal_tbl WHERE start_date<? and end_date>? and deal_type='special' and room_id=?";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
		this.uPreState.setString(1, date);
		this.uPreState.setString(2, date);
		this.uPreState.setInt(3, room_id);
		ResultSet getR = this.uPreState.executeQuery();
		Deal_Bean deal = null;
		while (getR.next()) {
			deal = new Deal_Bean();
			deal.setDeal_id(getR.getInt(1));
			deal.setRoom_id(getR.getInt(2));
			deal.setDeal_name(getR.getString(3));
			deal.setDeal_type(getR.getString(4));
			deal.setPercentage(getR.getInt(5));
			deal.setStart_date(getR.getString(6));
			deal.setEnd_date(getR.getString(7));
			deal.setDeal_timestamp(getR.getString(8));
			dealList.add(deal);
		}
		this.uPreState.close();
		return dealList;
	}

	@Override
	public ArrayList<Deal_Bean> Peak_DealList(int room_id, String date) throws Exception {
		ArrayList<Deal_Bean> dealList = new ArrayList<Deal_Bean>();
		String sql = "SELECT * FROM deal_tbl WHERE start_date<? and end_date>? and deal_type='peak' and room_id=?";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
		this.uPreState.setString(1, date);
		this.uPreState.setString(2, date);
		this.uPreState.setInt(3, room_id);
		ResultSet getR = this.uPreState.executeQuery();
		Deal_Bean deal = null;
		while (getR.next()) {
			deal = new Deal_Bean();
			deal.setDeal_id(getR.getInt(1));
			deal.setRoom_id(getR.getInt(2));
			deal.setDeal_name(getR.getString(3));
			deal.setDeal_type(getR.getString(4));
			deal.setPercentage(getR.getInt(5));
			deal.setStart_date(getR.getString(6));
			deal.setEnd_date(getR.getString(7));
			deal.setDeal_timestamp(getR.getString(8));
			dealList.add(deal);
		}
		this.uPreState.close();
		return dealList;
	}

}
