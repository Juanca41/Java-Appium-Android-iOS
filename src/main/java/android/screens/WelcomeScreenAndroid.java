package android.screens;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import appium.base.BaseActionsAndroid;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class WelcomeScreenAndroid extends BaseActionsAndroid{
	
	Logger log;

	public WelcomeScreenAndroid(AndroidDriver driver, Logger log) {
		super(driver, log);
		this.log = log;
	}
	
//	String[] login_button=new String[] {"id", "Login"};
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Login']")
	public WebElement login_button;
	
//	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='Experience better vet care from home']")
//	public WebElement welcome_screen_title;
//	
//	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[contains(@name, 'No more commuting.')]")
//	public WebElement welcome_screen_subtitle;
//	
//	@iOSXCUITFindBy(id="Already have an account?")
//	public WebElement have_account;
//	
//	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name='Sign Up'])[2]")
//	public WebElement sign_up_button;
	
	
//	public void assertTexts() {
//		Assert.assertEquals(welcome_screen_title.getText(), "Experience better vet care from home");
//		Assert.assertEquals(welcome_screen_subtitle.getText(), "No more commuting. Get the care your pet needs from the safety and comfort of your home.");
//		Assert.assertEquals(have_account.getText(), "Already have an account?");
//	}

	public void goToLoginScreen() {
//		assertTexts();
//		Assert.assertEquals(login_button.getText(), "Login");
//		login_button.click();
		while(check_if_element_is_present(login_button) == true) {
			click(login_button);
			if(check_if_element_is_present(login_button) == false) {
				break;
			}
		}
		log.info("User is navigating to Login screen by clicking Login button.");
	}

}
