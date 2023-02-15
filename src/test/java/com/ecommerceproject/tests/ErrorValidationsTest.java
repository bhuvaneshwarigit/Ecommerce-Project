package com.ecommerceproject.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecommerceproject.pageobjects.CartPage;
import com.ecommerceproject.pageobjects.CheckoutPage;
import com.ecommerceproject.pageobjects.HomePage;
import com.ecommerceproject.pageobjects.LandingPage;
import com.ecommerceproject.pageobjects.ProductCatalogue;
import com.ecommerceproject.testComponents.BaseTest;
import com.ecommerceproject.testComponents.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest {

	@Test(dataProvider = "getData", groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void errorValidation(HashMap<String, String> input) throws IOException, InterruptedException {
		landingPage.loginApplication(input.get("email"), input.get("wrongPassword"));
		Assert.assertEquals("Your password is incorrect", landingPage.getErrorMessage());

	}
}
