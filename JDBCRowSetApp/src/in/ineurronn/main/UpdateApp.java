package in.ineurronn.main;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class UpdateApp {

	public static void main(String[] args) throws Exception {
		
		RowSetFactory rsf = RowSetProvider.newFactory();
		JdbcRowSet jrs = rsf.createJdbcRowSet();// same as resultSet(connected),but it is scrollable and updatable.

		// setting url,username,password and getting connection object..
		jrs.setUrl("jdbc:mysql:///Student");
		jrs.setUsername("root");
		jrs.setPassword("Root@12345");

		// setting a command for execution
		jrs.setCommand("select sid,sname,sage,saddress,sgender,salary from student");
		jrs.execute();

		while (jrs.next()) {
			int actualSalary = jrs.getInt(6);
			if (actualSalary < 5000) {
				int updatedSalary = actualSalary + 1000;
				jrs.updateInt(6, updatedSalary);
				jrs.updateRow();
			}
		}
		System.out.println("Records updated succesfully....");
		
		System.out.println("Retreiveing the records...");
		System.out.println("ID\tNAME\tAGE\tADDRESS\tGENDER\tSALARY");
		while (jrs.next()) {
			System.out
					.println(jrs.getInt(1) + "\t" + jrs.getString(2) + "\t" + jrs.getInt(3) + "\t" + jrs.getString(4) + "\t" + jrs.getString(5) + "\t" + jrs.getInt(6));
			
		}

		jrs.close();
	}

}
