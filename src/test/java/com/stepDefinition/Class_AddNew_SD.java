package com.lms.stepDefinition;

import static com.lms.utilities.LMSUIConstants.CLASS_SHEET_NAME;
import static com.lms.utilities.LMSUIConstants.applicationData;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.lms.pageObjects.ClassAddPage;
import com.lms.pageObjects.LoginPage;
import com.lms.pageObjects.PageObjectFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Class_AddNew_SD {
  
	//#####################################Febi@########################################################

	LoginPage lp = PageObjectFactory.getLoginPage();
	ClassAddPage cp = PageObjectFactory.getClassAddPage();
	
	Map<String, String> classData;
	
	@Given("Admin is on the Dashboard Page in class")
	public void admin_is_on_the_dashboard_page() {
		
		System.out.println("Class Dashboard======> " + cp + "loginPage " + lp);
		if (!applicationData.isLoggedIn()) {

			lp.login("ValidCredentials");
		}
	}

	@When("Admin clicks the {string} in the Header in class")
	public void admin_clicks_the_in_the_header_in_class(String btnText) {

		cp.clickElementByText(btnText);

	}

	@Then("Admin should land on the {string} in class")
	public void admin_should_land_on_the_in_class(String expected) {

		Assert.assertEquals(true, cp.verifyPageTitle(expected));

	}

	@Then("Admin should see the {string} Title in class")
	public void admin_should_see_the_title_in_class(String string) {

		Assert.assertEquals(true, cp.verifyHeaderTitle(string));

	}

	@Then("Admin should see the Searchbar in class")
	public void admin_should_see_the_searchbar_in_class() {

		Assert.assertEquals(true, cp.verifySearhBox());
	}

	// ###################################################################################################################
	// Add New Class Validation
	// ###################################################################################################################

	@Given("Admin is on the Manage class page in class")
	public void admin_is_on_the_manage_class_page_in_class() {
		System.out.println("Class Manage ############" + cp);
		if (!applicationData.isLoggedIn()) {
			lp.login("ValidCredentials");

		}
		if (!cp.isOnClassPage()) {
			cp.classBtnClick();
		}

	}

	@When("Admin clicks add new class under the class menu bar in class")
	public void admin_clicks_add_new_class_under_the_class_menu_bar_in_class() {

		cp.addNewBtnClick();
	}

	@Then("Admin should see a manage class pop up with empty form and <SAVE> and <CANCEL> button and Close\\(X) Icon on the top right corner of the window in class")
	public void admin_should_see_a_manage_class_pop_up_with_empty_form_and_save_and_cancel_button_and_close_x_icon_on_the_top_right_corner_of_the_window_in_class() {

		Assert.assertEquals(true, cp.verifyPopup());

	}

	@Then("Admin should see few input fields and their respective text boxes in the class details window in class")
	public void admin_should_see_few_input_fields_and_their_respective_text_boxes_in_the_class_details_window_in_class() {

		Assert.assertEquals(true, cp.verifyPopupTextField());

	}

	@Given("Admin is on the Class Popup window in class")
	public void admin_is_on_the_class_popup_window_in_class() {

		if (!applicationData.isLoggedIn()) {

			lp.login("ValidCredentials");

		}
		//if (!cp.isOnClassPage()) {
			cp.classBtnClick();
			cp.addNewBtnClick();
		//}

	}

	@When("Admin enters {string} mandatory fields in the form and clicks on save button in class")
	public void admin_enters_mandatory_fields_in_the_form_and_clicks_on_save_button_in_class(String testcase) {
		classData = applicationData.getData(CLASS_SHEET_NAME, testcase);
		cp.createClassInputFields(testcase);
		
	}

	@Then("Admin gets message Class added Successfully in class")
	public void admin_gets_message_class_added_successfully_in_class() {
		
		Assert.assertTrue(cp.verifyPopup(classData.get("ExpectedMsg")));
		cp.wait(3000);
	}

	@When("Admin selects class date in date picker {string}")
	public void admin_selects_class_date_in_date_picker(String dates) {
		
		cp.datePicker(dates);
		
	}

	@Then("Admin should see no of class value is added automatically {string}")
	public void admin_should_see_no_of_class_value_is_added_automatically(String str) {

		Assert.assertEquals(str, cp.getNoOfClasses());
	}
	@When("Admin clicks date picker")
	public void admin_clicks_date_picker(){
		
		cp.clickDatePicker();
		
	}
	
	@Then("Admin should see weekends dates are disabled to select {string}")
	public void admin_should_see_weekends_dates_are_disabled_to_select(String day) {
		
		Assert.assertEquals(true, cp.isDateDisabled(day));
		
	}
	
	@When("Admin clicks on save button without entering data")
	public void admin_clicks_on_save_button_without_entering_data() {
		
		cp.emptyInputClick();
	
	}

	@Then("Class not created and Admin gets error message below mandatory fields in red")
	public void class_not_created_and_admin_gets_error_message_below_mandatory_fields_in_red() {

		Assert.assertTrue(cp.verifyNewClassPopupMandatoryFields());
	
	}
	
	@When("admin enters only the optional field {string} and clicks save")
	public void admin_enters_only_the_optional_field_and_clicks_save(String testcase) {
		
		cp.verifyOptionalInput(testcase);
	}
	
	@When("Admin clicks Cancel Icon on class Details form")
	public void admin_clicks_cancel_icon_on_class_details_form() {
		
		cp.verifyCancelBtn();
	
	}

	@Then("Class Details popup window should be closed")
	public void class_details_popup_window_should_be_closed() {

		Assert.assertEquals(true, cp.validateAddNewPopup());
	}

	@When("Admin clicks close button")
	public void admin_clicks_close_button() {

		cp.verifyCloseBtn();
	}

		
	@Then("Admin should see the data table heading")
	public void admin_should_see_the_data_table_heading(io.cucumber.datatable.DataTable dataTable) {
	   		
		List<String> headers = dataTable.asList();
		
		cp.verifyDataTableHeaders(headers);
		
	    
	}

	@Then("Admin should see the {string} and enabled pagination controls under the data table in class")
	public void admin_should_see_the_and_enabled_pagination_controls_under_the_data_table_in_class(String string) {

		Assert.assertTrue(cp.getFooterPaginationText().contains(string));
	}

	@Then("Admin should see the Sort icon for all the fields in the data table in class")
	public void admin_should_see_the_sort_icon_for_all_the_fields_in_the_data_table_in_class() {

		
		Assert.assertTrue(cp.verifySortIcons());
		
	}
	
	@Then("Admin should see the Delete button under the Manage class page header in class")
	public void admin_should_see_the_delete_button_under_the_manage_class_page_header_in_class() {

		Assert.assertEquals(true, cp.verifyMultiDeleteBtn());
		
	}

	@Then("Admin should see {string} in below of the data table in class")
	public void admin_should_see_in_below_of_the_data_table_in_class(String string) {

		Assert.assertTrue(cp.getFooterText().contains(string));
	
	}
	
	@When("Admin clicks the delete icon")
	public void admin_clicks_the_delete_icon() {
		
		cp.searchanddeleteBtn();
	
	}

	@Then("Admin should see a alert open with heading {string} along with  <YES> and <NO> button for deletion")
	public void admin_should_see_a_alert_open_with_heading_along_with_yes_and_no_button_for_deletion(String string) {

		//Assert.assertTrue(cp.verifyConfirmPopupContent());
	}
	
	@When("Admin clicks yes button")
	public void admin_clicks_yes_button() {
		cp.clickYesBtn();
	
	}

	@Then("Then Admin gets alert {string} and can see the selected class is deleted from the data table")
	public void then_admin_gets_alert_and_can_see_the_selected_class_is_deleted_from_the_data_table(String string) {

		
	
	}

	@When("Admin clicks delete icon and cliks No button")
	public void admin_clicks_delete_icon_and_cliks_no_button() {

	
	}

	@Then("Admin can see the deletion alert disappears without deleting")
	public void admin_can_see_the_deletion_alert_disappears_without_deleting() {

	
	
	}

	@When("Admin clicks delete icon and on close button")
	public void admin_clicks_delete_icon_and_on_close_button() {

		
	}

	
	
	

	
	/////////////////////////////////////////////////////////////////////////

	// Ann

}
