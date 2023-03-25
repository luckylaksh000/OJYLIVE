package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript { 
	public static WebDriver driver;
	String InputPath ="C:\\OJTLiveProject\\ERP_StockAccountingHybridFrame\\FileInput\\DataEngine.xlsx";
	String OutputPath = "C:\\OJTLiveProject\\ERP_StockAccountingHybridFrame\\FileOutput\\HybridResults.xlsx";
	public void startTest()throws Throwable
	{
		String ModuleStatus ="";
		ExcelFileUtil xl = new ExcelFileUtil(InputPath);
		//iterate all rows in mastertestcases
		for(int i=1;i<=xl.rowCount("MasterTestCases");i++)
		{
			//read cell
			String Execution_Status = xl.getCellData("MasterTestCases",	i,2);
			if(Execution_Status.equalsIgnoreCase("y")) {
				String TCModule = xl.getCellData("MasterTestCases", i, 1);
				//iterate all rows in TCModule
				for(int j=1; j<=xl.rowCount("TCModule");j++)
				{
					String Description = xl.getCellData("TCModule", j, 0);
					String Object_Type = xl.getCellData("TCModule", j, 1);
					String Locator_Type =xl.getCellData("TCModule", j, 2);
					String Locator_Value=xl.getCellData("TCModule", j, 3);
					String Test_Data =xl.getCellData("TCModule", j, 4);
					try {
						if(Object_Type.equalsIgnoreCase("startBrowser")) {
							System.setProperty("webdriver.chrome.driver", "C:\\Selenium Data\\chromedriver_win32 (4)\\ChromeDriver.exe");
							WebDriver driver = new ChromeDriver();
							driver= FunctionLibrary.startBrowser();
						}
						else if(Object_Type.equalsIgnoreCase("applicationUrl")) {
							FunctionLibrary.applicationUrl(driver);
						}
						else if(Object_Type.equalsIgnoreCase("waitforElement")) {
							FunctionLibrary.waitforElement(driver, Locator_Type, Locator_Value, Test_Data);
						}
						else if(Object_Type.equalsIgnoreCase("typeAction")) {
							FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
						}
						else if (Object_Type.equalsIgnoreCase("clickAction")) {
							FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
						}
						else if(Object_Type.equalsIgnoreCase("validateTitle")) {
							FunctionLibrary.validateTitle(driver, Test_Data);
						}
						else if (Object_Type.equalsIgnoreCase("closeBrowser")) {
							FunctionLibrary.closeBrowser(driver);
						}
						//write as pass
						xl.setCellData(TCModule, j, 5, "Pass", OutputPath);
						ModuleStatus ="True";
						
					}catch(Throwable t) {
						System.out.println(t.getMessage());
						//write as Fail
						xl.setCellData(TCModule, j, 5, "Fail", OutputPath);
						ModuleStatus = "False";
						
					}
					if(ModuleStatus.equalsIgnoreCase("True")) {
						xl.setCellData("MasterTestCases", i, 3, "Pass",	 OutputPath);
						
					}
					else {
						xl.setCellData("MasterTestCases", i, 3, "Fail", OutputPath);
					}	
						
				}
				
			}
			else {
				//write as bloceked into mastertestcase sheet which r flag to n
				xl.setCellData("MasterTestCases", i, 3, "blocked", OutputPath);
			}
		}
		
	}
	
}
