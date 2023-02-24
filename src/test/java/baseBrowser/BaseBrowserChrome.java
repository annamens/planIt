package baseBrowser;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseBrowserChrome {
	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest test;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		report = new ExtentReports(System.getProperty("user.dir") + "/Results/ExtentReportResults.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@AfterClass
	public void tearDown() {
//		driver.quit();
		report.endTest(test);
		report.flush();
	}
}
