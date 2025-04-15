package com.lms.stepDefinition;

import org.testng.Assert;

import com.lms.pageObjects.ClassPage;
import com.lms.pageObjects.LoginPage;
import com.lms.pageObjects.PageObjectFactory;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.lms.driverManager.WebDriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.lms.utilities.LMSUIConstants.*;

public class Class_SD {

	// #####################################Febi########################################################

	LoginPage lp = PageObjectFactory.getLoginPage();
	ClassPage cp = PageObjectFactory.getClassPage();

	@Given("Admin is on the Dashboard Page")
	public void admin_is_on_the_dashboard_page() {

		System.out.println("Class Dashboard======> " + cp + "loginPage " + lp);
		if (!applicationData.isLoggedIn()) {

			lp.login("ValidCredentials");
		}
	}

	@When("Admin clicks the {string} in the Header")
	public void admin_clicks_the_in_the_header(String btnText) {

		cp.clickElementByText(btnText);

	}

	@Then("Admin should land on the {string}")
	public void admin_should_land_on_the(String expected) {

		Assert.assertEquals(true, cp.verifyPageTitle(expected));

	}

	@Then("Admin should see the {string} Title")
	public void admin_should_see_the_title(String string) {

		Assert.assertEquals(true, cp.verifyHeaderTitle(string));

	}

	@Then("Admin should see the Searchbar")
	public void admin_should_see_the_searchbar() {

		Assert.assertEquals(true, cp.verifySearhBox());
	}

	// ###################################################################################################################
	// Add New Class Validation
	// ###################################################################################################################

	@Given("Admin is on the Manage Class page")
	public void admin_is_on_the_manage_class_page() {
		System.out.println("Class Manage ############" + cp);
		if (!applicationData.isLoggedIn()) {
			lp.login("ValidCredentials");

		}
		if (!cp.isOnClassPage()) {
			cp.classBtnClick();

		}

	}

	@When("Admin clicks add new class under the class menu bar")
	public void admin_clicks_add_new_class_under_the_class_menu_bar() {

		cp.addNewBtnClick();
	}

	@Then("Admin should see a manage class pop up with empty form and <SAVE> and <CANCEL> button and Close\\(X) Icon on the top right corner of the window")
	public void admin_should_see_a_manage_class_pop_up_with_empty_form_and_save_and_cancel_button_and_close_x_icon_on_the_top_right_corner_of_the_window() {

		Assert.assertEquals(true, cp.verifyPopup());

	}

	@Then("Admin should see few input fields and their respective text boxes in the class details window")
	public void admin_should_see_few_input_fields_and_their_respective_text_boxes_in_the_class_details_window() {

		Assert.assertEquals(true, cp.verifyPopupTextField());

	}

	@Given("Admin is on the Class Popup window")
	public void admin_is_on_the_class_popup_window() {

		if (!applicationData.isLoggedIn()) {

			lp.login("ValidCredentials");

		}
		if (!cp.isOnClassPage()) {
			cp.classBtnClick();
			cp.addNewBtnClick();
		}

	}

	@When("Admin enters {string} mandatory fields in the form and clicks on save button")
	public void admin_enters_mandatory_fields_in_the_form_and_clicks_on_save_button(String testcase) {

		// cp.validInputMandatoryFields(testcase);

	}

	@Then("Admin gets message Class added Successfully")
	public void admin_gets_message_class_added_successfully() {

	}

	@When("Admin selects class date in date picker")
	public void admin_selects_class_date_in_date_picker() {

	}

	@Then("Admin should see no of class value is added automatically")
	public void admin_should_see_no_of_class_value_is_added_automatically() {

	}

	// Ann

//	@Given("Admin is on the Manage Class page")
//	public void admin_is_on_the_manage_class_page() {
//		// Write code here that turns the phrase above into concrete actions		
//		cp.manageClassPage();
//	}

	@When("Admin clicks on the edit icon")
	public void admin_clicks_on_the_edit_icon() {
		// Write code here that turns the phrase above into concrete actions

		cp.classPopUpWindow();

	}

	@Then("A new pop up with class details appears")
	public void a_new_pop_up_with_class_details_appears() {
		// Write code here that turns the phrase above into concrete actions
		cp.validateClassPopUp();

	}

