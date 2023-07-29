package in.ineuron.main;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import in.ineuron.util.JDBCUtil;

public class DateInsertionApp {

	public static void main(String[] args) {
		
		//Resources used
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;
		
		//Variables used
		java.sql.Date sqlDob = null;
		java.sql.Date sqlDom = null;
		
		String name = null;
		String dob = null;
		String dom = null;
		int id = 0;
		
		try {
			connection = JDBCUtil.getJdbcConnection();
			
			String sqlInsertQuery = "insert into users (`name`,`dob`,`dom`) values (?,?,?)";
			if(connection != null) {
				pstmt = connection.prepareStatement(sqlInsertQuery);
			}
			
			
			if(pstmt != null) {
				scanner = new Scanner(System.in);
				
				//Collecting the inputs
				if(scanner != null) {
				
				
				System.out.print("Enter the username :: ");
				name = scanner.next();
				
				System.out.print("Enter the dob (dd-MM-yyyy) :: ");
				dob = scanner.next();
				
				System.out.print("Enter the dom (yyyy-MM-dd) :: ");
				dom = scanner.next();
			}
			
			if(dob != null) {
				// Conversion of string to sql date
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date uDate = sdf.parse(dob);
				
				long value = uDate.getTime();
				sqlDob = new java.sql.Date(value);
			}
			
			if(dom != null) {
				sqlDom = java.sql.Date.valueOf(dom);
			}
		}
			
			//Set the input values to query
			pstmt.setString(1, name);
			pstmt.setDate(2,sqlDob);
			pstmt.setDate(3,sqlDom);
			
			//Execute the query
			int rowAffected = pstmt.executeUpdate();
			System.out.println("No of rows inserted is :: "+ rowAffected);
			
			
		}
		catch(SQLException | IOException e)
		{
			e.printStackTrace();
		}
		catch(ParseException pe) {
			pe.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				JDBCUtil.cleanUp(connection, pstmt, null);
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			scanner.close();
		}
		
		
	}

}
