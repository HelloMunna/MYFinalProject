package MainProject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import FinalProject.HomePage;
import FinalProject.LoginPage;
import FinalProject.ProfilePage;
import FinalProject.RegistrationPage;
import Utility.DataReader;
import Utility.ExcelReader;
import Utility.screenShot;

public class ProjectTest {
	
	WebDriver driver;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	RegistrationPage reg;
	HomePage home;
	LoginPage login;
	ProfilePage profile;
	ExcelReader excel;

	    @BeforeSuite
	    public void htmlReport(){
	        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	        Date date = new Date();
	        String currentDate = dateFormat.format(date);
	        currentDate = currentDate.replaceAll("/", "-");
	        currentDate = currentDate.replaceAll(":", "-");
	        System.out.println(currentDate);
	        htmlReporter = new ExtentHtmlReporter ("./HTMLReports/"+currentDate+".html");
		    extent = new ExtentReports();
		    extent.attachReporter(htmlReporter);
		 
	}

	    @BeforeMethod
		public void browser() throws Exception{
			//System.setProperty("webdriver.gecko.driver", DataReader.getTestData("Gecko"));
			//driver = new FirefoxDriver();
	        System.setProperty("webdriver.chrome.driver", DataReader.getTestData("Chrome"));
	    	driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(DataReader.getTestData("URL"));
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
			reg = new RegistrationPage(driver);
			home = new HomePage(driver);
			login = new LoginPage (driver);
			profile = new ProfilePage (driver);
			excel = new ExcelReader(DataReader.getTestData("ExcelPath"));
			
		}
		
