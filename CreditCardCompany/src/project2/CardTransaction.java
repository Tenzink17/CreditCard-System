package project2;

import java.sql.Timestamp;
import java.text.DecimalFormat;

public class CardTransaction implements Comparable<CardTransaction> {
	
	private int accountNumber;
	
	private String storeName;
	
	private Timestamp dateTimePurchased;
	
	private double total;
	
	private String description;
	
	private String username;

	private DecimalFormat df= new DecimalFormat("#.00");
	
	public CardTransaction(int accountNumber, String storeName,
			Timestamp dateTimePurchased, double total, String description,
			String username) {
		
		this.accountNumber = accountNumber;
		this.storeName = storeName;
		this.dateTimePurchased = dateTimePurchased;
		this.total = Double.parseDouble(df.format(total));
		this.description = description;
		this.username = username;
	}
	
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public String getStoreName() {
		return storeName;
	}

	public Timestamp getDateTimePurchased() {
		return dateTimePurchased;
	}

	public double getTotal() {
		return total;
	}

	public String getDescription() {
		return description;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public int compareTo(CardTransaction o) {
		// TODO Auto-generated method stub
		return this.getDateTimePurchased().compareTo(o.dateTimePurchased);
	}

	@Override
	public String toString() {
		return "CardTransaction [accountNumber=" + accountNumber
				+ ", storeName=" + storeName + ", dateTimePurchased="
				+ dateTimePurchased + ", total=" + total + ", description="
				+ description + ", username=" + username + "]";
	}
	
	
	
	

}
