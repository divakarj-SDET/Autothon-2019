package autothon.webui.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class DriverSuite extends BaseTest{
	
  @Test
  public void FirstTest() throws IOException, InterruptedException {
	  
	  oBasepage.getDriver().get("https://www.google.com/ncr");
      WebElement element = oBasepage.getDriver().findElement(By.name("q"));
      element.sendKeys("BrowserStack");
      element.submit();
      Thread.sleep(5000);

      Assert.assertEquals("BrowserStack - Google Search", oBasepage.getDriver().getTitle());
	  
  }
  
  
 

}
