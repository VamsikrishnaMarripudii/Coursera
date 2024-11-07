package IdentifyCourses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage{
	
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;	
		PageFactory.initElements(driver,this);
	}
	
	//locators 
	@FindBy(xpath="//input[@type='text']")
	 WebElement searchbutton;
	
	@FindBy(xpath="//button[@class='nostyle search-button']//div")
	WebElement clicksearch;
	
	@FindBy(xpath="//input[@id='cds-react-aria-18']")
	WebElement lang;
	
	@FindBy(xpath="//input[@id='cds-react-aria-50']")
	WebElement level;
	
	@FindBy(xpath="//h3[@class='cds-CommonCard-title css-6ecy9b']")
	List<WebElement> c_title;
	
	@FindBy(xpath="(//span[@class=' css-6ecy9b'])[9] | (//span[@class=' css-6ecy9b'])[10]")
	List<WebElement> c_ratings;
	
	@FindBy(xpath="//ul[1]//div[@class='cds-CommonCard-metadata']/p")
	List<WebElement> duration;
	
	@FindBy(xpath="//div[@data-testid='search-filter-group-Language']")//-----scrolling to language
	WebElement language;
	
	@FindBy(xpath="//button[@data-track-component='expand_filter_items_button_language']")//-----languagebuttonselect
	WebElement LanguageButton;
	
//	@FindBy(xpath="//button[@class='cds-149 cds-button-disableElevation cds-button-ghost css-1s96oj']")//-----closing language window 
//	WebElement langclick;
//	
//	@FindBy(xpath="//*[@class=\"css-4pxpfb\"][2]//*[@class=\"css-133xgeo\"]")
//	List<WebElement> languagewindow;
//	
//	
//	@FindBy(xpath="//div[@class='css-q5d1os']/div")
//	List<WebElement> langlist;
	
	@FindBy(xpath="//div[@class='css-q5d1os']/div")//-----languagebuttonselect
	List<WebElement> linguals;
	
	@FindBy(xpath="//div[@data-testid='search-filter-group-Level']")//----locate  level button 
	WebElement levelbutton;
	
	@FindBy(xpath="(//div[@class='css-5ji5n2'])[3]/div")//------list of level
	List<WebElement> levellist;
//	
	
	//action methods
	public void clicksearch()//-------clicking the button 
	{
		clicksearch.click();
	}
	public void languageselect() throws InterruptedException//------selecting language 
	{
			Thread.sleep(3000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()",lang);
	} 
//		lang.click();
	public void levelselect() throws InterruptedException//---------level select 
	{
		Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", level);
	}

	public void searchbutton() 
		 {
		    	searchbutton.click();
		    	searchbutton.sendKeys("Web Development Courses");
		   }
	public List<WebElement> CourseTitle() throws InterruptedException//------extracting first two course title 
	{
		int count = 0;
		List<WebElement> courseTitles = c_title;
		System.out.println("Title of First 2 Courses:");
		for(WebElement el : courseTitles) {
			if(count>=2) {
				break;
			}
			System.out.println("	"+el.getText());
			count++;
		}
		
		return courseTitles;
	}
	public List<WebElement> CourseRating()//---------first two course ratings 
	{
		int c = 0;
		List<WebElement> courseRatings = c_ratings;
		System.out.println("Ratings of First 2 Courses:");
		for(WebElement ele : courseRatings) {
			if(c>=2) {
				break;
			}
			System.out.println("	"+ele.getText()+ " Stars");
			c++;
		}
		return courseRatings;
	}
	
	public List<WebElement> courseDuration()//-------extracting first two course detail 
	{
		int d = 0;
		List<WebElement> durations = duration;
		System.out.println("Duration of First 2 Courses:");
		for(WebElement ele : durations) {
			if(d>=2) {
				break;
			}
			System.out.println("	"+ele.getText());
			d++;
		}
		return durations;
	}
	
	public void Alllanguages() throws InterruptedException//--------printing all available languages 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", language);
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(LanguageButton));
		LanguageButton.click();
		System.out.println("Languages Available: "+linguals.size());
		try 
		{
			for (WebElement e : linguals)
				System.out.println("	"+e.getText());
		}
		catch(Exception el) {
			el.printStackTrace();
		}
		
			
}
	public List<WebElement> Alllevels() throws InterruptedException //------printing all levals
	{
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", levelbutton);
		Thread.sleep(3000);
		List<WebElement> levelList = levellist;
		System.out.println("Levels Available: "+levellist.size());
		for (WebElement e : levelList) {
			System.out.println("	"+e.getText());
		}
		return levelList;
		}
	
	
}
