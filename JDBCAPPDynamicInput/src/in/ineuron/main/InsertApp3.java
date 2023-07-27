//Approach 3
package in.ineuron.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertApp3 {

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
		
		Scanner scn = new Scanner(System.in);
		
		System.out.print("Enter the name of the student :: ");
		String sname = scn.next();
		
		System.out.print("Enter the age of the student :: ");
		int sage = scn.nextInt();
		
		System.out.print("Enter the address of the student :: ");
		String address = scn.next();
		
		
		
		//Execute the query and process the resultset
	
		
		String sqlInsertQuery = String.format("insert into student(`sname`,`sage`,saddress)values('%s',%d,'%s')",sname,sage,address);
		System.out.println(sqlInsertQuery);
		
		int rowAffected = statement.executeUpdate(sqlInsertQuery);
		System.out.println("No of rows affected is :: "+ rowAffected);
		
		
		//close the resource
		statement.close();
		connection.close();
		scn.close();
		System.out.println("Closing the resources");

	}

}




//Connection object created...
//Statement object created...
//Enter the name of the student :: Salman
//Enter the age of the student :: 34
//Enter the address of the student :: RCB
//insert into student(`sname`,`sage`,saddress)values('Salman',34,'RCB')
//No of rows affected is :: 1
//Closing the resources
