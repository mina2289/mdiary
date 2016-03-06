package mdiary;

import java.sql.Connection;

public class Main {
	public static void main(String arg[]) {
		Graphic panel = new Graphic();
		
		//Diary diary = new Diary();
		//Database db = new Database("test", "root", "root");// dbname, user, passwd
		Database db = new Database("diary1");// table name
		Connection conn = db.setconn();
		
		// Need to initialize data after setting up db connection
		db.initDBData(); 
		//diary.getUserInput();
		
		//db.insertDiarydb(conn, db.getDBTable(), db.getRowNum(), diary.getStrDiary(), "http://testtest");
		//db.deleteDiarydb(conn, db.getDBTable());
		
		db.getDiarydb(conn, "SELECT id, date, week, txt, url FROM " + db.getDBTable()); // debug
		db.closeconn();
		
		
		}
}
