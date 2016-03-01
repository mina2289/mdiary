package mdiary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private static Connection conn = null; 
	private static String DBname = "test";
	private static String User = "root";
	private static String Passwd = "root";
	private String DBTable = null;
	private int rowNum = 0;
	
	public Database(String dbname, String user, String passwd) {
		setDBname(dbname); // mysql database name
		setUser(user); 
	    setPasswd(passwd); 
		
	}
	
	public Database(String dbtable) {
		setDBTable(dbtable);
	}
	
	public Connection setconn() {
		System.out.println("Set up database connection." + DBname + User + Passwd);
		
		String url = "jdbc:mysql://localhost:3306/" + DBname + "?autoReconnect=true&useSSL=false"; 
		String driver = "com.mysql.jdbc.Driver"; 
	    
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, User, Passwd);
			
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
		return conn;
	}
	
	public Connection getconn() {
		System.out.println("Database get connection");
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

	public static void setDBname(String dBname) {
		DBname = dBname;
	}

	public static void setUser(String user) {
		User = user;
	}

	public static void setPasswd(String passwd) {
		Passwd = passwd;
	}

	public void setDBTable(String dBTable) {
		DBTable = dBTable;
	}
	
	public String getDBTable() {
		return DBTable;
	}

	public int getRowNum() {
		Statement stmt = null;
		ResultSet sqlResult = null;
		int num = 0;
		try {
			// Set up query to database
	    	stmt = conn.createStatement();
		    sqlResult = stmt.executeQuery("SELECT COUNT(*) FROM testdiary");
		
		    // Extract data from the query result
		    while(sqlResult.next()){
		       // Retrieve by column name
		       num = sqlResult.getInt("COUNT(*)");
		       
		       // Display values
		       System.out.println("Number: " + num);
		    }
		    
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    try {
		    	// Clean-up environment
				sqlResult.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}
		
		return num;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
}
