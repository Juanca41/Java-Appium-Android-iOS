package android.tests;
import java.net.MalformedURLException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import android.base.BaseMethodsAndroid;
import android.screens.WelcomeScreenAndroid;

public class LoginTests extends BaseMethodsAndroid{
	
//	public FirstTest(AndroidDriver<AndroidElement> driver) {
//		super(driver);
//	}
		
//	AndroidDriver<AndroidElement> driver;
	
	@Parameters({ "username", "password" })
	@Test
	public void positiveLogin(String username, String password) throws MalformedURLException{
		
//		driver = setCapabilities();
		
		WelcomeScreenAndroid welcome_screen = new WelcomeScreenAndroid(driver, log);
//		LoginScreen login_screen = new LoginScreen(driver, log);
		
//		welcome_screen.assertTexts();
		welcome_screen.goToLoginScreen();
		
//		while(true) {
//			welcome_screen.goToLoginScreen();
//			if(login_screen.inLoginPage()==true) {
//				break;
//			}
//		}
		
//		login_screen.positiveLoginProcess(username, password);
		
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
	
	@Parameters({ "username", "password", "error" })
	@Test
	public void negativeLoginErrors(String username, String password, String error) throws MalformedURLException{
		
		WelcomeScreenAndroid welcome_screen = new WelcomeScreenAndroid(driver, log);
//		LoginScreen login_screen = new LoginScreen(driver, log);
		
		welcome_screen.goToLoginScreen();
//		login_screen.negativeLoginProcess(username, password, error);
		
	}
}
