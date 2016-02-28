package mdiary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Diary {
	public ArrayList<String> strDiary = new ArrayList<String>();
	public Diary(){
		
	}
	
	void getInput(){
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
}
