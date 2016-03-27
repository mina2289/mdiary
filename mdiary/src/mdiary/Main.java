package mdiary;

import java.sql.Connection;

public class Main {
	public static void main(String arg[]) {
		
		Controller cn = new Controller();
		cn.setupDatabase();
		cn.setupGraph();
		cn.closeDB();
		}
}
