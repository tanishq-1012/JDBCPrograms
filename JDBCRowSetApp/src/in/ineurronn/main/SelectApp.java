package in.ineurronn.main;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class SelectApp {

	public static void main(String[] args) throws Exception{
		
		RowSetFactory rsf = RowSetProvider.newFactory();
		JdbcRowSet jrs = rsf.createJdbcRowSet();// same as resultSet(connected),but it is scrollable and updatable.

		// setting url,username,password and getting connection object..
		jrs.setUrl("jdbc:mysql:///Student");
		jrs.setUsername("root");
		jrs.setPassword("Root@12345");

		// setting a command for execution
		jrs.setCommand("select sid,sname,sage,saddress from student");
		jrs.execute();

		System.out.println("Retreiveing the records in forward direction...");
		System.out.println("ID\tNAME\tAGE\tADDRESS");
		while (jrs.next()) {
			System.out
					.println(jrs.getInt(1) + "\t" + jrs.getString(2) + "\t" + jrs.getInt(3) + "\t" + jrs.getString(4));
		}
		System.out.println();

		System.out.println("Retreiveing the records in backward direction...");
		System.out.println("ID\tNAME\tAGE\tADDRESS");
		while (jrs.previous()) {
			System.out
					.println(jrs.getInt(1) + "\t" + jrs.getString(2) + "\t" + jrs.getInt(3) + "\t" + jrs.getString(4));
		}

		System.out.println();

		// accessing the record randomly
		jrs.absolute(4);
		System.out.println(jrs.getInt(1) + "\t" + jrs.getString(2) + "\t" + jrs.getInt(3) + "\t" + jrs.getString(4));

		jrs.relative(-2);
		System.out.println(jrs.getInt(1) + "\t" + jrs.getString(2) + "\t" + jrs.getInt(3) + "\t" + jrs.getString(4));

	}

}





//Retreiveing the records in forward direction...
//ID	NAME	AGE	ADDRESS
//1	sachin	50	MI
//5	sky	28	MI
//6	Hardik	32	RP
//7	Tanishq	24	KKR
//8	Tanya	26	CSK
//9	Salman	34	RCB
//10	Rashi	24	DEL
//11	Neha	27	KP
//12	Sakshi	24	ON
//
//Retreiveing the records in backward direction...
//ID	NAME	AGE	ADDRESS
//12	Sakshi	24	ON
//11	Neha	27	KP
//10	Rashi	24	DEL
//9	Salman	34	RCB
//8	Tanya	26	CSK
//7	Tanishq	24	KKR
//6	Hardik	32	RP
//5	sky	28	MI
//1	sachin	50	MI
//
//7	Tanishq	24	KKR
//5	sky	28	MI
