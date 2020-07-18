
package project2;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel     usernameLabel, passwordLabel,
					   setFullNameLabel, setUsernameLabel, setPasswordLabel,
					   toUsernameLabel, howMuchLabel;
	public JTextField usernameField,
					   setFullNameField, setUsernameField, setPasswordField,
					   textField, text2Field, text4Field, text5Field, text6Field;
	private JButton    registerButton, loginButton, loginForAdminButton, loginAsAdminButton,
					   finishButton, backButton, backToMainFrameButton, loginAsAdminBackButton,
					   cardHolderScreenButton, shopButton, sendCheckButton, logoutButton,
					   cardInfoCheckFinishButton, cardInfoCheckBackButton,
					   adminAddAccountButton, adminViewAccountButton, adminDeleteAccountButton, adminViewVendorButton,
					   adminViewAccount1Button,adminViewAccount2Button,adminViewAccount3Button,adminViewAccount4Button,adminViewAccount5Button,
					   adminDeleteAccount1Button, adminDeleteAccount2Button, adminDeleteAccount3Button, adminDeleteAccount4Button, adminDeleteAccount5Button,
					   adminLogoutButton, adminFinishButton, adminViewAccountBackButton, adminDeleteAccountBackButton,
					   shop1Button, shop2Button, shop3Button, viewVendorBackButton,
					   sendCheckBackButton, sendCheckFinishButton;
	private JPanel 	   loginPanel, loginButtonPanel,
					   registerPanel, finishBackButtonPanel,
					   choicePanel,
					   sendCheckPanel,
					   textPanel, text2Panel, text3Panel, adminViewAccountPanel, adminDeleteAccountPanel,
					   shopsPanel, viewVendorBackPanel;
	private JPasswordField passwordField;

	private Font  messageFont = new Font(null, Font.PLAIN, 20);
	actionHandler handler     = new actionHandler();

	private String username, password, accountHolderName;
	private int accountNumber;
	private double creditLimit, amountDue;

	private CreditCardCompany visa;

	private DecimalFormat df= new DecimalFormat("#.00");

	private JLabel fromUsernameLabel;

	private JTextField text3Field;

	private JTextField cardNumberField;

	public LoginScreen(CreditCardCompany visa) {

		super("Credit Card System");
		this.visa = visa;
		this.username = "";
		this.password = "";
		this.accountNumber = 0;
		loginScreen();
	}

	public void loginScreen() {
		DataBaseSetUp.listOfAccountNumbers.clear();
		text3Panel = new JPanel();
		text3Panel.setBounds(180, 40, 300, 50);
		text3Panel.setLayout(new GridLayout(1,1));
		add(text3Panel);
		
		text4Field = new JTextField("Login");
		text4Field.setFont(messageFont);
		text4Field.setEditable(false);
		text3Panel.add(text4Field);
		
		loginPanel = new JPanel();
		loginPanel.setBounds(180, 100, 300, 50);
		loginPanel.setLayout(new GridLayout(2,2));
		add(loginPanel);

		usernameLabel = new JLabel("Username: ");
		usernameLabel.setFont(messageFont);
		loginPanel.add(usernameLabel);

		usernameField = new JTextField();
		loginPanel.add(usernameField);

		passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(messageFont);
		loginPanel.add(passwordLabel);

		passwordField = new JPasswordField();
		loginPanel.add(passwordField);

		loginButtonPanel = new JPanel();
		loginButtonPanel.setBounds(180, 160, 300, 250);
		loginButtonPanel.setLayout(new GridLayout(4,1));
		add(loginButtonPanel);

		loginButton = new JButton("Login");
		loginButton.setFont(messageFont);
		loginButton.addActionListener(handler);
		loginButtonPanel.add(loginButton);

		loginAsAdminButton = new JButton("Login as administrator");
		loginAsAdminButton.setFont(messageFont);
		loginAsAdminButton.addActionListener(handler);
		loginButtonPanel.add(loginAsAdminButton);

		registerButton = new JButton("Register");
		registerButton.setFont(messageFont);
		registerButton.addActionListener(handler);
		loginButtonPanel.add(registerButton);

		backToMainFrameButton = new JButton("Back");
		backToMainFrameButton.setFont(messageFont);
		backToMainFrameButton.addActionListener(handler);
		loginButtonPanel.add(backToMainFrameButton);
	}

	private class actionHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == registerButton) {
				// Registering a new account
				register();
			}else if(event.getSource() == loginButton) {
				// Check login info, once accepted login
				username = usernameField.getText();
				DataBaseSetUp.username = usernameField.getText();
				password = passwordField.getText();
				boolean check = false;
				if(username.equals("")|| password.equals("") || password.length()<6)
				{
					text3Panel.setVisible(false);
					loginPanel.setVisible(false);
					loginButtonPanel.setVisible(false);
					
					text3Panel = new JPanel();
					text3Panel.setBounds(180, 10, 300, 50);
					text3Panel.setLayout(new GridLayout(1,1));
					add(text3Panel);
					
					text4Field = new JTextField("Incorrect username or password");
					text4Field.setFont(messageFont);
					text4Field.setEditable(false);
					text3Panel.add(text4Field);
					
					loginPanel = new JPanel();
					loginPanel.setBounds(180, 100, 300, 50);
					loginPanel.setLayout(new GridLayout(2,2));
					add(loginPanel);

					usernameLabel = new JLabel("Username: ");
					usernameLabel.setFont(messageFont);
					loginPanel.add(usernameLabel);

					usernameField = new JTextField();
					loginPanel.add(usernameField);

					passwordLabel = new JLabel("Password: ");
					passwordLabel.setFont(messageFont);
					loginPanel.add(passwordLabel);

					passwordField = new JPasswordField();
					loginPanel.add(passwordField);

					loginButtonPanel = new JPanel();
					loginButtonPanel.setBounds(180, 160, 300, 250);
					loginButtonPanel.setLayout(new GridLayout(4,1));
					add(loginButtonPanel);

					loginButton = new JButton("Login");
					loginButton.setFont(messageFont);
					loginButton.addActionListener(handler);
					loginButtonPanel.add(loginButton);

					loginAsAdminButton = new JButton("Login as administrator");
					loginAsAdminButton.setFont(messageFont);
					loginAsAdminButton.addActionListener(handler);
					loginButtonPanel.add(loginAsAdminButton);

					registerButton = new JButton("Register");
					registerButton.setFont(messageFont);
					registerButton.addActionListener(handler);
					loginButtonPanel.add(registerButton);

					backToMainFrameButton = new JButton("Back");
					backToMainFrameButton.setFont(messageFont);
					backToMainFrameButton.addActionListener(handler);
					loginButtonPanel.add(backToMainFrameButton);
				}
				else
				{
					text3Panel.setVisible(false);
					List<CreditCard> cards= visa.getUserCreditCards();
					for(int i = 0; i<cards.size(); i++)
					{
						if(cards.get(i).getUsername().equals(username) && cards.get(i).getPassword().equals(password))
						{
							System.out.println("Welcome "+DataBaseSetUp.username +" your Card#: " +cards.get(i).getAccountNumber());
							check = true;
							
						}
					}
					if(check)
					{
						afterLogin();
					}
				}
				if(!check)
				{
					System.out.println("Your password or username is wrong");
				}

			}else if(event.getSource() == logoutButton) {
				// User logout and go back to login screen
				choicePanel.setVisible(false);
				System.out.println("logged out by "+DataBaseSetUp.username);
				DataBaseSetUp.username = "";
				DataBaseSetUp.listOfAccountNumbers.clear();
				loginScreen();
			}else if(event.getSource() == loginAsAdminButton) {
				// Login as admin
				loginAsAdmin();
			}else if(event.getSource() == loginForAdminButton) {
				// Checks admin login info, once accepted login as admin
				String password = passwordField.getText();
				if(password.equals(visa.getAdminPassword()))
				{
					text6Field.setVisible(false);
					afterLoginAsAdmin();
				}
			}else if(event.getSource() == loginAsAdminBackButton) {
				// Back from the login as admin screen to the login screen
				text6Field.setVisible(false);
				loginPanel.setVisible(false);
				loginButtonPanel.setVisible(false);
				loginScreen();
			}else if(event.getSource() == finishButton) {
				// Finish registering
				String username = setUsernameField.getText().toLowerCase();
				String password = setPasswordField.getText();
				String name  = setFullNameField.getText().toLowerCase();
				if(username.equals("")||password.equals("")|| name.equals("")|| password.length()<6){
					// its empty ////
					System.out.println("its empty or password not long enough");
				}
				else {
					visa.createAccount(username,password,name);
					text5Field.setVisible(false);
					registerPanel.setVisible(false);
					finishBackButtonPanel.setVisible(false);
					loginScreen();
				}
			}else if(event.getSource() == backButton) {
				// Back to the login screen from the registerPanel 
				text5Field.setVisible(false);
				registerPanel.setVisible(false);
				finishBackButtonPanel.setVisible(false);
				loginScreen();
			}else if(event.getSource() == adminAddAccountButton) {
				// Admin adds an account
				adminAddAccount();
			}else if(event.getSource() == adminFinishButton) {
				// Finish adding account as admin
//				String username = setUsernameField.getText();
//
//				for(int i = 0; i<visa.getUserCreditCards().size(); i++)
//				{
//					if(visa.getUserCreditCards().get(i).getUsername().equals(username))
//					{
//						System.out.println("username already exists");
//					}
//					else
//					{
//						String fullname = setFullNameField.getText();
//						String password = setPasswordField.getText();
//						visa.createAccount(username, password, fullname);
//					}
//				}

			}else if(event.getSource() == adminViewAccountButton) {
				// Admin views all accounts
				adminViewAccount();
				if(adminViewAccount1Button.getActionCommand().equals(""))
				{
					adminViewAccount1Button.setVisible(false);
				}
				if(adminViewAccount2Button.getActionCommand().equals(""))
				{
					adminViewAccount2Button.setVisible(false);
				}
				if(adminViewAccount3Button.getActionCommand().equals(""))
				{
					adminViewAccount3Button.setVisible(false);
				}
				if(adminViewAccount4Button.getActionCommand().equals(""))
				{
					adminViewAccount4Button.setVisible(false);
				}
				if(adminViewAccount5Button.getActionCommand().equals(""))
				{
					adminViewAccount5Button.setVisible(false);
				}
			}else if(event.getSource() == adminViewAccount1Button) {
				// Admin views account 1
				setVisible(false);

				accountNumber = visa.getUserCreditCards().get(0).getAccountNumber();
				username = visa.getUserCreditCards().get(0).getUsername();
				password = visa.getUserCreditCards().get(0).getPassword();
				accountHolderName = visa.getUserCreditCards().get(0).getAccountHolderName();
				creditLimit = visa.getUserCreditCards().get(0).getCreditLimit();
				amountDue = visa.getUserCreditCards().get(0).getAmountDue();
				CardHolderScreenAsAdmin cardHolderScreen = new CardHolderScreenAsAdmin(accountNumber, username, password, accountHolderName, creditLimit, amountDue, visa);

//				cardHolderScreen.accountNameField.setText("Account Name: "+visa.getUserCreditCards().get(0).getAccountHolderName());
//				cardHolderScreen.cardNumberField.setText("Card Number: "+visa.getUserCreditCards().get(0).getAccountNumber());
//				cardHolderScreen.creditLimitField.setText("Credit Limit: $"+df.format(visa.getUserCreditCards().get(0).getCreditLimit()));
//				cardHolderScreen.amountDueField.setText("Amount Due : $"+df.format(visa.getUserCreditCards().get(0).getAmountDue()));

				cardHolderScreen.setSize(700, 600);
				cardHolderScreen.setLocation(600, 250);
				cardHolderScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				cardHolderScreen.setLayout(null);
				cardHolderScreen.setVisible(true);
			}else if(event.getSource() == adminViewAccount2Button) {
				// Admin views account 2
				setVisible(false);
				//String username = adminViewAccount2Button.getActionCommand();
				accountNumber = visa.getUserCreditCards().get(1).getAccountNumber();
				username = visa.getUserCreditCards().get(1).getUsername();
				password = visa.getUserCreditCards().get(1).getPassword();
				accountHolderName = visa.getUserCreditCards().get(1).getAccountHolderName();
				creditLimit = visa.getUserCreditCards().get(1).getCreditLimit();
				amountDue = visa.getUserCreditCards().get(1).getAmountDue();
				CardHolderScreenAsAdmin cardHolderScreen = new CardHolderScreenAsAdmin(accountNumber, username, password, accountHolderName, creditLimit, amountDue, visa);

//				cardHolderScreen.accountNameField.setText("Account Name: "+visa.getUserCreditCards().get(1).getAccountHolderName());
//				cardHolderScreen.cardNumberField.setText("Card Number: "+visa.getUserCreditCards().get(1).getAccountNumber());
//				cardHolderScreen.creditLimitField.setText("Credit Limit: $"+df.format(visa.getUserCreditCards().get(1).getCreditLimit()));
//				cardHolderScreen.amountDueField.setText("Amount Due : $"+df.format(visa.getUserCreditCards().get(1).getAmountDue()));

				cardHolderScreen.setSize(700, 600);
				cardHolderScreen.setLocation(600, 250);
				cardHolderScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				cardHolderScreen.setLayout(null);
				cardHolderScreen.setVisible(true);

			}else if(event.getSource() == adminViewAccount3Button) {
				// Admin views account 3
				setVisible(false);

				accountNumber = visa.getUserCreditCards().get(2).getAccountNumber();
				username = visa.getUserCreditCards().get(2).getUsername();
				password = visa.getUserCreditCards().get(2).getPassword();
				accountHolderName = visa.getUserCreditCards().get(2).getAccountHolderName();
				creditLimit = visa.getUserCreditCards().get(2).getCreditLimit();
				amountDue = visa.getUserCreditCards().get(2).getAmountDue();
				CardHolderScreenAsAdmin cardHolderScreen = new CardHolderScreenAsAdmin(accountNumber, username, password, accountHolderName, creditLimit, amountDue, visa);

//				cardHolderScreen.accountNameField.setText("Account Name: "+visa.getUserCreditCards().get(2).getAccountHolderName());
//				cardHolderScreen.cardNumberField.setText("Card Number: "+visa.getUserCreditCards().get(2).getAccountNumber());
//				cardHolderScreen.creditLimitField.setText("Credit Limit: $"+df.format(visa.getUserCreditCards().get(2).getCreditLimit()));
//				cardHolderScreen.amountDueField.setText("Amount Due : $"+df.format(visa.getUserCreditCards().get(2).getAmountDue()));

				cardHolderScreen.setSize(700, 600);
				cardHolderScreen.setLocation(600, 250);
				cardHolderScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				cardHolderScreen.setLayout(null);
				cardHolderScreen.setVisible(true);
			}else if(event.getSource() == adminViewAccount4Button) {
				// Admin views account 4
				setVisible(false);

				accountNumber = visa.getUserCreditCards().get(3).getAccountNumber();
				username = visa.getUserCreditCards().get(3).getUsername();
				password = visa.getUserCreditCards().get(3).getPassword();
				accountHolderName = visa.getUserCreditCards().get(3).getAccountHolderName();
				creditLimit = visa.getUserCreditCards().get(3).getCreditLimit();
				amountDue = visa.getUserCreditCards().get(3).getAmountDue();
				CardHolderScreenAsAdmin cardHolderScreen = new CardHolderScreenAsAdmin(accountNumber, username, password, accountHolderName, creditLimit, amountDue, visa);

//				cardHolderScreen.accountNameField.setText("Account Name: "+visa.getUserCreditCards().get(3).getAccountHolderName());
//				cardHolderScreen.cardNumberField.setText("Card Number: "+visa.getUserCreditCards().get(3).getAccountNumber());
//				cardHolderScreen.creditLimitField.setText("Credit Limit: $"+df.format(visa.getUserCreditCards().get(3).getCreditLimit()));
//				cardHolderScreen.amountDueField.setText("Amount Due : $"+df.format(visa.getUserCreditCards().get(3).getAmountDue()));

				cardHolderScreen.setSize(700, 600);
				cardHolderScreen.setLocation(600, 250);
				cardHolderScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				cardHolderScreen.setLayout(null);
				cardHolderScreen.setVisible(true);

			}else if(event.getSource() == adminViewAccount5Button) {
				// Admin views account 5
				setVisible(false);

				accountNumber = visa.getUserCreditCards().get(4).getAccountNumber();
				username = visa.getUserCreditCards().get(4).getUsername();
				password = visa.getUserCreditCards().get(4).getPassword();
				accountHolderName = visa.getUserCreditCards().get(4).getAccountHolderName();
				creditLimit = visa.getUserCreditCards().get(4).getCreditLimit();
				amountDue = visa.getUserCreditCards().get(4).getAmountDue();
				CardHolderScreenAsAdmin cardHolderScreen = new CardHolderScreenAsAdmin(accountNumber, username, password, accountHolderName, creditLimit, amountDue, visa);

//				cardHolderScreen.accountNameField.setText("Account Name: "+visa.getUserCreditCards().get(4).getAccountHolderName());
//				cardHolderScreen.cardNumberField.setText("Card Number: "+visa.getUserCreditCards().get(4).getAccountNumber());
//				cardHolderScreen.creditLimitField.setText("Credit Limit: $"+df.format(visa.getUserCreditCards().get(4).getCreditLimit()));
//				cardHolderScreen.amountDueField.setText("Amount Due: $"+df.format(visa.getUserCreditCards().get(4).getAmountDue()));

				cardHolderScreen.setSize(700, 600);
				cardHolderScreen.setLocation(600, 250);
				cardHolderScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				cardHolderScreen.setLayout(null);
				cardHolderScreen.setVisible(true);
			}else if(event.getSource() == adminViewAccountBackButton) {
				// Back in the view account screen and go back to afterLoginAsAdmin screen
				adminViewAccountPanel.setVisible(false);
				textPanel.setVisible(false);
				text2Panel.setVisible(false);
				afterLoginAsAdmin();
			}else if(event.getSource() == adminDeleteAccountButton){
				// Go to admin delete accounts screen
				adminDeleteAccount();
				if(adminDeleteAccount1Button.getActionCommand().equals("")|| adminDeleteAccount1Button.getText().equals(""))
				{
					adminDeleteAccount1Button.setVisible(false);
				}
				if(adminDeleteAccount2Button.getActionCommand().equals("")|| adminDeleteAccount2Button.getText().equals(""))
				{
					adminDeleteAccount2Button.setVisible(false);
				}
				if(adminDeleteAccount3Button.getActionCommand().equals("")|| adminDeleteAccount3Button.getText().equals(""))
				{
					adminDeleteAccount3Button.setVisible(false);
				}
				if(adminDeleteAccount4Button.getActionCommand().equals("")|| adminDeleteAccount4Button.getText().equals(""))
				{
					adminDeleteAccount4Button.setVisible(false);
				}
				if(adminDeleteAccount5Button.getActionCommand().equals("")|| adminDeleteAccount5Button.getText().equals(""))
				{
					adminDeleteAccount5Button.setVisible(false);
				}

			}else if(event.getSource() == adminDeleteAccount1Button) {
				// Admins deletes account 1
				if(adminDeleteAccount1Button.getActionCommand().equals(""))
				{
					// do nothing
				}
				else{
					System.out.println("deleted "+adminDeleteAccount1Button.getActionCommand());
					int accountNumber = Integer.parseInt(adminDeleteAccount1Button.getName());
					String username = adminDeleteAccount1Button.getActionCommand();
					visa.deleteAccount(accountNumber,username);
					
					setVisible(false);
					LoginScreen loginScreen = new LoginScreen(visa);
					loginScreen.setSize(700, 600);
					loginScreen.setLocation(600, 250);
					loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					loginScreen.setLayout(null);
					loginScreen.setVisible(true);
					loginScreen.text3Panel.setVisible(false);
					loginScreen.afterLoginAsAdmin();
				}

			}else if(event.getSource() == adminDeleteAccount2Button) {
				// Admins deletes account 2
				if(adminDeleteAccount2Button.getActionCommand().equals(""))
				{
					// do nothing
				}
				else{
					System.out.println("deleted "+adminDeleteAccount2Button.getActionCommand());
					int accountNumber = Integer.parseInt(adminDeleteAccount2Button.getName());
					String username = adminDeleteAccount2Button.getActionCommand();
					visa.deleteAccount(accountNumber,username);
					
					setVisible(false);
					LoginScreen loginScreen = new LoginScreen(visa);
					loginScreen.setSize(700, 600);
					loginScreen.setLocation(600, 250);
					loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					loginScreen.setLayout(null);
					loginScreen.setVisible(true);
					loginScreen.text3Panel.setVisible(false);
					loginScreen.afterLoginAsAdmin();
				}
			}else if(event.getSource() == adminDeleteAccount3Button) {
				// Admins deletes account 3
				if(adminDeleteAccount3Button.getActionCommand().equals(""))
				{
					// do nothing
				}
				else{
					System.out.println("deleted "+adminDeleteAccount3Button.getActionCommand());
					int accountNumber = Integer.parseInt(adminDeleteAccount3Button.getName());
					String username = adminDeleteAccount3Button.getActionCommand();
					visa.deleteAccount(accountNumber,username);
					
					setVisible(false);
					LoginScreen loginScreen = new LoginScreen(visa);
					loginScreen.setSize(700, 600);
					loginScreen.setLocation(600, 250);
					loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					loginScreen.setLayout(null);
					loginScreen.setVisible(true);
					loginScreen.text3Panel.setVisible(false);
					loginScreen.afterLoginAsAdmin();
				}
			}else if(event.getSource() == adminDeleteAccount4Button) {
				// Admins deletes account 4
				if(adminDeleteAccount4Button.getActionCommand().equals(""))
				{
					// do nothing
				}
				else{
					System.out.println("deleted "+adminDeleteAccount4Button.getActionCommand());
					int accountNumber = Integer.parseInt(adminDeleteAccount4Button.getName());
					String username = adminDeleteAccount4Button.getActionCommand();
					visa.deleteAccount(accountNumber,username);
					
					setVisible(false);
					LoginScreen loginScreen = new LoginScreen(visa);
					loginScreen.setSize(700, 600);
					loginScreen.setLocation(600, 250);
					loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					loginScreen.setLayout(null);
					loginScreen.setVisible(true);
					loginScreen.text3Panel.setVisible(false);
					loginScreen.afterLoginAsAdmin();
				}
			}else if(event.getSource() == adminDeleteAccount5Button) {
				// Admins deletes account 5
				if(adminDeleteAccount5Button.getActionCommand().equals(""))
				{
					// do nothing
				}
				else{
					System.out.println("deleted "+adminDeleteAccount5Button.getActionCommand());
					int accountNumber = Integer.parseInt(adminDeleteAccount5Button.getName());
					String username = adminDeleteAccount5Button.getActionCommand();
					visa.deleteAccount(accountNumber,username);
					
					setVisible(false);
					LoginScreen loginScreen = new LoginScreen(visa);
					loginScreen.setSize(700, 600);
					loginScreen.setLocation(600, 250);
					loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					loginScreen.setLayout(null);
					loginScreen.setVisible(true);
					loginScreen.text3Panel.setVisible(false);
					loginScreen.afterLoginAsAdmin();
				}
			}else if(event.getSource() == adminDeleteAccountBackButton){
				// Back button for the admin delete account screen
				adminDeleteAccountPanel.setVisible(false);
				textPanel.setVisible(false);
				afterLoginAsAdmin();
			}else if(event.getSource() == adminLogoutButton) {
				// Admin logout and go back to login screen
				choicePanel.setVisible(false);
				loginPanel.setVisible(true);
				loginButtonPanel.setVisible(true);
			}else if(event.getSource() == adminViewVendorButton) {
				// Admin view the vendor screen
				adminViewVendor();
			}else if(event.getSource() == shop1Button) {
				// Admin chose a shop in adminViewVendor
				adminViewShopInfo(event);
			}else if(event.getSource() == shop2Button) {
				// Admin chose a shop in adminViewVendor
				adminViewShopInfo(event);
			}else if(event.getSource() == shop3Button) {
				// Admin chose a shop in adminViewVendor
				adminViewShopInfo(event);
			}else if(event.getSource() == viewVendorBackButton) {
				// Back button for admin viewVendor screen
				viewVendorBackPanel.setVisible(false);
				shopsPanel.setVisible(false);
				afterLoginAsAdmin();
			}else if(event.getSource() == cardHolderScreenButton) {
				// The user view card info button
				System.out.println("Card into been clicked for "+DataBaseSetUp.username);
				choicePanel.setVisible(false);
				cardInfoCheck();
			}else if(event.getSource() == cardInfoCheckFinishButton) {
				// The user finish entering username in cardInfoCheck to view their card info
				int cardNumber = Integer.parseInt(cardNumberField.getText());
				String password = passwordField.getText();
				boolean check = false;

				for(int i = 0; i<visa.getUserCreditCards().size(); i++)
				{
					if(visa.getUserCreditCards().get(i).getAccountNumber()== cardNumber)
					{
						if(visa.getUserCreditCards().get(i).getUsername().equals(DataBaseSetUp.username))
						{
							check = true;
							break;
						}
					}
				}
				if(check){
					for(int i =0; i<visa.getUserCreditCards().size(); i++)
					{
						if(visa.getUserCreditCards().get(i).getAccountNumber()==cardNumber&& visa.getUserCreditCards().get(i).getPassword().equals(password))
						{
							accountNumber = visa.getUserCreditCards().get(i).getAccountNumber();
							username = visa.getUserCreditCards().get(i).getUsername();
							password = visa.getUserCreditCards().get(i).getPassword();
							accountHolderName = visa.getUserCreditCards().get(i).getAccountHolderName();
							creditLimit = visa.getUserCreditCards().get(i).getCreditLimit();
							amountDue = visa.getUserCreditCards().get(i).getAmountDue();

							setVisible(false);

							CardHolderScreen cardHolderScreen = new CardHolderScreen(accountNumber, username, password, accountHolderName, creditLimit, amountDue, visa);
//							cardHolderScreen.accountNameField.setText("Account Name: "+fullname);
//							cardHolderScreen.cardNumberField.setText("Card Number: "+accountNumber);
//							cardHolderScreen.accountUserNameField.setText("Username: "+username);
//
//							cardHolderScreen.creditLimitField.setText("Credit Limit: "+creditLimit);
//							cardHolderScreen.amountDueField.setText("Amount Due: "+amountDue);

							cardHolderScreen.setSize(700, 600);
							cardHolderScreen.setLocation(600, 250);
							cardHolderScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							cardHolderScreen.setLayout(null);
							cardHolderScreen.setVisible(true);

							System.out.println("here is your card info "+DataBaseSetUp.username);
							break;
						}
					}
				}
				else
				{
					System.out.println("Wrong accountNumber " +DataBaseSetUp.username+"Please try again");
				}
			}else if(event.getSource() == cardInfoCheckBackButton) {
				// The user goes back to afterLogin screen from cardInfoCheck
				choicePanel.setVisible(false);
				loginButtonPanel.setVisible(false);
				System.out.println("username backed from card info "+DataBaseSetUp.username);
				afterLogin();
			}else if(event.getSource() == shopButton) {
				// The user shop button
				setVisible(false);
				System.out.println("Shop button pressed...by "+DataBaseSetUp.username);
				VendorScreen vendorScreen = new VendorScreen(visa);
				vendorScreen.setSize(700, 600);
				vendorScreen.setLocation(600, 250);
				vendorScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				vendorScreen.setLayout(null);
				vendorScreen.setVisible(true);
				
			}else if(event.getSource() == sendCheckButton) {
				// The user send check to another card holder
				choicePanel.setVisible(false);
				System.out.println("Send check is pressed...by "+DataBaseSetUp.username);
				sendCheck();
			}else if(event.getSource() == sendCheckFinishButton) {
				// Holder finishes sending check

				int toAccountNumber = Integer.parseInt(textField.getText());
				int fromAccountNumber = Integer.parseInt(text2Field.getText());
				double money = Double.parseDouble(text3Field.getText());
				double creditLimit;
				boolean limitExceeded = false;
				boolean check = false;

				for(int i = 0; i<visa.getUserCreditCards().size(); i++)
				{
					if(visa.getUserCreditCards().get(i).getAccountNumber()== fromAccountNumber)
					{
						if(visa.getUserCreditCards().get(i).getUsername().equals(DataBaseSetUp.username))
						{
							check = true;
							setVisible(false);
							LoginScreen loginScreen = new LoginScreen(visa);
							loginScreen.afterLogin();
							loginScreen.setSize(700, 600);
							loginScreen.setLocation(600, 250);
							loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							loginScreen.setLayout(null);
							loginScreen.setVisible(true);
							break;
						}
					}
				}

				if(check){
				if(toAccountNumber!= fromAccountNumber){
				for(int i = 0; i<visa.getUserCreditCards().size(); i++)
				{
					if(visa.getUserCreditCards().get(i).getAccountNumber() ==fromAccountNumber &&
							visa.getUserCreditCards().get(i).getCreditLimit()<money )
					{
						limitExceeded = true;
					}
				}}
				boolean itExists = false;

				if(!limitExceeded){

					for(int i = 0; i<visa.getUserCreditCards().size(); i++)
					{
						if(visa.getUserCreditCards().get(i).getAccountNumber()==toAccountNumber)
						{
							creditLimit = visa.getUserCreditCards().get(i).getCreditLimit();
							creditLimit += money;
							visa.updateCreditLimit(toAccountNumber, creditLimit);
							itExists = true;
						}
					}
				}
				if(itExists)
				{
					double limit;
					for(int i = 0; i<visa.getUserCreditCards().size(); i++)
					{
						if(visa.getUserCreditCards().get(i).getAccountNumber()==fromAccountNumber)
						{
							limit = visa.getUserCreditCards().get(i).getCreditLimit();
							limit = limit - money;
							visa.updateAmountDue(fromAccountNumber, money);
							visa.updateCreditLimit(fromAccountNumber, limit);
							String username = usernameField.getText().toLowerCase();
							visa.createCardTransaction(fromAccountNumber, " ", money, "send check", username);
						}
					}
				}
				else
				{
					 System.out.println("Wrong ToCardNumber you entered doesnt exists ");
				}
				}
				else
				{
					System.out.println("Wrong FromCardNumber you entered.");

				}
			}else if(event.getSource() == sendCheckBackButton) {
				// Back from the send check screen to the after login as holder screen
				sendCheckPanel.setVisible(false);
				System.out.println("SendCheckBackButton Pressed by "+DataBaseSetUp.username);
				afterLogin();
			}else if(event.getSource() == backToMainFrameButton) {
				// Goes back to the main frame from the login screen
				setVisible(false);

				MainFrame mainFrame = new MainFrame();
				mainFrame.setSize(700, 600);
				mainFrame.setLocation(600, 250);
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setLayout(null);
				mainFrame.setVisible(true);
			}
		}
	}

	public void register() {

		text3Panel.setVisible(false);
		loginPanel.setVisible(false);
		loginButtonPanel.setVisible(false);
		
		text5Field = new JTextField("Register ");
		text5Field.setFont(messageFont);
		text5Field.setBounds(180, 70, 350, 50);
		text5Field.setEditable(false);
		add(text5Field);
		
		registerPanel = new JPanel();
		registerPanel.setBounds(180, 150, 350, 80);
		registerPanel.setLayout(new GridLayout(3,3));
		add(registerPanel);
		
		setFullNameLabel = new JLabel("Set Full Name: ");
		setFullNameLabel.setFont(messageFont);
		registerPanel.add(setFullNameLabel);

		setFullNameField = new JTextField();
		registerPanel.add(setFullNameField);

		setUsernameLabel = new JLabel("Set User Name ");
		setUsernameLabel.setFont(messageFont);
		registerPanel.add(setUsernameLabel);

		setUsernameField = new JTextField();
		registerPanel.add(setUsernameField);

		setPasswordLabel = new JLabel("Set Password: ");
		setPasswordLabel.setFont(messageFont);
		registerPanel.add(setPasswordLabel);

		setPasswordField = new JTextField();
		registerPanel.add(setPasswordField);

		finishBackButtonPanel = new JPanel();
		finishBackButtonPanel.setBounds(180, 240, 350, 100);
		finishBackButtonPanel.setLayout(new GridLayout(2,1));
		add(finishBackButtonPanel);

		finishButton = new JButton("Finish ");
		finishButton.setFont(messageFont);
		finishButton.addActionListener(handler);
		finishBackButtonPanel.add(finishButton);

		backButton = new JButton("Back");
		backButton.setFont(messageFont);
		backButton.addActionListener(handler);
		finishBackButtonPanel.add(backButton);
	}

	public void afterLogin() {

		DataBaseSetUp.listOfAccountNumbers.clear();
		System.out.println("clicked byyyyyy "+DataBaseSetUp.username);
		List<CreditCard> cards= visa.getUserCreditCards();
		for(int i = 0; i<cards.size(); i++)
		{
			if(cards.get(i).getUsername().equals(DataBaseSetUp.username))
			{
				DataBaseSetUp.listOfAccountNumbers.add(cards.get(i).getAccountNumber());
				System.out.println("Welcome "+DataBaseSetUp.username +" your Card#: " +cards.get(i).getAccountNumber());
				
			}
		}
		text3Panel.setVisible(false);
		loginPanel.setVisible(false);
		loginButtonPanel.setVisible(false);

		choicePanel = new JPanel();
		choicePanel.setBounds(150, 100, 400, 250);
		choicePanel.setLayout(new GridLayout(5,1));
		add(choicePanel);
		
		StringBuilder sb = new StringBuilder();
		
		for(int a : DataBaseSetUp.listOfAccountNumbers)
		{
			sb.append(a+",");
		}
		
		textField = new JTextField("Welcome, "+DataBaseSetUp.username +". Card#: "+sb);
		textField.setFont(messageFont);
		textField.setEditable(false);
		choicePanel.add(textField);

		cardHolderScreenButton = new JButton("Card Info");
		cardHolderScreenButton.setFont(messageFont);
		cardHolderScreenButton.addActionListener(handler);
		choicePanel.add(cardHolderScreenButton);

		shopButton = new JButton("View Shop");
		shopButton.setFont(messageFont);
		shopButton.addActionListener(handler);
		choicePanel.add(shopButton);

		sendCheckButton = new JButton("Send Check");
		sendCheckButton.setFont(messageFont);
		sendCheckButton.addActionListener(handler);
		choicePanel.add(sendCheckButton);

		logoutButton = new JButton("Logout");
		logoutButton.setFont(messageFont);
		logoutButton.addActionListener(handler);
		choicePanel.add(logoutButton);	}

	public void loginAsAdmin() {

		text3Panel.setVisible(false);
		loginPanel.setVisible(false);
		loginButtonPanel.setVisible(false);

		text6Field = new JTextField("Login as admin");
		text6Field.setFont(messageFont);
		text6Field.setBounds(180, 70, 300, 50);
		text6Field.setEditable(false);
		add(text6Field);
		
		loginPanel = new JPanel();
		loginPanel.setBounds(180, 150, 300, 30);
		loginPanel.setLayout(new GridLayout(1,2));
		add(loginPanel);
		
		passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(messageFont);
		loginPanel.add(passwordLabel);

		passwordField = new JPasswordField();
		loginPanel.add(passwordField);

		loginButtonPanel = new JPanel();
		loginButtonPanel.setBounds(180, 190, 300, 100);
		loginButtonPanel.setLayout(new GridLayout(2,1));
		add(loginButtonPanel);

		loginForAdminButton = new JButton("Login");
		loginForAdminButton.setFont(messageFont);
		loginForAdminButton.addActionListener(handler);
		loginButtonPanel.add(loginForAdminButton);

		loginAsAdminBackButton = new JButton("Back");
		loginAsAdminBackButton.setFont(messageFont);
		loginAsAdminBackButton.addActionListener(handler);
		loginButtonPanel.add(loginAsAdminBackButton);
	}

	public void afterLoginAsAdmin() {

		loginPanel.setVisible(false);
		loginButtonPanel.setVisible(false);

		choicePanel = new JPanel();
		choicePanel.setBounds(180, 100, 300, 350);
		choicePanel.setLayout(new GridLayout(7,1));
		add(choicePanel);

		textField = new JTextField("Make a selection ");
		textField.setFont(messageFont);
		textField.setEditable(false);
		choicePanel.add(textField);
		
		adminAddAccountButton = new JButton("Add Account");
		adminAddAccountButton.setFont(messageFont);
		adminAddAccountButton.addActionListener(handler);
		choicePanel.add(adminAddAccountButton);

		adminViewAccountButton = new JButton("View Accounts");
		adminViewAccountButton.setFont(messageFont);
		adminViewAccountButton.addActionListener(handler);
		choicePanel.add(adminViewAccountButton);

		adminDeleteAccountButton = new JButton("Delete Accounts");
		adminDeleteAccountButton.setFont(messageFont);
		adminDeleteAccountButton.addActionListener(handler);
		choicePanel.add(adminDeleteAccountButton);

		adminViewVendorButton = new JButton("View Vendor");
		adminViewVendorButton.setFont(messageFont);
		adminViewVendorButton.addActionListener(handler);
		choicePanel.add(adminViewVendorButton);

		adminLogoutButton = new JButton("Logout ");
		adminLogoutButton.setFont(messageFont);
		adminLogoutButton.addActionListener(handler);
		choicePanel.add(adminLogoutButton);
	}

	public void adminAddAccount() {

		adminLogoutButton.setVisible(false);
		choicePanel.setVisible(false);
		register();
	}

	public void adminViewAccount() {

		int listSize = visa.getUserCreditCards().size();
		String accountNumber;
		String username;

		adminLogoutButton.setVisible(false);
		choicePanel.setVisible(false);

		textPanel = new JPanel();
		textPanel.setBounds(150, 30, 350, 50);
		add(textPanel);
		int size = visa.getUserCreditCards().size();
		textField = new JTextField("Total customer accounts: "+size);
		textField.setFont(messageFont);
		textField.setEditable(false);
		textPanel.add(textField);

		text2Panel = new JPanel();
		text2Panel.setBounds(150, 100, 350, 50);
		add(text2Panel);

		text2Field = new JTextField("Viewable limit is 5 accounts");
		text2Field.setFont(messageFont);
		text2Field.setEditable(false);
		text2Panel.add(text2Field);

		adminViewAccountPanel = new JPanel();
		adminViewAccountPanel.setBounds(150, 160, 350, 300);
		adminViewAccountPanel.setLayout(new GridLayout(6,1));
		add(adminViewAccountPanel);

		if(listSize<=0)
		{
			username ="";
			accountNumber = "";
		}
		else{
			accountNumber = String.valueOf(visa.getUserCreditCards().get(0).getAccountNumber());
			username = visa.getUserCreditCards().get(0).getUsername();
		}
		adminViewAccount1Button = new JButton(username);
		adminViewAccount1Button.setFont(messageFont);
		adminViewAccount1Button.setName(accountNumber);
		adminViewAccount1Button.addActionListener(handler);
		adminViewAccountPanel.add(adminViewAccount1Button);

		if(listSize<=1)
		{
			username = "";
			accountNumber ="";
		}
		else{
			accountNumber = String.valueOf(visa.getUserCreditCards().get(1).getAccountNumber());
			username = visa.getUserCreditCards().get(1).getUsername();
		}
		adminViewAccount2Button = new JButton(username);
		adminViewAccount2Button.setFont(messageFont);
		adminViewAccount2Button.setName(accountNumber);
		adminViewAccount2Button.addActionListener(handler);
		adminViewAccountPanel.add(adminViewAccount2Button);

		if(listSize<=2)
		{
			username ="";
			accountNumber ="";
		}
		else{
			accountNumber = String.valueOf(visa.getUserCreditCards().get(2).getAccountNumber());
			username = visa.getUserCreditCards().get(2).getUsername();

		}
		adminViewAccount3Button = new JButton(username);
		adminViewAccount3Button.setFont(messageFont);
		adminViewAccount3Button.setName(accountNumber);
		adminViewAccount3Button.addActionListener(handler);
		adminViewAccountPanel.add(adminViewAccount3Button);
		if(listSize<=3)
		{
			username ="";
			accountNumber = "";
		}
		else{
			accountNumber = String.valueOf(visa.getUserCreditCards().get(3).getAccountNumber());
			username = visa.getUserCreditCards().get(3).getUsername();
		}
		adminViewAccount4Button = new JButton(username);
		adminViewAccount4Button.setFont(messageFont);
		adminViewAccount4Button.setName(accountNumber);
		adminViewAccount4Button.addActionListener(handler);
		adminViewAccountPanel.add(adminViewAccount4Button);

		if(listSize<=4)
		{
			username="";
			accountNumber = "";
		}
		else{
			accountNumber = String.valueOf(visa.getUserCreditCards().get(4).getAccountNumber());
			username = visa.getUserCreditCards().get(4).getUsername();
		}
		adminViewAccount5Button = new JButton(username);
		adminViewAccount5Button.setFont(messageFont);
		adminViewAccount5Button.setName(accountNumber);
		adminViewAccount5Button.addActionListener(handler);
		adminViewAccountPanel.add(adminViewAccount5Button);

		adminViewAccountBackButton = new JButton("Back ");
		adminViewAccountBackButton.setFont(messageFont);
		adminViewAccountBackButton.addActionListener(handler);
		adminViewAccountPanel.add(adminViewAccountBackButton);
	}

	public void adminDeleteAccount() {

		int listSize = visa.getUserCreditCards().size();
		String username, accountNumber;

		adminLogoutButton.setVisible(false);
		choicePanel.setVisible(false);

		textPanel = new JPanel();
		textPanel.setBounds(150, 30, 350, 100);
		add(textPanel);

		int size = visa.getUserCreditCards().size();

		textField = new JTextField("Total Accounts: "+size+". Click to Delete");
		textField.setFont(messageFont);
		textField.setEditable(false);
		textPanel.add(textField);

		adminDeleteAccountPanel = new JPanel();
		adminDeleteAccountPanel.setBounds(150, 130, 350, 300);
		adminDeleteAccountPanel.setLayout(new GridLayout(6,1));
		add(adminDeleteAccountPanel);
		if(listSize<=0)
		{
			username ="";
			accountNumber = "";
		}
		else{
			accountNumber = String.valueOf(visa.getUserCreditCards().get(0).getAccountNumber());
			username = visa.getUserCreditCards().get(0).getUsername();
		}
		adminDeleteAccount1Button = new JButton(username);
		adminDeleteAccount1Button.setName(accountNumber);
		adminDeleteAccount1Button.setFont(messageFont);
		adminDeleteAccount1Button.addActionListener(handler);
		adminDeleteAccountPanel.add(adminDeleteAccount1Button);

		if(listSize<=1)
		{
			username ="";
			accountNumber = "";
		}
		else{
			accountNumber = String.valueOf(visa.getUserCreditCards().get(1).getAccountNumber());
			username = visa.getUserCreditCards().get(1).getUsername();
		}

		adminDeleteAccount2Button = new JButton(username);
		adminDeleteAccount2Button.setName(accountNumber);
		adminDeleteAccount2Button.setFont(messageFont);
		adminDeleteAccount2Button.addActionListener(handler);
		adminDeleteAccountPanel.add(adminDeleteAccount2Button);

		if(listSize<=2)
		{
			username ="";
			accountNumber = "";
		}
		else{
			accountNumber = String.valueOf(visa.getUserCreditCards().get(2).getAccountNumber());
			username = visa.getUserCreditCards().get(2).getUsername();
		}

		adminDeleteAccount3Button = new JButton(username);
		adminDeleteAccount3Button.setName(accountNumber);
		adminDeleteAccount3Button.setFont(messageFont);
		adminDeleteAccount3Button.addActionListener(handler);
		adminDeleteAccountPanel.add(adminDeleteAccount3Button);

		if(listSize<=3)
		{
			username ="";
			accountNumber = "";
		}
		else{
			accountNumber = String.valueOf(visa.getUserCreditCards().get(3).getAccountNumber());
			username = visa.getUserCreditCards().get(3).getUsername();
		}

		adminDeleteAccount4Button = new JButton(username);
		adminDeleteAccount4Button.setName(accountNumber);
		adminDeleteAccount4Button.setFont(messageFont);
		adminDeleteAccount4Button.addActionListener(handler);
		adminDeleteAccountPanel.add(adminDeleteAccount4Button);

		if(listSize<=4)
		{
			username ="";
			accountNumber = "";
		}
		else{
			accountNumber = String.valueOf(visa.getUserCreditCards().get(4).getAccountNumber());
			username = visa.getUserCreditCards().get(4).getUsername();
		}

		adminDeleteAccount5Button = new JButton(username);
		adminDeleteAccount5Button.setName(accountNumber);
		adminDeleteAccount5Button.setFont(messageFont);
		adminDeleteAccount5Button.addActionListener(handler);
		adminDeleteAccountPanel.add(adminDeleteAccount5Button);

		adminDeleteAccountBackButton = new JButton("Back ");
		adminDeleteAccountBackButton.setFont(messageFont);
		adminDeleteAccountBackButton.addActionListener(handler);
		adminDeleteAccountPanel.add(adminDeleteAccountBackButton);
	}

	private void adminViewVendor() {

		adminLogoutButton.setVisible(false);
		choicePanel.setVisible(false);

		viewVendorBackPanel = new JPanel();
		viewVendorBackPanel.setBounds(100, 450, 100, 50);
		add(viewVendorBackPanel);

		viewVendorBackButton = new JButton("Back");
		viewVendorBackButton.setFont(messageFont);
		viewVendorBackButton.addActionListener(handler);
		viewVendorBackPanel.add(viewVendorBackButton);

		shopsPanel = new JPanel();
		shopsPanel.setBounds(210, 100, 250, 300);
		shopsPanel.setLayout(new GridLayout(3,1));
		add(shopsPanel);

		shop1Button = new JButton(visa.getListOfAllVendors().get(0).getStoreName());
		shop1Button.setFont(messageFont);
		shop1Button.addActionListener(handler);
		shopsPanel.add(shop1Button);

		shop2Button = new JButton(visa.getListOfAllVendors().get(1).getStoreName());
		shop2Button.setFont(messageFont);
		shop2Button.addActionListener(handler);
		shopsPanel.add(shop2Button);

		shop3Button = new JButton(visa.getListOfAllVendors().get(2).getStoreName());
		shop3Button.setFont(messageFont);
		shop3Button.addActionListener(handler);
		shopsPanel.add(shop3Button);
	}

	public void adminViewShopInfo(ActionEvent event) {
		// Add JOptionPane to each of the shops
		OptionPane optionPane = new OptionPane();
		if(event.getSource() == shop1Button) {
			optionPane.viewShopInfo(shop1Button.getText());
		}else if(event.getSource() == shop2Button) {
			optionPane.viewShopInfo(shop2Button.getText());
		}else if(event.getSource() == shop3Button) {
			optionPane.viewShopInfo(shop3Button.getText());
		}
	}

	public void cardInfoCheck() {

		loginPanel = new JPanel();
		loginPanel.setBounds(200, 130, 300, 50);
		loginPanel.setLayout(new GridLayout(2,2));
		add(loginPanel);

		usernameLabel = new JLabel("Card Number: ");
		usernameLabel.setFont(messageFont);
		loginPanel.add(usernameLabel);

		cardNumberField = new JTextField();
		loginPanel.add(cardNumberField);

		passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(messageFont);
		loginPanel.add(passwordLabel);

		passwordField = new JPasswordField();
		loginPanel.add(passwordField);

		loginButtonPanel = new JPanel();
		loginButtonPanel.setBounds(200, 190, 300, 50);
		loginButtonPanel.setLayout(new GridLayout(1,2));
		add(loginButtonPanel);

		cardInfoCheckBackButton = new JButton("Back ");
		cardInfoCheckBackButton.setFont(messageFont);
		cardInfoCheckBackButton.addActionListener(handler);
		loginButtonPanel.add(cardInfoCheckBackButton);

		cardInfoCheckFinishButton = new JButton("Finish ");
		cardInfoCheckFinishButton.setFont(messageFont);
		cardInfoCheckFinishButton.addActionListener(handler);
		loginButtonPanel.add(cardInfoCheckFinishButton);
	}

	public void sendCheck() {

		sendCheckPanel = new JPanel();
		sendCheckPanel.setBounds(140, 150, 450, 110);
		sendCheckPanel.setLayout(new GridLayout(4,4));
		add(sendCheckPanel);

		toUsernameLabel = new JLabel("To Account Number: ");
		toUsernameLabel.setFont(messageFont);
		sendCheckPanel.add(toUsernameLabel);

		textField = new JTextField();
		sendCheckPanel.add(textField);

		fromUsernameLabel = new JLabel("From Account Number: ");
		fromUsernameLabel.setFont(messageFont);
		sendCheckPanel.add(fromUsernameLabel);

		text2Field = new JTextField();
		sendCheckPanel.add(text2Field);

		howMuchLabel = new JLabel("How Much: ");
		howMuchLabel.setFont(messageFont);
		sendCheckPanel.add(howMuchLabel);

		text3Field = new JTextField();
		sendCheckPanel.add(text3Field);

		sendCheckBackButton = new JButton("Back ");
		sendCheckBackButton.setFont(messageFont);
		sendCheckBackButton.addActionListener(handler);
		sendCheckPanel.add(sendCheckBackButton);

		sendCheckFinishButton = new JButton("Send ");
		sendCheckFinishButton.setFont(messageFont);
		sendCheckFinishButton.addActionListener(handler);
		sendCheckPanel.add(sendCheckFinishButton);
	}
	
	public void afterLoginAsAdminFromCardHolderScreen() {

		text3Panel.setVisible(false);
		loginPanel.setVisible(false);
		loginButtonPanel.setVisible(false);

		choicePanel = new JPanel();
		choicePanel.setBounds(180, 100, 300, 350);
		choicePanel.setLayout(new GridLayout(7,1));
		add(choicePanel);

		textField = new JTextField("Make a selection ");
		textField.setFont(messageFont);
		textField.setEditable(false);
		choicePanel.add(textField);
		
		adminAddAccountButton = new JButton("Add Account");
		adminAddAccountButton.setFont(messageFont);
		adminAddAccountButton.addActionListener(handler);
		choicePanel.add(adminAddAccountButton);

		adminViewAccountButton = new JButton("View Accounts");
		adminViewAccountButton.setFont(messageFont);
		adminViewAccountButton.addActionListener(handler);
		choicePanel.add(adminViewAccountButton);

		adminDeleteAccountButton = new JButton("Delete Accounts");
		adminDeleteAccountButton.setFont(messageFont);
		adminDeleteAccountButton.addActionListener(handler);
		choicePanel.add(adminDeleteAccountButton);

		adminViewVendorButton = new JButton("View Vendor");
		adminViewVendorButton.setFont(messageFont);
		adminViewVendorButton.addActionListener(handler);
		choicePanel.add(adminViewVendorButton);

		adminLogoutButton = new JButton("Logout ");
		adminLogoutButton.setFont(messageFont);
		adminLogoutButton.addActionListener(handler);
		choicePanel.add(adminLogoutButton);
	}
}
