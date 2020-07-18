package project2;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel  optionPanel;
	private JButton loginButton, shopButton;
	

	private Font messageFont = new Font(null, Font.PLAIN, 20);
	actionHandler handler    = new actionHandler();
	
	private CreditCardCompany visa = new CreditCardCompany();
	public MainFrame() {

		super("MainFrame");
		mainFrame();
	}
	
	public void mainFrame() {
		
		optionPanel = new JPanel();
		optionPanel.setBounds(180, 100, 300, 300);
		optionPanel.setLayout(new GridLayout(2,1));
		add(optionPanel);
		
		loginButton = new JButton("Login");
		loginButton.setFont(messageFont);
		loginButton.addActionListener(handler);
		optionPanel.add(loginButton);
		
		shopButton = new JButton("View Shop");
		shopButton.setFont(messageFont);
		shopButton.addActionListener(handler);
		optionPanel.add(shopButton);
	}
	
	private class actionHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == loginButton) {
				setVisible(false);
				LoginScreen loginScreen = new LoginScreen(visa);
				loginScreen.setSize(700, 600);
				loginScreen.setLocation(600, 250);
				loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				loginScreen.setLayout(null);
				loginScreen.setVisible(true);
			}else if(event.getSource() == shopButton) {
				setVisible(false);
				VendorScreen vendorScreen = new VendorScreen(visa);
				vendorScreen.setSize(700, 600);
				vendorScreen.setLocation(600, 250);
				vendorScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				vendorScreen.setLayout(null);
				vendorScreen.setVisible(true);
			}
		}
	}
}
