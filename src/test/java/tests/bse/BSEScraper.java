package tests.bse;

import org.testng.annotations.Test;

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
	@Test(invocationCount=501,enabled=true,priority=1,groups= {"dump","scraper"},description="NSE Scraper")
	public void scraperBSE() {

		String stockId = data.get("StockId");
		String scrapeDest = "/Users/vm/nse/scrape/src/";
		Log status = historicalDataPage.search(stockId);	
		historicalDataPage.moveFile( stockId,scrapeDest) ;
		test.log(status.status, "Stock Name: "+stockId,takeScreenshot());
		
	}

}
