package mdiary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class ActionSubmitButton implements ActionListener {
	private JTextArea InputArea;
	private ArrayList<String> StrDiary = new ArrayList<String>();
	public  ActionSubmitButton(JTextArea inputArea) {
		InputArea = inputArea;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		StrDiary = new ArrayList<String>();
		for (String line : InputArea.getText().split("\\n")) {
			System.out.println(line);
			StrDiary.add(line); // debug!!
		}
		Controller c = new Controller();
		c.setupDatabase();
		Database db = c.getDB();
		c.getDB().insertDiarydb(db.getconn(), db.getDBTable(), db.getRowNum(), StrDiary, "http://testtest");
		c.closeDB();
	}
}
