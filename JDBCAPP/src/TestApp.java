import java.sql.*;
public class TestApp {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		
		try {
			//1. Load and register the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded succesfully....");
			
			
			//2. Establish the connection with database
			String url = "jdbc:mysql://localhost:3306/Student";
			
			//user name and password would vary from user to user
			String userName = "root";
			String password = "Root@12345";
			
			connection = DriverManager.getConnection(url,userName,password);
			System.out.println("Connnection established sucessfully....");
			System.out.println("The implement class name is "+ connection.getClass().getName());
			
			
			//3. Create statement object and send the query
			String sqlSelectQuery = "select sid,sname,sage,saddress from student";
			statement = connection.createStatement();
			System.out.println("The implementation class name is :: "+statement.getClass().getName());
			
			resultset = statement.executeQuery(sqlSelectQuery);
			System.out.println("The implemetation class name is :: "+resultset.getClass().getName());
			
			System.out.println();
			
			System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
			
			//4. Process the resultset
			while(resultset.next()) {
				int sid = resultset.getInt(1);
				String sname = resultset.getNString(2);
				int sage = resultset.getInt(3);
				String saddr = resultset.getNString(4);
				
				System.out.println(sid+"\t"+sname+"\t"+sage+"\t"+saddr);
				
			}
			
		}
		catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//closing the resource
			if(connection!= null) {
				try {
					connection.close();
					System.out.println("Connection closed....");
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

}
