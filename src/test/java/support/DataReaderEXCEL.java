package support;
import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/**
 * 
 * @author         :VNatarajan
 * @since          :Sep 20, 2017
 * @filename       :DataReader.java
 * @version		   :
 * @description    :
 */
public class DataReaderEXCEL extends DataReader{
	private  String repetition;
	private String fileName;
	private String sheetName;
	private String testName;
	Sheet sheet;

	public DataReaderEXCEL(String fileName,String sheetName,String testName,String repetition) {
		this.fileName = fileName;
		this.sheetName = sheetName;
		this.testName = testName;
		this.repetition = repetition;
	}

	public DataReaderEXCEL(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 
	 * Method Name : get
	 * Return Type : String
	 * Author      : VNatarajan
	 * Date		   : Sep 20, 2017
	 * Description : This method is to return the test data by giving the column name of the sheet as the parameter
	 * @param dataName : The data which is to be read from the excel file
	 * @return
	 */
	@Override
	public String get(String dataName) {
		InputStream inputStream;
		Workbook wb;
		DataFormatter formatter = new DataFormatter(Locale.US);
		try {

			if(sheet==null) {
				inputStream = new FileInputStream(new File(fileName));
				wb = WorkbookFactory.create(inputStream);
				sheet = wb.getSheet(sheetName);
			}

			boolean firstRow = true;
			Row dtColumnNames = null;
			for (Row row : sheet) {
				if (firstRow == true) {
					dtColumnNames = row;
					firstRow = false;
					continue;
				}

				Cell cell = row.getCell(1);
				String dtTestName = formatter.formatCellValue(cell).trim();
				
				cell = row.getCell(2);
				String iteration = formatter.formatCellValue(cell).trim();
				
				//Reference is to point the row corresponding to the test which is being executed
				if(dtTestName.equalsIgnoreCase(testName) && iteration.equalsIgnoreCase(repetition)) {
					for(int column = 0;column<row.getLastCellNum();column++) {
						if(dtColumnNames.getCell(column).getStringCellValue().trim().equalsIgnoreCase(dataName.trim())) {
							return formatter.formatCellValue(row.getCell(column)).toString().trim();
						}
					}
				}
			}
			return "NOT_FOUND";

		} catch (Exception e) {
			return "NOT_FOUND";
		}
	}

	@Override
	public List<Map<String, String>> getList(String columnNames) {

		List<Map<String,String>> rows = new ArrayList<Map<String,String>>();
		Map<String,String> row = new HashMap<String,String>();
		InputStream inputStream;
		HSSFWorkbook  wb;
		DataFormatter formatter = new DataFormatter(Locale.US);
		
		if(sheet==null) {
			try {
				
				inputStream = new FileInputStream(new File(fileName));
				//wb = WorkbookFactory.create(inputStream);
				wb = new HSSFWorkbook(inputStream);
				sheet = wb.getSheetAt(0);
				
				for (Row sheetRow : sheet) {
					row.put("AccountNumber", formatter.formatCellValue(sheetRow.getCell(1)).toString().trim());
					row.put("Date", formatter.formatCellValue(sheetRow.getCell(4)).toString().trim());
					row.put("Usage", formatter.formatCellValue(sheetRow.getCell(7)).toString().trim());
					rows.add(row);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

}