package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.demoapp.NavigationMenu;
import pages.demoapp.RegistrationPage;
import pages.demoapp.SignInPage;


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
