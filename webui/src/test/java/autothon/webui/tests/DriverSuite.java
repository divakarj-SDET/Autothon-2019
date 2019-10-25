package autothon.webui.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import autothon.webui.pages.*;
import autothon.webui.utils.ExcelUtils;
import autothon.webui.utils.GenericUtils;
import autothon.webui.utils.Reporting;


public class DriverSuite extends BaseTest{
	LoginPage oLoginPage;
	HomePage oHomePage;
	
  @Test
  public void AddNewMovie() throws Exception {
	  
	 String strTitle = ExcelUtils.getExcelData("TestData.xlsx", "AddNewMovie", "TestCase", "Title"); 
	 String strDirector = ExcelUtils.getExcelData("TestData.xlsx", "AddNewMovie", "TestCase", "Director");
	 String strDescription = ExcelUtils.getExcelData("TestData.xlsx", "AddNewMovie", "TestCase", "Description");
	 String strCategories = ExcelUtils.getExcelData("TestData.xlsx", "AddNewMovie", "TestCase", "Categories");
	 String strURL = ExcelUtils.getExcelData("TestData.xlsx", "AddNewMovie", "TestCase", "URL");
	 String strRating = ExcelUtils.getExcelData("TestData.xlsx", "AddNewMovie", "TestCase", "Rating");
	 
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
	  String strTitle = ExcelUtils.getExcelData("TestData.xlsx", "verifyNewMovieAdded", "TestCase", "Title");
	  Map<String,String> oLoginData = new HashMap<String,String>();
		 oLoginData.put("Username", ExcelUtils.getExcelData("TestData.xlsx", "User", "Logins", "Username"));
		 oLoginData.put("Password", ExcelUtils.getExcelData("TestData.xlsx", "User", "Logins", "Password"));
		 oLoginData.put("LoginURL", GenericUtils.getInstance().getStrUiURL());
		 oLoginPage = new LoginPage();
		 oHomePage = new HomePage();
		 oLoginPage.loginAsUser(oLoginData);
		 oHomePage.verifyMovieListed(strTitle/*"Dirty Grandpa"*/);
  }
 
  @Test
  public void addAndVerifyParallel() throws Exception{
	  String strTitle = ExcelUtils.getExcelData("TestData.xlsx", "addAndVerifyParallel", "TestCase", "Title"); 
		 String strDirector = ExcelUtils.getExcelData("TestData.xlsx", "addAndVerifyParallel", "TestCase", "Director");
		 String strDescription = ExcelUtils.getExcelData("TestData.xlsx", "addAndVerifyParallel", "TestCase", "Description");
		 String strCategories = ExcelUtils.getExcelData("TestData.xlsx", "addAndVerifyParallel", "TestCase", "Categories");
		 String strURL = ExcelUtils.getExcelData("TestData.xlsx", "addAndVerifyParallel", "TestCase", "URL");
		 String strRating = ExcelUtils.getExcelData("TestData.xlsx", "addAndVerifyParallel", "TestCase", "Rating");
		 
		 Map<String,String> oAddMovieData = new HashMap<String,String>();
		 oAddMovieData.put("Title", strTitle);
		 oAddMovieData.put("Director", strDirector);
		 oAddMovieData.put("Description", strDescription);
		 oAddMovieData.put("Category", strCategories);
		 oAddMovieData.put("URL", strURL);
		 oAddMovieData.put("Rating", strRating);
		 
		 Map<String,String> oLoginDataAdmin = new HashMap<String,String>();
		 Map<String,String> oLoginDataUser = new HashMap<String,String>();
		 oLoginDataAdmin.put("Username", GenericUtils.getInstance().getStrAdmin());
		 oLoginDataAdmin.put("Password", GenericUtils.getInstance().getStrPassWord());
		 oLoginDataAdmin.put("LoginURL", GenericUtils.getInstance().getStrUiURL());
		 
		 oLoginDataUser.put("Username", ExcelUtils.getExcelData("TestData.xlsx", "User", "Logins", "Username"));
		 oLoginDataUser.put("Password", ExcelUtils.getExcelData("TestData.xlsx", "User", "Logins", "Password"));
		 oLoginDataUser.put("LoginURL", GenericUtils.getInstance().getStrUiURL());
		 
		 oLoginPage = new LoginPage();
		 oHomePage = new HomePage();
		 oLoginPage.loginAsUser(oLoginDataUser);
		 
		 List<String> moviesList =  oHomePage.listOfMovies();
		 
		 
		 String driver = oBasepage.getDriver().getWindowHandle();
		 
		 oBasepage.launchDriver(GenericUtils.getInstance().strParallelBrowser());
		 WebDriver newDriver = oBasepage.getDriver();
		 		 
		 oLoginPage.loginAsAdmin(oLoginDataAdmin);
		 oHomePage.addMovie(oAddMovieData);
		 
		 newDriver.switchTo().window(driver);
		 
		 List<String> newMoviesList =  oHomePage.listOfMovies();
		 
		 if(newMoviesList.contains(strTitle))
			 Reporting.PassTest("List should contains movie: "+strTitle, "List contains movie: "+strTitle);
		 else
			 Reporting.PassTest("List should contains movie: "+strTitle, "List does not contains movie: "+strTitle);
			 
  }

}
