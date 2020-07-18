package project2;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SomeVendorClass implements Comparable<SomeVendorClass> {

	private String storeName;
	private List<String> cart;
	public int itemsInCart = 0;
	private double total;
	private Connection conn;
	private Statement statement;
	private ResultSet resultSet;
	private NumberFormat nf= NumberFormat.getInstance();
	
	private Map<String, Double> items;
	
	public SomeVendorClass(String storeName) 
	{
		
		this.storeName = storeName;
		this.conn = DataBaseSetUp.getDbConnection();
		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    nf.setMaximumFractionDigits(2);
		cart = new ArrayList<String>();
		total = 0.0;
		items = new HashMap<String,Double> ();
		populateItemsFromDB();
		
		
	}

	
	
	private void populateItemsFromDB()
	{
		try {
			// Establish Connection Object

			resultSet = statement.executeQuery("Select item1, item1Price, item2, item2Price, item3, item3Price, item4, item4Price, item5, item5Price "
					+ "from VendorsTable where storeName = '"+storeName+"'");

			ResultSetMetaData rsmd;

			rsmd = (ResultSetMetaData) resultSet.getMetaData();


			int columns = rsmd.getColumnCount();
			
			List<String> itemName = new ArrayList<>();
			List<Double> itemPrice = new ArrayList<>();
			
			while(resultSet.next()){
				for(int i = 1; i<=columns; i++)
				{
					if(i% 2 ==0)
					{
						itemPrice.add(Double.parseDouble(resultSet.getString(i)));	
					}
					else
						itemName.add(resultSet.getString(i));

				}
			}
			
			for(int i = 0; i<itemName.size(); i++)
			{
				items.put(itemName.get(i), itemPrice.get(i));
			}
		}
		catch (SQLException e) {

			e.printStackTrace();
		}
}
			
	

	public void addToCart(String itemName) {
		
		for(String i: items.keySet())
		{
			if(itemName.equals(i))
			{
				cart.add(itemName);
				total +=items.get(i);	
				itemsInCart++;
			}
			
		}
	}

	public List<String> getCart() {
		return cart;
}
	
	public int getCartSize()
	{
		return cart.size();
	}

	public double getTotal() {
		// TODO Auto-generated method stub
		total = Double.parseDouble(nf.format(total).replaceAll(",", ""));
		return  total;
	}

	public Map<String, Double> getItems() {
		// TODO Auto-generated method stub
		return items;
	}

	public String getStoreName()
	{
		return storeName;
	}
	
	public void resetCart()
	{
		cart.clear();
		total = 0.0;
		itemsInCart = 0;
		
	}

//	public void makePurchase() 
//	{
//		
//		
//	}
	public String toString() {
		return "SomeVendorClass [storeName = " + storeName + "]";
	}
	@Override
	public int compareTo(SomeVendorClass vendor) {
		// TODO Auto-generated method stub
		return (this.storeName.compareTo(vendor.storeName));
	}
	
}
