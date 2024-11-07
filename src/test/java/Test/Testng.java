package Test;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import IdentifyCourses.HomePage;
import IdentifyCourses.Institution;
import IdentifyCourses.ScreenShot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Testng {
    WebDriver driver;
    HomePage ho;
    Institution inst;
    ScreenShot ss;
    private static final Logger logger = LoggerFactory.getLogger(Testng.class);

    @BeforeClass
    @Parameters({"browser"})
    void URL(String br) {
        if (br.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.managed_default_content_settings.images", 2);
            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);
        } else if (br.equals("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.coursera.org/");
        driver.manage().window().maximize();
        ss = new ScreenShot(driver);
        logger.info("Browser initialized and navigated to Coursera");
    }


    // Step 1: Search for web development courses for Beginners level & English Language
    @Test(priority = 1)
    void HomePagesearch() throws InterruptedException, IOException {
        ho = new HomePage(driver);
        ho.searchbutton(); // Clicking on search button and write web development course
        ho.clicksearch(); // Clicking and searching above course
        ss.takeScreenshot("HomePagesearch");
        logger.info("Home Page Search Screenshot taken");
        if(driver.getCurrentUrl().equals("https://www.coursera.org/search?query=Web+Development+Courses&=&language=English"))
        {
         	driver.quit();
        }
        ho.languageselect(); // Selecting the language
        ho.levelselect(); // Selecting the level
    }

    // Step 1: Extract the course names, details & rating for first 2 courses
    @Test(priority = 2, dependsOnMethods = {"HomePagesearch"})
    void searchResults() throws InterruptedException, IOException {
        ss.takeScreenshot("searchResults");
        logger.info("Search Results Screenshot taken");
        logger.info("Current URL: {}", driver.getCurrentUrl());
        ho.CourseTitle(); // First two course titles
        ho.CourseRating(); // First two course ratings
        ho.courseDuration(); // First two course details
    }

    @Test(priority = 3)
    void deSelect() throws InterruptedException {
        ho.languageselect(); // Deselecting language
        ho.levelselect(); // Deselecting levels
    }

    // Step 2: Extract all the languages and different levels with its total count & display them
    @Test(priority = 4, dependsOnMethods = {"HomePagesearch", "searchResults"})
    void languagesandLevels() throws InterruptedException, IOException {
        ho.Alllanguages(); // Printing all languages
        Thread.sleep(3000);
        ho.Alllevels(); // Printing all levels
        driver.navigate().to("https://www.coursera.org/"); // Navigating back to home page
        ss.takeScreenshot("LanguagesandLevels");
        logger.info("Languages and Levels Screenshot taken");
        Thread.sleep(3000);
    }

    @Test(priority = 5)
    void Navigate() throws InterruptedException, IOException {
        inst = new Institution(driver);
        inst.ClickEnterprise(); // Click on enterprise
        inst.campusclick(); // Click on my campus
        inst.locateform(); // Locate form
        Thread.sleep(2000);
        ss.takeScreenshot("Navigate");
        logger.info("Navigation Screenshot taken");
    }

    // Step 3: Filling form and capturing error message
    @Test(priority = 6, dataProvider = "formdetails")
    void form(String f_name, String l_name, String email, String phone, String institutiontype, String institutionname, String Jobrole, String department, String whichd, String country, String state) throws InterruptedException, IOException {
        try {
            inst.formdetails(f_name, l_name, email, phone, institutiontype, institutionname, Jobrole, department, whichd, country, state);
            inst.submitForm();
            Thread.sleep(3000);
            inst.errormsg();
            ss.takeScreenshot("form");
            Thread.sleep(2000);
        } catch (Exception e) {
        	logger.error("Error in form method: {}", e.getMessage(), e);
        }
    }

    @AfterClass
    void close() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed");
        }
    }
	
	@DataProvider(name ="formdetails")
	 String [][] formdetails()
	 {
		String data[][] = {
				{"Akash","kumar",
				"aaa123s@gmail.com","8989788900",
				"2 Year College","Ajki",
				"Vice-President/Vice-Provost","Career Services",
				"Courses for myself","India","Gujarat"}
 
			};
		return data;
		
	 }
	
	
         
}
