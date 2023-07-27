package in.ineuron.main;
import java.sql.*;

import in.ineuron.util.JDBCUtil;
public class InsertApp {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		
		try {
			
			connection = JDBCUtil.getJdbcConnnection();
			
			if(connection!=null) {
				statement = connection.createStatement();
			}
			if(statement!=null) {
				resultset = statement.executeQuery("select sid,sname,sage,saddress,sgender from student");
				
			}
			if(resultset!=null) {
				System.out.println("SID\tSNAME\tSAGE\tSADDRESS\tSGENDER");
				while(resultset.next()) {
					System.out.println(resultset.getInt(1) + "\t" + resultset.getString(2) + "\t" + resultset.getInt(3) + "\t" + resultset.getString(4) + "\t" + resultset.getString(5));
					
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
				JDBCUtil.cleanUp(connection, statement, resultset);
				System.out.println("Closing the resources...");
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
		}
	}

}


//Connection object created...
//SID	SNAME	SAGE	SADDRESS	SGENDER
//1	    sachin	50	       MI	      M
//3	    dhoni	41	       CSK        M
//4  	rahul	28	       LSG	      M
//5	    sky	    28	       MI	      M
//6	    Hardik	32	       GT	      M
//7	    Tanishq	24	       KKR 	      M
//8	    Tanya	26	       CSK	      F
//9	    Salman	34	       RCB	      M
//Closing the resources...
