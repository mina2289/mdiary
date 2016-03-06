package mdiary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Diary {
	public ArrayList<String> strDiary = new ArrayList<String>();
	
	/*
	 * Get diary input.
	 */
	void getUserInput() {
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
	
	public ArrayList<String> getStrDiary(){
		return strDiary;
	}
}
