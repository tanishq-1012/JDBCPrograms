package in.ineuron.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertApp {

	public static void main(String[] args) throws SQLException {
		//Establish the connection
				String url = "jdbc:mysql:///Student";
				String user = "root";
				String password = "Root@12345";
				
				Connection connection = DriverManager.getConnection(url,user,password);
				System.out.println("Connection object created...");
				
				
				//Create statement object and send the query
				Statement statement = connection.createStatement();
				System.out.println("Statement object created...");
				
				
				//Execute the query and process the resultset
				String sqlInsertQuery = "insert into student(`sname`,`sage`,`saddress`)values('Hardik',32,'GT') ";
				int rowAffected = statement.executeUpdate(sqlInsertQuery);
				System.out.println("No of rows affected is :: "+ rowAffected);
				
				
				//close the resource
				statement.close();
				connection.close();
				System.out.println("Closing the resources");
				
	}

}
