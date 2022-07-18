package appium.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseMethods {
	
//	protected static IOSDriver<IOSElement> driver;
	protected static AppiumDriver driver;
	protected String testSuiteName;
	protected String testName;
	protected String testMethodName; 
	static AppiumDriverLocalService appium;
	
	 public static void sleep(int s) throws InterruptedException {
			Thread.sleep(s);
	 }
	 
	@DataProvider(name = "csvReader")
	public static Iterator<Object[]> csvReader(Method method) {
		List<Object[]> list = new ArrayList<Object[]>();
		String pathname = "src" + File.separator + "test" + File.separator + "resources" + File.separator
				+ "DataProviders" + File.separator + method.getDeclaringClass().getSimpleName() + File.separator
				+ method.getName() + ".csv";
		File file = new File(pathname);
		try {
			CSVReader reader = new CSVReader(new FileReader(file));
			String[] keys = reader.readNext();
			if (keys != null) {
				String[] dataParts;
				while ((dataParts = reader.readNext()) != null) {
					Map<String, String> testData = new HashMap<String, String>();
					for (int i = 0; i < keys.length; i++) {
						testData.put(keys[i], dataParts[i]);
					}
					list.add(new Object[] { testData });
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + pathname + " was not found.\n" + e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + pathname + " file.\n" + e.getStackTrace().toString());
		} catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.iterator();
	}
	
	@DataProvider(name = "jsonReader")
	public static Object [][] jsonReader() throws IOException{
		
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"/src/test/resources/DataProviders/Json/negativeLoginErrors.json");
		return new Object [][] { {data.get(0)}, {data.get(1)}, {data.get(2)}, {data.get(3)} };
	}
	
	public static List<HashMap<String, String>> getJsonData(String jsonPath) throws IOException{
		
//		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/src/test/resources/DataProviders/Json/negativeLoginErrors.json"));
		String jsonContent = FileUtils.readFileToString(new File(jsonPath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	
	/** Take screenshot */
	protected void takeScreenshot(String fileName) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")//main directory
				+ File.separator + "test-output" 
				+ File.separator + "screenshots"
				+ File.separator + getTodaysDate() 
				+ File.separator + testSuiteName 
				+ File.separator + testName
				+ File.separator + testMethodName 
				+ File.separator + getSystemTime() 
				+ " " + fileName + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Todays date in yyyyMMdd format */
	protected static String getTodaysDate() {
		return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
	}

	/** Current time in HHmmssSSS */
	protected String getSystemTime() {
		return (new SimpleDateFormat("HHmmssSSS").format(new Date()));
	}
	
	/** Get logs from browser console */
	protected List<LogEntry> getBrowserLogs() {
		LogEntries log = driver.manage().logs().get("browser");
		List<LogEntry> logList = log.getAll();
		return logList;
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
		
		boolean isAppiumRunning = checkIfServerIsRunning(4723);
        
        if(isAppiumRunning == true) {
        	appium.close();
        	appium.stop();
            
            try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	 }

}
