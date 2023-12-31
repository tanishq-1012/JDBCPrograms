package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.util.JDBCUtil;

public class BatchUpdateUsingPreparedStatement {

	public static void main(String[] args) {
		
		//Resource used
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;
		
		try {
			connection = JDBCUtil.getJdbcConnection();
			
			String sqlInsertQuery = "insert into employees(`name`,`age`,`address`)values(?,?,?)";
			if(connection != null) {
				pstmt = connection.prepareStatement(sqlInsertQuery);
			}
				if(pstmt != null) {
					scanner = new Scanner(System.in);
					
					while(true) {
						System.out.print("Enter the name :: ");
						String name = scanner.next();
						
						System.out.print("Enter the age :: ");
						Integer age = scanner.nextInt();
						
						System.out.print("Enter the address :: ");
						String address = scanner.next();
						
						pstmt.setString(1, name);
						pstmt.setInt(2, age);
						pstmt.setString(3, address);
						
						//Query added to batch file
						pstmt.addBatch();
						
						System.out.print("Do you want to insert one more record[YES/NO]::  ");
						String option = scanner.next();
						
						if(option.equalsIgnoreCase("no")) {
							break;
						}
					}
					pstmt.executeBatch();
					System.out.println("Records inserted Successfully");
					
				}
		
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				JDBCUtil.cleanUp(connection, pstmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			scanner.close();
	   }

	}

}







// Connection object created...
// Enter the name :: Tanishq
// Enter the age :: 24
// Enter the address :: abc
// Do you want to insert one more record[YES/NO]::  yes
// Enter the name :: Aniket
// Enter the age :: 23
// Enter the address :: def
// Do you want to insert one more record[YES/NO]::  yes
// Enter the name :: Aman
// Enter the age :: 24
// Enter the address :: ghi
// Do you want to insert one more record[YES/NO]::  yes
// Enter the name :: Kritagya
// Enter the age :: 24
// Enter the address :: jkl
// Do you want to insert one more record[YES/NO]::  yes
// Enter the name :: Manish
// Enter the age :: 25
// Enter the address :: mno
// Do you want to insert one more record[YES/NO]::  no
// Records inserted Successfully

