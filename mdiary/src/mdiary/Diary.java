package mdiary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Diary {
	public ArrayList<String> strDiary = new ArrayList<String>();
	public Diary() {
		
	}
	
	/*
	 * Get diary input.
	 */
	void getInput() {
		strDiary = new ArrayList<String>();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd E 'at' hh:mm:ss a zzz");
		
		System.out.println("Mina's Diary " + "\n" + formatDate.format(new Date()));
		System.out.println("Enter \"end\" when finish editing." + "\n" + "Start writing:");
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
			String input;
			
			while((input = br.readLine()) != null){
				if (input.contentEquals("end")) break;
				strDiary.add(input);
			}
			for (String str: strDiary){
				System.out.println(str);
			}
			
		}catch(IOException io){
			io.printStackTrace();
		}	
	}
	
	/*
	 * Retrieve data from database
	 */
	public void getDiarydb(Connection conndb, String queryStr) {
		Statement stmt = null;
		ResultSet sqlResult = null;
		try {
			// Set up query to database
	    	stmt = conndb.createStatement();
		    sqlResult = stmt.executeQuery(queryStr);
		
		    // Extract data from the query result
		    while(sqlResult.next()){
		       // Retrieve by column name
		       int id  = sqlResult.getInt("id");
		       String txt = sqlResult.getString("txt");
		       String date = sqlResult.getString("date");
		
		       // Display values
		       System.out.println("ID: " + id);
		       System.out.println("txt: " + txt);
		       System.out.println("date: " + date + "\n");
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
	    
	}
	
	/*
	 * Insert data into database
	 */
	public void insertDiarydb(Connection conndb, String dbtable, int tableindex) {//, ArrayList<String> strDiary) {
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
			String insertStr = "INSERT INTO " +  dbtable + " VALUES ('" + ++tableindex + "', '" + txt.toString() 
				+ "', '" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) 
					+ "')";
		    
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
}
