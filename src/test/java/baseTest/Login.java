package baseTest;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Login {
	ExtentReports report = new ExtentReports(System.getProperty("user.dir") + "ExtentReportResults.html");
	ExtentTest test = report.startTest("Login");

	@Test
	public void login() {

	}

}
