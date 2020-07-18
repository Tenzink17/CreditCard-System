package project2;

import java.sql.Timestamp;

public class BillingStatement implements Comparable<BillingStatement> {
	
	private String username;
	
	private int accountNumber;
	
	private double amoundPaid;
	
	private Timestamp timeStamp; 
	
	private String paidBy;
	
	
	public BillingStatement(String username, int accountNumber,
			double amoundPaid, Timestamp timeStamp, String paidBy) {
		
		this.username = username;
		this.accountNumber = accountNumber;
		this.amoundPaid = amoundPaid;
		this.timeStamp = timeStamp;
		this.paidBy = paidBy;
	}


	public String getUsername() {
		return username;
	}


	public int getAccountNumber() {
		return accountNumber;
	}


	public double getAmoundPaid() {
		return amoundPaid;
	}





	public Timestamp getDateTime() {
		return timeStamp;
	}


	public String getPaidBy() {
		return paidBy;
	}

	@Override
	public int compareTo(BillingStatement o) {
		// TODO Auto-generated method stub
		return this.getDateTime().compareTo(o.getDateTime());
	}
	@Override
	public String toString() {
		return "BillingStatement [username=" + username + ", accountNumber="
				+ accountNumber + ", amoundPaid=" + amoundPaid + ", timeStamp="
				+ timeStamp + ", paidBy=" + paidBy + "]";
	}

	
	

}
