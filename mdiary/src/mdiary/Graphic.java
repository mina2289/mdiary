package mdiary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Graphic {
	private static final String IMG_PATH = "/Users/pengsh/Documents/Shu Ming/self/Diwali/20151110_151558.jpg";
	private JFrame mainFrame;
	private JPanel textPanel;
	private JPanel controlPanel;
	private JTextArea inputArea;
	public JTextArea outputArea;
	private JButton submitButton;
	private JButton getButton;
	private ArrayList<String> strDiary = new ArrayList<String>();
	private static final Font font = new Font("Verdana", Font.BOLD, 16);

	public Graphic() {
		prepareGUI();
	}

	private void prepareGUI() {
		// Set up mainframe
		mainFrame = new JFrame("mDiary");
		Color color = new Color(240, 124, 124, 255);
		mainFrame.getContentPane().setBackground(color);
		mainFrame.setSize(800, 800);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(new BorderLayout());// new FlowLayout()); new
												// GridLayout(3,1)
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		int scaledWidth = 100;
		int scaledHeight = 200;
		
//		try {
//			BufferedImage img = ImageIO.read(new File(IMG_PATH));
//			// creates output image
//			BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, img.getType());
//
//			// scales the input image to the output image
//			Graphics2D g2d = outputImage.createGraphics();
//			g2d.drawImage(img, 0, 0, scaledWidth, scaledHeight, null);
//			g2d.dispose();
//			ImageIcon icon = new ImageIcon(outputImage);
//			JLabel label = new JLabel(icon);
//			mainFrame.add(label, BorderLayout.LINE_START);
//			// JOptionPane.showMessageDialog(null, label);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		JLabel label = new JLabel();
		mainFrame.add(label, BorderLayout.LINE_START);
		
		textPanel = new JPanel(new GridLayout(2, 1));
		controlPanel = new JPanel();

		// InputArea
		inputArea = new JTextArea();
		color = new Color(255, 232, 232, 255);
		inputArea.setBackground(color);
		// Input Font
		inputArea.setFont(font);
		color = new Color(168, 49, 49, 255);
		inputArea.setForeground(color);
		
		// OutputArea
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		color = new Color(255, 204, 204, 255);
		outputArea.setBackground(color);
		// Output Font
		outputArea.setFont(font);
		color = new Color(100, 129, 144, 255);
		outputArea.setForeground(color);
		
		JScrollPane scrollPane = new JScrollPane(outputArea);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		// Button
		submitButton = new JButton("Submit");
		submitButton.setSize(50, 20);
		getButton = new JButton("Get");
		getButton.setSize(50, 20);

		// Panel 
		textPanel.add(inputArea);
		textPanel.add(scrollPane);
		color = new Color(255, 255, 255, 255);
		controlPanel.setBackground(color);
		controlPanel.add(submitButton);
		controlPanel.add(getButton);

		// Frame
		mainFrame.add(textPanel, BorderLayout.CENTER);
		mainFrame.add(controlPanel, BorderLayout.PAGE_END);

		// ActionListener act = new ActionListener();
		// submitButton.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// for (String line : inputArea.getText().split("\\n")) {
		// System.out.println(line);
		// strDiary.add(line); // debug!!
		// }
		// }
		// });
		// Listener for submit button
		ActionSubmitButton submitAction = new ActionSubmitButton(inputArea);
		submitButton.addActionListener(submitAction);

		ActionGetButton getAction = new ActionGetButton(outputArea);
		getButton.addActionListener(getAction);

		mainFrame.setVisible(true);
	}

	public ArrayList<String> getStrDiary() {
		return strDiary;
	}

	public JTextArea getOutputArea() {
		return outputArea;
	}
}
