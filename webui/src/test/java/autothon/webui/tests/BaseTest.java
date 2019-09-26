package autothon.webui.tests;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import autothon.webui.pages.BasePage;
import autothon.webui.utils.Reporting;

public class BaseTest {
	 protected static Reporting oReporting = new Reporting();
	 protected static BasePage oBasepage = new BasePage();
	 @BeforeMethod
	  public void beforeMethod(Method method) {
		 oReporting.setTestCaseName(method.getName());
	  }

	  @AfterMethod
	  public void afterMethod() {
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
