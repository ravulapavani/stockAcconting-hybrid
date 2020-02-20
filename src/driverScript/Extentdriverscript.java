package driverScript;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonfunctionlibrary.Functionlibrary;

public class Extentdriverscript {

	public static void main(String[] args) throws Exception{
	
		ExtentReports report=new ExtentReports("C:\\Users\\pavanir\\Desktop\\myreport.html");
	    
         ExtentTest writer=report.startTest("login test");
        
        
		
		   WebDriver driver1=Functionlibrary.startBrowser();
		   
		   writer.log(LogStatus.INFO,"chrome opened");
		   Functionlibrary.openApplication(driver1);
		  
			writer.log(LogStatus.INFO,"waiting for username");
			Functionlibrary.waitForElement(driver1, "id","username","10");
			
			 writer.log(LogStatus.INFO,"entering admin into username");
			 Functionlibrary.typeAction(driver1,"id","username","admin");
			
			  writer.log(LogStatus.INFO,"waiting for password");
			 Functionlibrary.waitForElement(driver1, "name","password","10");
			
			 writer.log(LogStatus.INFO,"entering master into password");
			Functionlibrary.typeAction(driver1, "name","password","master");
			 
			writer.log(LogStatus.INFO,"waiting login button");
			 Functionlibrary.waitForElement(driver1, "id","btnsubmit","10");
			
			 writer.log(LogStatus.INFO,"waiting for element");
				Functionlibrary.clickAction(driver1, "id", "btnsubmit");	
				
				 writer.log(LogStatus.INFO,"clicking on login button");
				 
				 writer.log(LogStatus.PASS,"Execution completed");
				 
				  report.endTest(writer);
				
				 report.flush();
				 
			
			
	}

}
