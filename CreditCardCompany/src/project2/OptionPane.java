package project2;

import java.awt.Dimension;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class OptionPane {
	private String optionPane;
	private String title;
	private JEditorPane ep;
	private JScrollPane sp;

	private int accountNumber;
	private String username;
	private String password;
	private String accountHolderName;
	private double creditLimit;
	private double creditAvaliable;
	private double amountDue;
	NumberFormat currency;
	CreditCard creditCard;
	private CreditCardCompany visa;

	// optionpane constructor
	public OptionPane() {
		optionPane = "";
		title = "";
		ep = new JEditorPane();
		sp = new JScrollPane();
		accountNumber = 0;
		username = "";
		password = "";
		accountHolderName = "";
		creditLimit = 0.0;
		amountDue = 0.0;
		currency = NumberFormat.getCurrencyInstance();
		creditCard = new CreditCard(0, "", "", "", 0.0, 0.0);
		visa = new CreditCardCompany();
	}

	// optionpane constructor
	public OptionPane(int accountNumber, String username, String password, String accountHolderName, double creditLimit, double amountDue, CreditCardCompany visa) {
		this.optionPane = "";
		this.title = "";
		this.accountNumber = accountNumber;
		this.username = username;
		this.password = password;
		this.accountHolderName = accountHolderName;
		this.creditLimit = creditLimit;
		this.creditAvaliable = creditLimit;
		this.amountDue = amountDue;
		currency = NumberFormat.getCurrencyInstance();
		creditCard = new CreditCard(accountNumber, username, password, accountHolderName, creditLimit, amountDue);
		this.visa = visa;
	}

	// setters
	public void setOptionPane(String optionPane) {
		this.optionPane = optionPane;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}
	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}

	// getters
	public String getOptionPane() {
		return this.optionPane;
	}
	public String getTitle() {
		return this.title;
	}
	public int getAccountNumber() {
		return this.accountNumber;
	}
	public String getUsername() {
		return this.username;
	}
	public String getAccountHolderName() {
		return this.accountHolderName;
	}
	public double getCreditLimit() {
		return this.creditLimit;
	}
	public double getAmountDue() {
		return this.amountDue;
	}
	public Timestamp getLatestPaymentDate(String username, int accountNumber) {
		return visa.getLatestPaymentDate(username, accountNumber);
	}
	// getaccountstatement
	public String getAccountStatement(String username, int accountNumber) {
		title = "Card Info";
		optionPane = "";

		// get card info
		optionPane += "<html><body><table>"
				+ "<tr><th align = 'left'><u>Account Statement</u></th></tr>"
				+ "<tr><th align = 'left'>Account name:</th>" + this.accountHolderName + "</tr>"
				+ "<tr><th align = 'left'>Card number:</th>" + this.accountNumber + "</tr>"
				+ "<tr><th align = 'left'>Credit limit:</th>" + currency.format(this.creditLimit) + "</tr>"
				+ "<tr><th align = 'left'>Credit limit:</th>" + currency.format(this.creditAvaliable) + "</tr>"
				+ "<tr><th align = 'left'>Amount due:</th>" + currency.format(this.amountDue) + "</tr>";

		// get recent purchases
		Timestamp dateTimePurchased = getLatestPaymentDate(username, accountNumber);
		visa.populateRecentPurchases(username, accountNumber, dateTimePurchased);
		optionPane += "<tr style='border-bottom: 1px solid #000; width='100%;'>"
				+ "<tr><th align = 'left'>Recent Purchases:</th></tr>"
				+ "<tr><th align = 'left'>date</th>"
				+ "<th align = 'left'>amount</th>"
				+ "<th align = 'left'>description</th></tr>";
		List<CardTransaction> listOfAllCardTransaction = visa.getListOfAllCardTransaction();
		CardTransaction ct;
		for (CardTransaction cardTransaction : listOfAllCardTransaction) {
			ct = cardTransaction;
			optionPane += "<tr><td align = 'left'>" + ct.getDateTimePurchased() + "</td>"
					+ "<td align = 'left'>" + currency.format(ct.getTotal()) + "</td>"
					+ "<td align = 'left'>" + ct.getDescription() + "</td></tr>";
		}

		// get latest payment
		visa.populateLatestPayment(username, accountNumber);
		optionPane += "<tr style='border-bottom: 1px solid #000; width='100%;'>"
				+ "<tr><th align = 'left'>Payments:</th></tr>"
				+ "<tr><th align = 'left'>date</th>"
				+ "<th align = 'left'>amount</th>"
				+ "<th align = 'left'>description</th></tr>";
		List<BillingStatement> listOfAllBillingStatement = visa.getListOfAllBillingStatement();
		BillingStatement bs;
		for (BillingStatement billingStatement : listOfAllBillingStatement) {
			bs = billingStatement;
			optionPane += "<tr><td align = 'left'>" + bs.getDateTime() + "</td>"
					+ "<td align = 'left'>" + currency.format(bs.getAmoundPaid()) + "</td>"
					+ "<td align = 'left'>" + "thank you" + "</td></tr>";
		}
		optionPane += "</table></body></html>";
		return optionPane;
	}

	// viewitemlist
	public void viewItemList(SomeVendorClass selectedShop) {
		title = selectedShop.getStoreName() + " Item List";
		optionPane = "";
		optionPane += "<html><body><table border = '1'>"
				+ "<tr><th align = 'left'><u>" + selectedShop.getStoreName() + " Item List</u></th></tr>"
				+ "<tr><th align = 'left'>item</th>"
				+ "<th align = 'left'>item size: " + selectedShop.getCartSize() + "</th></tr>";
		List<String> cart = selectedShop.getCart();
		for (String item : cart) {
			optionPane += "<tr><td align = 'left'>" + item + "</td></tr>";
		}
		optionPane += "</table></body></html>";
		displayOutput(optionPane, title);
	}

	// viewrecentpurchase
	public void viewRecentPurchases(String username, int accountNumber) {
		Timestamp dateTimePurchased = getLatestPaymentDate(username, accountNumber);
		title = "Recent Purchases";
		optionPane = "";
		visa.populateRecentPurchases(username, accountNumber, dateTimePurchased);
		optionPane += "<html><body><table>"
				+ "<tr><th align = 'left'>Recent Purchases:</th></tr>"
				+ "<tr><th align = 'left'>date</th>"
				+ "<th align = 'left'>amount</th>"
				+ "<th align = 'left'>description</th></tr>";
		List<CardTransaction> listOfAllCardTransaction = visa.getListOfAllCardTransaction();
		CardTransaction ct;
		for (CardTransaction cardTransaction : listOfAllCardTransaction) {
			ct = cardTransaction;
			optionPane += "<tr><td align = 'left'>" + ct.getDateTimePurchased() + "</td>"
					+ "<td align = 'left'>" + currency.format(ct.getTotal()) + "</td>"
					+ "<td align = 'left'>" + ct.getDescription() + "</td></tr>";
		}
		optionPane += "</table></body></html>";
		displayOutput(optionPane, title);
	}

	// viewlatestpayment
	public void viewLatestPayment(String username, int accountNumber) {
		title = "Latest Payment";
		optionPane = "";
		visa.populateLatestPayment(username, accountNumber);
		optionPane += "<html><body><table>"
				+ "<tr><th align = 'left'>Payments:</th></tr>"
				+ "<tr><th align = 'left'>date</th>"
				+ "<th align = 'left'>amount</th>"
				+ "<th align = 'left'>description</th></tr>";
		List<BillingStatement> listOfAllBillingStatement = visa.getListOfAllBillingStatement();
		BillingStatement bs;
		for (BillingStatement billingStatement : listOfAllBillingStatement) {
			bs = billingStatement;
			optionPane += "<tr><td align = 'left'>" + bs.getDateTime() + "</td>"
					+ "<td align = 'left'>" + currency.format(bs.getAmoundPaid()) + "</td>"
					+ "<td align = 'left'>" + "thank you" + "</td></tr>";
		}
		optionPane += "</table></body></html>";
		displayOutput(optionPane, title);
	}

	// viewall
	public void viewAll(String username, int accountNumber) {
		title = "Card Info";
		optionPane = "";

		// get card info
		optionPane += "<html><body><table>"
				+ "<tr><th align = 'left'><u>Account Statement</u></th></tr>"
				+ "<tr><th align = 'left'>Account name:</th>" + this.accountHolderName + "</tr>"
				+ "<tr><th align = 'left'>Card number:</th>" + this.accountNumber + "</tr>"
				+ "<tr><th align = 'left'>Credit limit:</th>" + currency.format(this.creditLimit) + "</tr>"
				+ "<tr><th align = 'left'>Credit limit:</th>" + currency.format(this.creditAvaliable) + "</tr>"
				+ "<tr><th align = 'left'>Amount due:</th>" + currency.format(this.amountDue) + "</tr>";

		// get all purchases
		visa.populateCardTransaction(username, accountNumber);
		optionPane += "<tr style='border-bottom: 1px solid #000; width='100%;'>"
				+ "<tr><th align = 'left'>All Purchases:</th></tr>"
				+ "<tr><th align = 'left'>date</th>"
				+ "<th align = 'left'>amount</th>"
				+ "<th align = 'left'>description</th></tr>";
		List<CardTransaction> listOfAllCardTransaction = visa.getListOfAllCardTransaction();
		CardTransaction ct;
		for (CardTransaction cardTransaction : listOfAllCardTransaction) {
			ct = cardTransaction;
			optionPane += "<tr><td align = 'left'>" + ct.getDateTimePurchased() + "</td>"
					+ "<td align = 'left'>" + currency.format(ct.getTotal()) + "</td>"
					+ "<td align = 'left'>" + ct.getDescription() + "</td></tr>";
		}

		// get all payments
		visa.populateBillingList(username, accountNumber);
		optionPane += "<tr style='border-bottom: 1px solid #000; width='100%;'>"
				+ "<tr><th align = 'left'>All Payments:</th></tr>"
				+ "<tr><th align = 'left'>date</th>"
				+ "<th align = 'left'>amount</th>"
				+ "<th align = 'left'>description</th></tr>";
		List<BillingStatement> listOfAllBillingStatement = visa.getListOfAllBillingStatement();
		BillingStatement bs;
		for (BillingStatement billingStatement : listOfAllBillingStatement) {
			bs = billingStatement;
			optionPane += "<tr><td align = 'left'>" + bs.getDateTime() + "</td>"
					+ "<td align = 'left'>" + currency.format(bs.getAmoundPaid()) + "</td>"
					+ "<td align = 'left'>" + "thank you" + "</td></tr>";
		}
		optionPane += "</table></body></html>";
		displayOutput(optionPane, title);
	}

	// viewshopinfo
	public void viewShopInfo(String storeName) {
		visa.populateCardTransaction(storeName);
		title = "Admin View " + storeName + " Shop Info";
		optionPane = "";
		optionPane += "<html><body><table border = '1'>"
				+ "<tr><th align = 'left'><u>" + storeName + " Shop Info</u></th></tr>"
				+ "<tr><th align = 'left'>Account Number</th>"
				+ "<th align = 'left'>Store Name</th>"
				+ "<th align = 'left'>Date Time Purchased</th>"
				+ "<th align = 'left'>Description</th>"
				+ "<th align = 'left'>Username</th>"
				+ "<th align = 'left'>Total</th></tr>";

		List<CardTransaction> listOfAllCardTransaction = visa.getListOfAllCardTransaction();
		CardTransaction ct;
		for (CardTransaction cardTransaction : listOfAllCardTransaction) {
			ct = cardTransaction;
			optionPane += "<tr><td align = 'left'>" + ct.getAccountNumber() + "</td>"
					+ "<td align = 'left'>" + ct.getStoreName() + "</td>"
					+ "<td align = 'left'>" + ct.getDateTimePurchased() + "</td>"
					+ "<td align = 'left'>" + ct.getDescription() + "</td>"
					+ "<td align = 'left'>" + ct.getUsername() + "</td>"
					+ "<td align = 'left'>" + currency.format(ct.getTotal()) + "</td></tr>";
		}
		optionPane += "</table></body></html>";
		displayOutput(optionPane, title);
	}

	// displayoutput
	public void displayOutput(String optionPane, String title) {
		ep = new JEditorPane ("text/html", optionPane);
		ep.setEditable(false);
		ep.setCaretPosition(0);
		sp = new JScrollPane(ep);
		sp.setPreferredSize(new Dimension(600, 600));
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JOptionPane.showMessageDialog(null, sp, title, JOptionPane.PLAIN_MESSAGE);
	}
}