package autothon.webui.tests.browserstack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests extends BrowserStackTestNGTest {
  @Test
  public void f() throws InterruptedException {
	  driver.get("https://www.google.com/ncr");
      WebElement element = driver.findElement(By.name("q"));
      element.sendKeys("BrowserStack");
      element.click();
      Thread.sleep(5000);

      Assert.assertEquals("BrowserStack - Google Search", driver.getTitle());
  }
}
