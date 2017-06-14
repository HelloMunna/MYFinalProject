package FinalProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage {
	
WebDriver driver;
	
	By titleMR = By.id("id_gender1");
	By titleMrs = By.id("id_gender2");
	By firstName = By.name("customer_firstname");
	By lastName = By.xpath(".//*[@id='customer_lastname']");
	By email = By.name("email");
	By creatPassWord = By.xpath(".//*[@id='passwd']");
	By selectDay = By.id("days");
	By selectMonth = By.name("months");
	By selectYear = By.xpath(".//*[@id='years']");
	By registerButton = By.xpath(".//*[@id='submitAccount']");
	
	
	public RegistrationPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void titleMR(){
		driver.findElement(titleMR);
	}
	
	public void titleMrs(){
		driver.findElement(titleMrs);
	}
	
	public void typeFirstName(String FirstName){
		driver.findElement(firstName).sendKeys(FirstName);
	}
	
	public void typeLastName(String LastName){
		driver.findElement(lastName).sendKeys(LastName);
	}
	
	public void typeEmail(String Email){
		driver.findElement(email).sendKeys(Email);
	}
	
	public void typePassWord(String PassWord){
		driver.findElement(creatPassWord).sendKeys(PassWord);
	}
	
	public WebElement selectDay(){
		return driver.findElement(selectDay);
	}
	
	public WebElement selectMonth(){
		return driver.findElement(selectMonth);
	}
	
	public WebElement selectYear(){
		return driver.findElement(selectYear);
	}
	
	public void ClickRegisterButton(){
		driver.findElement(registerButton).click();
	}

}
