package autothon.webui.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import autothon.webui.pages.*;
import autothon.webui.utils.ExcelUtils;
import autothon.webui.utils.GenericUtils;


public class DriverSuite extends BaseTest{
	LoginPage oLoginPage;
	HomePage oHomePage;
	
  @Test
  public void AddNewMovie() throws Exception {
	  
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
 
  public void addAndVerifyParallel() throws Exception{
	  
  }

}
