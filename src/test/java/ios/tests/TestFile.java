package ios.tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import ios.base.BaseMethodsIOS;

public class TestFile extends BaseMethodsIOS{
	
	
//	@Parameters({ "username", "password" })
	@Test
//	public void test(String username, String password) throws MalformedURLException{
	public static void main(String[] args) throws MalformedURLException{
		
		File appDir = new File("src/main/resources");
//		File app = new File(appDir, "UIKitCatalog.app");
		File app = new File(appDir, "BetterVet.app");
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.5");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 13 Pro Max");
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);//new step
		caps.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.bettervet");
		caps.setCapability(IOSMobileCapabilityType.TIMEOUTS, 500000);
		caps.setCapability("commandTimeouts", "12000");
		
		caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		
		driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		System.out.println("App is up and running.");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		
		By login_button = (By.id("Login"));
		waitForElementToBeClickable(login_button).click();
//		driver.findElementByAccessibilityId("Login").click();
//		driver.findElementByIosNsPredicate("");
		System.out.println("Login button was clicked.");

//		String[] element=new String[] {"Ani", "Sam"};
		
		WebElement email_field = driver.findElement(By.xpath("//XCUIElementTypeOther[2]/XCUIElementTypeTextField"));
		
		By pwd_field_locator = (By.xpath("(//XCUIElementTypeOther[@name=\"Password Password\"])[4]//XCUIElementTypeSecureTextField"));
//		WebElement pwd_field = driver.findElementByIosClassChain("**/XCUIElementTypeOther[`label == \"Password Password\"`][4]/XCUIElementTypeOther[2]/XCUIElementTypeSecureTextField");
		WebElement pwd_field = waitForElementToBeVisible(pwd_field_locator);
		
		email_field.sendKeys("juancarlosgularte@bettervet.com");
		pwd_field.clear();
		pwd_field.sendKeys("Helloworld10");
		
		WebElement login = driver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"Log In\"])[2]"));
		WebElement remember_me = driver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"Remember Me\"])[3]"));
		
		remember_me.click();
		remember_me.click();
		remember_me.click();
		login.click();
		
		System.out.println("USER LOGGED IN.");
		
		By login_loading_logo = (By.xpath("(//XCUIElementTypeOther[@name=\"Email Email Password Password Remember Me Forgot your password? Log In View other sign in options Horizontal scroll bar, 1 page\"])[3]/XCUIElementTypeOther[1]/XCUIElementTypeOther"));
		
		while(check_if_loading_logo_is_present(login_loading_logo) == true) {
			if(check_if_loading_logo_is_present(login_loading_logo) == false) {
				break;
			}
		}
		
		driver.findElement(By.id("Allow")).click();
		
		String home_page_title = driver.findElement(By.id("Need to connect with a doctor?")).getText();
		System.out.println(home_page_title);
		
		driver.findElement(By.id("Pets")).click();
		
		HashMap<String, Object> scrollDown = new HashMap<>();
		scrollDown.put("direction", "down");
		scrollDown.put("value", "GoT");
		
		driver.executeScript("mobile:scroll", scrollDown);
		
		driver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"Health Card\"])[9]/XCUIElementTypeOther/XCUIElementTypeOther")).click();
		
		driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"Pets Appointments Shop Account\"]/XCUIElementTypeButton")).click();
		driver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"Schedule Now\"])[1]")).click();
		
		driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"Deisy\"]/XCUIElementTypeOther")).click();
		driver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"Next\"])[2]")).click();
		
		driver.findElement(By.id("Comprehensive Exam - $120")).click();
		
		String info = driver.findElement(By.id("Every exam will include a full physical examination, discussion of your pet’s lifestyle and in-home environment. Doctors will also discuss recommended diagnostics, and appropriate vaccinations. Additional costs for these services will be provided at the time of the appointment.")).getText();
		
		Assert.assertEquals(info, "Every exam will include a full physical examination, discussion of your pet’s lifestyle and in-home environment. Doctors will also discuss recommended diagnostics, and appropriate vaccinations. Additional costs for these services will be provided at the time of the appointment.");
		
		driver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"Next\"])[2]")).click();
		
		By service_chosen_loading_logo = (By.xpath("//*[contains(@name, '2 pages Horizontal scroll bar, 1 page')][7]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage"));
		
		System.out.println(service_chosen_loading_logo+" was found.");
		while(check_if_loading_logo_is_present(service_chosen_loading_logo) == true) {
			if(check_if_loading_logo_is_present(service_chosen_loading_logo) == false) {
				break;
			}
		}
		
		driver.findElement(By.id("My pet has a new illness/injury/problem.")).click();
		driver.findElement(By.id("This is a recheck recommended by a BetterVet doctor.")).click();
		
		HashMap<String, Object> scrollDown2 = new HashMap<>();
		scrollDown2.put("direction", "down");
		scrollDown2.put("label", "Find media to upload");
		
		driver.executeScript("mobile:scroll", scrollDown2);
		
		WebElement temperament_slider = driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"How would you rate your pet's temperament? Super friendly, and loves meeting new people.\"]/XCUIElementTypeSlider"));
		temperament_slider.sendKeys("0.25");
		System.out.println(temperament_slider.getText());
	}
	
	protected static Boolean check_if_loading_logo_is_present(By locator) {
		try {
//			waitForElementToBeVisible(locator);
			WebElement element = driver.findElement(locator);
			return element.isDisplayed();
		}catch (NoSuchElementException e){
			return false;
		}
	}
	
	private static WebDriverWait wait_element() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait;
	}
	
	protected static WebElement waitForElementToBeVisible(By locator) {
		
		return wait_element().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	protected static WebElement waitForElementToBeClickable(By locator) {
		
		return wait_element().until(ExpectedConditions.elementToBeClickable(locator));
	}
}
