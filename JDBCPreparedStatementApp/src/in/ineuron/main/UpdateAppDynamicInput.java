package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.util.JDBCUtil;

public class UpdateAppDynamicInput {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scn = null;
		try {
			
			connection = JDBCUtil.getJdbcConnnection();
			
			String sqlUpdateQuery = "update student set saddress = ? where sid = ?";
			if(connection!=null) {
				pstmt = connection.prepareStatement(sqlUpdateQuery);
			}
			if(pstmt!=null) {
				//use precompiled query to set the values
				scn = new Scanner(System.in);
				
				System.out.print("Enter the id of the student :: ");
				int sid = scn.nextInt();

				System.out.print("Enter the saddress of the student :: ");
				String saddress = scn.next();

				// use precompiled query to set the values
				pstmt.setString(1, saddress);
				pstmt.setInt(2, sid);
				
				
				
				//Execute the query
				int rowCount = pstmt.executeUpdate();
				System.out.println("No of rows updated is :: "+rowCount); 
				
				
			}
			
		}
		catch(IOException ie){
			ie.printStackTrace();
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
