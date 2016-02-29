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
	public Diary() {
		Database db = new Database();
		getDiarydb(db.getconn()); //debug
		db.closeconn();
	}
	
	/*
	 * Get diary input.
	 */
	void getInput() {
		ArrayList<String> strDiary = new ArrayList<String>();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd E 'at' hh:mm:ss a zzz");
		
		System.out.println("Mina's Diary " + "\n" + formatDate.format(new Date()));
		System.out.println("Enter \"end\" when finish editing." + "\n" + "Start writing:");
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
			String input;
			
			while((input = br.readLine()) != null){
				//System.out.println(input);
				strDiary.add(input);
				if (input.contentEquals("end")) break;
			}
			for (String str: strDiary){
				System.out.println(str);
			}
			
		}catch(IOException io){
			io.printStackTrace();
		}	
	}
	public void saveDiarydb(Connection conndb, ArrayList<String> strDiary) {
		
		
	}
	
	
	/*
	 * Retrieve data from database
	 */
	public void getDiarydb(Connection conndb) {
		Statement stmt = null; 
	    try {
			stmt = conndb.createStatement();
		
			// Set up query to database
		    String query;
		    query = "SELECT id, txt, date FROM testtable";
		    ResultSet sqlResult = stmt.executeQuery(query);
		
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
		    // Clean-up environment
		    sqlResult.close();
		    stmt.close();
		    conndb.close();
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
}
