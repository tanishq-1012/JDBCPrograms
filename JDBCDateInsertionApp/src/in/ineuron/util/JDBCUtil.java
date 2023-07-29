package in.ineuron.util;
import java.io.*;
import java.sql.*;
import java.util.Properties;
public class JDBCUtil {
	
	private JDBCUtil() {}
	
	static {
		//Loading and register the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	public static Connection getJdbcConnection() throws SQLException, IOException{
		//Take the data from properties file
		FileInputStream fis = new FileInputStream("/Users/tanishqsharma/Desktop/ADJ/JDBCDateInsertionApp/src/in/ineuron/properties/application.properties");
		Properties properties = new Properties();
		properties.load(fis);
		
		
		//Establish the connection
//				String url = properties.getProperty("url");
//				String user = properties.getProperty("user");
//				
//				String password = properties.getProperty("password");
				
				Connection connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"),properties.getProperty("password"));
				System.out.println("Connection object created...");
				return connection;
	}
	
	public static void cleanUp(Connection connection, Statement statement, ResultSet resultset)throws SQLException {
		//Closing the resources
		if(connection != null) {
			connection.close();
		}
		if(statement != null) {
			statement.close();
		}
		if(resultset != null) {
			resultset.close();
		}
	}
}
