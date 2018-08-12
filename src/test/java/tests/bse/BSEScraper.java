package tests.bse;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

public class BSEScraper extends tests.TestSupporter{

	/**
	 * 
	 * Method Name : validLoginTest
	 * Description : TestNG Test to verify login with valid email and password
	 * Author      : VNatarajan
	 * Return Types: void
	 * Date        : Feb 6, 2018
	 * Version     : 1.0
	 */
	//
	@Test(invocationCount=5,enabled=true,priority=1,groups= {"bse","scraper"},description="BSE Scraper")
	public void scraperBSE() {

		String stock = data.get("StockName");
		String startDate = data.get("StartDate");
		
		String code = historicalDataPage.search(stock, startDate);	
		historicalDataPage.moveFile(code, stock) ;
		test.log(Status.PASS, "Stock Name: "+stock,takeScreenshot());
	}

	
}
