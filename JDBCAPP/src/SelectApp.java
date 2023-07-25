package in.ineuron.main;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SelectApp {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//Step1. Load and register the driver
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Loading the Driver....");
		
		//Step2. Establish the connection
		String url = "jdbc:mysql://localhost:3306/Student";
		String user = "root";
		String password = "Root@12345";
		
		Connection connection = DriverManager.getConnection(url,user,password);
		
		//Step3. Create statement object and send the Query
		Statement statement = connection.createStatement();
		
		//Step4. Execute the query and process the resultset
		String sqlSelectQuery = "select sid,sname,sage,saddress from Student";
		ResultSet resultset = statement.executeQuery(sqlSelectQuery);
		
		System.out.println("SID\tSNAME\tSAGE\tSADRESS");
		while(resultset.next()) {
			int sid = resultset.getInt(1);
			String sname =resultset.getString(2);
			int sage = resultset.getInt(3);
			String saddress = resultset.getString(4);
			System.out.println(sid+"\t"+sname+"\t"+sage+"\t"+saddress);
			
		}
		
		//Step6. Close the resources
		resultset.close();
		statement.close();
		connection.close();
		
	}

}








