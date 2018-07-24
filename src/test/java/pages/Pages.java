package test.java.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import test.java.pages.demoapp.NavigationMenu;
import test.java.pages.demoapp.RegistrationPage;
import test.java.pages.demoapp.SignInPage;

public class Pages {
	
	protected NavigationMenu navigationMenu;
	protected RegistrationPage registrationPage;
	protected SignInPage signInPage;
	
	/**
	 * 
	 * Method Name : setPages
	 * Return Type : void
	 * Author      : VNatarajan
	 * Date		   : Sep 29, 2017
	 * Description : Set pages to be used for the tests
	 * @return
	 */
	
	public void setPages(WebDriver driver) {
		//Initialize the pages
		navigationMenu = PageFactory.initElements(driver, NavigationMenu.class);
		registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
		signInPage = PageFactory.initElements(driver, SignInPage.class);
	}
}