		@Test (priority=0, enabled = true)
		public void accountCreat(){
			try {
				test = extent.createTest("Test1= Account Create");
				home.ClicksignInButton();
				login.enterNewEmailID("invalidEmail@Email");
				//login.enterNewEmailID(excel.getData(0, 2, 4));
				login.clickCreatAccountButton();
				
				String color = driver.findElement(By.xpath(DataReader.getLocator("EmailBox"))).getCssValue("background-color");
				System.out.println(color);
				String hex = Color.fromString(color).asHex();
				System.out.println(hex);
				String Red = "#ff0000";
				
				Assert.assertEquals(hex, Red);
				test.log(Status.PASS, "Color Matched");
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		
		@Test (priority=1, enabled = true)
		public void validAccountCreate(){
			try {
				test = extent.createTest("Test2= Valid Account Create");
				home.ClicksignInButton();
				login.enterNewEmailID("rabbani@rabbani.com");
				login.clickCreatAccountButton();
				
				String message = driver.findElement(By.xpath(DataReader.getLocator("Message"))).getText();
				System.out.println(message);
				String expectedMessage = "An account using this email address has already been registered. Please enter a valid password or request a new one.";
				Assert.assertEquals(message, expectedMessage);
				test.log(Status.PASS, "Both Messages Matched");
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	        
	     }
	     @Test (priority=2, enabled = true)
	     public void createNewValidAccount(){
	    	try {
				test = extent.createTest("Test3= Create New Valid Account");
				home.ClicksignInButton();
				Random randomGenerator = new Random(); 
				int randomInt = randomGenerator.nextInt(700);
				login.enterNewEmailID("munna" +randomInt+ "@gmail.com");
				login.clickCreatAccountButton();
				String createAccountPage = driver.findElement(By.xpath(DataReader.getLocator("CreateAccount"))).getText();
				System.out.println(createAccountPage);
				Assert.assertTrue(createAccountPage.contains("AUTHENTICATION"), "A/C Creation Page Not Displayed.");
				
				reg.titleMR();
				String firstName = RandomStringUtils.randomAlphabetic(10);
				reg.typeFirstName(firstName.toUpperCase());
				String lastName = RandomStringUtils.randomAlphabetic(8);
				reg.typeLastName(lastName);
				reg.typePassWord("1234567");
				Select day = new Select (reg.selectDay());
				day.selectByIndex(12);
				Select month = new Select (reg.selectMonth());
				month.selectByValue("10");
				Select year = new Select (reg.selectYear());
				year.selectByValue("1990");
				reg.ClickRegisterButton();
				
				String accountConfirmation = driver.findElement(By.xpath(DataReader.getLocator("ACConfirmation"))).getText().trim();
				System.out.println(accountConfirmation);
				Assert.assertTrue(accountConfirmation.contains("Your account has been created."), "Message Not Displayed");
				
				profile.clickPersonalInfo();
				WebElement lastNameInput = driver.findElement(By.xpath(DataReader.getLocator("LastName")));
				lastNameInput.clear();
				lastNameInput.sendKeys("MUNNA");
				reg.typeOldPassword("1234567");
				profile.clickSave();
				
				String nameValidation = driver.findElement(By.cssSelector(".account")).getText().toUpperCase();
				Assert.assertTrue(nameValidation.contains(firstName.toUpperCase()+" "+"MUNNA"), "Name Did Not Match");
	            test.log(Status.PASS, "Validation Completed");
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	 		
	 		
	 }
	   
	    @Test (priority = 3, enabled = true)
		public void productSearch() throws Exception{
		    try {
				test = extent.createTest("Test4= Product Search");
				WebElement search = driver.findElement(By.id(DataReader.getLocator("Search")));
				search.sendKeys("Shirt");
				search.submit();
				
				
				List <WebElement> allElements = driver.findElements(By.cssSelector(DataReader.getLocator("ProductName")));
				int allElementsSize = allElements.size();
				System.out.println(allElementsSize);
				
				for (WebElement element : allElements){
					String product = element.getText().trim().toUpperCase();
					Assert.assertTrue(product.contains("SHIRT"));
					
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
			
			
			List <WebElement> allPrices = driver.findElements(By.cssSelector(DataReader.getLocator("ProductPrice")));
			int allPricesSize = allPrices.size();
			System.out.println(allPricesSize);
			
			for (int i=0; i<allPricesSize-1; i++){
				System.out.println(allPrices.get(i).getText().replace("$", ""));
				String value = allPrices.get(i).getText().replace("$", "");
				double price = Double.parseDouble(value);
				Assert.assertTrue(price<=50.00, "One of the Products cost more than $50");
				test.log(Status.PASS, "All Products have SHIRT Keyword and Top Seller Products Cost less than $50");
			}
			
		} 
		    
	}
	    @Test (priority = 4, enabled = true)
	    public void addCart() throws Exception{
	    	try {
				WebElement element = driver.findElement(By.xpath(DataReader.getLocator("listProduct")));
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(element));
				Actions action = new Actions (driver);
				action.moveToElement(element).build().perform();
				driver.findElement(By.xpath(DataReader.getLocator("productClick"))).click();
				
				driver.switchTo().frame(driver.findElement(By.id(DataReader.getLocator("iFrame"))));
				WebElement quantity = driver.findElement(By.xpath(DataReader.getLocator("quantity")));
				quantity.clear();
				quantity.sendKeys("2");
				
				Select size = new Select (driver.findElement(By.xpath(DataReader.getLocator("size"))));
				size.selectByIndex(2);
				driver.findElement(By.name(DataReader.getLocator("submitButton"))).click();
				
				driver.switchTo().defaultContent();
				driver.findElement(By.xpath(DataReader.getLocator("checkOut"))).click();
				
				String Price = driver.findElement(By.xpath(DataReader.getLocator("price"))).getText().replace("$", "");
				double unitPrice = Double.parseDouble(Price);
				String QTN = driver.findElement(By.xpath(DataReader.getLocator("QtnText"))).getText();
				double productQTN = Double.parseDouble(QTN);
				String totalValue = driver.findElement(By.xpath(DataReader.getLocator("totalValue"))).getText().replace("$", "");
				double total = Double.parseDouble(totalValue);
				
				Assert.assertTrue(unitPrice*productQTN==total, "Calculation Error, Test Failed");  
				test.log(Status.PASS, "Test Passed");
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	    	
	     }
	    
		@AfterMethod
		public void browserClose(ITestResult result){
		   if (ITestResult.FAILURE==result.getStatus()){
			   screenShot.captureScreenShot(driver, result.getName());
		   }
		   driver.quit();
		   extent.flush();
		}

	}