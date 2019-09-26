package autothon.webui.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

    public void startReporting() {
        try {
            extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
            extent
                    .addSystemInfo("Environment", "Automation Testing")
                    .addSystemInfo("User Name", System.getProperty("user.name"));
            extent.loadConfig(new File(System.getProperty("user.dir")+"\\test-output\\extent-config.xml"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void PassTest(String strExpected, String strActual) {
        logger.log(LogStatus.PASS, strActual, strExpected);
        Assert.assertTrue(true);

    }

    public static void FailTest(String strExpected, String strActual) throws IOException {
        logger.log(LogStatus.FAIL, strActual, strExpected);
        logger.log(LogStatus.FAIL, logger.addScreenCapture(takeScreenShot()));
        Assert.assertTrue(false,strActual);
        extent.endTest(logger);
        bflagLogger=true;
    }

    public void endReporting() {
        if(!bflagLogger) extent.endTest(logger);
        extent.flush();

        extent.close();
    }

    private static String takeScreenShot() throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String destination = System.getProperty("user.dir")+"\\testScreenShot\\"+strTestCaseName+"_"+dateName+".png";
        File scrFile = ((TakesScreenshot)new BasePage().getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(destination));

        return destination;
    }

}
