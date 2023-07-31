package in.inuron.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.util.JDBCUtil;

public class ClobInsertionApp {

	public static void main(String[] args) {
		//Resource used
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;
		
		//Variables used
		String name = null;
		String pdfLocation = null;
		
		try {
			connection = JDBCUtil.getJdbcConnection();
			
			String sqlInsertQuery = "insert into candidate(`name`,`history`)values(?,?)";
			if(connection!=null) {
				pstmt = connection.prepareStatement(sqlInsertQuery);
			}
			if(pstmt!=null) {
				scanner = new Scanner(System.in);
				
				//collecting the inputs
				if(scanner!=null) {
					System.out.print("Enter the name :: ");
					name = scanner.next();
					
					System.out.print("Enter the pdf location :: ");
					pdfLocation = scanner.next();
				}
				//Set the input values to query
				pstmt.setString(1, name);
				pstmt.setCharacterStream(2, new FileReader(new File(pdfLocation)));
				
				//Execute the query
				int rowAffected = pstmt.executeUpdate();
				System.out.println("No of rows inserted is :: "+ rowAffected);
			}
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
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







//Connection object created...
//Enter the name :: DEL
//Enter the pdf location :: /Users/tanishqsharma/Desktop/ADJ/JDBCClobOperations/HisDelhi.txt
//No of rows inserted is :: 1


