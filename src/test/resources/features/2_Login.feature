@login_Scenarios
Feature: Login Page
  
  @TC_Login_01
  Scenario Outline: Login page validation
    Given Admin is in login Page
    And HTTP response >= 400. Then the link is broken for login page
    And Admin should see correct spellings in all fields 
    And Admin should see logo on the left  side
    And Admin should see  LMS - Learning Management System
    And Admin should see company name below the app name
    And Admin should see "Please login to LMS application"
    And Admin should see two text field
    And Admin should "User" in the first text field
    And Admin should see "*" symbol next to user text
    And Admin should "Password" in the second text field
    And Admin should see "*" symbol next to password text
    And Admin should see input field on the centre of the page
    And Admin should see login button 
    And Admin should see login button on the centre of the page
    And Admin should see Admin in gray color
    And Admin should see password in gray color
    And Admin should land on dashboard page by passing login credential "ValidCredentials"  
    And Error message please check Adminname/password for invalid credential "InvalidCredentials"

    #And Error message please check Adminname/password by giving only password "InvalidUserName"
    #And Error message please check Adminname/password by giving only adminname "InvalidPass"
    And Admin should land on dashboard page by submitting thorough keyboard "ValidCredentials"  
    And Admin should land on dashboard page by submitting thorough mouse "ValidCredentials"  
     Examples:
     |testcase|
     |ValidCredentials|
     |InvalidCredentials|
     |InvalidUserName|
     |InvalidPass|
