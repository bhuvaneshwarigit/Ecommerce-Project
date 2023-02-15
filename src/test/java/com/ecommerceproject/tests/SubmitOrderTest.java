package com.ecommerceproject.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ecommerceproject.pageobjects.CartPage;
import com.ecommerceproject.pageobjects.CheckoutPage;
import com.ecommerceproject.pageobjects.HomePage;
import com.ecommerceproject.pageobjects.LandingPage;
import com.ecommerceproject.pageobjects.ProductCatalogue;
import com.ecommerceproject.testComponents.BaseTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
  
	@Test(dataProvider = "getData",groups= {"purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		HomePage homePage;
		if(input.get("email")!=null) {
			 landingPage.loginApplication(input.get("email"),input.get("password"));
		}
		 homePage=landingPage.goToHomePage();
		    homePage.searchLogic(input.get("searchProduct"));
            ProductCatalogue productCatalogue=new ProductCatalogue(driver);
            productCatalogue.sortingApp("Featured");
            List<WebElement> webElement = productCatalogue.getProductList(); 
	        int c= 0 ; 
	        for(WebElement a: webElement) {
		      if(a.getText().contains("Shirt")) {
	    	  a.click();
	    	  Set<String> s = driver.getWindowHandles();
		      ArrayList<String> ar = new ArrayList<String>(s);
		      driver.switchTo().window((String)ar.get(1));
		      productCatalogue.selectSize("S");
		      productCatalogue.selectQuantity("2");
		      productCatalogue.addItem();
	    	  c++;
	    	  driver.close();
	    	  driver.switchTo().window((String)ar.get(0));
	    	     if(c==1) {
	    		  break;
	    	     }
		      }  
	       }
	        CartPage cartPage =homePage.gotoCartPage();
	      List<WebElement> cartItems = cartPage.itemsInCart();
	     
	      int totalPrice =0;
	      for( WebElement i: cartItems){
	    	  int quantity= cartPage.getQuantity(i);
	    	
	    	  int price = cartPage.getPrize(i);
	    	 
	          totalPrice=cartPage.calculateTotalPrize(quantity,price,totalPrice);
	          
	      }
	      int actualPrice =   cartPage.getActPrice();
	    
	    
	     Assert.assertEquals(actualPrice, totalPrice);
	      CheckoutPage checkoutPage= cartPage.goToCheckoutPage();
	   Assert.assertTrue(checkoutPage.checkHeader("Checkout"));
	      	

	
	}
	
	
	
	
	}

