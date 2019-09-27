package autothon.webui.tests;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import autothon.webui.pages.BasePage;
import autothon.webui.utils.Reporting;

public class BaseTest {
	final static Logger logger = Logger.getLogger(BaseTest.class);
	
	 protected static Reporting oReporting = new Reporting();
	 protected static BasePage oBasepage = new BasePage();
	 
	 @BeforeMethod
	 @Parameters({"strBrowser"})
	  public void beforeMethod(@Optional Method method,String strBrowser) throws IOException {
		 oReporting.setTestCaseName(method.getName());
		 oBasepage.launchDriver(strBrowser);
	}

	  @AfterMethod
	  public void afterMethod() {
		  oBasepage.closeDriver();
	  }

	  @BeforeSuite
	  public void beforeSuite() {
		  oReporting.startReporting();
		  
		  
	  }

	  @AfterSuite
	  public void afterSuite() {
		  oReporting.endReporting();
	  }
}
