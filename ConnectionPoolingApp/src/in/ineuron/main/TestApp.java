package in.ineuron.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class TestApp {

	public static void main(String[] args) throws SQLException {
		
		//Create an object of a class which implements javax.sql.DataSource0
		MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
		dataSource.setURL("jdbc:mysql:///Student");
		dataSource.setUser("root");
		dataSource.setPassword("Root@12345");
		
		
		//Getting the connection object from connection pool
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select id,name,age,address from employees");
		System.out.println("ID\tNAME\tAGE\tADDRESS");
		while(resultSet.next()) {
			System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3) + "\t" + resultSet.getString(4) );
		}
		
		//Sending the connection object back to connection pool
		connection.close();
		
	}

}



//ID	NAME	AGE	ADDRESS
//1	   Tanishq	24	abc
//2	   Aniket	26	def
//3	    Aman	25	ghi
//4	   Kritagya	24	jkl
//7	   Pooja	23	klm

