package mdiary;

import java.sql.Connection;
import java.util.List;

public class Controller {
	public Graphic panel;
	public Database db;

	public Controller() {

	}

	public void setupGraph() {
		panel = new Graphic();
	}

	public void setupDatabase() {
		// Diary diary = new Diary();
		db = new Database("test", "root", "root");// dbname, user,
													// passwd
		db.initDBTable("diary1");
		Connection conn = db.setconn();
		// Need to initialize data after setting up db connection
		db.updateTableData();
		// diary.getUserInput();

		// db.insertDiarydb(conn, db.getDBTable(), db.getRowNum(),
		// diary.getStrDiary(), "http://testtest");
		// db.deleteDiarydb(conn, db.getDBTable());

		
	}

	public List<List<String>> getHistory() {
		List<List<String>> history = db.getDiarydb(db.getconn(),
				"SELECT id, date, week, txt, url FROM " + db.getDBTable()); // debug
		return history;
	}

	public Database getDB() {
		return db;
	}
	
	public void closeDB() {
		db.closeconn();
	}
}