	@Then("Admin should see batch name field is disabled")
	public void admin_should_see_batch_name_field_is_disabled() {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("batch name is disabled--> " + cp.isBatchNameDisabled());
		Assert.assertTrue(cp.isBatchNameDisabled());

	}

	@Then("Admin should see class topic field is disabled")
	public void admin_should_see_class_topic_field_is_disabled() {
		// Write code here that turns the phrase above into concrete actions
		cp.isClassTopicDisplayed();
	}

	@Given("Admin is on the Edit Class Popup window")
	public void admin_is_on_the_edit_class_popup_window() {
		// Write code here that turns the phrase above into concrete actions
//		cp.manageClassPage();
//		cp.classPopUpWindow();
	}
	
	@Given("Admin is on the Edit Class Popup window again")
	public void admin_is_on_the_edit_class_popup_window_again() {
		// Write code here that turns the phrase above into concrete actions
		//cp.manageClassPage();
		cp.classPopUpWindow();
	}

	@When("Update the fields with valid data {string} and click save")
	public void update_the_fields_with_valid_data_and_click_save(String string) {
		// Write code here that turns the phrase above into concrete actions
		cp.updateData(string);

	}

	@Then("Admin gets message {string} and see the updated values in data table")
	public void admin_gets_message_and_see_the_updated_values_in_data_table(String string) {
		// Write code here that turns the phrase above into concrete actions
		cp.ValSuccessToastMsg(string);
		cp.validateUpdatedRowDetails();
	}

	@When("Update the fields with invalid values {string} and click save")
	public void update_the_fields_with_invalid_values_and_click_save(String string) {
		// Write code here that turns the phrase above into concrete actions
		cp.updateData(string);
	}

	@Then("Admin should get Error message {string}")
	public void admin_should_get_error_message(String string) {
		// Write code here that turns the phrase above into concrete actions
		cp.validateErrMsg(string);
	}

	@When("Update the mandatory fields with valid values {string} and click save")
	public void update_the_mandatory_fields_with_valid_values_and_click_save(String string) {
		// Write code here that turns the phrase above into concrete actions
		cp.updateData(string);
	}

	@When("Update the optional fields with valid values {string} and click save")
	public void update_the_optional_fields_with_valid_values_and_click_save(String string) {
		// Write code here that turns the phrase above into concrete actions
		cp.updateData(string);
	}

	@When("Admin enters only numbers or special char {string} in the text fields")
	public void admin_enters_only_numbers_or_special_char_in_the_text_fields(String string) {
	    // Write code here that turns the phrase above into concrete actions
		cp.updateData(string);
	}

	@Then("Admin should not get success message")
	public void admin_should_not_get_success_message() {
	    // Write code here that turns the phrase above into concrete actions
		cp.validateToastMessageNotDisplayed();
	}


	@When("Admin clicks Cancel button on edit popup {string}")
	public void admin_clicks_cancel_button_on_edit_popup(String string) {
		// Write code here that turns the phrase above into concrete actions
cp.ClkCancelBtn(string);
	}

	@Then("Admin can see the class details popup disappears and can see nothing changed for particular Class")
	public void admin_can_see_the_class_details_popup_disappears_and_can_see_nothing_changed_for_particular_class() {
		// Write code here that turns the phrase above into concrete actions
cp.validateCancelBtn();
	}

	
	////////   pagination

	@When("Admin clicks Next page link on the class table")
	public void admin_clicks_next_page_link_on_the_class_table() {
		// Write code here that turns the phrase above into concrete actions
		cp.clickNextPagelink();
	}

	@Then("Admin should see the next page record on the table  with Pagination has next active link enabled")
	public void admin_should_see_the_next_page_record_on_the_table_with_pagination_has_next_active_link_enabled() {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(cp.NextPageLinkEnabled());
		///////
	}

	@When("Admin clicks Last page link")
	public void admin_clicks_last_page_link() {
		// Write code here that turns the phrase above into concrete actions
		cp.clickLastPageLink();
	}

	@Then("Admin should see the last page record on the table with Next page link are disabled")
	public void admin_should_see_the_last_page_record_on_the_table_with_next_page_link_are_disabled() {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertFalse(cp.NextPageLinkEnabled());
	}

	@Given("Admin is on last page of class table")
	public void admin_is_on_last_page_of_class_table() {
		// Write code here that turns the phrase above into concrete actions
		// cp.clickLastPageLink();
	}

