package com.comp9321.assign2;

import java.util.ArrayList;

public interface interface_User_DAO {

	public boolean FindLogin(String username, String password) throws Exception;

	public boolean AddUser(User_Bean user) throws Exception;

	public User_Bean FindById(int id) throws Exception;

	public boolean DeleteUser(int user_id) throws Exception;

	public ArrayList<User_Bean> UserList() throws Exception;

	public int FindId_ByUsername(String username) throws Exception;

	public boolean UpdateUser(User_Bean user) throws Exception;

}
