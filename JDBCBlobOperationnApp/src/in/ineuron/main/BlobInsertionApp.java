package in.ineuron.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.util.JDBCUtil;

public class BlobInsertionApp {

	public static void main(String[] args) {
		
		//Resource used
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;
		
		//Variables used
		String name = null;
		String imageLoc = null;
		
		try {
			connection = JDBCUtil.getJdbcConnection();
			
			String sqlInsertQuery = "insert into person(`name`,`image`)values(?,?)";
			if(connection!=null) {
				pstmt = connection.prepareStatement(sqlInsertQuery);
			}
			if(pstmt!=null) {
				scanner = new Scanner(System.in);
				
				//collecting the inputs
				if(scanner!=null) {
					System.out.print("Enter the name");
					name = scanner.next();
					
					System.out.print("Enter the image location");
					imageLoc = scanner.next();
				}
				//Set the input values to query
				pstmt.setString(1, name);
				pstmt.setBinaryStream(2, new FileInputStream(new File(imageLoc)));
				
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
