package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
	// 1) Method for Start Browser
	public static WebDriver startBrowser()throws Throwable
	{
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32 (4)\\chromedriver.exe");
			WebDriver driver= new ChromeDriver();
			driver.manage().window().maximize();

		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver"," C:\\Selenium Data\\geckodriver_win32 (4)\\firefoxdriver.exe");
			WebDriver driver = new FirefoxDriver();
		}
		else
		{
			Reporter.log("Browser value is Not Matching");
		}
		return driver;
	}
	// 2) method for Open Application
	public static void applicationUrl(WebDriver driver) throws Throwable {
		driver.get(PropertyFileUtil.getValueForKey("Url"));


	}
	// 3) method for Wait for Element
	public static void waitforElement(WebDriver driver, String LocatorType, String LocatorValue, String MyWait)
	{
		WebDriverWait myWait = new WebDriverWait(driver, Integer.parseInt(MyWait));
		if(LocatorType.equalsIgnoreCase("xpath"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));


		}
		else if(LocatorType.equalsIgnoreCase("name"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));

		}
		else if(LocatorType.equalsIgnoreCase("id"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));

		}
	}
	// 4) method for Type Action
	public static void typeAction(WebDriver driver, String LocatorType, String LocatorValue, String TestData)
	{
		if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).clear();
			driver.findElement(By.name(LocatorValue)).sendKeys(TestData);
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).clear();
			driver.findElement(By.xpath(LocatorValue)).sendKeys(TestData);
		}	
		else if(LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).clear();
			driver.findElement(By.id(LocatorValue)).sendKeys(TestData);
		}
	}
	// 5) method for click Action
	public static void clickAction(WebDriver driver, String LocatorType, String LocatorValue)
	{
		if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).click();
		}
		else if (LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).click();

		}
		else if (LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).sendKeys(Keys.ENTER);

		}

	}
	// 6) method for validate Page Title
	public static void validateTitle(WebDriver driver, String ExpectedTitle)
	{
		String actualtitle = driver.getTitle();
		try {
			Assert.assertEquals(actualtitle, ExpectedTitle,"Title is Not Matching");
		}catch(Throwable t)
		{
			System.out.println(t.getMessage());
		}
	}
	// 7) method for close Browser
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}

}




