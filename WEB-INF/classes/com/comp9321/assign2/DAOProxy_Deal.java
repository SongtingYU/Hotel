package com.comp9321.assign2;

import java.util.ArrayList;

public class DAOProxy_Deal implements interface_Deal_DAO {

	private DatabaseConnection DBconnect = null;
	private interface_Deal_DAO uDAO = null;

	public DAOProxy_Deal() {
		try {
			this.DBconnect = new DatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.uDAO = new DAOImpl_Deal(this.DBconnect.getConnection());
	}

	@Override
	public ArrayList<Deal_Bean> DealList() throws Exception {
		ArrayList<Deal_Bean> dealList = null;
		try {
			dealList = this.uDAO.DealList();
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return dealList;
	}

	@Override
	public ArrayList<Deal_Bean> SpecialDealList() throws Exception {
		ArrayList<Deal_Bean> dealList = null;
		try {
			dealList = this.uDAO.SpecialDealList();
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return dealList;
	}

	@Override
	public ArrayList<Deal_Bean> Special_DealList(int room_id, String date) throws Exception {
		ArrayList<Deal_Bean> dealList = null;
		try {
			dealList = this.uDAO.Special_DealList(room_id, date);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return dealList;
	}

	@Override
	public ArrayList<Deal_Bean> Peak_DealList(int room_id, String date) throws Exception {
		ArrayList<Deal_Bean> dealList = null;
		try {
			dealList = this.uDAO.Peak_DealList(room_id, date);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return dealList;
	}

}
