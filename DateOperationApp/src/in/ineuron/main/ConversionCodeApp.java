package in.ineuron.main;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ConversionCodeApp {

	public static void main(String[] args) throws Exception {
		// Read the input from the user
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the date:: (dd-MM-yyyy)");
		String sdate = scanner.next();
		
		
		//Convert the date from String format to java.util.Date
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date uDate = sdf.parse(sdate);
		
		
		//Convert java.util.Date to java.sql.Date
		long value = uDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(value);
		
		
		//Printing all the 3 formats of date
		System.out.println("String format date is :: "+sdate);
		System.out.println("Util format date is :: "+uDate);
		System.out.println("sqlDate is :: "+sqlDate);
		
		System.out.println("*********************************");
		
		System.out.println("Enter the dom in the following format :: (yyyy-MM-dd)");
		String standardInput = scanner.next();
		java.sql.Date sqlStandardInput = java.sql.Date.valueOf(standardInput);
		System.out.println("String standardInput :: "+standardInput);
		System.out.println("String sqlStandardInput :: "+sqlStandardInput);
		
		
		scanner.close();

	}

}










//Enter the date:: (dd-MM-yyyy)
//10-12-1998
//String format date is :: 10-12-1998
//Util format date is :: Thu Dec 10 00:00:00 IST 1998
//sqlDate is :: 1998-12-10
//*********************************
//Enter the dom in the following format :: (yyyy-MM-dd)
//1997-02-23
//String standardInput :: 1997-02-23
//String sqlStandardInput :: 1997-02-23

