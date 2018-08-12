package tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import com.paulhammant.ngwebdriver.NgWebDriver;

import pages.Pages;
import support.Configurations;
import support.DBConnection;
import support.DataReader;
import support.DataReaderEXCEL;
import support.DataReaderXML;
import support.Report;
import support.Settings;
import support.UiDriver;
import support.Utility;

public class TestSupporter extends Pages{

	protected final static String DOWNLOAD_PATH=System.getProperty("user.home")+"\\Downloads\\";
	protected support.Settings settings = Settings.getInstance();
	String dataSourcePath = settings.getDataSource();
	String dataXmlSourcePath = settings.getXMLDataSource();
	String driversPath = settings.getDriverEXEDir();
	String screenshotsPath = settings.getScreenshotsDir();
	Configurations configurations = Configurations.getInstance();
	protected Report report = Report.getInstance();
	protected Utility appData = Utility.getInstance();
	protected UiDriver uidriver = new UiDriver();
	protected DBConnection dbConnection = new DBConnection();
	protected DataReader data;
	protected WebDriver driver;
	protected NgWebDriver ngDriver;
	protected ExtentTest test;
	protected Faker testData = new Faker();


	@BeforeSuite(alwaysRun=true)
	public void clearScreenshots() {

		try {
			FileUtils.cleanDirectory(new File(settings.getScreenshotsDir()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Method name  : setUpTest
	 * Return types : void
	 * Description  : Set up the tests
	 */

	@Parameters({"Browser","URL","Datasource","SheetName"})
	@BeforeMethod(alwaysRun=true)
	public void initializeBrowser(String browserName,String url,String datasource,String sheetName,Method method,ITestContext context) {

		String dataSource = configurations.getProperty("DataSource");
		String testId;
		String testName;
		String iteration;
		Test testMethod = method.getAnnotation(Test.class);
		if (testMethod.description().length()>0) {
			testName = testMethod.description().trim();
		}else {
			testName = "Test description is not given";
		}
		
		iteration = (context.getAllTestMethods()[0].getCurrentInvocationCount()+1)+"";
		
		if(dataSource.equalsIgnoreCase("excel")) {

			data = new DataReaderEXCEL(dataSourcePath+datasource,sheetName,testName,iteration);
			System.out.println(dataSourcePath+datasource);
			System.out.println(testName);
			System.out.println(iteration);
			testId = data.get("Test_ID");
			
		}else {
			data = new DataReaderXML(dataXmlSourcePath+"SearchCustomerTests.xml",testName);
			testId = data.getTestId();
		}


		System.out.println("Test Running :"+testName);

		String testDescription = context.getCurrentXmlTest().getName();
		test = report.createNewReport(testId,testDescription+" - "+testName);

		//Instantiate the driver instance
		driver = uidriver.getDriver(browserName);

		//Set pages
		setPages(driver);

		//browser initial set ups
		//driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(600, TimeUnit.SECONDS);
		driver.get(url);
	}

	/**
	 * 
	 * Method Name : cleanUp
	 * Return Type : void
	 * Author      : VNatarajan
	 * Date		   : Sep 29, 2017
	 * Description : 
	 */

	@AfterMethod(alwaysRun=true)
	public void cleanUp(ITestResult result,Method method) {
		String testName;

		Test testMethod = method.getAnnotation(Test.class);
		if (testMethod.description().length()>0) {
			testName = testMethod.description().trim();

		}else {
			testName = "Test description is not given";
		}
		try {

			//Close and quite driver instance
			driver.quit();
		}catch(Exception e) {
			test.log(Status.FAIL, "Exception occurred in the test -  Error Detail : "+result.getThrowable().getMessage(),takeScreenshot());
		}
		//Close report
		report.closeReport();
	}



	/**
	 * 
	 * Method Name : captureScreen
	 * Return Type : String
	 * Author      : VNatarajan
	 * Date		   : Sep 29, 2017
	 * Description : This is to capture the screenshot of the applicatiom under test during execution
	 * @return
	 */

	public String captureScreen() {
		try {
			Random rand = new Random();
			int  randomNo = rand.nextInt(100000000) + 1;
			File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String screenShotName = screenshotsPath+"screenshot_"+randomNo+".png";
			String relativeScreenShotName = "screenshots\\screenshot_"+randomNo+".png";
			FileUtils.copyFile(source, new File(screenShotName));
			return relativeScreenShotName;
		}
		catch(Exception e) {
			return "ERROR_IN_SCREEN_CAPTURE";
		}
	}

	/**
	 * 
	 * Method Name : takeScreenshot
	 * Return Type : MediaEntityModelProvider
	 * Author      : VNatarajan
	 * Date		   : Sep 29, 2017
	 * Description : 
	 * @return
	 */
	public  MediaEntityModelProvider takeScreenshot() {
		try {
			return MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * Method Name : takeExecutionHistory
	 * Return Type : void
	 * Author      : VNatarajan
	 * Date		   : Sep 29, 2017
	 * Description :
	 */
	@AfterSuite(alwaysRun=true)
	public void testsTearDown(){

		String reportPath=settings.getProjectPath();		
		String history = System.getProperty("user.dir")+System.getProperty("file.separator")+"History"+System.getProperty("file.separator");
		new File("history").mkdirs();
		File historyDir = new File(history);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.contains("Execution_");
			}
		};
		String[] resultsDir = historyDir.list(filter);
		int executionDirIndex = resultsDir.length+1;
		String executionDir = history+System.getProperty("file.separator")+"Execution_"+executionDirIndex;
		String screenshots = executionDir+System.getProperty("file.separator")+"screenshots";
		new File(executionDir).mkdirs();
		try {
			FileUtils.copyFileToDirectory(new File(reportPath+System.getProperty("file.separator")+"TestSummaryReport.html"), new File(executionDir),false);
			FileUtils.copyDirectory(new File(reportPath+System.getProperty("file.separator")+"screenshots"), new File(screenshots),false);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * Method Name : randomNumber
	 * Description : This generates the random of given length
	 * Author      : VNatarajan
	 * Return Types: void
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */

	public String randomNumber(int length) {
		Random rand = new Random();
		String  randomNo="";
		for(int len = 1;len<=length;len++) {
			randomNo = randomNo + (rand.nextInt(9) + 1);
			if(randomNo.length()==length) {
				return randomNo;
			}
		}
		return "NOT_RANDOM";
	}

	/**
	 * 
	 * Method Name : deleteFilesInFolder
	 * Description : This deletes files given the filename and folder
	 * Author      : VNatarajan
	 * Return Types: void
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */

	public boolean deleteFilesInFolder(String folder,String fileName) {
		File directory = new File(folder);
		File[] files = directory.listFiles();
		for (File file : files){
			if (file.getName().contains(fileName)){
				file.delete();
				System.out.println("deleting...");
			}
		}
		return true;
	}


	/**
	 * 
	 * Method Name : wait
	 * Description : This method is to make the script wait for specific time being passed as a parameter
	 * Author      : VNatarajan
	 * Return Types: void
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */

	public void wait(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 
	 * Method Name : changeFileToHTMLFormat
	 * Description : This method is to convert the format of the file to html
	 * Author      : VNatarajan
	 * Return Types: void
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */

	public boolean changeFileToHTMLFormat(String dir,String fileName,String destFileName,String extension) {
		String downloads = settings.getDownloadFolder();
		File file = new File(dir+fileName+extension);
		try {
			FileUtils.cleanDirectory(new File(downloads));
			File htmlFile = new File(downloads+fileName+".html");
			FileUtils.copyFile(file, htmlFile);
			htmlFile.renameTo(new File(downloads+destFileName+".html"));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	/**
	 * 
	 * Method Name : changeFileFormat
	 * Description : This converts file format to  given format
	 * Author      : VNatarajan
	 * Return Types: void
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */

	public boolean changeFileFormat(String dir,final String fileName,String destFileName,String format) {
		String downloads = settings.getDownloadFolder();
		File sysDownloadDir = new File(dir);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.contains(fileName);
			}
		};

		String[] filteredFiles = sysDownloadDir.list(filter);
		File file = new File(dir+filteredFiles[0]);
		try {
			FileUtils.cleanDirectory(new File(downloads));
			File htmlFile = new File(downloads+fileName+"."+format);
			FileUtils.copyFile(file, htmlFile);
			htmlFile.renameTo(new File(downloads+destFileName+"."+format));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * 
	 * Method Name : convertXLSToHTML
	 * Description : This converts xls file format to  html format
	 * Author      : VNatarajan
	 * Return Types: void
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */


	public void convertXLSToHTML(String fileName,String sheetName) {
		String downloads = settings.getDownloadFolder();
		try {
			String value="";
			String cellValue="";
			String rowValue="";
			DataFormatter formatter = new DataFormatter(Locale.US);
			Workbook workbook = WorkbookFactory.create(new File(downloads+fileName+".xls"));
			Sheet worksheet = workbook.getSheet(sheetName);
			for (Row row : worksheet) {
				for(Cell cell:row) {
					value = "<td style='border: 1px solid black;'>"+formatter.formatCellValue(cell).toString().trim()+"</td>";
					if(value.length()<=0) {
						value = " ";
					}
					cellValue = cellValue + value;
				}
				cellValue = "<tr>"+cellValue+"</tr>";
				rowValue = rowValue + cellValue;
				cellValue = "";
			}

			FileWriter fstream = new FileWriter(downloads+fileName+".html");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("<table>"+rowValue+"</table>");
			out.close();
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 
	 * Method Name : getDateFrom
	 * Description : This add or subtract the number of days being passed in the current date.
	 * Author      : VNatarajan
	 * Return Types: void
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */

	
	public String getDateFrom(int dateAdd) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, dateAdd);
		return formatter.format(cal.getTime());
	}

}
