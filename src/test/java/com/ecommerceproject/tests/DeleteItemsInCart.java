package com.ecommerceproject.tests;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecommerceproject.pageobjects.CartPage;
import com.ecommerceproject.pageobjects.HomePage;
import com.ecommerceproject.testComponents.BaseTest;



public class DeleteItemsInCart extends BaseTest {
	@Test(dataProvider = "getData")
	public void deleteItemsInCart(HashMap<String,String> input) throws InterruptedException {
		 HomePage homePage;
	     landingPage.loginApplication(input.get("email"),input.get("password"));
		 homePage=landingPage.goToHomePage();
		 CartPage cartPage =homePage.gotoCartPage();	    
	     boolean empty=cartPage.checkDeleteApplication();
         Assert.assertEquals(empty, true);	
         
	}
}

