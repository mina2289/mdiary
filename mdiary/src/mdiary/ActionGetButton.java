package mdiary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.text.Caret;

public class ActionGetButton implements ActionListener {
	private JTextArea OutputArea;
	private ArrayList<String> StrDiary = new ArrayList<String>();
	public  ActionGetButton(JTextArea outputArea) {
		OutputArea = outputArea;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		OutputArea.setText("mDiary\n\n");
	    //textField.selectAll();
		Controller c = new Controller();
		c.setupDatabase();
		List<List<String>> historyDiary = c.getHistory();
		for (List<String> dayDiary: historyDiary) {
			for (String itemDiary: dayDiary) {
				OutputArea.append(itemDiary + "\n");
			}
			OutputArea.append("\n");
		}
		
	    //Make sure the new text is visible, even if there
	    //was a selection in the text area.
	    OutputArea.setCaretPosition(OutputArea.getDocument().getLength());
	}
	
	
	
}
