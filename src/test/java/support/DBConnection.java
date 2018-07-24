package support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import com.aventstack.extentreports.Status;
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
		String iAnalystDBServerName = configurations.getProperty("iAnalystDBServerName");
		String iAnalyst = configurations.getProperty("iAnalystDBdatabaseName");
		String scrapperQuery = configurations.getProperty("Scrapper_Query");
		String url = "jdbc:sqlserver://"+iAnalystDBServerName+";databaseName="+iAnalyst+";integratedSecurity=true";
		float usageVolume=-1;
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			scrapperQuery = scrapperQuery.replace("_REPLACE_UTILITY_ID_", utilityName).replace("_REPLACE_START_DATE_", startDate).replace("_REPLACE_ACCOUNT_NUMBER_", accountNo);
			scrapperQuery = scrapperQuery.replace("_REPLACE_METER_NUMBER_", meterNo);
			ResultSet rs = stmt.executeQuery(scrapperQuery);

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


	public synchronized float compareSourceDataAgainstDBSource(String utilityName,String startDate,String customerName) {
		String iAnalystDBServerName = configurations.getProperty("iAnalystDBServerName");
		String iAnalyst = configurations.getProperty("iAnalystDBdatabaseName");
		String scrapperQuery = configurations.getProperty("Scrapper_Query_Customer");
		String url = "jdbc:sqlserver://"+iAnalystDBServerName+";databaseName="+iAnalyst+";integratedSecurity=true";
		float usageVolume=-1;
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			scrapperQuery = scrapperQuery.replace("_REPLACE_UTILITY_ID_", utilityName).replace("_REPLACE_START_DATE_", startDate);
			scrapperQuery = scrapperQuery.replace("_REPLACE_COMPANY_ID_", customerName);
			ResultSet rs = stmt.executeQuery(scrapperQuery);

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

	public synchronized float compareSourceDataAgainstDBSourceOnlyAccountNumber(String utilityName,String startDate,String accountNumber) {
		String iAnalystDBServerName = configurations.getProperty("iAnalystDBServerName");
		String iAnalyst = configurations.getProperty("iAnalystDBdatabaseName");
		String scrapperQuery = configurations.getProperty("Scrapper_Query_Only_AccountNumber");
		String url = "jdbc:sqlserver://"+iAnalystDBServerName+";databaseName="+iAnalyst+";integratedSecurity=true";
		float usageVolume=-1;
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			scrapperQuery = scrapperQuery.replace("_REPLACE_UTILITY_ID_", utilityName).replace("_REPLACE_START_DATE_", startDate);
			scrapperQuery = scrapperQuery.replace("_REPLACE_ACCOUNT_NUMBER_", accountNumber);
			ResultSet rs = stmt.executeQuery(scrapperQuery);

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

	public synchronized float compareSourceDataAgainstDBSourceWithAccountNumber(String utilityName,String startDate,String accountNo,String customerName) {
		String testSetId = configurations.getProperty("testSetId");
		System.out.println(testSetId);
		String iAnalystDBServerName = configurations.getProperty("iAnalystDBServerName");
		String iAnalyst = configurations.getProperty("iAnalystDBdatabaseName");
		String scrapperQuery = configurations.getProperty("Scrapper_Query_AccountNumber");
		String url = "jdbc:sqlserver://"+iAnalystDBServerName+";databaseName="+iAnalyst+";integratedSecurity=true";
		float usageVolume=-1;
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			scrapperQuery = scrapperQuery.replace("_REPLACE_UTILITY_ID_", utilityName).replace("_REPLACE_START_DATE_", startDate).replace("_REPLACE_ACCOUNT_NUMBER_", accountNo);
			scrapperQuery = scrapperQuery.replace("_REPLACE_COMPANY_ID_", customerName);
			ResultSet rs = stmt.executeQuery(scrapperQuery);

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


	public synchronized float compareSourceDataAgainstDBSourceWithCPR(String utilityName,String cpr,String date) {
		String testSetId = configurations.getProperty("testSetId");
		System.out.println(testSetId);
		String iAnalystDBServerName = configurations.getProperty("iAnalystDBServerName");
		String iAnalyst = configurations.getProperty("iAnalystDBdatabaseName");
		String scrapperQuery = configurations.getProperty("Scrapper_Query_CPR");
		String url = "jdbc:sqlserver://"+iAnalystDBServerName+";databaseName="+iAnalyst+";integratedSecurity=true";
		float usageVolume=-1;
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			scrapperQuery = scrapperQuery.replace("_REPLACE_UTILITY_ID_", utilityName).replace("_REPLACE_CPR_", cpr).replace("_REPLACE_DATE_", date);
			ResultSet rs = stmt.executeQuery(scrapperQuery);

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

	public synchronized Log validateNewRecord(DB_QUERY_NAME queryName,String utilityId,String pool) {
		Log log = PageSupporter.getLogger();
		String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
		String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
		String query = configurations.getProperty(queryName.toString());
		String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			query = query.replace("_REPLACE_UTILITY_ID_", utilityId).replace("_REPLACE_POOL_ID_", pool);
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				conn.close();
				log.message =  "<em> New record has been created </em>";
				return log;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (Exception e1) {
			}
		}
		log.message =  "<em> New record has not been created </em>";
		return log;
	}

	public synchronized Log validateNewRecord(DB_QUERY_NAME queryName,String customerId) {
		Log log = PageSupporter.getLogger();
		String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
		String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
		String query = configurations.getProperty(queryName.toString());
		String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			query = query.replace("_REPLACE_CUSTOMER_ID_", customerId);
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				conn.close();
				log.message =  "<em> New record has been created </em>";
				return log;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (Exception e1) {
			}
		}
		log.message =  "<em> New record has not been created </em>";
		return log;
	}

	
	public synchronized Log deleteRecord(DB_QUERY_NAME queryName,String utilityId,String pool) {
		Log log = PageSupporter.getLogger();
		String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
		String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
		String query = configurations.getProperty(queryName.toString());
		String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			query = query.replace("_REPLACE_UTILITY_ID_", utilityId).replace("_REPLACE_POOL_ID_", pool);
			int rs = stmt.executeUpdate(query);
			System.out.println("COUNT: "+rs);
			while (rs>0) {
				conn.close();
				log.message =  "<em> Record has been deleted </em>";
				log.status = Status.PASS;
				return log;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (Exception e1) {
			}
		}
		log.message =  "<em> Record has not been deleted </em>";
		log.status = Status.ERROR;
		return log;
		
	}
	
	public synchronized Log deleteRecordAssetPriority(DB_QUERY_NAME queryName,String utilityId) {
		Log log = PageSupporter.getLogger();
		String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
		String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
		String query = configurations.getProperty(queryName.toString());
		String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			query = query.replace("_REPLACE_UTILITY_ID_", utilityId);
			int rs = stmt.executeUpdate(query);
			System.out.println("COUNT: "+rs);
			while (rs>0) {
				conn.close();
				log.message =  "<em> Record has been deleted </em>";
				log.status = Status.PASS;
				return log;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (Exception e1) {
			}
		}
		log.message =  "<em> Record has not been deleted </em>";
		log.status = Status.ERROR;
		return log;
		
	}
	
	
	public synchronized Log deleteRecord(DB_QUERY_NAME queryName,String customerId) {
		Log log = PageSupporter.getLogger();
		String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
		String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
		String query = configurations.getProperty(queryName.toString());
		String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			query = query.replace("_REPLACE_CUSTOMER_ID_", customerId);
			int rs = stmt.executeUpdate(query);
			System.out.println("COUNT: "+rs);
			while (rs>0) {
				conn.close();
				log.message =  "<em> Record has been deleted </em>";
				log.status = Status.PASS;
				return log;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (Exception e1) {
			}
		}
		log.message =  "<em> Record has not been deleted </em>";
		log.status = Status.ERROR;
		return log;
		
	}
	
	public synchronized String validateExistingRecord(DB_QUERY_NAME queryName,String utilityId,String pool) {
		//Log log = PageSupporter.getLogger();
		String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
		String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
		String query = configurations.getProperty(queryName.toString());
		String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
		String usageUOM = null;
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			query = query.replace("_REPLACE_UTILITY_ID_", utilityId).replace("_REPLACE_POOL_ID_", pool);
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				
				usageUOM = rs.getString("UsageUOM");
				
				
				//log.message =  "<em> New record has been created </em>";
				
			}
			conn.close();
			return usageUOM;

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (Exception e1) {
			}
		}
		//log.message =  "<em> New record has not been created </em>";
		return usageUOM;
	}
	
	/**
	 * 
	 * Method Name : UpdateRecord
	 * Description : To Update the existing record with new values
	 * Author      : anbaran1
	 * Return Types: void
	 * Date        : Apr 11, 2018
	 * Version     : 1.0
	 */
 	
	public synchronized Log updateRecord(DB_QUERY_NAME queryName,String Role , String UserId) {
		Log log = PageSupporter.getLogger();
		String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
		String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
		String query = configurations.getProperty(queryName.toString());
		String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			query = query.replace("_REPLACE_ROLE_", Role).replace("_REPLACE_USER_ID_", UserId);
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


	public synchronized Log deleteRecordAMconfiguration(DB_QUERY_NAME queryName,String pipelineId) {
		Log log = PageSupporter.getLogger();
		String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
		String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
		String query = configurations.getProperty(queryName.toString());
		String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			query = query.replace("_REPLACE_pipelineid_", pipelineId);
			int rs = stmt.executeUpdate(query);
			System.out.println("COUNT: "+rs);
			while (rs>0) {
				conn.close();
				log.message =  "<em> Record has been deleted </em>";
				log.status = Status.PASS;
				return log;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (Exception e1) {
			}
		}
		log.message =  "<em> Record has not been deleted </em>";
		log.status = Status.ERROR;
		return log;
		
	}
	
	/**
	 * 
	 * Method Name : updateStartDateInDB
	 * Description : To update start date for the utility to convert as existing records
	 * Author      : ramasav1
	 * Return Types: void
	 * Date        : Apr 19, 2018
	 * Version     : 1.0
	 */
	
	public synchronized Log updateStartDateInDB(DB_QUERY_NAME queryName,String utilityId,String startDate, String newDate) {
		
		 
		String[] format = startDate.split("/");
		String startDate1 = format[2]+"-"+format[0]+"-"+format[1]+" 00:00:00.000";
		System.out.println(startDate1);
		
		String[] format1 = newDate.split("/");
		String newDate1 = format1[2]+"-"+format1[0]+"-"+format1[1]+" 00:00:00.000";
		System.out.println(newDate1);
		
		Log log = PageSupporter.getLogger();
		String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
		String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
		String query = configurations.getProperty(queryName.toString());
		String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
		Connection conn = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			query = query.replace("_REPLACE_UTILITY_ID_", utilityId).replace("_REPLACE_START_DATE_", newDate1).replace("_REPLACE_START_DATE1_", startDate1);
			int rs = stmt.executeUpdate(query);
			System.out.println("COUNT: "+rs);
			while (rs>0) {
				conn.close();
				log.message =  "<em> Record has been updated </em>";
				log.status = Status.PASS;
				return log;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (Exception e1) {
			}
		}
		log.message =  "<em> Record has not been updated</em>";
		log.status = Status.ERROR;
		return log;
		
	}
	
	/**
	 * 
	 * Method Name : updateStartDateInDB
	 * Description : To update start date for the utility to convert as existing records
	 * Author      : anbaran1
	 * Return Types: void
	 * Date        : Apr 19, 2018
	 * Version     : 1.0
	 */
	
	public synchronized Log updateStartDateInDBCus(DB_QUERY_NAME queryName,String CusId,String startDate, String newDate) {
		
		 
		String[] format = startDate.split("/");
		String startDate1 = format[2]+"-"+format[0]+"-"+format[1]+" 00:00:00.000";
		System.out.println(startDate1);
		
		String[] format1 = newDate.split("/");
		String newDate1 = format1[2]+"-"+format1[0]+"-"+format1[1]+" 00:00:00.000";
		System.out.println(newDate1);
		
		Log log = PageSupporter.getLogger();
		String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
		String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
		String query = configurations.getProperty(queryName.toString());
		String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
		Connection conn = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			query = query.replace("_REPLACE_CUSTOMER_ID_", CusId).replace("_REPLACE_START_DATE_", newDate1);
			int rs = stmt.executeUpdate(query);
			System.out.println("COUNT: "+rs);
			while (rs>0) {
				conn.close();
				log.message =  "<em> Record has been updated </em>";
				log.status = Status.PASS;
				return log;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (Exception e1) {
			}
		}
		log.message =  "<em> Record has not been updated</em>";
		log.status = Status.ERROR;
		return log;
		
	}
	
	public synchronized Log insertRecord(DB_QUERY_NAME queryName,String utilityId,String poolId,String startDate,String endDate,String forecastValue) {
		Log log = PageSupporter.getLogger();
		String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
		String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
		String query = configurations.getProperty(queryName.toString());
		String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			query = query.replace("_REPLACE_UTILITY_ID_", utilityId).replace("_REPLACE_POOL_ID_", poolId).replace("_REPLACE_START_DATE_", startDate);
			query = query.replace("_REPLACE_END_DATE_", endDate).replace("_REPLACE_ALGO_FORECAST_", forecastValue).replace("_REPLACE_MY_FORECAST_FORECAST_", forecastValue);
			int rs = stmt.executeUpdate(query);
			System.out.println("COUNT: "+rs);
			while (rs>0) {
				conn.close();
				log.message =  "<em> Record has been inserted </em>";
				log.status = Status.PASS;
				return log;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (Exception e1) {
			}
		}
		log.message =  "<em> Record has not been inserted </em>";
		log.status = Status.ERROR;
		return log;
		
	}

	
	public synchronized Log updateStartDateRecord(DB_QUERY_NAME queryName,String utilityId,String poolId) {
		LocalDate todaydate = LocalDate.now();
		String startDate = todaydate.withDayOfMonth(1).toString();
		String endDate;
		if(todaydate.getMonthValue()>=12){
			endDate = LocalDate.of(todaydate.getYear()+1, 1, 31).toString();
		} else {
			endDate = todaydate.withMonth(todaydate.getMonthValue()+1).withDayOfMonth(28).toString();
		}
		Log log = PageSupporter.getLogger();
		String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
		String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
		String query = configurations.getProperty(queryName.toString());
		String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			query = query.replace("_REPLACE_START_DATE_", startDate).replace("_REPLACE_END_DATE_", endDate).replace("_REPLACE_UTILITY_ID_", utilityId).replace("_REPLACE_POOL_ID_", poolId);
			int rs = stmt.executeUpdate(query);
			System.out.println("COUNT: "+rs);
			while (rs>0) {
				conn.close();
				log.message =  "<em> Record has been updated </em>";
				log.status = Status.PASS;
				return log;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (Exception e1) {
			}
		}
		log.message =  "<em> Record has not been updated </em>";
		log.status = Status.ERROR;
		return log;
		
	}
	
public synchronized String getSingleDataFromDB(DB_QUERY_NAME queryName,String columnName, String utilityId,String poolId, String date) {
		
		String resultData = "NOT_EXIST";
		
		String[] format = date.split("/");
		String date1 = format[2]+"-"+format[0]+"-"+format[1]+" 00:00:00.000";
		System.out.println(date1);
		
		String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
		String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
		String query = configurations.getProperty(queryName.toString());
		String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			query = query.replace("_REPLACE_COLUMN_NAME_", columnName).replace("_REPLACE_UTILITY_ID_", utilityId).replace("_REPLACE_POOL_ID_", poolId).replace("_REPLACE_GAS_DAY_", date1).replace("_REPLACE_Forecast_Date_", date1).replace("_REPLACE_FORECAST_DATE_",date1);;
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("COUNT: "+rs);
			while(rs.next()) {
				resultData = rs.getString(columnName);
				conn.close();
				return resultData;
			}
			System.out.println(resultData);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (Exception e1) {
			}
		}
		return resultData;
	}


