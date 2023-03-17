package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import utilities.PropertyFileUtil;
/*Project Name:Stock Accounting
 *  Tester:Narendra
 * Created Date:11/3/2023
 * Module Name:All Modules
 * 
 */

public class FunctionLibrary {
	public static WebDriver driver;
//Method for Start Browser
	public static WebDriver startBrowser()throws Throwable
	{
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32 (4)\\chromedriver.exe");
			WebDriver driver= new ChromeDriver();
			driver.manage().window().maximize();
			
		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.firefox.driver"," C:\\Selenium Data\\chromedriver_win32 (4)\\chromedriver.exe");
			WebDriver driver = new FirefoxDriver();
		}
		else
		{
			Reporter.log("Browser value is Not Matching");
		}
		return driver;
	}
}
