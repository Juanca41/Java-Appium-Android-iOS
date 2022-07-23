package android.base;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import appium.base.BaseMethods;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseMethodsAndroid extends BaseMethods{
	
	
	protected static AndroidDriver driver;
	protected Logger log;
	AppiumDriverLocalService appium;
	protected static String testSuiteName;
	protected static String testName;

	@BeforeClass(alwaysRun = true)
	public void setCapabilities(ITestContext ctx) throws IOException {
		
		startAppium();
		
		String testName = ctx.getCurrentXmlTest().getName(); //to get the name of the test suite
		log = LogManager.getLogger(testName);
		testSuiteName = ctx.getSuite().getName();
		
		File appDir = new File("src/main/resources");
		File app = new File(appDir, getPropData("androidBetterVet"));
		DesiredCapabilities caps = new DesiredCapabilities();
//		String device = (String) prop.get("androidDevice");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, getPropData("androidDevice"));
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");//new step
		caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		caps.setCapability("platformName", "Android");
		caps.setCapability("appActivity", "com.bettervet.MainActivity");
		caps.setCapability("appPackage", "com.bettervet");
		caps.setCapability("setWebContentsDebuggingEnabled", true);
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		System.out.println("App is up and running.");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public void goToHomeScreen() {
		Activity homeScreenActivity = new Activity("com.bettervet","com.bettervet.MainActivity");
		driver.startActivity(homeScreenActivity);
	}
	
	@AfterClass(alwaysRun = true)
	public void shutDown() throws NumberFormatException, IOException {
		driver.quit();
		stopAppium();
	}
//	
//	@AfterClass(alwaysRun = true)
//	public void stopAppium() {
//		
//	}	
	
	/** Take screenshot 
	 * @return */
	public static String takeScreenshot() {
		
//		String testName = ctx.getCurrentXmlTest().getName();
//		testSuiteName = ctx.getSuite().getName();
//		testMethodName = method.getName();
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")//main directory
				+ File.separator + "test-output" 
				+ File.separator + "screenshots"
				+ File.separator + getTodaysDate()
				+ File.separator + getSystemTime() 
				+ File.separator + testSuiteName 
				+ File.separator + testName + ".png";
//				+ File.separator + testMethodName + ".png";
//				+ File.separator + getSystemTime() 
//				+ " " + fileName + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}
	
	/** Todays date in yyyyMMdd format */
	protected static String getTodaysDate() {
		return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
	}

	/** Current time in HHmmssSSS */
	protected static String getSystemTime() {
		return (new SimpleDateFormat("HHmmssSSS").format(new Date()));
	}
	
	/** Get logs from browser console */
	protected List<LogEntry> getBrowserLogs() {
		LogEntries log = driver.manage().logs().get("browser");
		List<LogEntry> logList = log.getAll();
		return logList;
	}
	
}
