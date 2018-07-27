package support;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.javascript.host.URL;

/**
 * File name   :UIDriver.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 */
public class UiDriver {
	Configurations configurations = Configurations.getInstance();
	WebDriver wDriver;
	EventFiringWebDriver eventDriver;
	/**
	 * 
	 * Method name  : getDriver
	 * Return types : WebDriver
	 * Description  :The method that returns the various driver instance based on the parameter
	 */
	public WebDriver getDriver(String driver){

		try{
			if(driver.equalsIgnoreCase("Firefox")){
				String geckoDriver=Settings.getInstance().getDriverEXEDir()+"geckodriver.exe";
				System.setProperty("webdriver.gecko.driver", geckoDriver);
				wDriver = new FirefoxDriver();
				wDriver.manage().window().maximize();
				return wDriver;
			}

			else if(driver.equalsIgnoreCase("Chrome")) {
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--dns-prefetch-disable");
				option.addArguments("--start-maximized");
				String chromeDriver=Settings.getInstance().getDriverEXEDir()+"chromedriver";
				System.setProperty("webdriver.chrome.driver", chromeDriver);
				wDriver = new ChromeDriver(option);
				return wDriver;
			}

			else if(driver.equalsIgnoreCase("rChrome")) {
				ChromeOptions options = new ChromeOptions();
				DesiredCapabilities dc = DesiredCapabilities.chrome();
				dc.setCapability(ChromeOptions.CAPABILITY, options);
				wDriver = new RemoteWebDriver(dc);
				return wDriver;
			}
			
			else if(driver.equalsIgnoreCase("rFirefox")) {
				FirefoxProfile fp = new FirefoxProfile();
				DesiredCapabilities dc = DesiredCapabilities.firefox();
				dc.setCapability(FirefoxDriver.PROFILE, fp);
				wDriver = new RemoteWebDriver(dc);
			}
			
			else if(driver.equalsIgnoreCase("IE")) {
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				//capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				capabilities.setCapability("ignoreZoomSetting", true);
				capabilities.setCapability("ignoreProtectedModeSettings", true);
				capabilities.setCapability("requireWindowFocus", true);
				//capabilities.setCapability(CapabilityType.BROWSER_VERSION, 4);
				String ieDriver=Settings.getInstance().getDriverEXEDir()+"IEDriverServer.exe";
				System.setProperty("webdriver.ie.driver", ieDriver);
				wDriver = new InternetExplorerDriver(capabilities);	
				wDriver.manage().window().maximize();
				return wDriver;
			}

			else if(driver.equalsIgnoreCase("Custom")) {

				/*Map<String, Object> chromePrefs = new HashMap<String, Object>();
			    chromePrefs.put("profile.default_content_settings.popups", 0);
			    chromePrefs.put("download.default_directory", Settings.getInstance().getDownloadFolder());
			    chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1 );
			    chromePrefs.put("download.prompt_for_download", false);*/
				Map<String, Object> chromePrefs = new HashMap<String, Object>();
				ChromeOptions options = new ChromeOptions();
			    chromePrefs.put("download.default_directory", Settings.getInstance().getDownloadFolder());
				//options.setExperimentalOption("prefs", chromePrefs);
				//options.setExperimentalOption("prefs", chromePrefs);
				//options.addArguments("user-data-dir=C:/Users/VNatarajan/AppData/Local/Google/Chrome/User Data");
				options.addArguments("--start-maximized");
				// DesiredCapabilities cap = DesiredCapabilities.chrome();
				// cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				// cap.setCapability(ChromeOptions.CAPABILITY, options);
				String chromeDriver=Settings.getInstance().getDriverEXEDir()+"chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", chromeDriver);
				wDriver = new ChromeDriver(options);
				return wDriver;

			}

			else if(driver.equalsIgnoreCase("Edge")) {
				//Yet to implement
			}

			else if(driver.equalsIgnoreCase("Safari")) {
				//Yet to implement
			}

			else if(driver.equalsIgnoreCase("HtmlUnitDriver")) {
				wDriver = new HtmlUnitDriver(BrowserVersion.BEST_SUPPORTED);
				((HtmlUnitDriver) wDriver).setJavascriptEnabled(true);
				return wDriver;
			}
			else {
				String geckoDriver1=Settings.getInstance().getDriverEXEDir()+"geckodriver.exe";
				System.setProperty("webdriver.gecko.driver", geckoDriver1);
				wDriver = new FirefoxDriver();
				return wDriver;
			}

		}catch(Exception e){
			e.printStackTrace();
			wDriver = new FirefoxDriver();
			return wDriver;
		}
		return wDriver;
	}


	/**
	 * 
	 * Method name  : quitDriver
	 * Return types : boolean
	 * Description  : This method is to quit the driver instance after completing a test
	 */

	public boolean quitDriver(WebDriver wDriver){
		try{
			wDriver.quit();
			return true;
		}catch(Exception e){
			return false;
		}
	}

}
