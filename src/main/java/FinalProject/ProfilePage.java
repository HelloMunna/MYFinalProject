package FinalProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
	
	WebDriver driver;
	
	By personalInfo = By.xpath(".//*[@id='center_column']/div/div[1]/ul/li[5]/a/span");
	By save = By.xpath(".//*[@id='center_column']/div/form/fieldset/div[11]/button");
	
	public ProfilePage (WebDriver driver){
		this.driver = driver;
	}
	
	public void clickPersonalInfo (){
		driver.findElement(personalInfo).click();
	}
	
	public void clickSave (){
		driver.findElement(save).click();
	}

}