public synchronized String getDcqpoolforecastinginfoDataFromDB(DB_QUERY_NAME queryName,String columnName, String utilityId,String poolId, String date) {
	
		String resultData = "NOT_EXIST";
		
		String[] format = date.split("/");
		String date1 = format[2]+"-"+format[0]+"-"+format[1]+" 00:00:00.000";
		System.out.println(date1);
		
		String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
		String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
		String query = configurations.getProperty(queryName.toString());
		String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			query = query.replace("_REPLACE_COLUMN_NAME_", columnName).replace("_REPLACE_UTILITY_ID_", utilityId).replace("_REPLACE_POOL_ID_", poolId).replace("_REPLACE_FORECAST_DATE_", date1);
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("COUNT: "+rs);
			while(rs.next()) {
				resultData = rs.getString(columnName);
				conn.close();
				return resultData;
			}
			System.out.println(resultData);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (Exception e1) {
			}
		}
		return resultData;
}

public synchronized String getDcqpoolusageinfoDataFromDB(DB_QUERY_NAME queryName,String columnName, String utilityId,String poolId, String date) {

		String resultData = "NOT_EXIST";
		
		String[] format = date.split("/");
		String date1 = format[2]+"-"+format[0]+"-"+format[1]+" 00:00:00.000";
		System.out.println(date1);
		
		String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
		String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
		String query = configurations.getProperty(queryName.toString());
		String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			query = query.replace("_REPLACE_COLUMN_NAME_", columnName).replace("_REPLACE_UTILITY_ID_", utilityId).replace("_REPLACE_POOL_ID_", poolId).replace("_REPLACE_USAGE_DATE_", date1);
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("COUNT: "+rs);
			while(rs.next()) {
				resultData = rs.getString(columnName);
				conn.close();
				return resultData;
			}
			System.out.println(resultData);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (Exception e1) {
			}
		}
		return resultData;
}

