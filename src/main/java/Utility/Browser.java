package Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Browser {
	
WebDriver driver;
	
	public Browser(WebDriver driver){
		this.driver = driver;
	}
	
	public void browserOpen (String browserName, String URL){
		
		if(browserName.equalsIgnoreCase("FireFox")){
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Mustafa Munna\\Desktop\\Browser\\geckodriver-v0.16.1-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mustafa Munna\\Desktop\\Browser\\chromedriver.exe");
			driver = new ChromeDriver();
			
		}else{
			System.setProperty("WebDriver.ie.driver", "C:\\Users\\Mustafa Munna\\Desktop\\Browser\\MicrosoftWebDriver.exe");
			driver = new InternetExplorerDriver();
			}
		
		driver.get(URL);
		driver.manage().window().maximize();
		
	}
	
    public void closeBrowser(WebDriver driver){
		driver.quit();
	}

}
