
package in.ineuron.main;
import java.sql.*;
import java.util.Scanner;

import in.ineuron.util.JDBCUtil;
public class DeleteAppDynamicInput {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scn = null;
		
		try {
			
			connection = JDBCUtil.getJdbcConnnection();
			
			String sqlDeleteQuery = "delete from student where sid = ?";
			if(connection!=null) {
				pstmt = connection.prepareStatement(sqlDeleteQuery);
			}
			if(pstmt!=null) {
				
				scn = new Scanner(System.in);
				
				System.out.print("Enter the id of the student :: ");
				int sid = scn.nextInt();
				
				
				
				//use precompiled query to set the values
				pstmt.setInt(1,sid);
				
				System.out.println(sqlDeleteQuery);
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