public synchronized String getAssetschedulingsummaryDataFromDB(DB_QUERY_NAME queryName,String columnName, String utilityId,String poolId, String date) {

		String resultData = "NOT_EXIST";
		
		String[] format = date.split("/");
		String date1 = format[2]+"-"+format[0]+"-"+format[1];
		System.out.println(date1);
		
		String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
		String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
		String query = configurations.getProperty(queryName.toString());
		String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			query = query.replace("_REPLACE_COLUMN_NAME_", columnName).replace("_REPLACE_UTILITY_ID_", utilityId).replace("_REPLACE_POOL_ID_", poolId).replace("_REPLACE_GAS_DAY_", date1);
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("COUNT: "+rs);
			while(rs.next()) {
				resultData = rs.getString(columnName);
				conn.close();
				return resultData;
			}
			System.out.println(resultData);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (Exception e1) {
			}
		}
		return resultData;
}

public synchronized String getIndexValueFromApolloDB(DB_QUERY_NAME queryName,String columnName, String indexType, String date) {
	
	String resultData = "NOT_EXIST";
	String[] format = date.split("/");
	String date1 = format[2]+"-"+format[0]+"-"+format[1];
	System.out.println(date1);
	
	String apolloDBServerName = configurations.getProperty("Apollo_DBServerName");
	String apolloDBName = configurations.getProperty("Apollo_DBdatabaseName");
	String query = configurations.getProperty(queryName.toString());
	String url = "jdbc:sqlserver://"+apolloDBServerName+";databaseName="+apolloDBName+";integratedSecurity=true";
	Connection conn = null;
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		query = query.replace("_REPLACE_INDEX_NAME_", indexType).replace("_REPLACE_COMMENCEMENT_DATE_", date1);
		ResultSet rs = stmt.executeQuery(query);
		System.out.println("COUNT: "+rs);
		while(rs.next()) {
			resultData = rs.getString(columnName);
			conn.close();
			return resultData;
		}
		System.out.println(resultData);
	} catch (Exception e) {
		e.printStackTrace();
		try {
			conn.close();
		} catch (Exception e1) {
		}
	}
	return resultData;
}

