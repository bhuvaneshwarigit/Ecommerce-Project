package com.ecommerceproject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ecommerceproject.abstractComponents.AbstarctComponents;

public class ProductCatalogue  extends AbstarctComponents {

	
WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(css ="#s-result-sort-select")
	WebElement sortBy;
	
	By productBy=By.xpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']");
	
	@FindBy(xpath="//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']")
	List<WebElement> productList;
	

	@FindBy(xpath = "//select[@id='native_dropdown_selected_size_name']")
	WebElement productSize;
	 
	@FindBy(id = "quantity")
	WebElement productQuantity;
	
	@FindBy(xpath="//input[@id='add-to-cart-button']")
	WebElement cartBtn;
	
	public void sortingApp(String priority) {
		Select sortDropdown = new Select(sortBy);
	    sortDropdown.selectByVisibleText(priority);
	}
	public List<WebElement> getProductList() {
		waitForElementToAppear(productBy);
		return productList;
	}
	
	public int stringIntoInt(String s) {
		int i=Integer.parseInt(s);
		return i;
		
	}
	public  void selectSize(String size) throws InterruptedException {
	  try {
		  Thread.sleep(2000);
		    	 Select sizeDropdown = new Select(productSize);
				    sizeDropdown.selectByVisibleText(size);
		    }
		    catch(NoSuchElementException e) {
		    	System.out.println("size isn't displayed");
		    }
		    }
	public void selectQuantity(String quantity) throws InterruptedException {
		 
	
		   try { 
		
			   Thread.sleep(2000);  
		    Select dropdown = new Select(productQuantity);
		    dropdown.selectByVisibleText(quantity);
		    }
		    catch(NoSuchElementException e) {
		    System.out.println("Quantity isn't displayed");
		    }
		  
	
	}
	public void addItem() throws InterruptedException {
		
	
			Thread.sleep(2000);
	cartBtn.click();
		
	}
	 
}

