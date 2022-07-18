package android.tests;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import android.base.BaseMethodsAndroid;
import android.screens.LoginScreenAndroid;
import android.screens.WelcomeScreenAndroid;

public class AndroidLoginTests extends BaseMethodsAndroid{
	
//	public FirstTest(AndroidDriver<AndroidElement> driver) {
//		super(driver);
//	}
		
//	AndroidDriver<AndroidElement> driver;
	
	@Parameters({ "username", "password" })
	@Test
	public void positiveLogin(String username, String password) throws MalformedURLException{
		
//		driver = setCapabilities();
		
		WelcomeScreenAndroid welcome_screen = new WelcomeScreenAndroid(driver, log);
		LoginScreenAndroid login_screen = new LoginScreenAndroid(driver, log);
		
//		welcome_screen.assertTexts();
		welcome_screen.goToLoginScreen();
		
//		while(true) {
//			welcome_screen.goToLoginScreen();
//			if(login_screen.inLoginPage()==true) {
//				break;
//			}
//		}
		
		login_screen.positiveLoginProcess(username, password);
		
	}
	
	@Test
	public void positiveLogin2() throws MalformedURLException{
		
//		driver = setCapabilities();
		
		WelcomeScreenAndroid welcome_screen = new WelcomeScreenAndroid(driver, log);
//		LoginScreen login_screen = new LoginScreen(driver, log);
		
//		welcome_screen.assertTexts();
		welcome_screen.goToLoginScreen();
//		login_screen.assertTexts();
		
//		while(true) {
//			welcome_screen.goToLoginScreen();
//			if(login_screen.inLoginPage()==true) {
//				break;
//			}
//		}
		
//		login_screen.positiveLoginProcess(username, password);
		
	}
	
	@Test
	public void negativeLoginWithInvalidCredentials() throws MalformedURLException{
		
		WelcomeScreenAndroid welcome_screen = new WelcomeScreenAndroid(driver, log);
//		LoginScreen login_screen = new LoginScreen(driver, log);
		
		welcome_screen.goToLoginScreen();
//		login_screen.negativeLoginProcessWithIncorrectCredentials();;
		
	}
	
	@Test(dataProvider = "csvReader")//, dataProviderClass = CsvDataProviders.class)
	public void negativeLoginErrors(Map<String, String> testData) throws MalformedURLException{
		
		String username = testData.get("username");
		String password = testData.get("password");
		String expected_error_msg = testData.get("errorMessage");
		
		WelcomeScreenAndroid welcome_screen = new WelcomeScreenAndroid(driver, log);
		LoginScreenAndroid login_screen = new LoginScreenAndroid(driver, log);
		
		welcome_screen.goToLoginScreen();
		login_screen.negativeLoginProcess(username, password, expected_error_msg);
		
	}
	
	@Test(dataProvider = "jsonReader")//, dataProviderClass = CsvDataProviders.class)
	public void negativeLoginErrorsJson(HashMap<String, String> testData) throws MalformedURLException{
		
		String username = testData.get("username");
		String password = testData.get("password");
		String expected_error_msg = testData.get("errorMessage");
		
		WelcomeScreenAndroid welcome_screen = new WelcomeScreenAndroid(driver, log);
		LoginScreenAndroid login_screen = new LoginScreenAndroid(driver, log);
		
		welcome_screen.goToLoginScreen();
		login_screen.negativeLoginProcess(username, password, expected_error_msg);
		
	}
}
