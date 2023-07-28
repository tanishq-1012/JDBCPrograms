package in.ineuron.main;
import java.sql.*;
import java.util.Scanner;

import in.ineuron.util.JDBCUtil;
public class InsertAppDynamicInput {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scn = null;
		
		try {
			
			connection = JDBCUtil.getJdbcConnnection();
			
			String sqlInsertQuery = "insert into student(`sname`,`sage`,`saddress`,`sgender`)values(?,?,?,?)";
			if(connection!=null) {
				pstmt = connection.prepareStatement(sqlInsertQuery);
			}
			if(pstmt!=null) {
				
				scn = new Scanner(System.in);
				
				System.out.print("Enter the name of the student :: ");
				String sname = scn.next();
				
				System.out.print("Enter the age of the student :: ");
				int sage = scn.nextInt();
				
				System.out.print("Enter the address of the student :: ");
				String address = scn.next();
				
				System.out.print("Enter the gender of the student :: ");
				String gender = scn.next();
				
				
				//use precompiled query to set the values
				pstmt.setString(1, sname);
				pstmt.setInt(2, sage);
				pstmt.setString(3, address);
				pstmt.setString(4,gender);
				
				
				System.out.println(sqlInsertQuery);
				//Execute the query
				int rowCount = pstmt.executeUpdate();
				System.out.println("No of rows updated is :: "+rowCount); 
				
				
			}
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				JDBCUtil.cleanUp(connection, pstmt, null);
				scn.close();
				System.out.println("Closing the resources...");
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
		}
	}

}




//Connection object created...
//Enter the name of the student :: Neha
//Enter the age of the student :: 27
//Enter the address of the student :: KP
//Enter the gender of the student :: F
//insert into student(`sname`,`sage`,`saddress`,`sgender`)values(?,?,?,?)
//No of rows updated is :: 1
//Closing the resources...