package in.ineuron.main;
import java.sql.*;
import java.util.Scanner;

import in.ineuron.util.JDBCUtil;
public class SelectAppDynamicInput {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scn = null;
		ResultSet resultset = null;
		int sid = 0;
		
		try {
			
			connection = JDBCUtil.getJdbcConnnection();
			
			String sqlSelectQuery = "select sid,sname,sage,saddress,sgender from student where sid = ?";
			if(connection!=null) {
				pstmt = connection.prepareStatement(sqlSelectQuery);
			}
			if(pstmt!=null) {
				
				scn = new Scanner(System.in);
				
				System.out.print("Enter the id of the student :: ");
				sid = scn.nextInt();
				
				
				
				//use precompiled query to set the values
				pstmt.setInt(1,sid);
				
				System.out.println(sqlSelectQuery);
				//Execute the query
				resultset = pstmt.executeQuery();
				
				
			}
			if(resultset != null) {
				
				if(resultset.next()) {
				System.out.println("SID\tSNAME\tSAGE\tSADDRESS\tSGENDER");
				System.out.println(resultset.getInt(1) + "\t" + resultset.getString(2) + "\t" + resultset.getInt(3) + "\t" + resultset.getString(4) + "\t" + resultset.getNString(5) );
				
				}
				else {
					System.out.println("Record not available for the given id :: "+ sid);
				}
				
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
//Enter the id of the student :: 5
//select sid,sname,sage,saddress,sgender from student where sid = ?

//SID	SNAME	SAGE	SADDRESS	SGENDER
// 5	 sky	 28	        MI	       M
//Closing the resources...





