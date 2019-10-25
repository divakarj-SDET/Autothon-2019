package autothon.webui.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import autothon.webui.utils.Reporting;

public class HomePage extends BasePage{
	
	public HomePage() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath="//a[text()='add movie']")
	private WebElement linkAddMovie;
	
	@FindBy(xpath = "//a[text()='Logout']")
	private WebElement linkLogout;
	
	@FindBy(xpath="//input[@name='title']")
	private WebElement editTitle;
	
	@FindBy(xpath="//input[@name='director']")
	private WebElement editDirector;
	
	@FindBy(xpath="//textarea[@name='description']")
	private WebElement editDescription;

	@FindBy(xpath = "//select[@name = 'categories']")
	private WebElement selectCategory;
	
	@FindBy(xpath="//input[@name='file']")
	private WebElement editURl;
	
	@FindBy(xpath = "//label[@for='rating']/parent::div/div")
	private WebElement clickRating;
	
	
	@FindBy(xpath = "//button[text()='Save Movie']")
	private WebElement clickSaveMovie;
	
	@FindBy(xpath = "//div[@class='mycard']//h2[1]")
	private List<WebElement> lMovies;
	
	public void addMovie(Map<String,String> oAddMovieData) throws Exception {
		
		super.validateandclick(linkAddMovie, "Add movie");
		super.validateandSendKeys(editTitle, "title", oAddMovieData.get("Title"));
		super.validateandSendKeys(editDirector, "Director", oAddMovieData.get("Director"));
		super.validateandSendKeys(editDescription, "Description", oAddMovieData.get("Description"));
		super.validateandSelect(selectCategory,oAddMovieData.get("Category"));
		selectMovieRating(oAddMovieData.get("Rating"));
		super.validateandSendKeys(editURl, "URL", oAddMovieData.get("URL"));
		super.validateandclick(clickSaveMovie, "Save Movie");
		
		
	}
	
	
	public void selectMovieRating(String strRatingnumber) throws Exception{
		
		List<WebElement> elementStar = clickRating.findElements(By.tagName("svg"));
		if(elementStar.size()>0) {
			super.validateandclick(elementStar.get(Integer.parseInt(strRatingnumber)), "Rating selected is: "+strRatingnumber);
		}
		else {
			Reporting.FailTest("Rating was suppossed to be selected", "Invalid element picked up");
		}
		
		
	}
	
	
	
	public void verifyMovieListed(String strMovieName) throws Exception{
		WebElement eMovieName = getDriver().findElement(By.xpath("//h2[text()='"+strMovieName+"']"));
		super.validateElementPresent(eMovieName, strMovieName+" is present");
	}
	
	public void userLogout() throws Exception{
		super.validateandclick(linkLogout, "Logout");
	}
	
	public List<String> listOfMovies() throws Exception{
		List<String> moviesList = new ArrayList<String>();
		for(WebElement e :lMovies) {
			moviesList.add(e.getText().trim());
		}
		return moviesList;
	}
	
}
