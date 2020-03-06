package com.comp9321.assign2;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;

public class DatabaseConnection {

	private static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/COMP9321assignment2?characterEncoding=utf8&useSSL=true";
	private static final String DBUSER = "root";
	private static final String DBPASSWORD = "1234";
	private Connection connect = null;

	public DatabaseConnection() throws Exception {
		try {
			Class.forName(DBDRIVER);
			this.connect = (Connection) DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		} catch (Exception e) {
			throw e;
		}
	}

	public Connection getConnection() {
		return this.connect;
	}

	public void close() throws Exception {
		if (this.connect != null) {
			try {
				this.connect.close();
			} catch (Exception e) {
				throw e;
			}
		}
	}
}
