package support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import pages.PageSupporter;
import support.enums.DB_QUERY_NAME;

public class DBConnection{
	
	Configurations configurations = Configurations.getInstance();
	private static DBConnection dbConnection;
	/**
	 * 
	 * @return
	 */
	public static DBConnection getInstance(){
		if(dbConnection==null){
			dbConnection= new DBConnection();
		}
		return dbConnection;
	}



	/**
	 * 
	 * @param marketerAssignedID
	 * @param accountNo
	 * @param company
	 * @return
	 */
	public synchronized float compareSourceDataAgainstDBSource(String utilityName,String startDate,String accountNo,String meterNo) {
		String testSetId = configurations.getProperty("testSetId");
		System.out.println(testSetId);
		String iAnalystDBServerName = configurations.getProperty("DBServerName");
		String iAnalyst = configurations.getProperty("DBdatabaseName");
		String query = configurations.getProperty("ORDER_VERIFICATION");
		String url = "jdbc:sqlserver://"+iAnalystDBServerName+";databaseName="+iAnalyst+";integratedSecurity=true";
		float usageVolume=-1;
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				usageVolume = rs.getFloat("VolumeRead");
			}

			conn.close();
			return usageVolume;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (Exception e1) {
			}
			return usageVolume;
		}
	}



	/**
	 * 
	 * Method Name : UpdateRecord
	 * Description : To Update the existing record with new values
	 * Author      : Vm Natarajan
	 * Return Types: void
	 * Date        : Apr 11, 2018
	 * Version     : 1.0
	 */
 	
	public synchronized Log updateRecord(DB_QUERY_NAME queryName,String Role , String UserId) {
		Log log = PageSupporter.getLogger();
		String dbServerName = configurations.getProperty("DBServerName");
		String dbName = configurations.getProperty("DBdatabaseName");
		String query = configurations.getProperty(queryName.toString());
		String url = "jdbc:sqlserver://"+dbServerName+";databaseName="+dbName+";integratedSecurity=true";
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			int rs = stmt.executeUpdate(query);
			while (rs>0) {
				conn.close();
				log.message =  "(1 row affected) in DB </em>";
				return log;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (Exception e1) {
			}
		}
		log.message =  "<em> Query Not Executed </em>";
		return log;
	}


	

}
