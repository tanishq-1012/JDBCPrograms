package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import in.ineuron.util.JDBCUtil;

public class DateRetreivalApp {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Scanner scanner = null;
		int id;
		
		try {
			connection = JDBCUtil.getJdbcConnection();
			
			String  sqlSelectQuery = "select id,name,dob,dom from users where id = ?";
			
			if(connection!=null) {
				pstmt = connection.prepareStatement(sqlSelectQuery);
				
			}
			if(pstmt!=null) {
				//Setting input query
				scanner = new Scanner(System.in);
				System.out.print("Enter the id of user :: ");
			    id = scanner.nextInt();
				pstmt.setInt(1, id);
				
				//Executing the query
				resultSet = pstmt.executeQuery();
				
			}
			
			if(resultSet!=null) {
				
				//Processing the resultset
				if(resultSet.next()) {
					System.out.println("ID\tNAME\tDOB\t\tDOM");
					int uid = resultSet.getInt(1);
					String name = resultSet.getString(2);
					java.sql.Date udob = resultSet.getDate(3);
					java.sql.Date udom = resultSet.getDate(4);
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String strDob = sdf.format(udob);
					String strDom = sdf.format(udom);
					
					System.out.println(uid + "\t" + name + "\t" +strDob + "\t" + strDom);
					
					
				}else {
					System.out.println("Recod not available for the given id");
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
			catch(SQLException se){
				se.printStackTrace();
			}
			scanner.close();
		}
		
		
		
		
	}

}
