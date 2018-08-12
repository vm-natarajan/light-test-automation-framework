package pages.bse;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import junit.framework.Assert;
import pages.PageSupporter;


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

	@FindBy(how=How.CSS,using="[name*='btnDownload1']")
	private WebElement btnDownload1;
	
	@FindBy(how=How.CSS,using="a.ui-corner-all")
	private WebElement menu;
	
	@FindBy(how=How.CSS,using="[id*='ScripCodeValue']")
	private WebElement code;
	
	public String search(String stockName,String startDate) {
		
		Actions action = new Actions(driver);
		waitFor(search,60);
		etf.click();
		wait(1);
		equity.click();
		wait(1);
		search.click();
		wait(1);
		action.sendKeys(stockName).build().perform();
		wait(5);
		//search.sendKeys(stockName);
		menu.click();
		wait(1);
		txtFromDate.sendKeys(startDate);
		txtToDateCal.click();
		wait(1);
		txtToDate.click();
		wait(1);
		btnSubmit.click();
		wait(1);
		waitFor(btnDownload1,90);
		btnDownload1.click();
		wait(20);
		return code.getText();
	}


	public boolean moveFile(String code,String stockName) {
		
		String fileName = code.trim()+".csv";
		// File (or directory) with old name
		File file = new File("/Users/veera/Downloads/"+fileName);

		// File (or directory) with new name
		File file2 = new File("/Users/veera/d-science/bse/src/"+stockName.trim()+".csv");

		
		// Rename file (or directory)
		boolean success = file.renameTo(file2);

		if (!success) {
			return false;
		}
		return true;
	}
	
	
}
