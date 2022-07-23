package ios.base;

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

import appium.base.BaseMethods;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseMethodsIOS extends BaseMethods{
	
//	protected static IOSDriver<IOSElement> driver;
	protected static IOSDriver driver;
	protected Logger log;
	static AppiumDriverLocalService appium;
	protected static String testSuiteName;
	protected static String testName;

	@BeforeClass(alwaysRun = true)
	public void setCapabilities(ITestContext ctx) throws IOException {
		
		startAppium();
		
		String testName = ctx.getCurrentXmlTest().getName(); //to get the name of the test suite
		log = LogManager.getLogger(testName);
		
		testSuiteName = ctx.getSuite().getName();
//		this.testMethodName = method.getName();
		
		File appDir = new File("src/main/resources");
		DesiredCapabilities caps = new DesiredCapabilities();
		
		File app = new File(appDir, getPropData("iOSBetterVet"));
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.5");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, getPropData("IOSDevice"));
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);//new step
//		caps.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.bettervet");
		caps.setCapability(IOSMobileCapabilityType.TIMEOUTS, 50000);
		caps.setCapability(IOSMobileCapabilityType.COMMAND_TIMEOUTS, 50000);
//		caps.setCapability("commandTimeouts", "12000");
		
		caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		
		driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		log.info("App is up and running.");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
	
	@AfterClass(alwaysRun = true)
	public void shutDownApp() throws NumberFormatException, IOException {
//		driver.close();
		driver.quit();
		log.info("App closed.");
		stopAppium();
	}
	
//	@AfterSuite
//	public void shutDownAppium() {
//		stopAppium();
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
