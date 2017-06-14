package Utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class screenShot {
	
public static String captureScreenShot (WebDriver driver, String screenShotName){
		
		TakesScreenshot ScreenShot = (TakesScreenshot)driver;
		File src = ScreenShot.getScreenshotAs(OutputType.FILE);
		String dest = "./ScreenShots/"+screenShotName+".jpg";
		File destination = new File (dest);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return dest;
		
	}

}
