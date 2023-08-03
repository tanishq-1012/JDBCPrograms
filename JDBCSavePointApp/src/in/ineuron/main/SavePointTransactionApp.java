package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import in.ineuron.util.JDBCUtil;

public class SavePointTransactionApp {

	public static void main(String[] args) {
		
		//Resources used
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = JDBCUtil.getJdbcConnection();
			
			if(connection != null) {
				stmt = connection.createStatement();
			}
			
			System.out.println("Transaction begins...");
			connection.setAutoCommit(false);
			
			stmt.executeUpdate("insert into politicians(`name`,`party`)values('Modi','BJP')");
			stmt.executeUpdate("insert into politicians(`name`,`party`)values('Rahul','Congress')");
			Savepoint sp = connection.setSavepoint();
			stmt.executeUpdate("insert into politicians(`name`,`party`)values('AAP','Kajrival')");
			System.out.println("OOP's somthing went wrong during the insertion...");
			connection.rollback(sp);
			System.out.println("Operations are rolled back to savepoint");
			connection.commit();
			
			System.out.println("Transaction done...");
		}
		catch(SQLException|IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				JDBCUtil.cleanUp(connection, stmt, null);
				
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		

	}

}





//Connection object created...
//Transaction begins...
//OOP's somthing went wrong during the insertion...
//Operations are rolled back to savepoint
//Transaction done...

