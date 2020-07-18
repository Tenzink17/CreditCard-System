package project2;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	
	
	public static void main(String[] args) {
	
		SwingUtilities.invokeLater(new Runnable(){

		
			public void run() {
				MainFrame mainFrame = new MainFrame();
				mainFrame.setSize(700, 600);
				mainFrame.setLocation(600, 250);
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setLayout(null);
				mainFrame.setVisible(true);
			}
		});
	}
}
