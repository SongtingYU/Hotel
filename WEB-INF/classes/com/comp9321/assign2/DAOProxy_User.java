package com.comp9321.assign2;

import java.util.ArrayList;

public class DAOProxy_User implements interface_User_DAO {

	private DatabaseConnection DBconnect = null;
	private interface_User_DAO uDAO = null;

	public DAOProxy_User() {
		try {
			this.DBconnect = new DatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.uDAO = new DAOlmpl_User(this.DBconnect.getConnection());
	}

	@Override
	public boolean FindLogin(String username, String password) throws Exception {
		boolean flag = false;
		try {
			flag = this.uDAO.FindLogin(username, password);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return flag;
	}

	@Override
	public boolean AddUser(User_Bean user) throws Exception {
		boolean flag = false;
		try {
			flag = this.uDAO.AddUser(user);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return flag;
	}

	@Override
	public User_Bean FindById(int id) throws Exception {
		User_Bean user = null;
		try {
			user = this.uDAO.FindById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return user;
	}

	@Override
	public boolean DeleteUser(int user_id) throws Exception {
		boolean flag = false;
		try {
			flag = this.uDAO.DeleteUser(user_id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return flag;
	}

	@Override
	public ArrayList<User_Bean> UserList() throws Exception {
		ArrayList<User_Bean> userList = null;
		try {
			userList = this.uDAO.UserList();
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return userList;
	}

	@Override
	public int FindId_ByUsername(String username) throws Exception {
		int id = 0;
		try {
			id = this.uDAO.FindId_ByUsername(username);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return id;
	}

	@Override
	public boolean UpdateUser(User_Bean user) throws Exception {
		boolean flag = false;
		try {
			flag = this.uDAO.UpdateUser(user);
		} catch (Exception e) {
			throw e;
		} finally {
			this.DBconnect.close();
		}
		return flag;
	}
}
