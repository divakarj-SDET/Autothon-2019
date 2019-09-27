package autothon.webui.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import autothon.webui.pages.BasePage;


public class Reporting {

    private static ExtentReports extent;
    private static ExtentTest logger;
    private static String strTestCaseName;
    static boolean bflagLogger = false;
    
    
    public void setTestCaseName(String strTestCaseName) {
        this.strTestCaseName = strTestCaseName;
        logger = extent.startTest(strTestCaseName);
    }

    /**
     * This method initiates the extent reporting
     */
    public void startReporting() {
        try {
        	String time_stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
        	
            extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport/"+time_stamp+".html", true);
            extent
                    .addSystemInfo("Environment", "Automation Testing")
                    .addSystemInfo("User Name", System.getProperty("user.name"));
            
            extent.loadConfig(new File(System.getProperty("user.dir")+"\\test-output\\extent-config.xml"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will log step status that is performed
     * @param strExpected
     * @param strActual
     * @throws IOException
     */
    public static void PassTest(String strExpected, String strActual) throws IOException {
        logger.log(LogStatus.PASS, strActual, strExpected);
        Assert.assertTrue(true);

    }

    /**
     * This method will log step status that is not performed as expected
     * @param strExpected
     * @param strActual
     * @throws IOException
     */
    public static void FailTest(String strExpected, String strActual) throws IOException {
        logger.log(LogStatus.FAIL, strActual, strExpected);
        Assert.assertTrue(false,strActual);
        extent.endTest(logger);
        bflagLogger=true;
    }

    /**
     * This method is used to end the reporting
     */
    public void endReporting() {
        if(!bflagLogger) extent.endTest(logger);
        extent.flush();

        extent.close();
    }

    /**
     * This method takes the screenshot of the failed test cases
     * @return
     * @throws IOException
     */
    private static String takeScreenShot() throws IOException {
    	String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String destination = System.getProperty("user.dir")+"\\testScreenShot\\"+strTestCaseName+"_"+dateName+".png";
        File scrFile = ((TakesScreenshot)new BasePage().getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(destination));

        return destination;
    }

}
