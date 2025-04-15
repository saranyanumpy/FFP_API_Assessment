package com.lms.stepDefinition;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static org.testng.Assert.assertFalse;

import com.lms.pageObjects.BasePage;
import com.lms.pageObjects.ClassPage;
import com.lms.pageObjects.DashboardPage;
import com.lms.pageObjects.LoginPage;
import com.lms.pageObjects.PageObjectFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.sourceforge.tess4j.TesseractException;

public class Login_SD extends BasePage{
	
	//LoginPage lp = new LoginPage();
	ClassPage cp = PageObjectFactory.getClassPage();
	DashboardPage dp=PageObjectFactory.getDashboardPage();
	LoginPage lp = PageObjectFactory.getLoginPage();
	
	@Given("Admin is in login Page")
	public void admin_is_in_login_page() {
	  
	   lp.openPage();
	   
	}
	
	@When("HTTP response >= {int}. Then the link is broken for login page")
	public void http_response_then_the_link_is_broken_for_login_page(Integer statusCodeThreshold) {
	    dp.broken_links();
	}

	@When("Admin should see correct spellings in all fields")
	public void admin_should_see_correct_spellings_in_all_fields() throws Exception {
	  
	   lp.spellChecking();
	}

	@When("Admin should see logo on the left  side")
	public void admin_should_see_logo_on_the_left_side() throws Exception {
		lp.verifyLogo();
	}


	@Then("Admin should land on dashboard page")
	public void admin_should_land_on_dashboard_page() {
		  // Expected title of the dashboard page
	    String expectedTitle = "LMS - Learning Management System";
	    String dashboardUrl = "https://lms-frontend-hackathon-oct24-173fe394c071.herokuapp.com/dashboard";

	    dp.navigateToUrlAndVerifyTitle(dashboardUrl, expectedTitle, 30000); // 30 seconds timeout
	
		
	}
	@Then("Admin should see  LMS - Learning Management System")
	public void admin_should_see_lms_learning_management_system() throws TesseractException {
		System.out.println("dcdsc1");
		lp.verifyApplicationTitle();
	}

	@Then("Admin should see company name below the app name")
	public void admin_should_see_company_name_below_the_app_name() throws TesseractException {
		System.out.println("dcdsc2");
		lp.verifyCompanyName();
	}

	@Then("Admin should see {string}")
	public void admin_should_see(String string) {
		System.out.println("dcdsc3");
		lp.verifyPleaseLoginTitle();
	}
	@Then("Admin should see two text field")
	public void admin_should_see_two_text_field() {
		lp.TextFieldsDisplayed(2);
	}

	@Then("Admin should {string} in the first text field")
	public void admin_should_in_the_first_text_field(String string) {
	    lp.usertext(string);
	}

	@Given("Admin should see {string} symbol next to user text")
	public void admin_should_see_symbol_next_to_user_text(String string) {
    lp.astrikuser(string);
	}

	@Then("Admin should {string} in the second text field")
	public void admin_should_in_the_second_text_field(String string) {
		lp.passwordtext(string);
	}
	
	@Then("Admin should see {string} symbol next to password text")
	public void admin_should_see_symbol_next_to_password_text(String string) {
		lp.astrikpassword(string);
	}

	@Then("Admin should see input field on the centre of the page")
	public void admin_should_see_input_field_on_the_centre_of_the_page() {
	 //   lp.inputFieldAlign();
	}

	@Then("Admin should see login button")
	public void admin_should_see_login_button() {
		lp.loginBtnDisplay();
		
	}

	@Then("Admin should see login button on the centre of the page")
	public void admin_should_see_login_button_on_the_centre_of_the_page() {
		lp.loginBtnLocation();
	}
	@Then("Admin should see Admin in gray color")
	public void admin_should_see_admin_in_gray_color() {
		lp.userTextFieldColorCheck();
	}

	@Then("Admin should see password in gray color")
	public void admin_should_see_password_in_gray_color() {
		lp.userPassFieldColorCheck();
	}

	@Then("Admin should land on dashboard page by passing login credential {string}")
	public void admin_should_land_on_dashboard_page_by_passing_login_credential(String credentialsType) {
	    driver.manage().deleteAllCookies();
	    if (credentialsType.equals("ValidCredentials")) {
	        lp.login("ValidCredentials");
	        // Additional checks for successful dashboard landing
	    }
	}
	@Given("Error message please check Adminname\\/password for invalid credential {string}")
	public void error_message_please_check_adminname_password_for_invalid_credential(String credentialsType) {
		driver.manage().deleteAllCookies();
	    
	    boolean isLoggedIn = lp.login(credentialsType);
	    
	    if (credentialsType.equals("InvalidCredentials")) {
	        assertFalse(isLoggedIn, "Expected login to fail with invalid credentials but it succeeded.");
	        
	        // Verify error message is displayed (update the locator to match your actual error message element)
	        WebElement errorMessage = driver.findElement(By.xpath("//form//mat-error[@id='errormessage']"));
	        Assert.assertTrue(errorMessage.isDisplayed(), "Error message was not displayed for invalid credentials.");
	    }
	}

	@Then("Error message please check Adminname\\/password by giving only password {string}")
	public void error_message_please_check_adminname_password_by_giving_only_password(String credentialsType) {
		 driver.manage().deleteAllCookies();
		    
		    boolean loginSuccess;
		    
		    // Call the login method based on the credentials type
		    if (credentialsType.equals("InvalidUserName")) {
		        loginSuccess = lp.login("InvalidUserName");
		        
		        // Here, you can assert that the login failed
		        Assert.assertFalse(loginSuccess, "Login should have failed for invalid username.");
		        
		        // Additional checks for error message display
		        String expectedErrorMessage = "Please check Adminname/password"; // Adjust as per the actual error message
		        String actualErrorMessage = getErrorMessage(); // Implement this method to fetch the error message from the UI
		        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message did not match.");
		    }
		    
		    // You can add more conditions for different scenarios if needed
		}

		// A method to retrieve the error message from the UI
		private String getErrorMessage() {
		    // Locate the element that displays the error message and return its text
		    // Example: 
		    WebElement errorMessageElement = driver.findElement(By.id("errorMessageId")); // Replace with actual locator
		    return errorMessageElement.getText();
		}

	@Then("Error message please check Adminname\\/password by giving only adminname {string}")
	public void error_message_please_check_adminname_password_by_giving_only_adminname(String credentialsType) {
		driver.manage().deleteAllCookies();
	    if (credentialsType.equals("InvalidPass")) {
	        lp.login("InvalidPass");
	    }
	        // Additional checks for error message display
	  	}

	@Then("Admin should land on dashboard page by submitting thorough keyboard {string}")
	public void admin_should_land_on_dashboard_page_by_submitting_thorough_keyboard(String ValidCredentials) {
	    lp.loginBtnKeyboardAction(ValidCredentials);
	    
	}

	@Then("Admin should land on dashboard page by submitting thorough mouse {string}")
	public void admin_should_land_on_dashboard_page_by_submitting_thorough_mouse(String ValidCredentials) {
	    lp.loginBtnMouseAction(ValidCredentials);
	}









}
