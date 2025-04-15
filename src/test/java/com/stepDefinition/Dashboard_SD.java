package com.lms.stepDefinition;

import static com.lms.utilities.LMSUIConstants.applicationData;

import org.testng.Assert;

import com.lms.pageObjects.BasePage;
import com.lms.pageObjects.ClassPage;
import com.lms.pageObjects.DashboardPage;
import com.lms.pageObjects.LoginPage;
import com.lms.pageObjects.PageObjectFactory;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Dashboard_SD extends BasePage {
	
	LoginPage lp = PageObjectFactory.getLoginPage();
	ClassPage cp = PageObjectFactory.getClassPage();
	DashboardPage dp = PageObjectFactory.getDashboardPage(); 
	@When("Admin enter valid credentials and clicks login button")
	public void admin_enter_valid_credentials_and_clicks_login_button() {
		System.out.println("Class Dashboard======> " + cp + "loginPage " + lp);
		if (!applicationData.isLoggedIn()) {

			lp.login("ValidCredentials");
		}
	}

	
	@When("Admin should see dashboard")
	public void admin_should_see_dashboard() {
	    	dp.isDashboardLoaded();
		
	}
	@When("Maximum navigation time in milliseconds, defaults to {int} seconds")
	public void maximum_navigation_time_in_milliseconds_defaults_to_seconds(Integer timeoutMs) {
		String url ="https://lms-frontend-hackathon-oct24-173fe394c071.herokuapp.com/";
		String expectedTitle=" LMS - Learning Management System ";
		timeoutMs =30000; 
		dp.navigateToUrlAndVerifyTitle(BASE_URL, expectedTitle, timeoutMs);

	    System.out.println("Linkkkk1111");
	}

	@When("HTTP response >= {int}. Then the link is broken")
	public void http_response_then_the_link_is_broken(Integer statusCodeThreshold) {
	   dp.broken_links();
	   System.out.println("Linkkkk");
	  
	}

	@When("Admin should see {string} as title")
	 public void adminShouldSeeAsTitle(String expected) {
		Assert.assertEquals(true, cp.verifyHeaderTitle(expected));
}

	@When("The Title {string} should be on the top left corner of page")
	public void the_title_should_be_on_the_top_left_corner_of_page(String expectedTitle) {
	     dp.titleCheck(expectedTitle);
}

	@When("Admin should see {string} correct spelling in navigation bar text")
	public void admin_should_see_correct_spelling_in_navigation_bar_text(String expectedTitle) {
    dp.titleCheck(expectedTitle);
}
	
	@When("Admin should see correct spelling and spacing in LMS title")
	public void admin_should_see_correct_spelling_and_spacing_in_lms_title() {
	  
	   dp.correctSpellingAndSpacingInTitle();
	}

	@When("Admin should see the navigation bar text on the top right side")
	public void admin_should_see_the_navigation_bar_text_on_the_top_right_side() {
		 System.out.println("###");
		 dp.NavigationBarText();
	}
	@When("Admin should see Home in the 1st place")
	public void admin_should_see_home_in_the_1st_place() {
		 System.out.println("###");
		 dp.verifyButtonOrder();
	}
	@When("Admin should see program in the 2nd place")
	public void admin_should_see_program_in_the_2nd_place() {
		dp.verifyButtonOrder();
	}

	@When("Admin should see batch in the 3rd place")
	public void admin_should_see_batch_in_the_3rd_place() {
		dp.verifyButtonOrder();
	}

	@When("Admin should see class in the 4th place")
	public void admin_should_see_class_in_the_4th_place() {
		dp.verifyButtonOrder();
	}

	@When("Admin should see logout in the 5th place")
	public void admin_should_see_logout_in_the_5th_place() {
		dp.verifyButtonOrder();
	}



}
