package appium.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import android.base.BaseMethodsAndroid;
import io.appium.java_client.AppiumDriver;
import ios.base.BaseMethodsIOS;

public class TestListener implements ITestListener{
	
	AppiumDriver driver;
	Logger log;
	String testName;
	String testMethodName;
	ExtentReports report = BaseMethods.reporter();
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		this.testMethodName = result.getMethod().getMethodName();
		test = report.createTest(testName);
		log.info("[Starting " + testMethodName + "]");
	}
	
	@Override
	public void onStart(ITestContext context) {
		this.testName = context.getCurrentXmlTest().getName();
		this.log = LogManager.getLogger(testName);
		log.info("[TEST " + testName + " STARTED]");
	}

	@Override
	public void onFinish(ITestContext context) {
		log.info("[ALL " + testName + " FINISHED]");
		
		report.flush();
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("[Test " + testMethodName + " passed]");
		test.log(Status.PASS, "Test Passed");
//		test.pass(result.getThrowable());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		log.info("[Test " + testMethodName + " failed]");
		test.log(Status.FAIL, "Test Failed");
		test.fail(result.getThrowable());
		
//		try {
//			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String os = result.getTestClass().getRealClass().toString();
		System.out.println("THIS IS THE "+os);
		
		if (os.contains("IOS")) {
			test.addScreenCaptureFromPath(BaseMethodsIOS.takeScreenshot(), result.getMethod().getMethodName());
		}
		else if (os.contains("Android")) {
			test.addScreenCaptureFromPath(BaseMethodsAndroid.takeScreenshot(), result.getMethod().getMethodName());
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		log.info("[Test " + testMethodName + " skipped]");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

}