public synchronized String validateExistingRecordinBaseSlopeDBTable(DB_QUERY_NAME queryName,String utilityId,String columnName) {
	//Log log = PageSupporter.getLogger();
	String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
	String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
	String query = configurations.getProperty(queryName.toString());
	String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
	String temp = null;
	String columnValue =null;
	Connection conn = null;
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		query = query.replace("_REPLACE_UTILITY_ID_", utilityId);
		System.out.println(query);
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {
			
			temp = rs.getString(columnName);
			columnValue = temp.replace(".0000", "");
			break;
			
			//log.message =  "<em> New record has been created </em>";
			
		}
		conn.close();
		return columnValue;

	} catch (Exception e) {
		e.printStackTrace();
		try {
			conn.close();
		} catch (Exception e1) {
		}
	}
	//log.message =  "<em> New record has not been created </em>";
	return columnValue;
}

/**
 * 
 * Method Name : update_forecastinginfo
 * Description : To update [JANIS_forecastinginfo] with the IsDroped to Yes
 * Author      : anbaran1
 * Return Types: void
 * Date        : Apr 19, 2018
 * Version     : 1.0
 */

public synchronized Log UPDATE_JANIS_forecastinginfo(DB_QUERY_NAME queryName,String utilityId,String PoolID, String CusID, String Date ) {
	
	 
	String[] format = Date.split("/");
	String startDate1 = format[2]+"-"+format[0]+"-"+format[1]+" 00:00:00.000";
	System.out.println(startDate1);
	
	
	Log log = PageSupporter.getLogger();
	String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
	String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
	String query = configurations.getProperty(queryName.toString());
	String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
	Connection conn = null;
	
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		query = query.replace("_REPLACE_UTILITY_ID_", utilityId).replace("_REPLACE_POOL_ID_", PoolID).replace("_REPLACE_Cus_ID_", CusID).replace("_REPLACE_TodayDate_", startDate1);
		int rs = stmt.executeUpdate(query);
		System.out.println("COUNT: "+rs);
		while (rs>0) {
			conn.close();
			log.message =  "<em> Record has been updated </em>";
			log.status = Status.PASS;
			return log;
		}

	} catch (Exception e) {
		e.printStackTrace();
		try {
			conn.close();
		} catch (Exception e1) {
		}
	}
	log.message =  "<em> Record has not been updated</em>";
	log.status = Status.ERROR;
	return log;
	
}
	

