package gokul.extentReports;

import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class MyExtentReports {
    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") + "/Screenshots/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("Gokul Automation Report");
        reporter.config().setReportName("Naukri Job Portal Automation");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        return extent;
    }
}

