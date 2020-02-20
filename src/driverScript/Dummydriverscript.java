package driverScript;


import org.openqa.selenium.WebDriver;

import commonfunctionlibrary.Functionlibrary;

public class Dummydriverscript {

	public static void main(String[] args) throws Exception {
		
		WebDriver driver1=Functionlibrary.startBrowser();
		Functionlibrary.openApplication(driver1);
		Functionlibrary.waitForElement(driver1, "id","username","10");
		Functionlibrary.typeAction(driver1,"id","username","admin");
		Functionlibrary.waitForElement(driver1, "name","password","10");
		Functionlibrary.typeAction(driver1, "name","password","master");
		Functionlibrary.waitForElement(driver1, "id","btnsubmit","10");
		Functionlibrary.clickAction(driver1, "id", "btnsubmit");		
		
		
		

	}

}
