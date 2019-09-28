package autothon.webui.tests.browserstack;


import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import autothon.webui.pages.BasePage;
import autothon.webui.pages.HomePage;
import autothon.webui.pages.LoginPage;
import autothon.webui.utils.ExcelUtils;
import autothon.webui.utils.GenericUtils;

public class Tests extends BrowserStackTestNGTest {
	BasePage oBasePage = new BasePage();
	LoginPage oLoginPage;
	HomePage oHomePage;
  @Test
  public void AddNewMovie() throws Exception {
	  oBasePage.setDriver(driver);
	  String strTitle = ExcelUtils.getExcelData("TestData.xlsx", "Add_Movie", "TestCase", "Title"); 
		 String strDirector = ExcelUtils.getExcelData("TestData.xlsx", "Add_Movie", "TestCase", "Director");
		 String strDescription = ExcelUtils.getExcelData("TestData.xlsx", "Add_Movie", "TestCase", "Description");
		 String strCategories = ExcelUtils.getExcelData("TestData.xlsx", "Add_Movie", "TestCase", "Categories");
		 String strURL = ExcelUtils.getExcelData("TestData.xlsx", "Add_Movie", "TestCase", "URL");
		 String strRating = ExcelUtils.getExcelData("TestData.xlsx", "Add_Movie", "TestCase", "Rating");
		 
		 Map<String,String> oAddMovieData = new HashMap<String,String>();
		 oAddMovieData.put("Title", strTitle);
		 oAddMovieData.put("Director", strDirector);
		 oAddMovieData.put("Description", strDescription);
		 oAddMovieData.put("Category", strCategories);
		 oAddMovieData.put("URL", strURL);
		 oAddMovieData.put("Rating", strRating);
		 
		 
		 Map<String,String> oLoginData = new HashMap<String,String>();
		 oLoginData.put("Username", GenericUtils.getInstance().getStrAdmin());
		 oLoginData.put("Password", GenericUtils.getInstance().getStrPassWord());
		 oLoginData.put("LoginURL", GenericUtils.getInstance().getStrUiURL());
		 oLoginPage = new LoginPage();
		 oHomePage = new HomePage();
		 oLoginPage.loginAsAdmin(oLoginData);
		 oHomePage.addMovie(oAddMovieData);
		 oHomePage.verifyMovieListed(strTitle); 
	  
  }
  
  
  @Test
  public void verifyNewMovieAdded() throws Exception{
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
