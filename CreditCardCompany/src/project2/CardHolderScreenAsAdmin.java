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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class CardHolderScreenAsAdmin extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel  paymentPanel, buttonPanel, cardLimitInputPanel;
	private JLabel  accountStatementLabel, cardNumberLabel, setCardLimitLabel;
	private JButton viewAllButton, changeCardLimitButton,
					backButton,
					setCardLimitBackButton, setCardLimitFinishButton;
	public JTextField accountNameField, cardNumberField, creditLimitField, amountDueField, paymentField, setCardLimitField;
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

	public CardHolderScreenAsAdmin(int accountNumber, String username, String password, String accountHolderName, double creditLimit, double amountDue, CreditCardCompany visa) {
		super("Admin Card Holder Screen");
		this.visa = visa;
		this.accountNumber = accountNumber;
		this.username = username;
		this.password = password;
		this.accountHolderName = accountHolderName;
		this.creditLimit = creditLimit;
		this.amountDue = amountDue;
		cardHolderScreenAsAdmin();
	}

	public void cardHolderScreenAsAdmin() {
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
//		accountStatementPanel.setLayout(new GridLayout(5,1));
//		add(accountStatementPanel);
//
//		accountStatementLabel = new JLabel("Account statement");
//		accountStatementLabel.setFont(messageFont);
//		accountStatementPanel.add(accountStatementLabel);
//
//		accountNameField = new JTextField("Account Name: ");
//		accountNameField.setFont(messageFont);
//		accountNameField.setEditable(false);
//		accountStatementPanel.add(accountNameField);
//
//		cardNumberField = new JTextField("Card Number: ");
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
		buttonPanel.setLayout(new GridLayout(3,1));
		add(buttonPanel);

		viewAllButton = new JButton("View all");
		viewAllButton.setFont(messageFont);
		viewAllButton.addActionListener(handler);
		buttonPanel.add(viewAllButton);

		changeCardLimitButton = new JButton("Change Card Limit");
		changeCardLimitButton.setFont(messageFont);
		changeCardLimitButton.addActionListener(handler);
		buttonPanel.add(changeCardLimitButton);

		backButton = new JButton("Back");
		backButton.setFont(messageFont);
		backButton.addActionListener(handler);
		buttonPanel.add(backButton);
	}

	private class actionHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == viewAllButton) {
				// admin sees the user's info
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
			}
			else if(event.getSource() == changeCardLimitButton) {
				// Admin changes the user's card limits
				sp.setVisible(false);
			//	paymentPanel.setVisible(false);
				buttonPanel.setVisible(false);
				changeCardLimit();
			}else if(event.getSource() == backButton) {
				// Goes back to afterLoginAsAdmin from the view card holder screen
				setVisible(false);
				LoginScreen loginScreen = new LoginScreen(visa);
				loginScreen.setSize(700, 600);
				loginScreen.setLocation(600, 250);
				loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				loginScreen.setLayout(null);
				loginScreen.setVisible(true);
				loginScreen.afterLoginAsAdminFromCardHolderScreen();
			}else if(event.getSource() == setCardLimitFinishButton) {
				// Finish setting the card limit of user as the admin
				if(cardNumberField.getText().equals("")){

				}
				else
				{
					String card = cardNumberField.getText();
					double creditLimit = Double.parseDouble(setCardLimitField.getText());

					int cardNumber = Integer.parseInt(card);
				
					for(int i = 0; i<visa.getUserCreditCards().size(); i++)
					{
						if(visa.getUserCreditCards().get(i).getAccountNumber() == cardNumber)
						{
							visa.updateCreditLimit(cardNumber, creditLimit);
							visa.getUserCreditCards().get(i).setCreditLimit(creditLimit);
							setVisible(false);
							LoginScreen loginScreen = new LoginScreen(visa);
							loginScreen.setSize(700, 600);
							loginScreen.setLocation(600, 250);
							loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							loginScreen.setLayout(null);
							loginScreen.setVisible(true);
							loginScreen.afterLoginAsAdminFromCardHolderScreen();
							break;
						}
					}
				}
			}else if(event.getSource() == setCardLimitBackButton) {
				// Back from the set card limit screen to the card holder screen as admin
				setVisible(false);
				LoginScreen loginScreen = new LoginScreen(visa);
				loginScreen.setSize(700, 600);
				loginScreen.setLocation(600, 250);
				loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				loginScreen.setLayout(null);
				loginScreen.setVisible(true);
				loginScreen.afterLoginAsAdminFromCardHolderScreen();
			}
		}
	}

	public void changeCardLimit() {

		cardLimitInputPanel = new JPanel();
		cardLimitInputPanel.setBounds(180, 150, 350, 80);
		cardLimitInputPanel.setLayout(new GridLayout(3,3));
		add(cardLimitInputPanel);
		
		cardNumberLabel = new JLabel("Card Number: ");
		cardNumberLabel.setFont(messageFont);
		cardLimitInputPanel.add(cardNumberLabel);

		cardNumberField = new JTextField();
		cardLimitInputPanel.add(cardNumberField);

		setCardLimitLabel = new JLabel("Set Card Limit: ");
		setCardLimitLabel.setFont(messageFont);
		cardLimitInputPanel.add(setCardLimitLabel);

		setCardLimitField = new JTextField();
		cardLimitInputPanel.add(setCardLimitField);

		setCardLimitFinishButton = new JButton("Finish");
		setCardLimitFinishButton.setFont(messageFont);
		setCardLimitFinishButton.addActionListener(handler);
		cardLimitInputPanel.add(setCardLimitFinishButton);

		setCardLimitBackButton = new JButton("Back");
		setCardLimitBackButton.setFont(messageFont);
		setCardLimitBackButton.addActionListener(handler);
		cardLimitInputPanel.add(setCardLimitBackButton);
	}
}
