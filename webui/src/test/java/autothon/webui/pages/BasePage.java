package autothon.webui.pages;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import autothon.webui.utils.GenericUtils;
import autothon.webui.utils.Reporting;

public class BasePage {

	private static WebDriver driver;
    
	private static String strDriverPath = System.getProperty("user.dir")+"\\resources\\Drivers";

    public BasePage() {
    	PageFactory.initElements(driver, this);
    }
    
    
    public void setDriver(WebDriver driver) {
    	this.driver = driver;
    	
    }
    
    /**
     * This method returns the driver
     * @return
     */
    public  WebDriver getDriver() {
    	return driver;
    	
    }

    /**
     * This method close and quite the driver in execution
     */
    public void closeDriver(){
        driver.close();
        driver.quit();
    }
    
    /**
     * This method will launch browser for action. Accepted parameters are ie,chrome,firefox
     * @param strBrowser
     * @throws IOException
     */
    public void launchDriver(String strBrowser) throws IOException {
        
        try {

            switch(strBrowser.toUpperCase()){
                case "CHROME":
                    System.setProperty("webdriver.chrome.driver", strDriverPath+"//chromedriver.exe");
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                    Reporting.PassTest(strBrowser+" should open", "Browser successfully launched");
                    break;
                case "IE":
                	System.setProperty("webdriver.ie.driver", strDriverPath+"//IEDriverServer.exe");
                	DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
                	capability.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                	capability.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, 1); 
                	capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                	capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);        
                	driver = new InternetExplorerDriver(capability);
                	break;
                default :
                    Reporting.FailTest(strBrowser+" should open", "Invalid browser name");
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            Reporting.FailTest(strBrowser+" should open", e.getMessage());
        }

    }
    

    protected void validateandSendKeys(WebElement element, String strTestBoxName, String strtextBoxValue) throws IOException {
        try {
            if(element!=null) {
                element.clear();
                element.sendKeys(strtextBoxValue);
                System.out.println("Value entered in"+strTestBoxName+" is "+strtextBoxValue);
                Reporting.PassTest(strTestBoxName+" should be filled with value: "+strtextBoxValue, "Value is usccessfully filled");
            }
            else
                Reporting.FailTest(strTestBoxName+" should be filled with value: "+strtextBoxValue, strTestBoxName+" is not displayed");
        }
        catch(Exception e) {
            Reporting.FailTest(strTestBoxName+" should be filled with value: "+strtextBoxValue, e.getMessage());
        }
    }

    protected void validateandclick(WebElement element,String strButtonName) throws Exception {
        try {
            if(element!=null) {
                element.click();
                System.out.println("Button clicked in: "+strButtonName);
                Reporting.PassTest(strButtonName+"should be clicked", "button/radio/checkbox is successfully clicked");
            }
            else
                Reporting.PassTest(strButtonName+"should be clicked", "button/radio/checkbox is not displayed");
        }
        catch(Exception e) {
            Reporting.FailTest(strButtonName+" should be clicked", e.getMessage());
        }
    }


    protected void validateandHovering(WebElement element,String strTextName) throws IOException {
        try {
            if(element!=null) {
                Actions action = new Actions(driver);
                action.moveToElement(element).perform();
                System.out.println("Hovering in: "+strTextName);
                Reporting.PassTest(strTextName+"should be hovered", "Hovering successfull");
            }
            else
                Reporting.FailTest(strTextName+"should be hovered", "Element is not displayed");
        }
        catch(Exception e) {
            Reporting.FailTest(strTextName+"should be hovered", e.getMessage());
        }
    }

    /**
     * This method is used to navigate to specific URL based on the test case requirements
     * @param strURL
     * @throws Exception
     */
    public void navigateToURL(String strURL) throws Exception {

        try {
            getDriver().get(strURL);
            Reporting.PassTest(strURL+"should be opened", "Opened and loaded successfully successfull");
        }
        catch(Exception e) {
            Reporting.FailTest(strURL+"should be opened", e.getMessage());
        }

    }

    protected void validateElementPresent(WebElement element, String strElementName) throws Exception{
        String strTimeunits = GenericUtils.getInstance().getStrTimeOuts();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(strTimeunits));
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            Reporting.PassTest(strElementName+" should be displayed and visible",strElementName+" should be displayed and visible");
        }catch (Exception e){
            Reporting.FailTest(strElementName+" should be displayed",e.getMessage());
        }

    }
    
   public void validateandSelect(WebElement element, String strValue) throws Exception{
	   String strTimeunits = GenericUtils.getInstance().getStrTimeOuts();
	   try {
           WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(strTimeunits));
           wait.until(ExpectedConditions.visibilityOf(element));
           
           Select select = new Select(element);
           select.selectByValue(strValue);
           Reporting.PassTest(strValue+" should be displayed and selected",strValue+" should be displayed and visible");
       }catch (Exception e){
           Reporting.FailTest(strValue+" should be displayed",e.getMessage());
       }
   }

}
