package test.java.tests.demoapp;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import test.java.support.enums.Menu;
import test.java.tests.TestSupporter;

public class LoginTests extends TestSupporter{

	/**
	 * 
	 * Method Name : validLoginTest
	 * Description : TestNG Test to verify login with valid email and password
	 * Author      : VNatarajan
	 * Return Types: void
	 * Date        : Feb 6, 2018
	 * Version     : 1.0
	 */
	
	@Test(enabled=true,priority=1,groups= {"login","smoke-test"},description="Valid Login Test")
	public void validLoginTest() {

		String email = data.get("Email");
		String password = data.get("Password");
		
		navigationMenu.navigateWithHeader(Menu.SIGN_IN);
		test.log(Status.INFO, "Navigating to Sign In page...",takeScreenshot());
		
		signInPage.signIn(email,password);
		test.log(Status.INFO, "Sign in email Id : "+email+" password : "+password,takeScreenshot());
		
	}

	
	/**
	 * 
	 * Method Name : invalidLoginTest
	 * Description : TestNG Test to verify login with invalid email or/and password
	 * Author      : VNatarajan
	 * Return Types: void
	 * Date        : Feb 6, 2018
	 * Version     : 1.0
	 */
	
	@Test(enabled=true,priority=1,groups= {"login","smoke-test"},description="Invalid Login Test")
	public void invalidLoginTest() {
	
		String email = data.get("Email");
		String password = data.get("Password");
		
		navigationMenu.navigateWithHeader(Menu.SIGN_IN);
		test.log(Status.INFO, "Navigating to Sign In page...",takeScreenshot());
		
		signInPage.inValidSignIn(email,password);
		test.log(Status.INFO, "Sign in email Id : "+email+" password : "+password,takeScreenshot());
		
	}
	
}
