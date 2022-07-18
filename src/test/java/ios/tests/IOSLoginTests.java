package ios.tests;
import java.net.MalformedURLException;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ios.base.BaseMethodsIOS;
import ios.screens.LoginScreenIOS;
import ios.screens.WelcomeScreenIOS;

public class IOSLoginTests extends BaseMethodsIOS{
	
//	public FirstTest(AndroidDriver<AndroidElement> driver) {
//		super(driver);
//	}
		
//	AndroidDriver<AndroidElement> driver;
	
	@Parameters({ "username", "password" })
	@Test
	public void positiveLogin(String username, String password) throws MalformedURLException{
		
//		driver = setCapabilities();
		
		WelcomeScreenIOS welcome_screen = new WelcomeScreenIOS(driver, log);
		LoginScreenIOS login_screen = new LoginScreenIOS(driver, log);
		
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
		
		WelcomeScreenIOS welcome_screen = new WelcomeScreenIOS(driver, log);
		LoginScreenIOS login_screen = new LoginScreenIOS(driver, log);
		
//		welcome_screen.assertTexts();
		welcome_screen.goToLoginScreen();
		login_screen.assertTexts();
		
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
		
		WelcomeScreenIOS welcome_screen = new WelcomeScreenIOS(driver, log);
		LoginScreenIOS login_screen = new LoginScreenIOS(driver, log);
		
		welcome_screen.goToLoginScreen();
//		login_screen.negativeLoginProcessWithIncorrectCredentials();;
		
	}
	
//	@Parameters({ "username", "password", "error" })
	@Test(dataProvider = "csvReader")//, dataProviderClass = CsvDataProviders.class)
	public void negativeLoginErrors(Map<String, String> testData) throws MalformedURLException{
		
		String username = testData.get("username");
		String password = testData.get("password");
		String expected_error_msg = testData.get("errorMessage");
		
		WelcomeScreenIOS welcome_screen = new WelcomeScreenIOS(driver, log);
		LoginScreenIOS login_screen = new LoginScreenIOS(driver, log);
		
		welcome_screen.goToLoginScreen();
//		login_screen.negativeLoginProcess(username, password, expected_error_msg);
		
	}
}
