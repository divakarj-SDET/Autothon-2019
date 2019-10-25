package autothon.webui.tests.browserstack;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import autothon.webui.pages.BasePage;
import autothon.webui.pages.HomePage;
import autothon.webui.pages.LoginPage;
import autothon.webui.utils.ExcelUtils;
import autothon.webui.utils.GenericUtils;

public class LocalTest extends BrowserStackTestNGTest {

	BasePage oBasePage = new BasePage();
	LoginPage oLoginPage;
	HomePage oHomePage;
    @Test
    public void test() throws Exception {
    	 oBasePage.setDriver(driver);
   	  String strTitle = ExcelUtils.getExcelData("TestData.xlsx", "Add_Movie", "TestCase", "Title");
   	  Map<String,String> oLoginData = new HashMap<String,String>();
   		 oLoginData.put("Username", ExcelUtils.getExcelData("TestData.xlsx", "User", "Logins", "Username"));
   		 oLoginData.put("Password", ExcelUtils.getExcelData("TestData.xlsx", "User", "Logins", "Password"));
   		 oLoginData.put("LoginURL", GenericUtils.getInstance().getStrUiURL());
   		 oLoginPage = new LoginPage();
   		 oHomePage = new HomePage();
   		 oLoginPage.loginAsUser(oLoginData);
   		 oHomePage.verifyMovieListed(/*strTitle*/"Dirty Grandpa");
    }
}
