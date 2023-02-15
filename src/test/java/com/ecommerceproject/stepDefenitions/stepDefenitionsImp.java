package com.ecommerceproject.stepDefenitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.ecommerceproject.pageobjects.CartPage;
import com.ecommerceproject.pageobjects.CheckoutPage;
import com.ecommerceproject.pageobjects.HomePage;
import com.ecommerceproject.pageobjects.LandingPage;
import com.ecommerceproject.pageobjects.ProductCatalogue;
import com.ecommerceproject.testComponents.BaseTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefenitionsImp extends BaseTest {
public LandingPage landingPage;
public HomePage homePage;
public List<WebElement> webElement;
public ProductCatalogue productCatalogue;
public CartPage cartPage;
public CheckoutPage checkoutPage;

	@Given("I landed on ecommerce page")
	public void I_landed_on_ecommerce_page() throws IOException
	{
		landingPage=launchApplication();
	}

	@Given("Logged in with email(.+) and password(.+)$")
	public void logged_in_with_email_and_password(String email,String password) {
		 landingPage.loginApplication(email,password);
		 HomePage homePage=landingPage.goToHomePage();
	
	}
	
	@When("I search product by (.+)$")
	public void I_earch_product_by(String searchProduct ) {
		 homePage.searchLogic(searchProduct);
	}
	
	@Then("I add all the products into the list")
	public void I_add_the_products_into_the_list() {
		 productCatalogue=new ProductCatalogue(driver);
        productCatalogue.sortingApp("Featured");
        webElement = productCatalogue.getProductList();
	}
	
	@When("I add(.+) product from Product list with size(.+) and quantity(.+)$")
	public void I_select_product_from_Product_list_page_with_size_and_quantity(String max,String size,String quantity) throws InterruptedException {
	int c= 0 ; 
	int m=productCatalogue.stringIntoInt(max);
    for(WebElement a: webElement) {
      if(a.getText().contains("Shirt")) {
	  a.click();
	  Set<String> s = driver.getWindowHandles();
      ArrayList<String> ar = new ArrayList<String>(s);
      driver.switchTo().window((String)ar.get(1));
      productCatalogue.selectSize(size);
      productCatalogue.selectQuantity(quantity);
      productCatalogue.addItem();
	  c++;
	  driver.close();
	  driver.switchTo().window((String)ar.get(0));
	     if(c==m) {
		  break;
	     }
      }  
   }
	}
	      
	@And("I verify actual price with total price")
	public void I_verify_actual_price_with_total_price() {
		
		   cartPage =homePage.gotoCartPage();
	      List<WebElement> cartItems = cartPage.itemsInCart();
	      int totalPrice =0;
	      for( WebElement i: cartItems){
	    	  int quantity= cartPage.getQuantity(i);
	    	  int price = cartPage.getPrize(i);
	          totalPrice=cartPage.calculateTotalPrize(quantity,price,totalPrice);
	      }
	      int actualPrice =   cartPage.getActPrice();
	     Assert.assertEquals(actualPrice, totalPrice);
	}
	
	@When("I click checkout page")
	public void I_click_checkout_page() {
		
		 checkoutPage= cartPage.goToCheckoutPage();
	}
	
	@And("I verify the title of the checkout page")
	public void  I_verify_the_title_of_the_checkout_page() {
		  Assert.assertTrue(checkoutPage.checkHeader("Checkout"));
	      	driver.close();
	}
}
