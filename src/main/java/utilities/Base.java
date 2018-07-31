package utilities;

//import java.io.IOException;
//import java.lang.reflect.Method;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;


//import java.util.Iterator;









import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
/*import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
*/
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Base {
	
	public static WebDriver ob = null;
	public static Actions action = null;
	
	public static ExtentReports extent;
    public static ExtentTest parentTest, childTest;
    
    public static ArrayList<String> ss_paths = new ArrayList<String>();
	
	public boolean init(){
		return false;
	}
	
	public static boolean openBrowser(String browserType){
		
		if( browserType.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromeDriver.exe");
			ob = new ChromeDriver();
			return true;
		}else if(browserType.equalsIgnoreCase("ff")){
			System.setProperty("webdriver.firefox.driver", System.getProperty("user.dir") + "\\drivers\\FirefoxDriver.exe");
			ob = new FirefoxDriver();
			return true;
		}else if (browserType.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\drivers\\InternetExplorerDriver.exe");
			ob = new InternetExplorerDriver();
			return true;
		}else{
			return false;
		}
	}	
	
	//extent reports implementation	
	@BeforeSuite
	public void beforeSuite() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\test-output-extent\\" + "extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	
	@BeforeTest
	public void beforeTest(){
		
	}
	
    @BeforeClass (alwaysRun = true)
    public void beforeClass() {
    	parentTest = extent.createTest(getClass().getName());
        
    }

    @BeforeMethod (alwaysRun = true)
    public void beforeMethod(Method method) {
    	//need to get test desc here
    	childTest = parentTest.createNode(method.getName());
    }

    @AfterMethod (alwaysRun = true)
    public void afterMethod( ITestResult result) {
    
    	if (result.getStatus() == ITestResult.FAILURE){
    		childTest.fail(result.getThrowable());
    		
    		Iterator<String> itr = ss_paths.iterator();
    		while(itr.hasNext()){
    			try {
					childTest.addScreenCaptureFromPath(itr.next());
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    		
    		ss_paths.clear();
    	}
        	
        else if (result.getStatus() == ITestResult.SKIP){
        	childTest.skip(result.getThrowable());
    		
    		Iterator<String> itr = ss_paths.iterator();
    		while(itr.hasNext()){
    			try {
    				childTest.addScreenCaptureFromPath(itr.next());
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
		
		ss_paths.clear();
        }
        else{
        	childTest.pass("Test passed");
        	Iterator<String> itr = ss_paths.iterator();
    		while(itr.hasNext()){
    			try {
					childTest.addScreenCaptureFromPath(itr.next());
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    		ss_paths.clear();
        }
    }
    
 /*   @AfterClass
    public void afterClass() {
    	
    }
    
    @AfterTest
    public void afterTest() {
    	
    }*/
    
    @AfterSuite
    public void afterSuite(){
    	extent.flush();
    }
}
