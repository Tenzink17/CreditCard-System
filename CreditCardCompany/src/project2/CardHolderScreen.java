package project2;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class CardHolderScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel  buttonPanel, setPasswordPanel, finishBackButtonPanel, makePaymentPanel;
	private JLabel  oldPasswordLabel, newPasswordLabel,
			 	    makePaymentHowMuchLabel;
	private JButton viewAllButton, changePasswordButton, makePaymentButton,
					finishButton, backButton, returnButton,
					makePaymentBackButton, makePaymentFinishButton;

	public  JTextField     accountNameField, accountUserNameField, cardNumberField, creditLimitField, amountDueField, paymentField, makePaymentHowMuchField;
	private JPasswordField oldPasswordField, newPasswordField;

	private OptionPane optionPane;
	String accountStatement;
	private JEditorPane ep;
	private JScrollPane sp;

	private Font  messageFont = new Font(null, Font.PLAIN, 20);
	actionHandler handler     = new actionHandler();

	private int accountNumber;
	private String username, password, accountHolderName;
	private CreditCardCompany visa;
	private double creditLimit, amountDue;

	public CardHolderScreen(int accountNumber, String username, String password, String accountHolderName, double creditLimit, double amountDue, CreditCardCompany visa) {
		super("Card Holder Screen");
		this.visa = visa;
		this.accountNumber = accountNumber;
		this.username = username;
		this.password = password;
		this.accountHolderName = accountHolderName;
		this.creditLimit = creditLimit;
		this.amountDue = amountDue;
		cardHolderScreen();
	}

	public void cardHolderScreen() {
		optionPane = new OptionPane(accountNumber, username, password, accountHolderName, creditLimit, amountDue, visa);
		accountStatement = optionPane.getAccountStatement(username, accountNumber);
		ep = new JEditorPane ("text/html", accountStatement);
		ep.setEditable(false);
		ep.setCaretPosition(0);
		sp = new JScrollPane(ep);
		sp.setBounds(20, 20, 650, 300);
		sp.setPreferredSize(new Dimension(600, 600));
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(sp);

//		accountStatementPanel = new JPanel();
//		accountStatementPanel.setBounds(0, 0, 600, 200);
//		accountStatementPanel.setLayout(new GridLayout(6,1));
//		add(accountStatementPanel);
//
//		accountStatementLabel = new JLabel("Account Statement");
//		accountStatementLabel.setFont(messageFont);
//		accountStatementPanel.add(accountStatementLabel);
//
//		accountNameField = new JTextField("Account name: ");
//		accountNameField.setFont(messageFont);
//		accountNameField.setEditable(false);
//		accountStatementPanel.add(accountNameField);
//
//		accountUserNameField = new JTextField("Username: ");
//		accountUserNameField.setFont(messageFont);
//		accountUserNameField.setEditable(false);
//		accountStatementPanel.add(accountUserNameField);
//
//		cardNumberField = new JTextField("Card number: ");
//		cardNumberField.setFont(messageFont);
//		cardNumberField.setEditable(false);
//		accountStatementPanel.add(cardNumberField);
//
//		creditLimitField = new JTextField("Credit Limit: ");
//		creditLimitField.setFont(messageFont);
//		creditLimitField.setEditable(false);
//		accountStatementPanel.add(creditLimitField);
//
//		amountDueField = new JTextField("Amount Due: ");
//		amountDueField.setFont(messageFont);
//		amountDueField.setEditable(false);
//		accountStatementPanel.add(amountDueField);
//
//		paymentPanel = new JPanel();
//		paymentPanel.setBounds(0, 210, 600, 100);
//		paymentPanel.setLayout(new GridLayout(2,1));
//		add(paymentPanel);
//
//		paymentLabel = new JLabel("Last payment ");
//		paymentLabel.setFont(messageFont);
//		paymentPanel.add(paymentLabel);
//
//		paymentField = new JTextField("Date: " + "                    Amount: " + "                    Item: " );
//		paymentField.setFont(messageFont);
//		paymentField.setEditable(false);
//		paymentPanel.add(paymentField);

		buttonPanel = new JPanel();
		buttonPanel.setBounds(60, 330, 480, 200);
		buttonPanel.setLayout(new GridLayout(2,2));
		add(buttonPanel);

		makePaymentButton = new JButton("Make payment ");
		makePaymentButton.setFont(messageFont);
		makePaymentButton.addActionListener(handler);
		buttonPanel.add(makePaymentButton);

		viewAllButton = new JButton("View all");
		viewAllButton.setFont(messageFont);
		viewAllButton.addActionListener(handler);
		buttonPanel.add(viewAllButton);

		changePasswordButton = new JButton("Set password");
		changePasswordButton.setFont(messageFont);
		changePasswordButton.addActionListener(handler);
		buttonPanel.add(changePasswordButton);

		returnButton = new JButton("Back");
		returnButton.setFont(messageFont);
		returnButton.addActionListener(handler);
		buttonPanel.add(returnButton);
	}

	private class actionHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == changePasswordButton) {
				// The user sets the password for his account
				System.out.println("hello "+DataBaseSetUp.username);
				setPassword();
			}else if(event.getSource() == viewAllButton) {
				// Shows JOptionPane of recent purchase
				// Add a check
				for(int i =0; i<visa.getUserCreditCards().size(); i++)
				{
					if(visa.getUserCreditCards().get(i).getUsername().equals(username)&& visa.getUserCreditCards().get(i).getPassword().equals(password))
					{
						String username = visa.getUserCreditCards().get(i).getUsername();
						String accountHolderName = visa.getUserCreditCards().get(i).getAccountHolderName();
						int accountNumber = visa.getUserCreditCards().get(i).getAccountNumber();
						double amountDue = visa.getUserCreditCards().get(i).getAmountDue();
						double creditLimit = visa.getUserCreditCards().get(i).getCreditLimit();

						OptionPane optionPane = new OptionPane(accountNumber, username, password, accountHolderName, creditLimit, amountDue,visa);
						optionPane.viewAll(username, accountNumber);
					}
				}
			}else if(event.getSource() == makePaymentButton) {
				// Card holder makes payment
				makePayment();
			}else if(event.getSource() == makePaymentFinishButton) {
				// Card holder finish making payment
				int cardNumber = accountNumber;
				double money = Double.parseDouble(makePaymentHowMuchField.getText());

				for(int i = 0; i<visa.getUserCreditCards().size(); i++)
				{
					if(visa.getUserCreditCards().get(i).getAccountNumber()== cardNumber
							&& visa.getUserCreditCards().get(i).getAmountDue()>0)
					{
						visa.decraseAmountDue(cardNumber, money);
						setVisible(false);
						LoginScreen loginScreen = new LoginScreen(visa);
						loginScreen.setSize(700, 600);
						loginScreen.setLocation(600, 250);
						loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						loginScreen.setLayout(null);
						loginScreen.setVisible(true);
						loginScreen.afterLogin();
					}
				}
			}

			else if(event.getSource() == makePaymentBackButton) {
				// Card holder back from the makes payment screen
				setVisible(false);

				for(int i =0; i<visa.getUserCreditCards().size(); i++)
				{
					if(visa.getUserCreditCards().get(i).getUsername().equals(username)&& visa.getUserCreditCards().get(i).getPassword().equals(password))
					{

					accountNumber = visa.getUserCreditCards().get(i).getAccountNumber();
					username = visa.getUserCreditCards().get(i).getUsername();
					password = visa.getUserCreditCards().get(i).getPassword();
					accountHolderName = visa.getUserCreditCards().get(i).getAccountHolderName();
					creditLimit = visa.getUserCreditCards().get(i).getCreditLimit();
					amountDue = visa.getUserCreditCards().get(i).getAmountDue();
					CardHolderScreen cardHolderScreen = new CardHolderScreen(accountNumber, username, password, accountHolderName, creditLimit, amountDue, visa);
//					cardHolderScreen.accountNameField.setText("Account Name: "+accountHolderName);
//					cardHolderScreen.cardNumberField.setText("Card Number: "+accountNumber);
//					cardHolderScreen.accountUserNameField.setText("Username: "+username);
//
//					cardHolderScreen.creditLimitField.setText("Credit Limit: "+creditLimit);
//					cardHolderScreen.amountDueField.setText("Amount Due: "+amountDue);

					cardHolderScreen.setSize(700, 600);
					cardHolderScreen.setLocation(600, 250);
					cardHolderScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					cardHolderScreen.setLayout(null);
					cardHolderScreen.setVisible(true);
				}
			}
			}else if(event.getSource() == finishButton) {
				// Finish setting password
				String newPassword = newPasswordField.getText();
				String oldPassword = oldPasswordField.getText();
				if(newPassword.length()<6)
				{
					// do nothing
					// maybe add a message saying its less than 6 characters.
					System.out.println("Cannot update password ");
				}
				else
				{boolean check = false;
					for(int i =0; i<visa.getUserCreditCards().size(); i++)
					{
						if(visa.getUserCreditCards().get(i).getPassword().equals(oldPassword)&& visa.getUserCreditCards().get(i).getAccountHolderName().equals(DataBaseSetUp.username))
						{
							visa.updatePassword(DataBaseSetUp.username,oldPassword, newPassword);
							visa.getUserCreditCards().get(i).setPassword(newPassword);
							System.out.println("updated");
							check = true;
							
							setVisible(false);
							LoginScreen loginScreen = new LoginScreen(visa);
							loginScreen.afterLogin();
							loginScreen.setSize(700, 600);
							loginScreen.setLocation(600, 250);
							loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							loginScreen.setLayout(null);
							loginScreen.setVisible(true);
						}
					}
					if(!check)
					{
						System.out.println("wrong oldpassword ");
					}
				}

			}else if(event.getSource() == backButton) {
				// Back button in user set password to return to their card info screen
				setPasswordPanel.setVisible(false);
				finishBackButtonPanel.setVisible(false);

				sp.setVisible(true);
//				paymentPanel.setVisible(true);
				buttonPanel.setVisible(true);
			}else if(event.getSource() == returnButton) {
				// In user card info screen, returns back to afteLogin Screen
				setVisible(false);
				LoginScreen loginScreen = new LoginScreen(visa);
				loginScreen.afterLogin();
				loginScreen.setSize(700, 600);
				loginScreen.setLocation(600, 250);
				loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				loginScreen.setLayout(null);
				loginScreen.setVisible(true);
			}
		}
	}

	public void setPassword() {

		sp.setVisible(false);
//		paymentPanel.setVisible(false);
		buttonPanel.setVisible(false);

		setPasswordPanel = new JPanel();
		setPasswordPanel.setBounds(180, 150, 300, 50);
		setPasswordPanel.setLayout(new GridLayout(2,2));
		add(setPasswordPanel);

		oldPasswordLabel = new JLabel("Old Password: ");
		oldPasswordLabel.setFont(messageFont);
		setPasswordPanel.add(oldPasswordLabel);

		oldPasswordField = new JPasswordField();
		setPasswordPanel.add(oldPasswordField);

		newPasswordLabel = new JLabel("New Password: ");
		newPasswordLabel.setFont(messageFont);
		setPasswordPanel.add(newPasswordLabel);

		newPasswordField = new JPasswordField();
		setPasswordPanel.add(newPasswordField);

		finishBackButtonPanel = new JPanel();
		finishBackButtonPanel.setBounds(180, 210, 300, 100);
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

	public void makePayment() {

		sp.setVisible(false);
//		paymentPanel.setVisible(false);
		buttonPanel.setVisible(false);

		makePaymentPanel = new JPanel();
		makePaymentPanel.setBounds(180, 150, 300, 80);
		makePaymentPanel.setLayout(new GridLayout(2,2));
		add(makePaymentPanel);

		makePaymentHowMuchLabel = new JLabel("How Much: ");
		makePaymentHowMuchLabel.setFont(messageFont);
		makePaymentPanel.add(makePaymentHowMuchLabel);

		makePaymentHowMuchField = new JTextField();
		makePaymentPanel.add(makePaymentHowMuchField);

		makePaymentBackButton = new JButton("Back");
		makePaymentBackButton.setFont(messageFont);
		makePaymentBackButton.addActionListener(handler);
		makePaymentPanel.add(makePaymentBackButton);

		makePaymentFinishButton = new JButton("Finish ");
		makePaymentFinishButton.setFont(messageFont);
		makePaymentFinishButton.addActionListener(handler);
		makePaymentPanel.add(makePaymentFinishButton);
	}
}
