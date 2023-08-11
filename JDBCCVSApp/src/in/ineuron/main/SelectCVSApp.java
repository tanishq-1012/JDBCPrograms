package in.ineuron.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectCVSApp {
	// workbook.<sheetname> represents the table name
			private static final String EXCEL_FILE = "select * from data.cvs";

	public static void main(String[] args) {
		//jdbc:Excel:///location where the file is present
		String url = "jdbc:Text:///Users/tanishqsharma/Desktop/ADJNotes/JDBCSourceCode_20_01_2023/data.csv";
		
		try (Connection connection = DriverManager.getConnection(url)) {
			try (PreparedStatement pstmt = connection.prepareStatement(EXCEL_FILE)) {
				try (ResultSet resultSet = pstmt.executeQuery()) {
					while (resultSet.next()) {
						System.out.println(
								resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));
					}
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
