package project2;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class CreditCard implements Comparable<CreditCard> {

	private String accountHolderName;
	private int accountNumber;
	private double creditLimit;
	private double creditAvaliable;
	public double getCreditAvaliable() {
		return creditAvaliable;
	}

	public void setCreditAvaliable(double creditAvaliable) {
		this.creditAvaliable = creditAvaliable;
	}


	private List<String> purchaseList;
	private String username, password;
	private boolean isActivated;
	private List<String> paymentHistory;
	private double amountDue;

	private DecimalFormat df= new DecimalFormat("#.00");

	CreditCard(int accountNumber, String username, String password, String accountHolderName, double creditLimit, double amountDue)
	{
		this.accountNumber = accountNumber;
		this.username = username;
		this.password = password;

		this.accountHolderName = accountHolderName;
		this.creditLimit = Double.parseDouble(df.format(creditLimit));
		this.creditAvaliable = Double.parseDouble(df.format(creditLimit));
		this.amountDue = Double.parseDouble(df.format(amountDue));

		if(this.password.length()>= 6)
		{
			isActivated = true;
		}
	purchaseList = new ArrayList<>();

		paymentHistory = new ArrayList<>();
	}

	public List<String> getPurchaseList()
	{
		return purchaseList;
	}

	public List<String> getPaymentHistory()
	{

		return paymentHistory;
	}

//	public void payBill(double amount)
//	{
//		if(isActivated){
//
//			if(amountDue>0 && amount >0 && creditLimit >0)
//			{
//				if(amountDue>= amount)
//				{
//					amountDue -= amount;
//					paymentHistory.add("You made a payment of $ "+amount+ ". You have to pay $" + amountDue);
//				}
//				else{
//					double change = amount - amountDue;
//					System.out.println("You overpaid you received back "+change);
//					paymentHistory.add("You made a payment of $ "+amountDue +". You have to pay $ "+0.0);
//					amountDue = 0.0;
//				}
//			}
//		}
//	}

//	public void addTranscation(String storeName, double total,  )
//	{
//
//
//
//	}

//	public void setUsername(String username)
//	{
//		this.username = username;
//
//	}

	public String getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public int getAccountNumber()
	{
		return accountNumber;
	}

	public String getAccountHolderName()
	{
		return accountHolderName;
	}

	public double getAmountDue()
	{
		return amountDue;
	}

	public void setAmountDue(double amountDue) {

		this.amountDue = amountDue;

	}

	public double getCreditLimit()
	{
		creditLimit = Double.parseDouble(df.format(creditLimit));
		 return creditLimit;
	}

	public String getFullName(){
		return accountHolderName;
	}

	public void setCreditLimit(double limit)
	{
		this.creditLimit = limit;
	}


	public String getRecentPurchases()
	{

		if(purchaseList.size()<=0)
		{

			return "No transaction available yet";
		}
		else
		{
			return getPurchaseList().get(getPurchaseList().size()-1);
		}



	}

	@Override
	public String toString() {
		return "CreditCard [accountHolderName = " + accountHolderName
				+ ", accountNumber = " + accountNumber + ", creditLimit = "
				+ creditLimit + ", username = " + username + ", password = "
				+ password + ", isActivated = " + isActivated + ", amountDue = "
				+ amountDue + "]";
	}


	@Override
	public int compareTo(CreditCard o) {

		return this.username.compareToIgnoreCase(o.username);
	}



}
