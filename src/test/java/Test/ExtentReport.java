package Test;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
 

	public class ExtentReport implements ITestListener
	{
		public ExtentSparkReporter sparkReporter; // UI of the report
		public ExtentReports extent; //populate common info on the report
		public ExtentTest test; // creating test case entries in the report and update status of the test methods
 
		public void onStart(ITestContext context) {
 
//			sparkReporter=new ExtentSparkReporter("C:\\Desktop\\HackathonProject-master\\src\\test\\resources\\"+ "reports/myReport.html");//specify location of the report
			sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/testngReports/");

			sparkReporter.config().setDocumentTitle("Automation Report"); // TiTle of report
			sparkReporter.config().setReportName("Functional Testing"); // name of the report
			sparkReporter.config().setTheme(Theme.STANDARD);
 
			extent=new ExtentReports();
			extent.attachReporter(sparkReporter);
 
			extent.setSystemInfo("Tester Name","Sakthi");
			extent.setSystemInfo("Browser name","chrome");
 
		}
 
 
		public void onTestSuccess(ITestResult result) {
 
			test = extent.createTest(result.getName()); // create a new entry in the report
			test.log(Status.PASS, "Test case PASSED is:" + result.getName()); // update status p/f/s
//			test.addScreenCaptureFromPath("C:\\Users\\2317300\\HackathonProject\\src\\test\\resources\\Screenshots\\"+result.getName()+".png");// ---for screenshot but not able to take ss 
		}
 
		public void onTestFailure(ITestResult result) {
 
			test = extent.createTest(result.getName());
			test.log(Status.FAIL, "Test case FAILED is:" + result.getName());
			test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable());
 
		}
 
		public void onTestSkipped(ITestResult result) {
 
			test = extent.createTest(result.getName());
			test.log(Status.SKIP, "Test case SKIPPED is:" + result.getName());
 
		}
 
		
		public void onFinish(ITestContext context) {
 
			extent.flush();
		}
 
 
	}