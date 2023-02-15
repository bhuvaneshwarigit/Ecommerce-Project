package com.ecommerceproject.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstarctComponents  {

	WebDriver driver;
	public AbstarctComponents(WebDriver driver) {
		
		this.driver=driver;
		
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	public String splitProductprice(String p) {
		 String pr= p.split("[.]")[0];
		 return pr;
	}
	
	public int stringToInt(String string) {
		int res=Integer.parseInt(string);
		return res;
	}
}
