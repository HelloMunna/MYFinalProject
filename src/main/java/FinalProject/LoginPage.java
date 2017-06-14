package FinalProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
WebDriver driver;
	
	By emailID = By.xpath(".//*[@id='email_create']");
	By passWord = By.xpath(".//*[@id='passwd']");
	By clickSignIn = By.xpath(".//*[@id='SubmitLogin']");
	By creatEmailID = By.xpath(".//*[@id='email_create']");
	By creatAccountButton = By.xpath(".//*[@id='SubmitCreate']");
	
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void typeEmailID(String Email){
		driver.findElement(emailID).sendKeys(Email);
	}
	
	public void typePassWord(String PassWord){
		driver.findElement(passWord).sendKeys(PassWord);
	}
	
	public void clickSignIn(){
		driver.findElement(clickSignIn).click();
	}
	
	public void enterNewEmailID(String Email){
		driver.findElement(creatEmailID).sendKeys(Email);
	}
	
	public void clickCreatAccountButton(){
		driver.findElement(creatAccountButton).click();
	}
	
	public void signIN(String Email, String PassWord){
		driver.findElement(emailID).sendKeys(Email);
		driver.findElement(passWord).sendKeys(PassWord);
		driver.findElement(clickSignIn);
	}

}