public synchronized String getSingleDatacusIDFromDB(DB_QUERY_NAME queryName,String columnName, String utilityId,String poolId, String date,String cusID) {
	
	String resultData = "NOT_EXIST";
	
	String[] format = date.split("/");
	String date1 = format[2]+"-"+format[0]+"-"+format[1]+" 00:00:00.000";
	System.out.println(date1);
	
	String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
	String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
	String query = configurations.getProperty(queryName.toString());
	String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
	Connection conn = null;
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		query = query.replace("_REPLACE_COLUMN_NAME_", columnName).replace("_REPLACE_UTILITY_ID_", utilityId).replace("_REPLACE_POOL_ID_", poolId).replace("_REPLACE_Forecast_Date_", date1).replace("_REPLACE_CusID_Date_", cusID);
		ResultSet rs = stmt.executeQuery(query);
		System.out.println("COUNT: "+rs);
		while(rs.next()) {
			resultData = rs.getString(columnName);
			conn.close();
			return resultData;
		}
		System.out.println(resultData);
	} catch (Exception e) {
		e.printStackTrace();
		try {
			conn.close();
		} catch (Exception e1) {
		}
	}
	return resultData;
}

public synchronized Log deleteAssetLocalProdSetup(DB_QUERY_NAME queryName,String notes) {
	Log log = PageSupporter.getLogger();
	String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
	String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
	String query = configurations.getProperty(queryName.toString());
	String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
	Connection conn = null;
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		query = query.replace("_REPLACE_COMP_NOTES_", notes);
		int rs = stmt.executeUpdate(query);
		System.out.println("COUNT: "+rs);
		while (rs>0) {
			conn.close();
			log.message =  "<em> Record has been deleted </em>";
			log.status = Status.PASS;
			return log;
		}

	} catch (Exception e) {
		e.printStackTrace();
		try {
			conn.close();
		} catch (Exception e1) {
		}
	}
	log.message =  "<em> Record has not been deleted </em>";
	log.status = Status.ERROR;
	return log;
	
}

