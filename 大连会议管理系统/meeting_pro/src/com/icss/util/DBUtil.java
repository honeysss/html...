package com.icss.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String name = "scott";
	private static String psw = "tiger";
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, name, psw);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
