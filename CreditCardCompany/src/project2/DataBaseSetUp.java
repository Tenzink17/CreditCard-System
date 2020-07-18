package project2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DataBaseSetUp {
	

	static Connection conn;
	
	static String username;

	public static int cardNum;
	
	static List<Integer> listOfAccountNumbers = new ArrayList<Integer>();
	
	
	
	
	public static Connection getDbConnection()
	{
		String dbUrl = "";
		String dbUsername = "";
		String dbPassword = "";
		
		
		
		return getConnection(dbUrl,dbUsername,dbPassword);
	}

	private static Connection getConnection(String dbUrl2, String dbUsername2,
			String dbPassword2) {
		// TODO Auto-generated method stub
		try {
			conn = DriverManager.getConnection(dbUrl2,dbUsername2,dbPassword2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	

}
