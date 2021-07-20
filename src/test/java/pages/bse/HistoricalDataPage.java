package pages.bse;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.Status;
import pages.PageSupporter;
import support.Log;


public class HistoricalDataPage  extends PageSupporter{
	WebDriver driver;
	public HistoricalDataPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}


	@FindBy(how=How.CSS,using="[id*='rad_no1']")
	private WebElement equity;

	@FindBy(how=How.CSS,using="[id*='rad_no2']")
	private WebElement etf;

	@FindBy(how=How.CSS,using="[name*='smartSearch']")
	private WebElement search;

	@FindBy(how=How.CSS,using="[name*='txtFromDate']")
	private WebElement txtFromDate;

	@FindBy(how=How.CSS,using="[name*='txtToDate']")
	private WebElement txtToDateCal;

	@FindBy(how=How.CSS,using="td.current a")
	private WebElement txtToDate;

	@FindBy(how=How.CSS,using="[name*='btnSubmit']")
	private WebElement btnSubmit;
//
	@FindBy(how=How.CSS,using="[name*='btnDownload1']")
	private WebElement btnDownload1;

	@FindBy(how=How.CSS,using="a.ui-corner-all")
	private WebElement menu;

	@FindBy(how=How.CSS,using="[id*='ScripCodeValue']")
	private WebElement code;

	@FindBy(how=How.ID,using="dataType")
	private WebElement dataType;
	
	@FindBy(how=How.ID,using="symbol")
	private WebElement symbol;
	
	@FindBy(how=How.ID,using="series")
	private WebElement series;
	
	@FindBy(how=How.CSS,using="div[data-test=dropdown][class*=date]")
	private WebElement dateRange;
	
	@FindBy(how=How.CSS,using="button[data-value='3_M']")
	private WebElement threeMonth;
	
	@FindBy(how=How.CSS,using="button[data-value='6_M']")
	private WebElement sixMonth;
	
	@FindBy(how=How.CSS,using="a[href*='download']")
	private WebElement download;
	
	public Log search(String stockId) {
		Log log = getLogger();
		String stockUrl = "https://in.finance.yahoo.com/quote/_REPLACE_.NS/history?p=_REPLACE_.NS";
		stockUrl = stockUrl.replaceAll("_REPLACE_", stockId);
		//Actions action = new Actions(action)
		try {
			
			driver.get(stockUrl);
			waitFor(dateRange,90);
			dateRange.click();
			waitFor(sixMonth, 60);
			sixMonth.click();
			wait(5);
			download.click();
			wait(15);
			log.message = stockId;
			log.status =Status.PASS;
		}catch(Exception e) {
			e.printStackTrace();
			log.message = stockId;
			log.status =Status.FAIL;	
		}
		return log;
	}


	public boolean moveFile(String stockName,String scrapeDest) {

		String fileName = stockName.trim()+".NS.csv";
		// File (or directory) with old name
		File file = new File("/Users/vm/Downloads/"+fileName);

		// File (or directory) with new name
		File file2 = new File(scrapeDest+stockName.trim()+".csv");

		// Rename file (or directory)
		boolean success = file.renameTo(file2);

		if (!success) {
			return false;
		}
		return true;
	}


	@FindBy(how=How.ID,using="fdate1")
	private WebElement day;

	@FindBy(how=How.ID,using="fmonth1")
	private WebElement month;

	@FindBy(how=How.ID,using="fyear1")
	private WebElement year;

	@FindBy(how=How.ID,using="btnSubmit")
	private WebElement submit;

	@FindBy(how=How.ID,using="btnHylSearBhav")
	private WebElement downloadLink;

	@FindBy(how=How.CSS,using="iframe[src*='Equitydebcopy']")
	private WebElement iframe;


	public boolean dailySearch(String date) {

		String[] sDate = date.split("-");
		waitFor(iframe,60);
		driver.switchTo().frame(iframe);
		wait(3);
		Select select = new Select(day);
		select.selectByVisibleText(sDate[0]);
		wait(2);
		select = new Select(month);
		if(sDate[1].substring(0, 1).equalsIgnoreCase("0")) {
			sDate[1] = sDate[1].substring(1, 2);
			wait(1);
		}
		select.selectByValue(sDate[1]);
		wait(2);
		select = new Select(year);
		select.selectByVisibleText(sDate[2]);
		wait(2);
		submit.click();
		waitFor(downloadLink,30);
		downloadLink.click();
		wait(20);
		return true;
	}


	public boolean moveFileDup(String date,String scrapeDest) {
		String[] sDate = date.split("-");
		String fileName = sDate[0]+sDate[1]+sDate[2].substring(2, 4);
		fileName = "EQ"+fileName+"_CSV.zip";
		// File (or directory) with old name
		File file = new File("/Users/veera/Downloads/"+fileName);

		// File (or directory) with new name
		File file2 = new File(scrapeDest+fileName);

		// Rename file (or directory)
		boolean success = file.renameTo(file2);

		if (!success) {
			return false;
		}
		return true;
	}
}