	@When("Admin clicks First page link")
	public void admin_clicks_first_page_link() {
		// Write code here that turns the phrase above into concrete actions
		cp.clickPrevPageLink();
	}

	@Then("Admin should see the previous page record on the table with pagination has previous page link enabled")
	public void admin_should_see_the_previous_page_record_on_the_table_with_pagination_has_previous_page_link_enabled() {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(cp.PrevPageLinkEnabled());
	}

	@Given("Admin is on Previous class page")
	public void admin_is_on_previous_class_page() {
		// Write code here that turns the phrase above into concrete actions
		// cp.clickPrevPageLink();
	}

	@When("Admin clicks Start page link")
	public void admin_clicks_start_page_link() {
		// Write code here that turns the phrase above into concrete actions
		cp.clickStrtPageLink();
	}

	@Then("Admin should see the very first page record on the table with Previous page link are disabled")
	public void admin_should_see_the_very_first_page_record_on_the_table_with_previous_page_link_are_disabled() {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertFalse(cp.PrevPageLinkEnabled());
	}
	


@When("Admin clicks on the Batchname sort icon")
public void admin_clicks_on_the_batchname_sort_icon() {
    // Write code here that turns the phrase above into concrete actions
cp.clickSortBatchName();
}

@Then("Admin should see Class details are sorted by Batch Name")
public void admin_should_see_class_details_are_sorted_by_batch_name() {
    // Write code here that turns the phrase above into concrete actions
  cp.SortAscendingOrder(cp.getSortBatchName());
}

@When("Admin clicks on the Class topic sort icon")
public void admin_clicks_on_the_class_topic_sort_icon() {
    // Write code here that turns the phrase above into concrete actions
  cp.clickSortClassTopic(); 
}

@Then("Admin should see Class details are sorted by Class topic")
public void admin_should_see_class_details_are_sorted_by_class_topic() {
    // Write code here that turns the phrase above into concrete actions
	 cp.SortAscendingOrder(cp.getSortClassTopic());
}

@When("Admin clicks on the descreption sort icon")
public void admin_clicks_on_the_descreption_sort_icon() {
    // Write code here that turns the phrase above into concrete actions
	  cp.clickSortClassDescription(); 
}

@Then("Admin should see Class details are sorted by descreption")
public void admin_should_see_class_details_are_sorted_by_descreption() {
    // Write code here that turns the phrase above into concrete actions
	 cp.SortAscendingOrder(cp.getSortClassDescription());
}

@When("Admin clicks on the Status sort icon")
public void admin_clicks_on_the_status_sort_icon() {
    // Write code here that turns the phrase above into concrete actions
	
	cp.clickSortStatus(); 
}

@Then("Admin should see Class details are sorted by Status")
public void admin_should_see_class_details_are_sorted_by_status() {
    // Write code here that turns the phrase above into concrete actions
	 cp.SortAscendingOrder(cp.getSortStatus());
}

@When("Admin clicks on the Class Date sort icon")
public void admin_clicks_on_the_class_date_sort_icon() {
    // Write code here that turns the phrase above into concrete actions
	cp.clickSortClassDate(); 
}

@Then("Admin should see Class details are sorted by Class Date")
public void admin_should_see_class_details_are_sorted_by_class_date() {
    // Write code here that turns the phrase above into concrete actions
	 cp.SortAscendingOrder(cp.getSortClassDate());
}

@When("Admin clicks on the Staff Name sort icon")
public void admin_clicks_on_the_staff_name_sort_icon() {
    // Write code here that turns the phrase above into concrete actions
	cp.clickSortStaffName(); 
}

@Then("Admin should see Class details are sorted by Staff Name")
public void admin_should_see_class_details_are_sorted_by_staff_name() {
    // Write code here that turns the phrase above into concrete actions
	cp.SortAscendingOrder(cp.getSortStaffName());
}

@When("Admin clicks on the logout in the menu bar")
public void admin_clicks_on_the_logout_in_the_menu_bar() {
    // Write code here that turns the phrase above into concrete actions
	cp.logout();
}

@Then("Admin should be redirected to login page {string}")
public void admin_should_be_redirected_to_login_page(String string) {
    // Write code here that turns the phrase above into concrete actions
cp.vallogout(string);
}


}
