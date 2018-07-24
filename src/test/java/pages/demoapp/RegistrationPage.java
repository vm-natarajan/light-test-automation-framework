package test.java.pages.demoapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import junit.framework.Assert;
import test.java.pages.PageSupporter;

public class RegistrationPage  extends PageSupporter{
	WebDriver driver;
	public RegistrationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}


	@FindBy(how=How.ID,using="id_gender1")
	private WebElement mr;

	@FindBy(how=How.ID,using="id_gender2")
	private WebElement mrs;

	@FindBy(how=How.ID,using="customer_firstname")
	private WebElement firstName;

	@FindBy(how=How.CSS,using="#customer_lastname")
	private WebElement lastName;

	@FindBy(how=How.XPATH,using="//input[@id='email']")
	private WebElement email;

	@FindBy(how=How.ID,using="passwd")
	private WebElement password;

	@FindBy(how=How.NAME,using="days")
	private WebElement days;

	@FindBy(how=How.ID,using="months")
	private WebElement months;

	@FindBy(how=How.ID,using="years")
	private WebElement years;


	/**
	 * 
	 * Method Name : enterPersonalInformation
	 * Return Type : void
	 * Author      : VNatarajan
	 * Date		   : Feb 01, 2018
	 * Description : This enters all required fields in the address section of the registration page
	 * 
	 */

	public void enterPersonalInformation(String title,String firstName1,String lastName1,String email1,String password1) {

		waitFor(mr,30);
		if(title.equals("Mrs")) {
			mrs.click();
		}else {
			mr.click();
		}
		firstName.sendKeys(firstName1);
		lastName.sendKeys(lastName1);
		email.clear();
		email.sendKeys(email1);
		password.sendKeys(password1);

		String day = testData.number().numberBetween(1, 28)+"";
		selectDropdownByValue(days,day);

		selectDropdownByVisibleText(months,"March ");

		String year = testData.number().numberBetween(1980, 2000)+"";
		selectDropdownByValue(years,year);

	}

	@FindBy(how=How.ID,using="newsletter")
	private WebElement newsletter;

	/**
	 * 
	 * Method Name : signUpForNewsletter
	 * Return Type : void
	 * Author      : VNatarajan
	 * Date		   : Feb 01, 2018
	 * Description : This enables signup for newsletter during registration
	 * 
	 */

	public boolean signUpForNewsletter(String newsletter1) {
		if(newsletter1.equals("Yes")){
			newsletter.click();
			return true;
		}
		return false;
	}


	@FindBy(how=How.ID,using="optin")
	private WebElement optin;

	/**
	 * 
	 * Method Name : optInForSpecialOffers
	 * Return Type : void
	 * Author      : VNatarajan
	 * Date		   : Feb 01, 2018
	 * Description : This enables optin for special offers during registration
	 * 
	 */

	public boolean optInForSpecialOffers(String specialOffers) {
		if(specialOffers.equals("Yes")){
			optin.click();
			return true;
		}
		return false;
	}

	@FindBy(how=How.CSS,using="#firstname")
	private WebElement addressFirstname;

	@FindBy(how=How.CSS,using="#lastname")
	private WebElement addresslastname;

	@FindBy(how=How.CSS,using="#company")
	private WebElement company;

	@FindBy(how=How.CSS,using="#address1")
	private WebElement address1;

	@FindBy(how=How.CSS,using="#address2")
	private WebElement address2;

	@FindBy(how=How.CSS,using="#city")
	private WebElement city;

	@FindBy(how=How.CSS,using="#id_state")
	private WebElement state;

	@FindBy(how=How.CSS,using="#postcode")
	private WebElement postcode;

	@FindBy(how=How.CSS,using="#id_country")
	private WebElement country;

	@FindBy(how=How.CSS,using="#other")
	private WebElement additionalInfo;

	@FindBy(how=How.CSS,using="#phone_mobile")
	private WebElement mobile;


	/**
	 * 
	 * Method Name : enterYourAddress
	 * Return Type : void
	 * Author      : VNatarajan
	 * Date		   : Feb 01, 2018
	 * Description : This enters all required fields in the address section of the registration page
	 * 
	 */

	public void enterYourAddress(String firstName1,String lastName1,String company1,String addresss1,String city1,String state1,String zip1,String country1,String mobile1) {

		addressFirstname.clear();
		addressFirstname.sendKeys(firstName1);
		addresslastname.clear();
		addresslastname.sendKeys(lastName1);
		company.sendKeys(company1);
		address1.sendKeys(addresss1);
		city.sendKeys(city1);
		state.sendKeys(state1);
		postcode.sendKeys(zip1);
		country.sendKeys(country1);
		additionalInfo.sendKeys("Additional Info updated by automated script");
		mobile.sendKeys(mobile1);

	}


	@FindBy(how=How.CSS,using="#phone")
	private WebElement phone;

	/**
	 * 
	 * Method Name : enterHomePhone
	 * Return Type : void
	 * Author      : VNatarajan
	 * Date		   : Feb 01, 2018
	 * Description : This enters home phone in the registration page
	 * 
	 */

	public void enterHomePhone(String homePhoneNumber) {
		phone.sendKeys(homePhoneNumber);
	}

	@FindBy(how=How.CSS,using="#alias")
	private WebElement alias;

	/**
	 * 
	 * Method Name : createAliasForReference
	 * Return Type : Alias name
	 * Author      : VNatarajan
	 * Date		   : Feb 01, 2018
	 * Description : This creates alias name and uses it in the registration
	 *  
	 */

	public String createAliasForReference() {
		String aliasName = testData.superhero().name();
		alias.sendKeys(aliasName);
		return aliasName;
	}


	@FindBy(how=How.CSS,using="#submitAccount")
	private WebElement register;

	@FindBy(how=How.CSS,using="[title*='Information']")
	private WebElement info;


	/**
	 * 
	 * Method Name : register
	 * Return Type : void
	 * Author      : VNatarajan
	 * Date		   : Feb 01, 2018
	 * Description : This registers the new account
	 * 
	 */

	public void register() {
		register.click();
		waitFor(info,30);
		Assert.assertTrue(info.isDisplayed());
	}
}
