package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	private static Connection conn;
	
	public static Connection getConnection() { //getter 역할
		
		try {
			
			if(conn == null || conn.isClosed()) {
				
				Properties prop = new Properties();
				
				prop.loadFromXML(new FileInputStream("driver.xml"));
				
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String password = prop.getProperty("password");
				
				Class.forName(driver);
				
				conn = DriverManager.getConnection(url,user,password);
				
				conn.setAutoCommit(false);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	
	public static void close(Connection conn) {
		
		try {
			
			if(conn != null && !conn.isClosed()) {
				// conn.isClosed() : 닫혀있으면 true
				conn.close();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void close(Statement stmt) {
		
		try {
			
			if(stmt != null && !stmt.isClosed()) {
				// conn.isClosed() : 닫혀있으면 true
				stmt.close();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void close(ResultSet rs) {
		
		try {
			
			if(rs != null && !rs.isClosed()) {
				// conn.isClosed() : 닫혀있으면 true
				rs.close();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void commit(Connection conn) {
		
		try {
			
			if(conn != null && !conn.isClosed()) {
				// conn.isClosed() : 닫혀있으면 true
				conn.commit();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void rollback(Connection conn) {
		
		try {
			
			if(conn != null && !conn.isClosed()) {
				// conn.isClosed() : 닫혀있으면 true
				conn.rollback();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	

}
