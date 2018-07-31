package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;



public class Methods extends Base{
	
	public static Random random = new Random();
	
	public static boolean compareTitle(String ExpectedTitle){
		if(ob.getTitle().compareToIgnoreCase(ExpectedTitle)== 0){
			captureScreeshot();
			return true;}
			else{
				captureScreeshot();
				return false;
			}
	}
	
	public static void captureScreeshot(){
		TakesScreenshot ts = (TakesScreenshot)ob;
		File raw = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\test-output-extent\\screenshots\\" + random.nextInt(1000) + ".jpg";
		try {
			FileUtils.copyFile(raw, new File(path));
			ss_paths.add(path);
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}