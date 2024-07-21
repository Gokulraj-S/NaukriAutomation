package gokul.testClass;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import gokul.extentReports.MyExtentReports;
import gokul.utilities.BaseTest;

public class Naukri extends BaseTest {
	ExtentReports extent = MyExtentReports.getReportObject();

	@Test()
	public void findJobs() throws InterruptedException, IOException {
//		initilize();
		extent.createTest("Test1");
//		SafariOptions options=new SafariOptions();
//		options.set
		
		Thread.sleep(3000);
		driver.findElement(By.id("usernameField")).sendKeys("gokulraj.tester@gmail.com");
		driver.findElement(By.id("passwordField")).sendKeys("Gokulraj260@");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
//	 

		Thread.sleep(10000);
		System.out.println(driver
				.findElement(By.xpath("//div[contains(@class,'search')]//span[contains(text(),'Search')]")).getText());
		WebElement searchbox = driver
				.findElement(By.xpath("//div[contains(@class,'search')]//span[contains(text(),'Search')]"));
		searchbox.click();
		Thread.sleep(3000);
		driver.findElement(By.className("suggestor-input")).sendKeys("Selenium");
		driver.findElement(By.xpath("//span[text()='Search']")).click();
		Thread.sleep(3000);
		List<WebElement> jobs = driver.findElements(By.xpath("//div[contains(@class,'styles_job-listing-container')]//div[contains(@class,'row1')]"));
		System.out.println(jobs.size());
		for (int i=0;i<jobs.size();i++) {
			System.out.println(jobs.get(i).getText());
		}
		if(ExpectedConditions.alertIsPresent() == null) {
			driver.switchTo().alert().dismiss();
		}
		driver.close();
		extent.flush();

	}
}
