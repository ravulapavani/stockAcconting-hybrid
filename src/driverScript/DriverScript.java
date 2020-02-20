package driverScript;



import java.io.File;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonfunctionlibrary.Functionlibrary;
import utilities.Excelfileutilities;

public class DriverScript {

	public static WebDriver driver;
	static ExtentReports report;
	static ExtentTest test;
	
	public static void main(String[] args) throws Exception {
	
		Excelfileutilities excel=new Excelfileutilities();
		
		for(int i=1;i<=excel.rowCount("MasterTestCases");i++){
			
			String ModuleStatus="";
			
			if(excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y")){
				String TCModule=excel.getData("MasterTestCases", i, 1);
			
				report=new ExtentReports("D:\\Batch82\\StockAccounting_Hybrid\\Reports\\"+TCModule+Functionlibrary.generateDate()+".html");
				test=report.startTest(TCModule);
				for(int j=1;j<=excel.rowCount(TCModule);j++){
					
				    String Description=excel.getData(TCModule, j, 0);
					String Function_Name=excel.getData(TCModule, j, 1);
					String	Locator_Type=excel.getData(TCModule, j, 2);
					String Locator_Value=excel.getData(TCModule, j, 3);
					String Test_Data=excel.getData(TCModule, j, 4);
					try{
						if(Function_Name.equalsIgnoreCase("startBrowser")){
						      driver=Functionlibrary.startBrowser();
						      test.log(LogStatus.INFO, Description);
						}else if(Function_Name.equalsIgnoreCase("openApplication")){
							 Functionlibrary.openApplication(driver);
							 test.log(LogStatus.INFO, Description);
						}else if(Function_Name.equalsIgnoreCase("waitForElement")){
							Functionlibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
							test.log(LogStatus.INFO, Description);
						}else if(Function_Name.equalsIgnoreCase("typeAction")){
							Functionlibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
							test.log(LogStatus.INFO, Description);
						}else if(Function_Name.equalsIgnoreCase("clickAction")){
							Functionlibrary.clickAction(driver, Locator_Type, Locator_Value);
							test.log(LogStatus.INFO, Description);
						}else if(Function_Name.equalsIgnoreCase("closeBrowser")){
							Functionlibrary.closeBrowser(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("captureData")){
							Functionlibrary.captureData(driver,  Locator_Type, Locator_Value);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("tableValidation")){
							Functionlibrary.tableValidation(driver, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						excel.setData(TCModule, j, 5, "PASS");
						ModuleStatus="PASS";
						test.log(LogStatus.PASS, Description);
						
					}catch(Exception e){
						System.out.println("the exception is ");
						e.printStackTrace();
						excel.setData(TCModule, j, 5, "FAIL");
						ModuleStatus="FAIL";
						String reqdate=Functionlibrary.generateDate();
						
						File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(srcFile, new File("D:\\pavani_82\\StockAccounting_Hybrid\\Screenshots\\"+Description+reqdate+".png"));
					   
						test.log(LogStatus.FAIL,Description);
					   
					    test.log(LogStatus.INFO, test.addScreenCapture("D:\\pavani_82\\StockAccounting_Hybrid\\Screenshots\\"+Description+reqdate+".png"));
						
					    break;
					}
				}
				
				if(ModuleStatus.equalsIgnoreCase("Pass")){
					excel.setData("MasterTestCases",i,3,"Pass");
				}else{
					excel.setData("MasterTestCases",i,3,"Fail");
				}
				
				
			}else{
				excel.setData("MasterTestCases", i, 3,"Not executed");
			}
			
		}

	}

}
