package project2;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CreditCardCompany {

	private List<CreditCard> listOfAllCreditCards;
	private List<SomeVendorClass> listOfAllVendors;

	private List<BillingStatement> listOfAllBillingStatement;
	private List<CardTransaction> listOfAllCardTransaction;

	private List<String> userNameCheck;



	private Connection conn;
	private Statement statement;
	private ResultSet resultSet;
	private String adminPassword;
//	private List<CreditCard> userSearchedCreditCard;

	CreditCardCompany()
	{
		listOfAllCreditCards = new ArrayList<>();
		listOfAllVendors = new ArrayList<>(3);
		listOfAllBillingStatement = new ArrayList<>();
		listOfAllCardTransaction = new ArrayList<>();
		userNameCheck = new ArrayList<>();
		this.conn  = DataBaseSetUp.getDbConnection();
		connectToDBAndPrepareStatement();
		populateListOfallCreditCardFromDB();
		populateListOfallVendors();
		Collections.sort(listOfAllCreditCards);
		Collections.sort(listOfAllVendors);
		populateBillingList();
		populateCardTransaction();
		populateSpecificUserCardTransaction();
		populateSpecificUsersBillingPayement();
		populateFromLoginTable();
		Collections.sort(listOfAllBillingStatement);
		Collections.sort(listOfAllCardTransaction);
	}

	private void connectToDBAndPrepareStatement()

	{
		try {
			// Establish Connection Object
				statement = conn.createStatement();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

	}

	public void createAccount(String username, String password, String accountHolderName)
	{
		boolean exists = false;
		int passwordlength = password.length();
		if(passwordlength<6){
			System.out.println("password Not long enough");
		}
		try {
			resultSet = statement.executeQuery("Select username from LoginTable where username = '"+username+"'");

			while(resultSet.next())
			{
				exists = true;
				break;

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int accountNumber = 0; double creditLimit = 1000, amountDue = 0;

	if(!exists)
	{
		try {
			statement.executeUpdate("insert into LoginTable (username, password, fullname)"
					+ " values('"+username.toLowerCase() +"','"+ password.toLowerCase()+"','"+ accountHolderName.toLowerCase()+"')");

			statement.executeUpdate("insert into CreditCardsTable(username,accountHolderName,creditLimit,amountDue)"
					+ " values('"+username.toLowerCase()+"','"+accountHolderName.toLowerCase()+"',''+'"+ creditLimit+"','"+amountDue+"')");


			resultSet = statement.executeQuery("Select * from CreditCardsTable where username = '"+username.toLowerCase()+"'");



			System.out.println("New userName Created "+username.toLowerCase());


			while(resultSet.next())
			{
				accountNumber= resultSet.getInt("accountNumber");
			}



		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			listOfAllCreditCards.add(new CreditCard(accountNumber,username.toLowerCase(),password.toLowerCase(),accountHolderName.toLowerCase(),creditLimit,amountDue));
			userNameCheck.add(username.toLowerCase());
			Collections.sort(listOfAllCreditCards);
		}
	}
	else
	{
		boolean valid = false;
		for(int i = 0; i<listOfAllCreditCards.size(); i++)
		{
			if(listOfAllCreditCards.get(i).getUsername().equals(username.toLowerCase()) && listOfAllCreditCards.get(i).getPassword().equals(password.toLowerCase())
					&& listOfAllCreditCards.get(i).getAccountHolderName().equals(accountHolderName.toLowerCase()))
			{
				System.out.println(listOfAllCreditCards.get(i).getPassword());
					valid = true;
					break;

			}
		}

		if(valid){
		try {
			statement.executeUpdate("insert into CreditCardsTable(username,accountHolderName,creditLimit,amountDue)"
					+ " values('"+username.toLowerCase()+"','"+accountHolderName.toLowerCase()+"',''+'"+ creditLimit+"','"+amountDue+"')");



			resultSet = statement.executeQuery("Select accountNumber from CreditCardsTable where username = '"+username.toLowerCase()+"'");



			System.out.println("Created "+username.toLowerCase());


			while(resultSet.next())
			{
				accountNumber= resultSet.getInt("accountNumber");
			}



		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			listOfAllCreditCards.add(new CreditCard(accountNumber,username.toLowerCase(),password.toLowerCase(),accountHolderName.toLowerCase(),creditLimit,amountDue));
			Collections.sort(listOfAllCreditCards);
			System.out.println(username+" you have another new card");
			valid = true;
		}
	}
		else
			System.out.println("Your username or password or fullname and failure to link credit card");

}


}

	public void deleteAccount(int accountNumber, String username)
	{
		int check = 0;
		for(int i = 0; i<userNameCheck.size(); i++)
		{
			if(userNameCheck.get(i).equals(username.toLowerCase()))
			{
				check++;
			}
		}

		try {
			if(check >1){
				statement.executeUpdate("delete from CreditCardsTable where accountNumber = '"+accountNumber+"'");


				for(int i = 0; i<listOfAllCreditCards.size(); i++)
				{
					if(listOfAllCreditCards.get(i).getAccountNumber()==accountNumber)
					{
						listOfAllCreditCards.remove(i);
					}
				}
			}
			else if(check ==1)
			{
			statement.executeUpdate("delete from CreditCardsTable where username = '"+username.toLowerCase()+"'");
			statement.executeUpdate("delete from LoginTable where username = '"+username.toLowerCase()+"'");

			for(int i = 0; i<listOfAllCreditCards.size(); i++)
			{
				if(listOfAllCreditCards.get(i).getAccountNumber()==accountNumber && listOfAllCreditCards.get(i).getUsername().equals(username.toLowerCase()))
				{
					listOfAllCreditCards.remove(i);
					break;
				}
			}

			for(int  i = 0; i<userNameCheck.size();i++)
			{
				if(userNameCheck.get(i).equals(username.toLowerCase()))
				{
					userNameCheck.remove(i);
				}
			}
			}

			deleteBillingStatement(accountNumber);
			deleteCardTransaction(accountNumber);
		} catch (SQLException e) {
			e.printStackTrace();

		}


	}


	public void updateCreditLimit(int accountNumber, double newCreditLimit)
	{
		boolean success = false;


		for(int  i = 0; i<listOfAllCreditCards.size(); i++)
		{
			if(listOfAllCreditCards.get(i).getAccountNumber()==accountNumber)
			{

				success = true;
				System.out.println("found it");
				break;
			}

		}

		if(success){
		try {
			statement.executeUpdate("update CreditCardsTable set creditLimit = '"+newCreditLimit+"' where accountNumber = '"+accountNumber+"'");

			for(int  i = 0; i<listOfAllCreditCards.size(); i++)
			{
				if(listOfAllCreditCards.get(i).getAccountNumber()==accountNumber)
				{
					listOfAllCreditCards.get(i).setCreditLimit(newCreditLimit);
					break;

				}
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
				System.out.println("Updated to: "+accountNumber);

		}
		}
		else
			System.out.println("CC number not avaliable");
	}

	public void updateAmountDue(int accountNumber, double total)
	{
		double newAmountDue = 0, oldAmountDue;
		for(int i = 0; i<listOfAllCreditCards.size();i++)
		{
			if(listOfAllCreditCards.get(i).getAccountNumber()== accountNumber)
			{
				oldAmountDue = listOfAllCreditCards.get(i).getAmountDue();
				newAmountDue = oldAmountDue +total;
			}
		}

		boolean updated = false;

			for(int  i = 0; i<listOfAllCreditCards.size(); i++)
			{
				if(listOfAllCreditCards.get(i).getAccountNumber()== accountNumber)
				{
					listOfAllCreditCards.get(i).setAmountDue(newAmountDue);
					updated = true;
					break;
				}
			}

			if(updated)
			{
				try {
					statement.executeUpdate("update CreditCardsTable set amountDue = '"+newAmountDue+"' where accountNumber = '"+accountNumber+"'");
				System.out.println("Updated to: "+accountNumber);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}

	public void decraseAmountDue(int accountNumber, double total){

		double newAmountDue, creditavaliable, oldAmountDue;
		String username;
		for(int i = 0; i<listOfAllCreditCards.size();i++)
		{
			if(listOfAllCreditCards.get(i).getAccountNumber()== accountNumber)
			{
				username = listOfAllCreditCards.get(i).getUsername();
				creditavaliable = listOfAllCreditCards.get(i).getCreditAvaliable();
				oldAmountDue = listOfAllCreditCards.get(i).getAmountDue();

					if(oldAmountDue< total){
						createBillingStatement(username, accountNumber, total, "");

						creditavaliable = creditavaliable + total;
						updateCreditLimit(accountNumber, creditavaliable);
						try {
						statement.executeUpdate("update CreditCardsTable set creditAvaliable = '"+creditavaliable+"',amountDue = '"+0.0+"'  where accountNumber = '"+accountNumber+"'");
						listOfAllCreditCards.get(i).setAmountDue(0.0);
						listOfAllCreditCards.get(i).setCreditAvaliable(creditavaliable);
						System.out.println("Updated to: "+accountNumber);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
						else  // if amountDue is bigger than or equal to money
						{
							newAmountDue = oldAmountDue - total;
							creditavaliable = creditavaliable + total;
							createBillingStatement(username, accountNumber,total,"");
							updateAmountDue(accountNumber, creditavaliable);

					try {
						statement.executeUpdate("update CreditCardsTable set creditLimit = '"+creditavaliable+"',amountDue = '"+newAmountDue+"'  where accountNumber = '"+accountNumber+"'");
					listOfAllCreditCards.get(i).setAmountDue(newAmountDue);
					listOfAllCreditCards.get(i).setCreditLimit(creditavaliable);
				System.out.println("Updated to: "+accountNumber);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
					break;
			}

		}

	}

	public void updatePassword(String username, String oldpassword,String newPassword)
	{

		try {
			statement.executeUpdate("update LoginTable set password = '"+newPassword.toLowerCase()+"' where username = '"+username.toLowerCase()+ "'and password = '"+oldpassword.toLowerCase()+"'");
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private void populateListOfallCreditCardFromDB()
	{
		try {
			// Establish Connection Object


			resultSet = statement.executeQuery("Select * from CreditCardsTable, LoginTable where CreditCardsTable.username"
					+ "= LoginTable.username");


			int accountNumber;
			String userName, accountHolderName;
			double creditLimit, amountDue;
			String password;

			while(resultSet.next()){
					accountNumber = resultSet.getInt(1);
					userName = resultSet.getString(2);
					accountHolderName = resultSet.getString(3);
					creditLimit = resultSet.getDouble(4);
					amountDue = resultSet.getDouble(5);
					password = resultSet.getString(7);
					listOfAllCreditCards.add(new CreditCard(accountNumber,userName,password,
							accountHolderName, creditLimit,amountDue ));

			}

		}
		catch (SQLException e) {

			e.printStackTrace();
		}

	}


	private void populateFromLoginTable()
	{
		try {
			resultSet = statement.executeQuery("Select username from CreditCardsTable");
			String username;
			while(resultSet.next()){
				username = resultSet.getString(1);
				userNameCheck.add(username);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private void populateListOfallVendors()
	{
		try {
			// Establish Connection Object

			resultSet = statement.executeQuery("Select storeName from VendorsTable");


			String vendorName;
			while(resultSet.next()){
					vendorName = resultSet.getString(1);
					listOfAllVendors.add(new SomeVendorClass(vendorName));
			}

		}
		catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void populateBillingList()
	{
		try {
			// Establish Connection Object

			resultSet = statement.executeQuery("Select *"
					+ "from BillingTable order by dateTime desc");


			int accountNumber;
			String userName, paidBy;
			Timestamp timestamp;
			double amountPaid;

			while(resultSet.next()){
					userName = resultSet.getString(1);
					accountNumber = resultSet.getInt(2);
					amountPaid = resultSet.getDouble(3);
					timestamp = resultSet.getTimestamp(4);
					paidBy = resultSet.getString(5);
					listOfAllBillingStatement.add(new BillingStatement(userName,accountNumber,amountPaid,timestamp,paidBy ));
			}
		}
		catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void populateBillingList(String username, int accountnumber)
	{
		try {
			// Establish Connection Object

			listOfAllBillingStatement = new ArrayList<BillingStatement>();
			resultSet = statement.executeQuery("Select *"
					+ "from BillingTable where username = '" + username  + "' and accountNumber = '" + accountnumber + "' order by dateTime desc");


			int accountNumber;
			String userName, paidBy;
			Timestamp timestamp;
			double amountPaid;

			while(resultSet.next()){
					userName = resultSet.getString(1);
					accountNumber = resultSet.getInt(2);
					amountPaid = resultSet.getDouble(3);
					timestamp = resultSet.getTimestamp(4);
					paidBy = resultSet.getString(5);
					listOfAllBillingStatement.add(new BillingStatement(userName,accountNumber,amountPaid,timestamp,paidBy ));
			}
		}
		catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void populateLatestPayment(String username, int accountnumber)
	{
		try {
			// Establish Connection Object

			listOfAllBillingStatement = new ArrayList<BillingStatement>();
			resultSet = statement.executeQuery("Select *"
					+ "from BillingTable where username = '" + username  + "' and accountNumber = '" + accountnumber + "' order by dateTime desc limit 1");


			int accountNumber;
			String userName, paidBy;
			Timestamp timestamp;
			double amountPaid;

			while(resultSet.next()){
					userName = resultSet.getString(1);
					accountNumber = resultSet.getInt(2);
					amountPaid = resultSet.getDouble(3);
					timestamp = resultSet.getTimestamp(4);
					paidBy = resultSet.getString(5);
					listOfAllBillingStatement.add(new BillingStatement(userName,accountNumber,amountPaid,timestamp,paidBy ));
			}
		}
		catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public Timestamp getLatestPaymentDate(String username, int accountnumber)
	{
		Timestamp timestamp = null;
		try {
			// Establish Connection Object

			resultSet = statement.executeQuery("Select *"
					+ "from BillingTable where username = '" + username  + "' and accountNumber = '" + accountnumber + "' order by dateTime desc limit 1");


			while(resultSet.next()){
					timestamp = resultSet.getTimestamp(4);
			}
		}
		catch (SQLException e) {

			e.printStackTrace();
		}
		return timestamp;

	}

	private void populateCardTransaction()
	{
		try {
			// Establish Connection Object

			resultSet = statement.executeQuery("Select * "
					+ "from TransactionTable order by dateTimePurchased desc");


			int accountNumber;
			String storeName;
			Timestamp datePurchased;
			double total;
			String description, userName;

			while(resultSet.next()){
					accountNumber = resultSet.getInt(1);
					storeName = resultSet.getString(2);
					datePurchased = resultSet.getTimestamp(3);
					total = resultSet.getDouble(4);
					description = resultSet.getString(5);
					userName = resultSet.getString(6);
					listOfAllCardTransaction.add(new CardTransaction(accountNumber, storeName, datePurchased, total, description,userName));
			}
		}
		catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void populateCardTransaction(String username, int accountnumber)
	{
		try {
			// Establish Connection Object

			listOfAllCardTransaction = new ArrayList<CardTransaction>();
			resultSet = statement.executeQuery("Select * "
					+ "from TransactionTable where username = '" + username + "' and accountNumber = '" + accountnumber + "' order by dateTimePurchased desc");


			int accountNumber;
			String storeName;
			Timestamp datePurchased;
			double total;
			String description, userName;

			while(resultSet.next()){
					accountNumber = resultSet.getInt(1);
					storeName = resultSet.getString(2);
					datePurchased = resultSet.getTimestamp(3);
					total = resultSet.getDouble(4);
					description = resultSet.getString(5);
					userName = resultSet.getString(6);
					listOfAllCardTransaction.add(new CardTransaction(accountNumber, storeName, datePurchased, total, description,userName));
			}
		}
		catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void populateCardTransaction(String shopName)
	{
		try {
			// Establish Connection Object

			listOfAllCardTransaction = new ArrayList<CardTransaction>();
			resultSet = statement.executeQuery("Select * "
					+ "from TransactionTable where storeName = '" + shopName + "' order by dateTimePurchased desc");


			int accountNumber;
			String storeName;
			Timestamp datePurchased;
			double total;
			String description, userName;

			while(resultSet.next()){
					accountNumber = resultSet.getInt(1);
					storeName = resultSet.getString(2);
					datePurchased = resultSet.getTimestamp(3);
					total = resultSet.getDouble(4);
					description = resultSet.getString(5);
					userName = resultSet.getString(6);
					listOfAllCardTransaction.add(new CardTransaction(accountNumber, storeName, datePurchased, total, description,userName));
			}
		}
		catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void populateRecentPurchases(String username, int accountnumber, Timestamp dateTimePurchased)
	{
		try {
			// Establish Connection Object

			listOfAllCardTransaction = new ArrayList<CardTransaction>();
			resultSet = statement.executeQuery("Select * "
					+ "from TransactionTable where username = '" + username + "' and accountNumber = '" + accountnumber + "' and dateTimePurchased > '" + dateTimePurchased + "' order by dateTimePurchased desc");


			int accountNumber;
			String storeName;
			Timestamp datePurchased;
			double total;
			String description, userName;

			while(resultSet.next()){
					accountNumber = resultSet.getInt(1);
					storeName = resultSet.getString(2);
					datePurchased = resultSet.getTimestamp(3);
					total = resultSet.getDouble(4);
					description = resultSet.getString(5);
					userName = resultSet.getString(6);
					listOfAllCardTransaction.add(new CardTransaction(accountNumber, storeName, datePurchased, total, description,userName));
			}
		}
		catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void populateSpecificUserCardTransaction()
	{


		try {
			// Establish Connection Object

			resultSet = statement.executeQuery("Select *"
					+ "from TransactionTable");

			int accountNumber;
			Timestamp ts;
			String description, storeName;
			double total;

			while(resultSet.next()){
					accountNumber = resultSet.getInt(1);
					storeName = resultSet.getString(2);
					ts = resultSet.getTimestamp(3);
					total = resultSet.getDouble(4);
					description = resultSet.getString(5);



					for(int i = 0; i<listOfAllCreditCards.size(); i++)
					{
						if(listOfAllCreditCards.get(i).getAccountNumber()==accountNumber)
						{
							listOfAllCreditCards.get(i).getPurchaseList().add(ts+" "+ total +" "+storeName+" "+description);
						}

					}

			}

		}
		catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void populateSpecificUsersBillingPayement()
	{


		try {
			// Establish Connection Object

			resultSet = statement.executeQuery("Select *"
					+ "from BillingTable");

			int accountNumber;
			Timestamp ts;
			double amountPaid;
			while(resultSet.next()){

					accountNumber = resultSet.getInt(2);
					amountPaid = resultSet.getDouble(3);
					ts = resultSet.getTimestamp(4);


					for(int i = 0; i<listOfAllCreditCards.size(); i++)
					{
						if(listOfAllCreditCards.get(i).getAccountNumber()==accountNumber)
						{
							listOfAllCreditCards.get(i).getPaymentHistory().add(ts+" "+amountPaid+" ThankYou");

						}


					}

			}

		}
		catch (SQLException e) {

			e.printStackTrace();
		}

	}


//	public void viewAllAccounts()
//	{
//		try {
//			resultSet = statement.executeQuery("Select accountNumber, userName, accountHolderName, creditLimit, isActivated, amountDue from CreditCardTable");
//
//		ResultSetMetaData rsmd;
//
//			rsmd = (ResultSetMetaData) resultSet.getMetaData();
//
//
//		int columns = rsmd.getColumnCount();
//
//		while(resultSet.next()){
//		for(int i = 1; i<=columns; i++)
//		{
//
//			String columnValue = resultSet.getString(i);
//
//			System.out.print(rsmd.getColumnName(i)+": "+columnValue+", ");
//
//		}
//		System.out.println("");
//	}
//		}
//	 catch (SQLException e) {
//
//		e.printStackTrace();
//	}


//
//	public void viewAllVendors()
//	{
//		try {
//			resultSet = statement.executeQuery("Select * from VendorsTable");
//			ResultSetMetaData rsmd;
//			rsmd = (ResultSetMetaData) resultSet.getMetaData();
//
//
//			int columns = rsmd.getColumnCount();
//
//			while(resultSet.next()){
//				for(int i = 1; i<=columns; i++)
//				{
//
//					String columnValue = resultSet.getString(i);
//
//					System.out.print(rsmd.getColumnName(i)+": "+columnValue+", ");
//
//				}
//				System.out.println("");
//			}
//		}
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//		}
//	}

	public void createBillingStatement(String username, int accountNumber, double amountPaid, String paidBy )
	{
		if(amountPaid<=0)
		{
			// do nothing
		}
		else
		{long millis=System.currentTimeMillis();
		Date date = new Date(millis);

		long time = date.getTime();

		Timestamp ts = new Timestamp(time);


		boolean flag = false;
		for(int i = 0; i<listOfAllCreditCards.size(); i++)
		{
			if(listOfAllCreditCards.get(i).getAccountNumber()==accountNumber&& listOfAllCreditCards.get(i).getAmountDue()>0.0)
			{
				flag = true;
				listOfAllCreditCards.get(i).getPaymentHistory().add(ts+" "+amountPaid+" "+"ThankYou" );
			}


		}

		if(flag){
		try {
			statement.executeUpdate("insert into BillingTable (userName, accountNumber, amountPaid, dateTime, paidBy)"
					+ " values('"+username.toLowerCase() +"','"+accountNumber+"','"+amountPaid+"','"+ts+"','"+paidBy.toLowerCase()+"')");


			System.out.println("Created billing");



			listOfAllBillingStatement.add(new BillingStatement(username.toLowerCase(),accountNumber,amountPaid,ts,paidBy.toLowerCase()));


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		}

}

	private void deleteBillingStatement(int accountNumber)
	{

		try {
			statement.executeUpdate("delete from BillingTable where accountNumber = '"+accountNumber+"'");
			for(int i = 0; i<listOfAllBillingStatement.size(); i++)
			{
				if(listOfAllBillingStatement.get(i).getAccountNumber()==accountNumber)
				{
					listOfAllBillingStatement.remove(i);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public void createCardTransaction(int accountNumber, String storeName, double total, String description, String username)
	{
		long millis=System.currentTimeMillis();
		Date date = new Date(millis);

		long time = date.getTime();

		Timestamp ts = new Timestamp(time);


		boolean flag = false;
		for(int i = 0; i<listOfAllCreditCards.size(); i++)
		{
			if(listOfAllCreditCards.get(i).getAccountNumber()==accountNumber && listOfAllCreditCards.get(i).getCreditLimit()>total)
			{
				flag = true;
				listOfAllCreditCards.get(i).getPurchaseList().add(ts+"  "+total+"  "+description);
			}


		}

		if(flag){
		try {
			statement.executeUpdate("insert into TransactionTable (accountNumber, storeName, dateTimePurchased, total, description, username)"
					+ " values('"+accountNumber +"','"+storeName+"','"+ts+"','"+total+"','"+description+"','"+username.toLowerCase()+"')");


			System.out.println("Created Transaction..");



			listOfAllCardTransaction.add(new CardTransaction(accountNumber, storeName, ts, total, description, username.toLowerCase()));


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
	private void deleteCardTransaction(int accountNumber)
	{
		try {
			statement.executeUpdate("delete from TransactionTable where accountNumber = '"+accountNumber+"'");
			for(int i = 0; i<listOfAllCardTransaction.size(); i++)
			{
				if(listOfAllCardTransaction.get(i).getAccountNumber()==accountNumber)
				{
					listOfAllCardTransaction.remove(i);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public List<CreditCard> getUserCreditCards()
	{
		return listOfAllCreditCards;

	}

	public List<SomeVendorClass> getListOfAllVendors() {

		return listOfAllVendors;
	}

	private void setAdminPassword()
	{
		try {
			resultSet = statement.executeQuery("Select Password from adminTable");
			resultSet.next();
			adminPassword = resultSet.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getAdminPassword()
	{
		setAdminPassword();
		return adminPassword;
	}

	public List<String> checkUserNames()
	{
		return userNameCheck;
	}

	public List<BillingStatement> getListOfAllBillingStatement() {
		return listOfAllBillingStatement;
	}


	public List<CardTransaction> getListOfAllCardTransaction() {
		return listOfAllCardTransaction;
	}




}




