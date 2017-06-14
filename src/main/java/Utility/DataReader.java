package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class DataReader {
	
public static String getTestData (String TestDataKey) throws Exception{
		
		File src = new File ("./Config/ObjRep.property");
		FileInputStream fis = new FileInputStream (src);
		Properties prop = new Properties ();
		prop.load(fis);
		String testData = prop.getProperty(TestDataKey);
		return testData;
		
		
	}
	
	public static String getLocator (String LocatorName) throws Exception{
		
		File src = new File ("./Config/Locator.property");
		FileInputStream fis = new FileInputStream (src);
		Properties prop = new Properties ();
		prop.load(fis);
		String locatorData = prop.getProperty(LocatorName);
		return locatorData;
		
		
	}

}
