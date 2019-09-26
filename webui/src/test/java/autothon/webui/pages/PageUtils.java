package autothon.webui.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import autothon.webui.utils.GenericUtils;
import autothon.webui.utils.Reporting;

public class PageUtils {

	private static WebDriver driver;
    private static String strDriverPath = System.getProperty("user.dir")+"//resources//Drivers";

    public  WebDriver getDriver() {
        return driver;
    }

    public void closeDriver(){
        driver.close();
        driver.quit();
    }
    public void launchDriver() throws IOException {
        String strBrowserName = GenericUtils.getInstance().getStrBrowserName();
        try {

            switch(strBrowserName.toUpperCase()){
                case "CHROME":
                    System.setProperty("webdriver.chrome.driver", strDriverPath+"//chromedriver.exe");
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                    Reporting.PassTest(strBrowserName+" should open", "Browser successfully launched");
                    break;

                default :
                    Reporting.FailTest(strBrowserName+" should open", "Invalid browser name");
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            Reporting.FailTest(strBrowserName+" should open", e.getMessage());
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
     * This method is used to navigate to default URL
     * @throws Exception
     */
    public void navigateToURL() throws Exception {
        String strURL = GenericUtils.getInstance().getStrURL();
        try {
            getDriver().get(strURL);
            Reporting.PassTest(strURL+"should be opened", "Opened and loaded successfully successfull");
        }
        catch(Exception e) {
            Reporting.FailTest(strURL+"should be opened", e.getMessage());
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
}
