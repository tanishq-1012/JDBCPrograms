package in.ineuron.main;
import java.sql.*;

import in.ineuron.util.JDBCUtil;
public class InsertApp {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			
			connection = JDBCUtil.getJdbcConnnection();
			
			String sqlInsertQuery = "insert into student(`sname`,`sage`,`saddress`,`sgender`)values(?,?,?,?)";
			if(connection!=null) {
				pstmt = connection.prepareStatement(sqlInsertQuery);
			}
			if(pstmt!=null) {
				//use precompiled query to set the values
				pstmt.setString(1, "Rashi");
				pstmt.setInt(2, 24);
				pstmt.setString(3, "DEL");
				pstmt.setString(4,"F");
				
				
				System.out.println(sqlInsertQuery);
				//Execute the query
				int rowCount = pstmt.executeUpdate();
				System.out.println("No of rows updated is :: "+rowCount); 
				
				
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
				JDBCUtil.cleanUp(connection, pstmt, null);
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
