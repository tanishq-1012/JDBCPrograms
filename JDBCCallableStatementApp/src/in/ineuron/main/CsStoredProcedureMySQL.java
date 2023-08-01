package in.ineuron.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
import java.io.IOException;
import java.sql.CallableStatement;


import in.ineuron.util.JDBCUtil;



public class CsStoredProcedureMySQL {
	private static final String storedProcedureCall = "{CALL P_GET_PRODUCT_DETAILS_BY_ID(?,?,?,?)}";
	public static void main(String[] args) {
		//Resource used
		Connection connection = null;
		CallableStatement cstmt = null;
		Scanner scanner = null;
		Integer id = null;
		
		try {
			connection = JDBCUtil.getJdbcConnection();
			if(connection != null) {
				cstmt = connection.prepareCall(storedProcedureCall);
				
				}
			scanner = new Scanner(System.in);
			if(scanner!=null) {
				System.out.println("Enter the product id :: ");
				id = scanner.nextInt();
			}
			
			//Setting up values
			if(cstmt != null) {
				cstmt.setInt(1, id);
			}
			
			//Setting the datatype of output values
			if(cstmt != null) {
				cstmt.registerOutParameter(2,Types.VARCHAR);
				cstmt.registerOutParameter(3,Types.INTEGER);
				cstmt.registerOutParameter(4,Types.INTEGER);
			}
			if(cstmt != null) {
				cstmt.execute();
			}
			
			//Retreive the result
			if(cstmt != null) {
				System.out.println("Product Name is :: "+cstmt.getString(2));
				System.out.println("Product cost is :: "+cstmt.getString(3));
				System.out.println("Product QTY is :: "+cstmt.getString(4));
			}
			
		
		}
		catch(SQLException|IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				JDBCUtil.cleanUp(connection, cstmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			scanner.close();
		}
		
		

	}

}




//Connection object created...
//Enter the product id :: 
//2
//Product Name is :: tissot
//Product cost is :: 35000
//Product QTY is :: 3
