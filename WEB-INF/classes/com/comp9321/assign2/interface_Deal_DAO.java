package com.comp9321.assign2;

import java.util.ArrayList;

public interface interface_Deal_DAO {

	public ArrayList<Deal_Bean> DealList() throws Exception;

	public ArrayList<Deal_Bean> SpecialDealList() throws Exception;

	public ArrayList<Deal_Bean> Special_DealList(int room_id, String date) throws Exception;

	public ArrayList<Deal_Bean> Peak_DealList(int room_id, String date) throws Exception;

}
