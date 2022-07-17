package ios.screens;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import appium.base.BaseActionsIOS;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginScreenIOS extends BaseActionsIOS{
	
	Logger log;
	
	public LoginScreenIOS(IOSDriver driver, Logger log) {
		super(driver, log);
		this.log = log;
	}
	
//	String[] element=new String[] {"id", "Login"};
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name='Welcome Back'])[2]")
	public WebElement login_screen_title;
	
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name='Remember Me'])[3]")
	public WebElement remember_me;
	
	@iOSXCUITFindBy(id="Forgot your password?")
	public WebElement forgot_password;
	
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name='View other sign in options'])[2]")
	public WebElement other_sign_in_options;
	
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name='Email Email'])[4]//XCUIElementTypeTextField")
	public WebElement email_field;
	
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name='Password Password'])//XCUIElementTypeSecureTextField")
	public WebElement pwd_field;
	
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name='Log In'])[2]")
	public WebElement login_button;
	
//	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name=\\\"Welcome Back\\\"])[2]")
//	public WebElement incorrect_email_pwd_text;
//	
//	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name=\\\"Welcome Back\\\"])[2]")
//	public WebElement incorrect_email_pwd_ok_button;
//	
//	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name=\\\"Welcome Back\\\"])[2]")
//	public WebElement invalid_format_pwd;
	
//	@iOSXCUITFindBy(xpath="")
//	public WebElement invalid_format_email;
//	
//	@iOSXCUITFindBy(xpath="")
//	public WebElement field_required_error;
//	
//	@iOSXCUITFindBy(xpath="")
//	public WebElement error_message_element;
	
//	By incorrect_email_pwd_text = (By.xpath("//android.widget.TextView[@text='User or password invalid.']"));
//	By incorrect_email_pwd_ok_button = (By.xpath("//android.widget.Button[@text='OK']"));
//	By invalid_format_pwd = (By.xpath("//android.widget.TextView[@text='Your password must contain at least one number, a capital letter and a minimum of 6 characters']"));
//	By invalid_format_email = (By.xpath("//android.widget.TextView[@text='Please enter a valid email address']"));
//	By field_required_error = (By.xpath("//android.widget.TextView[@text='This field is required']"));
//	By error_message_element = (By.xpath("//android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView"));
	
//	public Boolean inLoginPage() {
//		return check_if_element_is_present(login_screen_title);
//	}
	
	public void assertTexts() {
//		System.out.println(login_screen_title.getText());
		Assert.assertEquals(login_screen_title.getText(), "Welcome Back");
		Assert.assertEquals(remember_me.getText(), "Remember Me");
		Assert.assertEquals(forgot_password.getText(), "Forgot your password?");
		Assert.assertEquals(other_sign_in_options.getText(), "View other sign in options");
	}
	
	public void loginProcess(String username, String password) {
		assertTexts();
		email_field.sendKeys(username);
		log.info("User has added the email: "+username);
		pwd_field.sendKeys(password);
		log.info("User has added the password: "+password);
		clickLoginButton();
	}
	
	public void positiveLoginProcess(String username, String password) {
		loginProcess(username, password);
		
	}
	
//	public void negativeLoginProcessWithIncorrectCredentials() {
//		loginProcess("juancarlosgularte@bettervet.com", "Incorrect10");
//		Assert.assertEquals(incorrect_email_pwd_text.getText(), "User or password invalid.");
//	}
//	
//	public void negativeLoginProcess(String username, String password, String error) {
//		loginProcess(username, password);
//		Assert.assertEquals(error_message_element.getText(), error);
//	}
	

	public void clickLoginButton() {
		
//		login_button.click();
		click(login_button);
		log.info("User has clicked on Login button.");
	}

}
