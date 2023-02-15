package com.ecommerceproject.testComponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.ecommerceproject.abstractComponents.AbstarctComponents;
import com.ecommerceproject.pageobjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest  {

	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {
		
		Properties prob= new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//com//ecommerceproject//resources//GlobalData.properties");
		prob.load(fis);
		String browserName= prob.getProperty("browser");
		
		  if (browserName.equalsIgnoreCase("Chrome")) {
			  WebDriverManager.chromedriver().setup();
			  driver = new ChromeDriver();
		  }else if (browserName.equalsIgnoreCase("Firefox")) {
			  WebDriverManager.firefoxdriver().setup();
			  driver = new FirefoxDriver();
		  }else if (browserName.equalsIgnoreCase("IE")) {
			  WebDriverManager.iedriver().setup();
			  driver = new InternetExplorerDriver();
		  }
	
	    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
		return driver;
	}
	

		
		public List<HashMap<String, String>> getJsonFileToMap(String filePath) throws IOException {
			String jsonContent = FileUtils.readFileToString(new File(filePath) ,
	        StandardCharsets.UTF_8);
		    ObjectMapper mapper= new ObjectMapper();
		    List<HashMap<String,String>> data= mapper.readValue(jsonContent , new TypeReference<List<HashMap<String,String>>>() {});
		     return data;	
		}
		
		public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
			TakesScreenshot ts= (TakesScreenshot)driver;
			File srcFile=ts.getScreenshotAs(OutputType.FILE);
			File file=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
			FileUtils.copyFile(srcFile, file);
			return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		}
		
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver= initializeDriver();
		landingPage = new LandingPage(driver);
	    landingPage.goTo();
	    return  landingPage;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		
		List<HashMap<String,String>> data= getJsonFileToMap(System.getProperty("user.dir")+"//src//test//java//com//ecommerceproject//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)}};
	}
}
