package tests.demoapp;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import support.enums.Menu;
import tests.TestSupporter;

public class RegistrationTests extends TestSupporter{

	/**
	 * 
	 * Method Name : newRegistrationTest
	 * Description : TestNG Test to create new customer
	 * Author      : VNatarajan
	 * Return Types: void
	 * Parameters  : none
	 * Date        : Feb 6, 2018
	 * Version     : 1.0
	 */
	
	@Test(enabled=true,priority=1,groups= {"registration","smoke-test"},description="New Registration Test")
	public void newRegistrationTest() {

		String title = data.get("Title");
		String firstName = data.get("FirstName");
		String lastName = data.get("LastName");
		String email = data.get("Email");
		String password = data.get("Password");
		String newsletter = data.get("Newsletter");
		String specialOffers = data.get("SpecialOffers");
		String company = data.get("Company");
		String address1 = data.get("Address_1");
		String city = data.get("City");
		String state = data.get("State");
		String zip = data.get("Zip");
		String country = data.get("Country");
		String mobile = data.get("MobileNumber");
		
		navigationMenu.navigateWithHeader(Menu.SIGN_IN);
		test.log(Status.INFO, "Navigating to Sign In page...",takeScreenshot());
		
		email = signInPage.createNewAccount(email);
		test.log(Status.INFO, "Creating New Account using email Id : "+email,takeScreenshot());
		
		registrationPage.enterPersonalInformation(title, firstName, lastName, email,password);
		test.log(Status.INFO, "Entering personal information such as title,firstname,lastname and email",takeScreenshot());
		
		registrationPage.signUpForNewsletter(newsletter);
		test.log(Status.INFO, "Signing up for newsletter while registering",takeScreenshot());
		
		registrationPage.optInForSpecialOffers(specialOffers);
		test.log(Status.INFO, "Opting in for special offers while registering",takeScreenshot());
		
		registrationPage.enterYourAddress(firstName,lastName,company,address1,city,state,zip,country,mobile);
		test.log(Status.INFO, "Entering address information such as firstname,lastname,address and mobile...",takeScreenshot());
		
		String alias = registrationPage.createAliasForReference();
		test.log(Status.INFO, "Creating alias name as "+alias,takeScreenshot());
		
		registrationPage.register();
		test.log(Status.PASS, "Registering as a new customer",takeScreenshot());
		
	}

}
