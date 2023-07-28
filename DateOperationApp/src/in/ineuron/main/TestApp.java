package in.ineuron.main;
import java.util.Date;
public class TestApp {

	public static void main(String[] args) {
		
		// java.util.Date --> we use to store both date and time information
		Date udate = new Date();
		System.out.println(udate);
		
		long value = udate.getTime();
		System.out.println("Util date is :: "+udate);
		
		//java.sql.Date --> we use to store date information
		java.sql.Date sqlDate = new java.sql.Date(value);
		System.out.println("sqlDate is :: "+sqlDate);
		
	}

}
