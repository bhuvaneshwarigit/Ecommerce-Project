 package com.ecommerceproject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
        WebDriver driver;
        
        
        public LandingPage(WebDriver driver) {
        	this.driver=driver;
        	PageFactory.initElements(driver, this);
        }
        
        @FindBy(xpath= "//a[@data-nav-ref='nav_ya_signin']")
        WebElement signIn;
        
        @FindBy(css = "input#ap_email")
        WebElement email;
        
        @FindBy(css="input#continue")
        WebElement continueBt;
        
        @FindBy(css = "input[type='password']")
        WebElement password;
        
        @FindBy(css = "input[type='submit']" )
        WebElement submitBt;
        
        @FindBy(css="#auth-error-message-box  div[class='a-alert-content']")
        WebElement passwordError;
        
        public void loginApplication(String emailId,String passwordIn)
        {
        signIn.click();
        email.sendKeys(emailId);
        continueBt.click();
        password.sendKeys(passwordIn);
        submitBt.click();
		
        
        }
        
        public void goTo() {
        	driver.get("https://www.amazon.in/");
        }
        
        public String getErrorMessage() {
        	return passwordError.getText();
        }

		public HomePage goToHomePage() {
		
			return new HomePage(driver);
		}
}   

