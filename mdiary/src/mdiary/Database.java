package mdiary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static Connection conn = null; 
	
	
	public Database() {
		String dbname = "test"; // mysql database name
		String user = "root"; 
	    String passwd = "root"; 
		String url = "jdbc:mysql://localhost:3306/" + dbname + "?autoReconnect=true&useSSL=false"; 
		String driver = "com.mysql.jdbc.Driver"; 
	    
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, passwd);
			
			if (conn != null && !conn.isClosed()) {
				System.out.println("Connection set up successfully");
			}
		}
	     
	    catch(ClassNotFoundException e) { 
	        System.out.println("Cannot find driver."); 
	        e.printStackTrace(); 
	    } 
	    catch(SQLException e) { 
	        e.printStackTrace(); 
	    } 
	}
	
	public Connection getconn() {
		System.out.println("Database connection closed/");
		return conn;
	}
	
	public void closeconn() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
