package pages;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.github.javafaker.Faker;
import support.DBConnection;
import support.Log;
import support.Settings;
/**
 * 
 * @author         :VNatarajan
 * @since          :Sep 20, 2017
 * @filename       :PageSupporter.java
 * @version		   :1.0
 * @description    :The class to provide to common tests features.This needs to be extended by all page classes
 */
public class PageSupporter {
	protected Settings settings = Settings.getInstance();
	protected DBConnection dbConnection = new DBConnection();
	WebDriverWait wait;
	protected WebDriver driver;
	protected Faker testData = new Faker();
	public PageSupporter(WebDriver driver) {
		this.driver = driver;
	}



	/**
	 * 
	 * Method Name : waitFor
	 * Description : To wait for an element for a specified time
	 * Author      : VNatarajan
	 * Return Types: boolean
	 * Paramters   : PageSupporter
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */

	public boolean waitFor(WebElement element,int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * Method Name : waitFor
	 * Description : To wait for a list of elements for a specified time
	 * Author      : VNatarajan
	 * Return Types: boolean
	 * Paramters   : PageSupporter
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */

	public boolean waitFor(List<WebElement> elements,int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		try {
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * Method Name : timeout
	 * Description : To set the implicit wait time by passing seconds as parameter
	 * Author      : VNatarajan
	 * Return Types: void
	 * Paramters   : PageSupporter
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */

	public void timeout(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	/**
	 * 
	 * Method Name : wait
	 * Description : Wait for the given time period in seconds
	 * Author      : VNatarajan
	 * Return Types: void
	 * Paramters   : PageSupporter
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
	 * Method Name : waitUntil
	 * Description : This 
	 * Author      : VNatarajan
	 * Return Types: void
	 * Paramters   : element to wait for and how long to wait for
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */

	public void waitUntil(By element,int seconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		}catch(Exception e) {

		}
	}


	/**
	 * 
	 * Method Name : pageLoadWait
	 * Description : Wait for the page to load
	 * Author      : VNatarajan
	 * Return Types: void
	 * Paramters   : PageSupporter
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */

	public void pageLoadWait(WebDriver driver,int seconds) {				

		JavascriptExecutor je = (JavascriptExecutor) driver;
		String page_state="";
		System.out.println("Waiting for the page to load...");

		while(((!page_state.equalsIgnoreCase("complete"))||(!page_state.contains("complete")))&&(seconds<0)){

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			page_state=(String) je.executeScript("return document.readyState");
			System.out.println("..."+page_state+"...");
			System.out.println(((!page_state.equalsIgnoreCase("complete"))||(!page_state.contains("complete")))&&(seconds>0));
			seconds--;
		}		
	}


	/**
	 * 
	 * Method Name : randomNumber
	 * Description : This generates the random number,by taking the length of that number as parameter
	 * Author      : VNatarajan
	 * Return Types: String
	 * Parameters   : Length of the random random number
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
	 * The method is to scroll down at the end of the page using Javascript
	 */
	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("scroll(0, 1600);");
	}

	/**
	 * 
	 * Method Name : scrollToElement
	 * Description : This scrolls the focus to the element being passed as the parameter
	 * Author      : VNatarajan
	 * Return Types: void
	 * Parameters   : element 
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */

	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int yaxis = element.getLocation().getY();
		int height = element.getSize().getHeight();
		int scrollHeight = yaxis-height;
		js.executeScript("scroll(0, "+scrollHeight+");");
		wait(1);
	}

	/**
	 * 
	 * Method Name : scrollToElement
	 * Description : This scrolls the focus to the element being passed as the parameter and offset the height by scrolling up
	 * Author      : VNatarajan
	 * Return Types: void
	 * Paramters   : PageSupporter
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */

	public void scrollToElement(WebElement element,int heightOffset) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int yaxis = element.getLocation().getY();
		int height = element.getSize().getHeight();
		int scrollHeight = yaxis-height-heightOffset;
		js.executeScript("scroll(0, "+scrollHeight+");");
		wait(1);
	}

	/**
	 * 
	 * Method Name : switchToNewWindow
	 * Description : This method switch the focus to newly opened window.
	 * Author      : VNatarajan
	 * Return Types: void
	 * Paramters   : PageSupporter
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */

	public void switchToNewWindow() {

		// Switch to new window opened
		for(String window : driver.getWindowHandles()){
			driver.switchTo().window(window);
		}
	}

	/**
	 * 
	 * Method Name : switchToParentWindow
	 * Description : It changes focus to parent window
	 * Author      : VNatarajan
	 * Return Types: void
	 * Paramters   : PageSupporter
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */

	public void switchToParentWindow() {
		// Switch to new window opened
		for(String window : driver.getWindowHandles()){
			driver.switchTo().window(window);
			driver.findElement(By.tagName("body")).click();
		}
	}

	/**
	 * 
	 * Method Name : switchToParentWindow
	 * Description : It changes focus to parent window
	 * Author      : VNatarajan
	 * Return Types: void
	 * Paramters   : PageSupporter
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */
	
	public void clickUsingJavascript(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);

	}


	/**
	 * 
	 * Method Name : switchToParentWindow
	 * Description : It changes focus to parent window
	 * Author      : VNatarajan
	 * Return Types: void
	 * Paramters   : PageSupporter
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */
	
	public void waitForText(By element,int seconds,String text) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(element, text));
		}catch(Exception e) {

		}
	}


	/**
	 * 
	 * Method Name : selectDropdownByValue
	 * Description : It selects drop down values given the value of the dropdown
	 * Author      : VNatarajan
	 * Return Types: void
	 * Paramters   : PageSupporter
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */
	
	public void selectDropdownByValue(WebElement element,String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * 
	 * Method Name : selectDropdownByVisibleText
	 * Description : It selects drop down values given the visible text of the dropdown
	 * Author      : VNatarajan
	 * Return Types: void
	 * Paramters   : PageSupporter
	 * Date        : Nov 6, 2017
	 * Version     : 1.0
	 */
	
	public void selectDropdownByVisibleText(WebElement element,String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}


	public synchronized static Log getLogger() {
		return new Log();
	}
}
