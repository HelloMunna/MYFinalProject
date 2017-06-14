package FinalProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	
WebDriver driver;
	
	By signInButton = By.xpath(".//*[@id='header']/div[2]/div/div/nav/div[1]/a");
	By searchBox = By.name("search_query");
	By contactLink = By.id("contact-link");
	By womenSection = By.xpath(".//*[@id='block_top_menu']/ul/li[1]/a");
	By dressesSection = By.xpath(".//*[@id='block_top_menu']/ul/li[2]/a");
	By tShirtsSection = By.xpath(".//*[@id='block_top_menu']/ul/li[3]/a");
	By bruteForceSection = By.xpath(".//*[@id='block_top_menu']/ul/li[4]/a");
	
	
	public HomePage(WebDriver driver){
		this.driver= driver;
	}
	
	public void ClicksignInButton(){
		driver.findElement(signInButton).click();
	}
	
	public void searchBox(String searchItem){
		driver.findElement(searchBox).sendKeys(searchItem);
	}
	
	public void clickContactLink(){
		driver.findElement(contactLink).click();
	}
	
	public void womenSection(){
		driver.findElement(womenSection);
	}
	
	public void dressesSection(){
		driver.findElement(dressesSection);
	}
	
	public void tShirtsSection(){
		driver.findElement(tShirtsSection);
	}
	
	public void bruteForceSection(){
		driver.findElement(bruteForceSection);
	}

}
