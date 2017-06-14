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
			
		}
		
		@Test (priority=0, enabled = true)
		public void accountCreat(){
			try {
				test = extent.createTest("Test1= Account Create");
				home.ClicksignInButton();
				login.enterNewEmailID("invalidEmail@Email");
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
				int randomInt = randomGenerator.nextInt(500);
				login.enterNewEmailID("munna" +randomInt+ "@gmail.com");
				login.clickCreatAccountButton();
				String createAccountPage = driver.findElement(By.xpath(DataReader.getLocator("CreateAccount"))).getText();
				System.out.println(createAccountPage);
				Assert.assertTrue(createAccountPage.contains("CREATE AN ACCOUNT"), "Not Displayed.");
				
				reg.titleMR();
				String firstName = RandomStringUtils.randomAlphabetic(8);
				reg.typeFirstName(firstName);
				String lastName = RandomStringUtils.random(8);
				reg.typeLastName(lastName);
				reg.typePassWord("12345");
				Select day = new Select (reg.selectDay());
				day.selectByIndex(12);
				Select month = new Select (reg.selectMonth());
				month.selectByValue("10");
				Select year = new Select (reg.selectYear());
				year.selectByValue("1990");
				reg.ClickRegisterButton();
				
				//String accountConfirmation = driver.findElement(By.xpath(".//*[@id='center_column']/p[1]")).getText().trim();
				//Assert.assertTrue(accountConfirmation.contains("Your account has been created."), "Account Created Message Displayed");
				
				profile.clickPersonalInfo();
				WebElement lastNameInput = driver.findElement(By.xpath(DataReader.getLocator("LastName")));
				lastNameInput.clear();
				lastNameInput.sendKeys("Munna");
				reg.typePassWord("12345");
				profile.clickSave();
				
				//String nameValidation = driver.findElement(By.cssSelector(".account")).getText();
				//Assert.assertTrue(nameValidation.contains(firstName +lastName), "Name Did Not Match");
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	 		
	 		
	 }
	   
	    @Test (priority = 3, enabled = false)
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
					test.log(Status.PASS, "All Products have SHIRT Keyword.");
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
			}
			
		} 
		    
	}
	    @Test (priority = 4, enabled = false)
	    public void addCart(){
	    	WebElement element = driver.findElement(By.xpath(".//*[@id='homefeatured']/li[1]"));
	    	WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.visibilityOf(element));
	        Actions action = new Actions (driver);
	        action.moveToElement(element).build().perform();
	        driver.findElement(By.xpath(".//*[@id='homefeatured']/li[1]/div/div[1]/div/a[2]")).click();
	        
	        //WebDriverWait wait2 = new WebDriverWait(driver, 60);
	        //wait2.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("fancybox-frame1497081697296")));
	  
	        driver.switchTo().frame(0);
	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        Select size = new Select (driver.findElement(By.xpath(".//*[@id='group_1']")));
	        size.selectByIndex(2);
	        driver.findElement(By.name("Submit")).click();
	        driver.findElement(By.xpath(".//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span")).click();
	    	
	    	
	    }
	  
		
		@AfterMethod
		public void browserClose(ITestResult result){
		   if (ITestResult.FAILURE==result.getStatus()){
			   screenShot.captureScreenShot(driver, result.getName());
		   }
		   driver.close();
		   extent.flush();
		}

	}