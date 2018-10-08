package tests.bse;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import support.Log;

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
	@Test(invocationCount=11,enabled=true,priority=1,groups= {"dump","scraper"},description="BSE Scraper")
	public void scraperBSE() {

		String stockCode = data.get("StockCode");
		String stockId = data.get("StockId");
		String startDate = data.get("StartDate");
		String scrapeDest = "/Users/veera/d-science/bse/scrape/dump/src/";
		
		Log status = historicalDataPage.search(stockCode, stockId,startDate);	
		historicalDataPage.moveFile(stockCode, stockId,scrapeDest) ;
		test.log(status.status, "Stock Name: "+stockId,takeScreenshot());
	}

}
