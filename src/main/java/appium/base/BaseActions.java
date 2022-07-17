package appium.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
