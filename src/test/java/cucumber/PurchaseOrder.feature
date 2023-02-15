@tag
Feature: Purchase the order from ecommerce website

Background:
Given I landed on ecommerce page

@tag2
  Scenario Outline:Positive test of submitting the order
    Given Logged in with email<email> and password<password>
    When I search product by <searchProduct> 
    Then I add all the products into the list
    When I add<max> product from Product list with size<size> and quantity<quantity>
    And I verify actual price with total price
    When I click checkout page
    And I verify the title of the checkout page
    
    

    Examples:
      | email                  | password     | searchProduct    | size| quantity|max|
      | enter@gmail.com | password | t-shirt for men| S|2|2|

