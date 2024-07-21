package gokul.utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import gokul.extentReports.MyExtentReports;

public class Listeners extends BaseTest implements ITestListener {
	ExtentReports extent=MyExtentReports.getReportObject();
	ExtentTest test;
	WebDriver driver;

	@Override
	public void onTestStart(ITestResult result) {
		test=extent.createTest("Fetch Selenium Jobs");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		String path=null;
		try {
			path = getScreenshot(driver,result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(path,result.getMethod().getMethodName());

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
 
}
