package com.ecommerceproject.tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecommerceproject.pageobjects.HomePage;
import com.ecommerceproject.testComponents.BaseTest;

public class ValidateLogoAndTitle extends BaseTest {

	@Test
	public void validateLogoAndTitle() {
			 HomePage homePage = new HomePage(driver); 
			 boolean result = homePage.validateLogo();
			 Assert.assertTrue(result);
			 String actTitle = homePage.getMyStoreTitle();
			 Assert.assertEquals(actTitle,"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
	}
	
}
