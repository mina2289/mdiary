package mdiary;

import java.sql.Connection;

public class Main {
	public static void main(String arg[]) {
		Diary diary = new Diary();
		//Database db = new Database("test", "root", "root");// dbname, user, passwd
		Database db = new Database("testdiary");// table name
		Connection conn = db.setconn();
		db.getRowNum();
		diary.getInput();
		diary.insertDiarydb(conn, db.getDBTable(), db.getRowNum());
		diary.getDiarydb(conn, "SELECT id, txt, date FROM " + db.getDBTable()); // debug
		db.closeconn();
	}
}
