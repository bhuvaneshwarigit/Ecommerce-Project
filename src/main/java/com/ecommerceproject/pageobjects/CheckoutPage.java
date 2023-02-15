package com.ecommerceproject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerceproject.abstractComponents.AbstarctComponents;

public class CheckoutPage extends AbstarctComponents {

	 WebDriver driver;
		
		public CheckoutPage(WebDriver driver) {
			
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}	
	
	@FindBy(css="div[id='header'] h1")
	WebElement checkoutHeader;
	
	public boolean checkHeader(String header) {
		boolean f=checkoutHeader.getText().contains(header);
		return f;
	}
	
}
