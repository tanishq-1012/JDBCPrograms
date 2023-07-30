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

import org.apache.commons.io.IOUtils;

import in.ineuron.util.JDBCUtil;

public class ImageRetreivalApp3 {

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
					
					File file = new File("Cpoied2.jpg");
					FileOutputStream fos = new FileOutputStream(file);
					
					//Copying the data from input stream to fos 
					//In this we are using the external jar file.
					
					IOUtils.copy(is, fos);
					
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
//Enter the id :: 1
//ID	NAME	IMAGE
//1	Tanya	/Users/tanishqsharma/Desktop/ADJ/JDBCBlobOperationnApp/Cpoied2.jpg
//
