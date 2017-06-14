package Utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class extentReport {
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	 
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
	    
	/*@AfterMethod
	public void getResult(ITestResult result){
		if (ITestResult.FAILURE==result.getStatus()){
			test.fail(MarkupHelper.createLabel(result.getName()+ "Test Case Failed", ExtentColor.RED));
			test.fail(result.getThrowable());
		}else if (ITestResult.SUCCESS==result.getStatus()){
			test.pass(MarkupHelper.createLabel(result.getName()+ "Test Case Passed", ExtentColor.GREEN));
		}else{
			test.skip(MarkupHelper.createLabel(result.getName()+ "Test Case Skipped", ExtentColor.PINK));
			test.skip(result.getThrowable());
		}
	}*/
	@AfterSuite
	public void tearDown(){
		extent.flush();
	}
	 
}