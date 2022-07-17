package appium.base;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import org.apache.logging.log4j.Logger;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseMethods {
	
//	protected static IOSDriver<IOSElement> driver;
//	protected static AppiumDriver<WebElement> driver;
	protected Logger log;
	static AppiumDriverLocalService appium;
	
	 public static void sleep(int s) throws InterruptedException {
			Thread.sleep(s);
	 }
	 
	 public boolean checkIfServerIsRunning(int port) {

	    boolean isServerRunning = false;
	    ServerSocket serverSocket;
	    try {
	        serverSocket = new ServerSocket(port);
	        serverSocket.close();
	    } catch (IOException e) {
	        //If control comes here, then it means that the port is in use
	        isServerRunning = true;
	    } finally {
	        serverSocket = null;
	    }
	    return isServerRunning;
	 }

	 public void startAppium() {
		 
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
        // Tell builder where node is installed. Or set this path in an environment variable named NODE_PATH
        builder.usingDriverExecutable(new File("/usr/local/bin/node"));
        // Tell builder where Appium is installed. Or set this path in an environment variable named APPIUM_PATH
        builder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"));
        builder.withIPAddress("127.0.0.1");
        builder.withArgument(() -> "--base-path", "/wd/hub");
        builder.usingPort(4723);
        
        boolean isAppiumRunning = checkIfServerIsRunning(4723);
        
        if(isAppiumRunning == false) {
        	appium = AppiumDriverLocalService.buildService(builder);
            appium.start();
            
            try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	 }
	 
	 
	 public void stopAppium() {
		appium.close();
		appium.stop();
		log.info("Appium was closed.");
	 }

}
