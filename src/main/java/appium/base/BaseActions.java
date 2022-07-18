package appium.base;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.time.Duration;

import org.apache.logging.log4j.Logger;

public class BaseActions {
	
	protected AppiumDriver driver;
	protected Logger log;
	protected WebDriverWait wait;
	
	public BaseActions(AppiumDriver driver, Logger log) {
		
		this.driver = driver;
		this.log = log;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void click(WebElement element) {
		
		waitForElementToBeClickable(element).click();
	}
	
	protected String text(WebElement element) {
		return waitForElementToBeClickable(element).getText();
	}
	
	protected void type(WebElement element, String text) {
		waitForElementToBeVisible(element).sendKeys(text);
	}
	
	protected void assert_text(WebElement element, String text) {
		Assert.assertEquals(text(element), text, "Text is not equal.\nText found: "+text(element)+"\nText expected: "+text+"\n");
	}
	
	protected void assert_contains_text(WebElement element, String text) {
		Assert.assertEquals(text(element).contains(text), "Text is not equal.\nText found: "+text(element)+"\nText expected: "+text+"\n");
	}
	
	protected void assert_element_is_displayed(WebElement element) {
		Assert.assertTrue(waitForElementToBeVisible(element).isDisplayed(), "The element is not displayed: "+element);
	}
	
	protected Boolean check_if_element_is_present(WebElement element) {
		try {
			return element.isDisplayed();
		}catch (NoSuchElementException e){
			return false;
		}
	}
	
	public WebDriverWait wait_for() {
		return wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public WebElement waitForElementToBeVisible(WebElement element) {
		
		return wait_for().until(ExpectedConditions.visibilityOf(element));
		
//		try {
//			wait.until(ExpectedConditions.visibilityOf(element));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public WebElement waitForElementToBeClickable(WebElement element) {
		
		return wait_for().until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public Boolean waitForElementToDisappear(WebElement element) {
		
		return wait_for().until(ExpectedConditions.invisibilityOf(element));
	}

}
