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

public class CartPage extends AbstarctComponents {

  WebDriver driver;
	
	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(xpath="//div[@data-name=\"Active Items\"]/child::div[@data-itemcategory=\"normal\"]")
	List<WebElement> cartItems;
	
	By by=By.xpath("//span[@class='a-dropdown-prompt']");
	
	By by1= By.cssSelector(".sc-product-price");
	
	@FindBy(id="sc-subtotal-amount-buybox")
	WebElement subTotal;
	
	@FindBy(css="span[id=\"sc-buy-box-ptc-button\"]")
	WebElement checkoutBtn;
	
	@FindBy(css = "div[id='sc-active-cart'] input[value='Delete']")
	List<WebElement> deleteBtn;
	
	@FindBy(xpath = "//div[@id='sc-active-cart']//input[@value='Delete'][1]")
	WebElement delBtn;
	
	@FindBy(css = ".sc-cart-header")
	WebElement cartPageHeader;
	
	
	By by3 =By.cssSelector("select[name='quantity']");
	
	public List<WebElement> itemsInCart() {
		
		return cartItems;
	}

	public int getQuantity(WebElement i) {
		WebElement quantityBtn =i.findElement(by3);
		Select quantityDropdown = new Select(quantityBtn);
		WebElement o = quantityDropdown.getFirstSelectedOption();
		int quan= stringToInt( o.getText());
		return quan;
	}
	
	public int getPrize(WebElement i ) {
		String prize=splitProductprice(i.findElement(by1).getText().trim());
		int productPrize=stringToInt(prize);
		return productPrize;
	}

	public int calculateTotalPrize(int quantity, int price,int totalPrice) {
		int total = (quantity*price)+totalPrice;
		return total;
	}

	public int getActPrice() {
		String actPrice=subTotal.getText().trim();
		 int actP=stringToInt(actPrice.split("[.]")[0].replace(",", ""));
		return actP;
	}

	

	public CheckoutPage goToCheckoutPage() {
		checkoutBtn.click();
		return new CheckoutPage(driver);
	}
	
	
	public boolean checkDeleteApplication() throws InterruptedException {
		for (int i=0;i<deleteBtn.size();i++) {
			
			
			delBtn.click();
			Thread.sleep(2000);
		}
		Thread.sleep(2000);
		System.out.println(cartPageHeader.getText());
		return cartPageHeader.getText().contains("empty");
	}
	
}
