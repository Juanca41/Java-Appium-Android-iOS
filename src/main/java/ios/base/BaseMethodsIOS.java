package ios.base;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import appium.base.BaseMethods;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
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

	@BeforeClass(alwaysRun = true)
	public void setCapabilities(ITestContext ctx) throws IOException {
		
		startAppium();
		
		String testName = ctx.getCurrentXmlTest().getName(); //to get the name of the test suite
		log = LogManager.getLogger(testName);
		
		FileInputStream propFile = new FileInputStream("src/main/resources/global.properties");
		Properties prop = new Properties();
		prop.load(propFile);
		
		File appDir = new File("src/main/resources");
		DesiredCapabilities caps = new DesiredCapabilities();
		
		File app = new File(appDir, (String) prop.get("iOSBetterVet"));
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.5");
		String device = (String) prop.get("IOSDevice");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, device);
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
	public void shutDownApp() {
//		driver.close();
		driver.quit();
		log.info("App closed.");
		stopAppium();
	}
	
//	@AfterSuite
//	public void shutDownAppium() {
//		stopAppium();
//	}

}
