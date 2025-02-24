package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;


public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe

	@Override
	public void onTestStart(ITestResult result) {
		// Code to execute before a test starts
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //unique thread id{ErrorValidationTest)->test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// Code to execute after a test succeeds
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Code to execute after a test fails
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		// screenshot and attach it to a report
		
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// Code to execute if a test is skipped
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Code to execute if a test fails but is within a success percentage
	}

	@Override
	public void onStart(ITestContext context) {
		// Code to execute before any test methods in this context
	}

	@Override
	public void onFinish(ITestContext context) {
		// Code to execute after all the test methods in this context
		extent.flush();
	}

}