public synchronized Log deleteAssetLocalProdInput(DB_QUERY_NAME queryName,String poolID) {
	Log log = PageSupporter.getLogger();
	String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
	String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
	String query = configurations.getProperty(queryName.toString());
	String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
	Connection conn = null;
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		query = query.replace("_REPLACE_POOL_ID_", poolID);
		int rs = stmt.executeUpdate(query);
		System.out.println("COUNT: "+rs);
		while (rs>0) {
			conn.close();
			log.message =  "<em> Record has been deleted </em>";
			log.status = Status.PASS;
			return log;
		}

	} catch (Exception e) {
		e.printStackTrace();
		try {
			conn.close();
		} catch (Exception e1) {
		}
	}
	log.message =  "<em> Record has not been deleted </em>";
	log.status = Status.ERROR;
	return log;
	
}

public synchronized Log deleteUserLoginTable(DB_QUERY_NAME queryName) {
	Log log = PageSupporter.getLogger();
	String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
	String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
	String query = configurations.getProperty(queryName.toString());
	String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
	Connection conn = null;
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate(query);
		System.out.println("COUNT: "+rs);
		while (rs>0) {
			conn.close();
			log.message =  "<em> Record has been deleted </em>";
			log.status = Status.PASS;
			return log;
		}

	} catch (Exception e) {
		e.printStackTrace();
		try {
			conn.close();
		} catch (Exception e1) {
		}
	}
	log.message =  "<em> Record has not been deleted </em>";
	log.status = Status.ERROR;
	return log;
	
}

