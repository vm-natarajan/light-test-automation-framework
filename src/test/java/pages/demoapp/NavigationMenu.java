package test.java.pages.demoapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.java.pages.PageSupporter;
import test.java.support.enums.Menu;


public class NavigationMenu  extends PageSupporter {

	WebDriver driver;
	public NavigationMenu(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	public void navigateWithMenu(Menu menuName) {

		WebElement menu = driver.findElement(By.xpath("//*[contains(text(),'"+menuName+"')]"));
		waitFor(menu,30);
		scrollToElement(menu);
		menu.click();
		
	}


	public void navigateWithSubMenu(Menu subMenuName) {

		WebElement menu = driver.findElement(By.xpath("//*[contains(text(),'"+subMenuName+"')]"));
		waitFor(menu,30);
		scrollToElement(menu);
		menu.click();
		
	}

	public void navigateWithHeader(Menu headerName) {

		WebElement menu = driver.findElement(By.xpath("//*[contains(text(),'"+headerName+"')]"));
		waitFor(menu,30);
		scrollToElement(menu);
		menu.click();
		
	}
}
