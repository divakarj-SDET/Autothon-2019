package autothon.webui.pages;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

	
	@FindBy(xpath="//div[@id='main']/span/button")
	private WebElement linkHambergunIcon;
	
	@FindBy(xpath = "//a[text()='Login']")
	private WebElement linkLogin;
	
	@FindBy(xpath = "//a[text()='Logout']")
	private WebElement linkLogout;
	
	@FindBy(xpath = "//input[@name='username']")
	private WebElement editUsername;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement editPassword;
	
	@FindBy(xpath = "//span[text()='Admin Menu']")
	private WebElement labelAdmin;
	
	@FindBy(xpath = "//button[@name='Login']")
	private WebElement buttonLogin;
	
	public LoginPage() {
		
		PageFactory.initElements(getDriver(), this);
	}
	
	
	
	public void loginAsAdmin(Map<String,String> oData) throws Exception {
		super.navigateToURL(oData.get("LoginURL"));
		super.validateandclick(linkHambergunIcon, "Hamburger Icon for login");
		Thread.sleep(2000);
		super.validateandclick(linkLogin, "Login Link");
		super.validateandSendKeys(editUsername, "user name", oData.get("Username"));
		super.validateandSendKeys(editPassword, "Password", oData.get("Password"));
		super.validateandclick(buttonLogin, "Login");
		Thread.sleep(2000);
		super.validateElementPresent(labelAdmin, "User successfully login by verifying Admin menu label");
		
	}
	
	
	
	public void loginAsUser(Map<String,String> oData) throws Exception {
		
		super.navigateToURL(oData.get("LoginURL"));
		super.validateandclick(linkHambergunIcon, "Hamburger Icon for login");
		Thread.sleep(2000);
		super.validateandclick(linkLogin, "Login Link");
		super.validateandSendKeys(editUsername, "user name", oData.get("Username"));
		super.validateandSendKeys(editPassword, "Password", oData.get("Password"));
		super.validateandclick(buttonLogin, "Login");
		Thread.sleep(2000);
		super.validateElementPresent(linkLogout, "User successfully login by verifying user loutout");
		
	}
	
}
