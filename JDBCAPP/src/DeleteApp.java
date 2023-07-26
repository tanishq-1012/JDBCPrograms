package in.ineuron.main;
import java.sql.*;
public class DeleteApp {

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
		String sqlDeleteQuery = "delete from student where sid = 2";
		int rowAffected = statement.executeUpdate(sqlDeleteQuery);
		System.out.println("No of rows affected is :: "+ rowAffected);
		
		
		//close the resource
		statement.close();
		connection.close();
		System.out.println("Closing the resources");
		

		
	}

}



//Connection object created...
//Statement object created...
//No of rows affected is :: 1
//Closing the resources
