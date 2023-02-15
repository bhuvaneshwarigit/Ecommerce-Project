package com.ecommerceproject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerceproject.abstractComponents.AbstarctComponents;

public class HomePage extends AbstarctComponents{

	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (id = "twotabsearchtextbox")
	WebElement searchBox;
	
	@FindBy(xpath = "//input[@id='nav-search-submit-button']")
	WebElement searchsub;
	
	@FindBy(css= "#nav-cart-count")
	WebElement cartBtn;
	
	 @FindBy(id="nav-logo-sprites")
	   WebElement storelogo;
	 
	 public boolean validateLogo() {
		   return storelogo.isDisplayed();
	   }
	   
	   public String getMyStoreTitle() {
		   String getMyStore=driver.getTitle();
		   return getMyStore;
	   }
	public void searchLogic(String text){
     
		searchBox.click();
		searchBox.sendKeys(text);
		searchsub.click();
	}


	public CartPage gotoCartPage() {
		cartBtn.click();
		  return new CartPage(driver);
	}
	
	
	
	
}
