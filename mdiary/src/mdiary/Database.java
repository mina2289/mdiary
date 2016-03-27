package mdiary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database {
	private static Connection conn = null; 
	private static String DBname = "test";
	private static String User = "root";
	private static String Passwd = "root";
	private String DBTable = null;
	private int rowNum = 0;
	
	/*
	 * Init database.
	 */
	public Database(String dbname, String user, String passwd) {
		setDBname(dbname); // mysql database name
		setUser(user); 
	    setPasswd(passwd); 
	}
	
	/*
	 * Set table.
	 */
	public void initDBTable(String dBTable) {
		DBTable = dBTable;
	}
	
	/*
	 * Update Database Class Data:rowNum whenever modify the db table
	 */
	public void updateTableData() {
		setRowNum(updateRowNum());
	}
	
	/*
	 * Get row number in the db table. 
	 */
	public int updateRowNum() {
		Statement stmt = null;
		ResultSet sqlResult = null;
		int num = 0;
		try {
			// Set up query to database
	    	stmt = conn.createStatement();
		    sqlResult = stmt.executeQuery("SELECT COUNT(*) FROM " + this.getDBTable());
		
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

	
	
	/*
	 * DEBUG: Retrieve data from database
	 */
	public List<List<String>> getDiarydb(Connection conndb, String queryStr) {
		Statement stmt = null;
		ResultSet sqlResult = null;
		List<List<String>> history = new ArrayList<List<String>>();
		
		try {
			// Set up query to database
	    	stmt = conndb.createStatement();
		    sqlResult = stmt.executeQuery(queryStr);
		
		    // Extract data from the query result
		    while(sqlResult.next()){
		    	List<String> historyDay = new ArrayList<String>(); 
		       // Retrieve by column name
		       int id  = sqlResult.getInt("id");
		       String date = sqlResult.getString("date");
		       String week = sqlResult.getString("week");
		       String txt = sqlResult.getString("txt");
		       String url = sqlResult.getString("url");
		       historyDay.add(String.valueOf(id));
		       historyDay.add(date);
		       historyDay.add(week);
		       historyDay.add(txt);
		       historyDay.add(url);
		       
		       history.add(historyDay);
		       // Display values
		       System.out.println("ID: " + id);
		       System.out.println("date: " + date);
		       System.out.println("week: " + week);
		       System.out.println("txt: " + txt);
		       System.out.println("url: " + url + "\n");
		       
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
		return history;
	}
	
	/*
	 * Insert data into database
	 */
	public void insertDiarydb(Connection conndb, String dbtable, int tableindex
			, ArrayList<String> strDiary, String url) {
		Statement stmt = null;
		
		try {
			// Insert data into database
			stmt = conndb.createStatement();
			StringBuilder txt = new StringBuilder();
			for (String str: strDiary){
				txt.append(str);
			}
			System.out.println(txt);
			
			//"yyyy-MM-dd E 'at' hh:mm:ss a zzz"
			String insertStr = "INSERT INTO " +  dbtable + " VALUES ('" + ++tableindex + "', '" + 
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', '" + 
					new SimpleDateFormat("E").format(new Date()) + "', '" + txt.toString() + "', '" +
					url + "')";
			
			System.out.println(insertStr);
		    stmt.executeUpdate(insertStr);
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
		    	// Clean-up environment
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/*
	 * Delete last entry in database
	 */
	public void deleteDiarydb(Connection conndb, String dbtable) {
		//DELETE FROM LoginTime WHERE user_id=1 ORDER BY datetime DESC LIMIT 1
		Statement stmt = null;
		
		try {
			// Insert data into database
			stmt = conndb.createStatement();
			String insertStr = "DELETE FROM " + dbtable + " ORDER BY date DESC LIMIT 1";
			System.out.println(insertStr);
		    stmt.executeUpdate(insertStr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Update row number in db
			setRowNum(getRowNum() - 1);
			try {
		    	// Clean-up environment
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	public String getDBTable() {
		return DBTable;
	}

	public int getRowNum() {
		return rowNum;
	}
	
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
}
