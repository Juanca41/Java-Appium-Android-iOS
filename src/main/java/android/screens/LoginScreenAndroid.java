package android.screens;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import appium.base.BaseActionsAndroid;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginScreenAndroid extends BaseActionsAndroid{
	
	public LoginScreenAndroid(AndroidDriver driver, Logger log) {
		super(driver, log);
	}
	
	@AndroidFindBy(xpath="//android.view.View[@text='Welcome Back']")
	private WebElement login_screen_title;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Remember Me']")
	private WebElement remember_me;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Forgot your password?']")
	private WebElement forgot_password;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='View other sign in options']")
	private WebElement other_sign_in_options;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.widget.EditText")
	private WebElement email_field;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.widget.EditText")
	private WebElement pwd_field;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Log In']")
	private WebElement login_button;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='User or password invalid.']")
	private WebElement incorrect_email_pwd_text;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	private WebElement incorrect_email_pwd_ok_button;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Your password must contain at least one number, a capital letter and a minimum of 6 characters']")
	private WebElement invalid_format_pwd;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Please enter a valid email address']")
	private WebElement invalid_format_email;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='This field is required']")
	private WebElement field_required_error;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView")
	private WebElement error_message_element;
	
//	public Boolean inLoginPage() {
//		return check_if_element_is_present(login_screen_title);
//	}
	
	public void assertTexts() {
		assert_text(login_screen_title, "Welcome Back");
		assert_text(remember_me, "Remember Me");
		assert_text(forgot_password, "Forgot your password?");
		assert_text(other_sign_in_options, "View other sign in options");
	}
	
	public void loginProcess(String username, String password) {
		assertTexts();
		type(email_field, username);
		type(pwd_field, password);
		clickLoginButton();
	}
	
	public void positiveLoginProcess(String username, String password) {
		loginProcess(username, password);
		
	}
	
//	public void negativeLoginProcessWithIncorrectCredentials() {
//		loginProcess("juancarlosgularte@bettervet.com", "Incorrect10");
//		assert_text(incorrect_email_pwd_text, "User or password invalid.");
//	}
	
	public void negativeLoginProcess(String username, String password, String error) {
		loginProcess(username, password);
		assert_text(error_message_element, error);
	}
	

	public void clickLoginButton() {
		
		click(login_button);
	}

}
