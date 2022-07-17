package appium.base;

import org.apache.logging.log4j.Logger;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class BaseActionsAndroid extends BaseActions{

	protected AndroidDriver driver; //to make it available for other class that extends this one
	public Logger log; //variable to control and print the logs
	

	public BaseActionsAndroid(AndroidDriver driver, Logger log) {
		// TODO Auto-generated constructor stub
		super(driver, log);
	}
	
	public void scrollToText(String text) {
		
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))"));
	}
	
//	@FindBy(xpath = "")
//	public WebElement element;

//	protected IOSElement find(By locator) {
//		return driver.findElement(locator);
//	}
//	
//	protected List<IOSElement> findAll(By locator) {
//		return driver.findElements(locator);
//	}
	
//	protected void click(By locator) {
//		waitForElementToBeClickable(locator);
//		find(locator).click();
//	}
//	
//	protected String text(By locator) {
//		waitForElementToBeClickable(locator);
//		String text = find(locator).getText();
//		return text;
//	}
//	
//	protected void type(By locator, String text) {
//		waitForElementToBeVisible(locator);
//		find(locator).sendKeys(text);
//	}
//	
//	protected void assert_text(By locator, String text) {
//		Assert.assertEquals(text(locator), text, "Text is not equal.\nText found: "+text(locator)+"\nText expected: "+text+"\n");
//	}
//	
//	protected void assert_contains_text(By locator, String text) {
//		Assert.assertEquals(text(locator).contains(text), "Text is not equal.\nText found: "+text(locator)+"\nText expected: "+text+"\n");
//	}
//	
//	protected void assert_element_is_displayed(By locator) {
//		waitForElementToBeVisible(locator);
//		WebElement element = find(locator);
//		Assert.assertTrue(element.isDisplayed(), "The element is not displayed: "+locator);
//	}
//	
//	protected Boolean check_if_element_is_present(By locator) {
//		try {
////			waitForElementToBeVisible(locator);
//			WebElement element = find(locator);
//			return element.isDisplayed();
//		}catch (NoSuchElementException e){
//			return false;
//		}catch (StaleElementReferenceException e){
//		return false;
//	}
//	}
//	
//	private WebDriverWait wait_element() {
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		return wait;
//	}
//	
//	protected void waitForElementToBeVisible(By locator) {
//		
//		wait_element().until(ExpectedConditions.visibilityOfElementLocated(locator));
//	}
//	
//	protected void waitForElementToBeClickable(By locator) {
//		
//		wait_element().until(ExpectedConditions.elementToBeClickable(locator));
//	}
	 
	
//	private void wait_for(ExpectedCondition<AndroidElement> condition, Duration timeout) {
//		
//		timeout = timeout != null ? timeout : Duration.ofSeconds(15);
//		WebDriverWait wait = new AndroidDriver(driver, timeout);
//		wait.until(condition);
//	}
//	
//	protected void waitForElementToBeVisible(By locator, Duration... timeout) {
//		
//		int attempts = 0;
//		while(attempts < 2) {
//			try {
//				wait_for(ExpectedConditions.visibilityOfElementLocated(locator), (timeout.length > 0 ? timeout[0] : null));
//			}catch(StaleElementReferenceException error) {
//			}
//		
//			attempts ++;
//		}
//	}
//		
//	protected void waitForElementToBeClickable(By locator, Duration... timeout) {
//		
//		int attempts = 0;
//		while(attempts < 2) {
//			try {
//				wait_for(ExpectedConditions.elementToBeClickable(locator), (timeout.length > 0 ? timeout[0] : null));
//			}catch(StaleElementReferenceException error) {
//			}
//		
//			attempts ++;
//		}
//	}
}
