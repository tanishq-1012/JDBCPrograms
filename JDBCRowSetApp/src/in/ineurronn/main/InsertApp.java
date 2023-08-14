package in.ineurronn.main;

import java.util.Scanner;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class InsertApp {

	public static void main(String[] args) throws Exception{
		
		RowSetFactory rsf = RowSetProvider.newFactory();
		JdbcRowSet jrs = rsf.createJdbcRowSet();// same as resultSet(connected),but it is scrollable and updatable.

		// setting url,username,password and getting connection object..
		jrs.setUrl("jdbc:mysql:///Student");
		jrs.setUsername("root");
		jrs.setPassword("Root@12345");

		// setting a command for execution
		jrs.setCommand("select sid,sname,sage,saddress,sgender from student");
		jrs.execute();

		Scanner scanner = new Scanner(System.in);
		jrs.moveToInsertRow();
		while (true) {
			System.out.print("Enter the name::");
			String name = scanner.next();

			System.out.print("Enter the age::");
			Integer age = scanner.nextInt();

			System.out.print("Enter the address::");
			String address = scanner.next();
			
			System.out.print("Enter the gender:: ");
			String gender = scanner.next();

			jrs.updateString(2, name);
			jrs.updateInt(3, age);
			jrs.updateString(4, address);
			jrs.updateString(5, gender);

			jrs.insertRow();

			System.out.println("Record inserted succesfully.....");
			System.out.print("Do u want to insert one more record [Yes/No]::  ");
			String option = scanner.next();
			if (option.equalsIgnoreCase("No")) {
				break;
			}

		}
		System.out.println("***************************************************");
		System.out.println("Retreiveing the records ");
		System.out.println("ID\tNAME\tAGE\tADDRESS\tGENDER");
		while (jrs.next()) {
			System.out
					.println(jrs.getInt(1) + "\t" + jrs.getString(2) + "\t" + jrs.getInt(3) + "\t" + jrs.getString(4) + "\t" + jrs.getString(5));
		
		}
		scanner.close();
		jrs.close();
	}

}





//Enter the name::Adan
//Enter the age::27
//Enter the address::po
//Enter the gender:: M
//Record inserted succesfully.....
//Do u want to insert one more record [Yes/No]::  No
//Retreiveing the records 
//ID	NAME	AGE	ADDRESS	GENDER
//1	sachin	50	MI	M
//5	sky	28	MI	M
//6	Hardik	32	RP	M
//7	Tanishq	24	KKR	M
//8	Tanya	26	CSK	F
//9	Salman	34	RCB	M
//10	Rashi	24	DEL	F
//11	Neha	27	KP	F
//12	Sakshi	24	ON	F
//13	Nakul	34	rt	M
//14	Karan	32	Mm	M
//15	Adan	27	po	M
