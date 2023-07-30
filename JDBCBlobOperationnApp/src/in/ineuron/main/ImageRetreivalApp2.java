package in.ineuron.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.util.JDBCUtil;

public class ImageRetreivalApp2 {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Scanner scanner = null;
		int id = 0;
		
		try {
			connection = JDBCUtil.getJdbcConnection();
			
			String sqlSelectQuery = "select id,name,image from person where id = ?";
			
			if(connection!=null) {
				pstmt = connection.prepareStatement(sqlSelectQuery);
			}
			if(pstmt!=null) {
				
				scanner = new Scanner(System.in);
				
				if(scanner!=null) {
			
				   System.out.print("Enter the id :: ");
				   id = scanner.nextInt();
				 
			    }
				 //Setting input values
				  pstmt.setInt(1, id);
				  
				  //Executing the query
				  resultSet = pstmt.executeQuery();
			}
			
			if(resultSet!=null) {
				
				//Processing the resultSet
				if(resultSet.next()) {
					System.out.println("ID\tNAME\tIMAGE");
					int pid = resultSet.getInt(1);
					String pname = resultSet.getString(2);
					InputStream is = resultSet.getBinaryStream(3);
					
					File file = new File("Cpoied1.jpg");
					FileOutputStream fos = new FileOutputStream(file);
					
					int i = is.read();
					
					// Reading line by line stream, performance is low by doing this.
					while(i != -1) {
						fos.write(i);
						i = is.read();
					}
					fos.close();
					System.out.println(pid + "\t" + pname + "\t" + file.getAbsolutePath());
				}
				else {
					System.out.println("Record is not available for the given id");
				}
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
				JDBCUtil.cleanUp(connection, pstmt, resultSet);
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			scanner.close();
		}
		
	}

}







//Connection object created...
//Enter the id :: 2
//ID	NAME	IMAGE
//2	Tanishq	/Users/tanishqsharma/Desktop/ADJ/JDBCBlobOperationnApp/Cpoied1.jpg

