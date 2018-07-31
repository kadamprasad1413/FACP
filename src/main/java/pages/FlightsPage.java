package pages;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.Base;

public class FlightsPage extends Base{
	
	public static Properties CONFIG = null;
	
	public FlightsPage(){
		
		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\tiraths\\git\\mmt_workspace\\mmt\\src\\test\\resources\\config.properties");
			
			Properties CONFIG = new Properties();
			CONFIG.load(fis);
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		catch (IOException e) {
		
			e.printStackTrace();
		}
		
		
		
	}
	
	
	//WebElements
	
	public static String getValue(String key){
		return CONFIG.getProperty(key).intern();
		
		
		
		
	}
	
	
	
	
	
	
	final String xpath = "//input[@id = '" + CONFIG.getProperty("name").intern() + "'])";
	@FindBy(how = How.XPATH, using = "") public WebElement config;
	
	@FindBy(how = How.XPATH, using = "//input[@id = 'hp-widget__sfrom']") public WebElement from_dd;
	@FindBy(how = How.XPATH, using = "//input[@id = 'hp-widget__sTo']") public WebElement to_dd;
	
	@FindBy(how = How.XPATH, using = "//input[@id = 'hp-widget__sTo']") public WebElement search_btn;
	
	
	
	
	
	
	
	

	public FlightsPage(WebDriver ob){
		PageFactory.initElements(ob, "FlightsPage");
		
	}
	
	
	public boolean searchFlights(String from, String to){
		try{
			from_dd.sendKeys(from);
			to_dd.sendKeys(to);
			search_btn.click();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	
	
	
}