public synchronized String getSingleDataFromDB(DB_QUERY_NAME queryName,String columnName, String utilityId,String poolId, String date, String customerID) {
	
	String resultData = "NOT_EXIST";
	
	String[] format = date.split("/");
	String date1 = format[2]+"-"+format[0]+"-"+format[1]+" 00:00:00.000";
	System.out.println(date1);
	
	String janisDBServerName = configurations.getProperty("JanIS_DBServerName");
	String janisDBName = configurations.getProperty("JanIS_DBdatabaseName");
	String query = configurations.getProperty(queryName.toString());
	String url = "jdbc:sqlserver://"+janisDBServerName+";databaseName="+janisDBName+";integratedSecurity=true";
	Connection conn = null;
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		query = query.replace("_REPLACE_COLUMN_NAME_", columnName).replace("_REPLACE_UTILITY_ID_", utilityId).replace("_REPLACE_POOL_ID_", poolId).replace("_REPLACE_GAS_DAY_", date1).replace("_REPLACE_CUSTOMER_ID_", customerID);
		System.out.println("Query Used : "+query);
		ResultSet rs = stmt.executeQuery(query);
		System.out.println("COUNT: "+rs);
		while(rs.next()) {
			resultData = rs.getString(columnName);
			conn.close();
			return resultData;
		}
		System.out.println(resultData);
	} catch (Exception e) {
		e.printStackTrace();
		try {
			conn.close();
		} catch (Exception e1) {
		}
	}
	return resultData;
}

}
