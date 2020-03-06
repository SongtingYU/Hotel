package com.comp9321.assign2;

import java.sql.*;
import java.util.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DAOlmpl_User implements interface_User_DAO {

	private Connection DBconnect = null;
	private PreparedStatement uPreState = null;

	public DAOlmpl_User(Connection DBconnect) {
		this.DBconnect = DBconnect;
	}

	@Override
	public boolean FindLogin(String username, String password) throws Exception {
		boolean flag = false;
		try {
			String sql = "SELECT user_name FROM user_tbl WHERE user_name=? AND password=?";
			this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
			this.uPreState.setString(1, username);
			this.uPreState.setString(2, password);
			ResultSet getR = this.uPreState.executeQuery();
			if (getR.next()) {
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

	@Override
	public boolean AddUser(User_Bean user) throws Exception {
		boolean flag = false;
		try {
			String sql = "INSERT INTO user_tbl(user_id,user_name,password,nick_name,first_name,last_name,email,full_address,credit_card,user_type,user_status,reg_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);

			this.uPreState.setInt(1, user.getUser_id());
			this.uPreState.setString(2, user.getUser_name());
			this.uPreState.setString(3, user.getPassword());
			this.uPreState.setString(4, user.getNick_name());
			this.uPreState.setString(5, user.getFirst_name());
			this.uPreState.setString(6, user.getLast_name());
			this.uPreState.setString(7, user.getEmail());
			this.uPreState.setString(8, user.getFull_address());
			this.uPreState.setString(9, user.getCredit_card());
			this.uPreState.setInt(10, user.getUser_type());
			this.uPreState.setString(11, user.getUser_status());
			this.uPreState.setString(12, user.getReg_date());
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

	@Override
	public User_Bean FindById(int id) throws SQLException {

		User_Bean user = null;
		String sql = "SELECT * FROM user_tbl WHERE user_id=?";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
		this.uPreState.setInt(1, id);
		ResultSet getR = this.uPreState.executeQuery();
		if (getR.next()) {
			user = new User_Bean();
			user.setUser_id(getR.getInt(1));
			user.setUser_name(getR.getString(2));
			user.setPassword(getR.getString(3));
			user.setNick_name(getR.getString(4));
			user.setFirst_name(getR.getString(5));
			user.setLast_name(getR.getString(6));
			user.setEmail(getR.getString(7));
			user.setFull_address(getR.getString(8));
			user.setCredit_card(getR.getString(9));
			user.setUser_type(getR.getInt(10));
			user.setUser_status(getR.getString(11));
			user.setReg_date(getR.getString(12));
		}
		this.uPreState.close();
		return user;
	}

	@Override
	public boolean DeleteUser(int user_id) throws Exception {
		boolean flag = false;
		try {
			String sql = "DELETE FROM user_tbl WHERE user_id=?";
			this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
			this.uPreState.setInt(1, user_id);
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

	@Override
	public ArrayList<User_Bean> UserList() throws Exception {
		ArrayList<User_Bean> userList = new ArrayList<User_Bean>();
		String sql = "SELECT * FROM user_tbl";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
		ResultSet getR = this.uPreState.executeQuery();
		User_Bean user = null;
		while (getR.next()) {
			user = new User_Bean();
			user.setUser_id(getR.getInt(1));
			user.setUser_name(getR.getString(2));
			user.setPassword(getR.getString(3));
			user.setNick_name(getR.getString(4));
			user.setFirst_name(getR.getString(5));
			user.setLast_name(getR.getString(6));
			user.setEmail(getR.getString(7));
			user.setFull_address(getR.getString(8));
			user.setCredit_card(getR.getString(9));
			user.setUser_type(getR.getInt(10));
			user.setUser_status(getR.getString(11));
			user.setReg_date(getR.getString(12));
			userList.add(user);
		}
		this.uPreState.close();
		return userList;
	}

	@Override
	public int FindId_ByUsername(String username) throws Exception {
		int id = 0;
		String sql = "SELECT user_id FROM user_tbl WHERE user_name=?";
		this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
		this.uPreState.setString(1, username);
		ResultSet getR = this.uPreState.executeQuery();
		if (getR.next()) {
			id = getR.getInt(1);
		}
		this.uPreState.close();
		return id;
	}

	@Override
	public boolean UpdateUser(User_Bean user) throws Exception {
		boolean flag = false;
		try {
			String sql = "UPDATE user_tbl SET user_name=?,password=?,nick_name=?,first_name=?,last_name=?,email=?,full_address=?,credit_card=?,user_type=?,user_status=?,reg_date=? WHERE user_id=?";
			this.uPreState = (PreparedStatement) this.DBconnect.prepareStatement(sql);
			this.uPreState.setString(1, user.getUser_name());
			this.uPreState.setString(2, user.getPassword());
			this.uPreState.setString(3, user.getNick_name());
			this.uPreState.setString(4, user.getFirst_name());
			this.uPreState.setString(5, user.getLast_name());
			this.uPreState.setString(6, user.getEmail());
			this.uPreState.setString(7, user.getFull_address());
			this.uPreState.setString(8, user.getCredit_card());
			this.uPreState.setInt(9, user.getUser_type());
			this.uPreState.setString(10, user.getUser_status());
			this.uPreState.setString(11, user.getReg_date());
			this.uPreState.setInt(12, user.getUser_id());
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
