package android.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	
	protected AndroidDriver driver;
	protected Logger log;
	AppiumDriverLocalService appium;

	@BeforeClass(alwaysRun = true)
	public void setCapabilities(ITestContext ctx) throws IOException {
		
		startAppium();
		
		String testName = ctx.getCurrentXmlTest().getName(); //to get the name of the test suite
		log = LogManager.getLogger(testName);
		
		FileInputStream propFile = new FileInputStream("src/main/resources/global.properties");
		Properties prop = new Properties();
		prop.load(propFile);
		
		File appDir = new File("src/main/resources");
		File app = new File(appDir, (String) prop.get("androidBetterVet"));
		DesiredCapabilities caps = new DesiredCapabilities();
		String device = (String) prop.get("androidDevice");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, device);
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
	public void shutDown() {
		driver.quit();
		stopAppium();
	}
//	
//	@AfterClass(alwaysRun = true)
//	public void stopAppium() {
//		
//	}	
	
}
