package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import in.ineuron.util.JDBCUtil;

public class UpdateableApp {

	public static void main(String[] args) {
		//Resources used
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = JDBCUtil.getJdbcConnection();
			
			if(connection!=null) {
				//Expecting resultset to be SCROLLABLE and UPDATEABLE
				stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			}
			
			String sqlQuery = "select id,name,age,address from employees";
			
			if(stmt!=null) {
				resultSet = stmt.executeQuery(sqlQuery);
			}
			if(resultSet!=null) {
				System.out.println("Records before updation...");
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				while(resultSet.next()) {
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3) + "\t" + resultSet.getString(4));
				}
				System.out.println();
				
				System.out.println("Application is in pausing state, please update database");
				System.in.read();
				System.out.println("Records after updation...");
				
				resultSet.beforeFirst(); // Takes the cursor to BFR
				
				while(resultSet.next()) {
					resultSet.refreshRow();//To get the updated value
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3) + "\t" + resultSet.getString(4));
				}
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
				JDBCUtil.cleanUp(connection, stmt, resultSet);
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
		}
		
	}

}




//Connection object created...
//Records before updation...
//ID	NAME	AGE	ADDRESS
//1	    Tanishq	24	abc
//2	    Aniket	23	def
//3	    Aman	25	ghi
//4	   Kritagya	24	jkl
//5	    Manish	25	mno
//
//Application is in pausing state, please update database
//
//Records after updation...
//1	   Tanishq	24	abc
//2	   Aniket	26	def
//3	    Aman	25	ghi
//4	   Kritagya	24	jkl
//5	   Manish	27	mno
