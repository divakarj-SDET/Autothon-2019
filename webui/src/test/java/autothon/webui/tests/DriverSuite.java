package autothon.webui.tests;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class DriverSuite extends BaseTest{
	
  @Test
  @Parameters({"strBrowser"})
  public void FirstTest(String strBrowser) throws IOException {
	  oBasepage.launchDriver(strBrowser);
	  
  }
 

}
