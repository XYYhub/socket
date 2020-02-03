package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("加载驱动失败");
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/server?serverTimezone=UTC";
		String username = "root";
		String password = "oipopo09";
		
		
		try {
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			System.out.println("连接失败");
			e.printStackTrace();
		}
		
		return conn;
		
	}
}
