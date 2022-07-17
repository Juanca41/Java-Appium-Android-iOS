package ios.screens;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import appium.base.BaseActionsIOS;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class WelcomeScreenIOS extends BaseActionsIOS{
	
	Logger log;

	public WelcomeScreenIOS(IOSDriver driver, Logger log) {
		super(driver, log);
		this.log = log;
	}
	
//	String[] login_button=new String[] {"id", "Login"};
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name='Login']")
	public WebElement login_button;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='Experience better vet care from home']")
	public WebElement welcome_screen_title;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[contains(@name, 'No more commuting.')]")
	public WebElement welcome_screen_subtitle;
	
	@iOSXCUITFindBy(id="Already have an account?")
	public WebElement have_account;
	
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name='Sign Up'])[2]")
	public WebElement sign_up_button;
	
	
	public void assertTexts() {
		Assert.assertEquals(welcome_screen_title.getText(), "Experience better vet care from home");
		Assert.assertEquals(welcome_screen_subtitle.getText(), "No more commuting. Get the care your pet needs from the safety and comfort of your home.");
		Assert.assertEquals(have_account.getText(), "Already have an account?");
	}

	public void goToLoginScreen() {
		assertTexts();
		Assert.assertEquals(login_button.getText(), "Login");
//		login_button.click();
		click(login_button);
		log.info("User is navigating to Login screen by clicking Login button.");
	}

}
