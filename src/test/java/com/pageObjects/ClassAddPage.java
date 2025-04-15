package com.lms.pageObjects;

import static com.lms.utilities.LMSUIConstants.CLASS_SHEET_NAME;
import static com.lms.utilities.LMSUIConstants.applicationData;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ClassAddPage extends BasePage {

	@FindBy(xpath = "//span[text()='Class']/..")
	WebElement classLink;
	@FindBy(xpath = "//div[contains(@role, 'dialog')]")
	WebElement addNewClassPopup;
	@FindBy(xpath = "//span[@id='pr_id_85-label']")
	WebElement PopupTitle;
	@FindBy(xpath = "//div[text()=' Manage Class']")
	WebElement pageTitle;
	@FindBy(xpath = "//span[text()=' LMS - Learning Management System ']")
	WebElement headerTitle;
	@FindBy(id = "filterGlobal")
	WebElement searchBar;
	@FindBy(xpath = "//button[text()='Add New Class']")
	WebElement addNewBtn;
	@FindBy(xpath = "//span[text()='Save']")
	WebElement saveBtn;
	@FindBy(xpath = "//span[@class='p-button-icon pi pi-calendar']")
	WebElement calenderIcon;
	@FindBy(xpath = "//input[@id='classTopic']")
	WebElement classTopic;
	@FindBy(xpath = "//label[text()='No of Classes']")
	WebElement noOfClassesLabel;
	@FindBy(xpath = "//input[@id='classNo']")
	WebElement noOfClasses;
	@FindBy(xpath = "//input[@id='icon']")
	WebElement classDates;
	@FindBy(xpath = "//input[@placeholder='Select a Batch Name']")
	WebElement batchNamedd;
	@FindBy(xpath = "//input[@placeholder='Select a Staff Name']")
	WebElement staffNamedd;
	@FindBy(xpath = "//input[@id='Active']/../..")
	WebElement statusActiveOption;
	@FindBy(xpath = "//input[@id='Inactive']/../..")
	WebElement statusInactiveOption;
	@FindBy(xpath = "//button[@id='saveClass']")
	WebElement savebtn;
	@FindBy(xpath = "//input[@id = 'classDescription']")
	WebElement classDescText;
	@FindBy(xpath = "//input[@id = 'classComments']")
	WebElement classCommentsText;
	@FindBy(xpath = "//input[@id = 'classNotes']")
	WebElement classNotesText;
	@FindBy(xpath = "//input[@id = 'classRecordingPath']")
	WebElement classRecordingText;
	@FindBy(xpath = "//small[@class='p-invalid ng-star-inserted']")
	List<WebElement> mandatoryErrorMsg;
	@FindBy(xpath = "//button[@label='Cancel']")
	WebElement cancelBtn;
	@FindBy(xpath = "//button[contains(@class,'p-dialog-header-close')]")
	WebElement closeBtn;
	@FindBy(xpath = "//mat-card-title/div[2]/div[1]/button")
	WebElement multiDeleteBtn;
	@FindBy(xpath = "//div[contains(@class,'p-datatable-footer')]")
	WebElement footerText;
	@FindBy(xpath = "//span[contains(@class,'p-paginator-current ng-star-inserted')]")
	WebElement footerPagination;
	@FindBy(xpath = "//i[contains(@class,'p-sortable-column-icon')]")
	List<WebElement> sortIcons;
	@FindBy(xpath = "//button[contains(@class,'p-confirm-dialog-accept')]")
	WebElement yesBtn;  
	@FindBy(xpath = "//button[contains(@class,'p-confirm-dialog-reject')]")
	WebElement noBtn;
	@FindBy(xpath="//span[text()='Confirm']")
	WebElement confirmlabel;
	@FindBy(xpath = "//button[@icon='pi pi-trash']")
	WebElement deleteBtn;
	
	
	
	
	

	JavascriptExecutor js = (JavascriptExecutor) driver;

	Map<String, String> classData;

	public void openPage() {
		driver.get(BASE_URL + "session");

	}

	public boolean isOnClassPage() {
		if (driver.getCurrentUrl().contains("session")) {
			return true;
		}
		return false;
	}


	public void createClassInputFields(String testcase) {

		classData = applicationData.getData(CLASS_SHEET_NAME, testcase); // TestData from Excel based on the testcase

		driver.findElements(By.xpath("//div[@aria-haspopup='listbox']")).get(0).click();
		System.out.println("");
		wait(100);
		if (classData.get("Batchname") != null) {
			driver.findElement(By.xpath("//li[@aria-label='" + classData.get("Batchname") + "']")).click();
		}
		batchNamedd.sendKeys(Keys.TAB);
		if (classData.get("ClassTopic") != null) {
			sendKeys(classTopic, classData.get("ClassTopic"));
		}
		if (classData.get("ClassDescription") != null) {
			sendKeys(classDescText, classData.get("ClassDescription"));
		}

		click(classDates);
		if (classData.get("SelectClassDates") != null) {
			sendKeys(classDates, classData.get("SelectClassDates"));
		}
		classDates.sendKeys(Keys.TAB);

		js.executeScript("arguments[0].scrollIntoView(true);", staffNamedd);
		driver.findElements(By.xpath("//div[@aria-haspopup='listbox']")).get(1).click();

		if (classData.get("StaffName") != null) {
			driver.findElement(By.xpath("//li[@aria-label='" + classData.get("StaffName") + "']")).click();
		}
		staffNamedd.sendKeys(Keys.TAB);
		click(statusActiveOption);
		if (classData.get("Comments") != null) {
			sendKeys(classCommentsText, classData.get("Comments"));
		}
		if (classData.get("Notes") != null) {
			sendKeys(classNotesText, classData.get("Notes"));
		}
		if (classData.get("Recording") != null) {
			sendKeys(classRecordingText, classData.get("Recording"));
		}

		click(savebtn);
		applicationData.getClassTopics().add(classData.get("ClassTopic"));

	}

	public boolean datePicker(String dates) {

		click(classDates);
		sendKeys(classDates, dates);
		click(calenderIcon);
		classDates.sendKeys(Keys.TAB);
		js.executeScript("arguments[0].scrollIntoView(true);", noOfClasses);
		click(classDates);
		classDates.sendKeys(Keys.TAB);

		return true;
	}
	
	public void deleteClass() {
		
		click(deleteBtn);
	}

	public void searchanddeleteBtn() {
		
		String classTopic = applicationData.getClassTopics().get(0);
		searchBar.clear();
		sendKeys(searchBar,classTopic);
		click(deleteBtn);
		
	}

	public boolean verifyConfirmPopupContent() {
		
		return confirmlabel.isDisplayed() && yesBtn.isDisplayed() && noBtn.isDisplayed() && closeBtn.isDisplayed();
			
	}
	
	public void clickYesBtn() {
		
		click(yesBtn);
		
	}
	
	
	public String getFooterText() {

		return getText(footerText);

	}

	public String getFooterPaginationText() {

		return getText(footerPagination);
	}

	public boolean clickDatePicker() {

		click(classDates);

		return true;
	}

	public void emptyInputClick() {

		click(savebtn);
	}

	public boolean verifyDataTableHeaders(List<String> headers) {

		headers.forEach(str -> {

			Assert.assertNotNull(driver.findElement(By.xpath("//th[contains(text(),'" + str + "')]")));
		});

		return true;
	}

	public boolean verifyMultiDeleteBtn() {

		return isViewable(multiDeleteBtn);

	}

	public Boolean verifyNewClassPopupMandatoryFields() {
		Boolean result = Boolean.FALSE;
		if (addNewClassPopup.isDisplayed()) {
			if (mandatoryErrorMsg != null && !mandatoryErrorMsg.isEmpty() && mandatoryErrorMsg.size() == 6) {
				WebElement batchNameAlert = mandatoryErrorMsg.get(0);
				WebElement classTopicAlert = mandatoryErrorMsg.get(1);
				WebElement classDatesAlert = mandatoryErrorMsg.get(2);
				WebElement noOfClassesAlert = mandatoryErrorMsg.get(3);
				WebElement staffNameAlert = mandatoryErrorMsg.get(4);
				WebElement statusAlert = mandatoryErrorMsg.get(5);

				if (batchNameAlert.isDisplayed() && classTopicAlert.isDisplayed() && classDatesAlert.isDisplayed()
						&& noOfClassesAlert.isDisplayed() && staffNameAlert.isDisplayed()
						&& statusAlert.isDisplayed()) {

					return mandatoryErrorMsg.stream().allMatch(we -> we.getAttribute("style").contains("color: red"));

					// result = Boolean.TRUE;
				}
			} else {
				result = Boolean.FALSE;
			}
		}
		return result;
	}

	public boolean verifySortIcons() {

		return sortIcons.stream().allMatch(we -> we.isDisplayed());
	}

	public void verifyOptionalInput(String testcase) {

		classData = applicationData.getData(CLASS_SHEET_NAME, testcase); // TestData from Excel based on the testcase

		if (classData.get("ClassDescription") != null) {
			sendKeys(classDescText, classData.get("ClassDescription"));
		}
		if (classData.get("Comments") != null) {
			sendKeys(classCommentsText, classData.get("Comments"));
		}
		if (classData.get("Notes") != null) {
			sendKeys(classNotesText, classData.get("Notes"));
		}
		if (classData.get("Recording") != null) {
			sendKeys(classRecordingText, classData.get("Recording"));
		}

		click(savebtn);

	}

	public void verifyCancelBtn() {

		click(cancelBtn);
	}

	public void verifyCloseBtn() {

		click(closeBtn);
	}

	public boolean isDateDisabled(String day) {

		return driver.findElement(By.xpath("//span[text()='" + day + "' and contains(@class,'p-disabled')]")) != null;
	}

	public String getNoOfClasses() {

		return noOfClasses.getAttribute("ng-reflect-model");
	}

	public boolean searchClass() {

		String applnClassTopic = applicationData.getClassTopics().get(0);
		// searchText(text);
		System.out.println("applnClassTopic ======>" + applnClassTopic);
		sendKeys(searchBar, applnClassTopic);
		searchBar.sendKeys(Keys.TAB);
		String dataTableClassTopic = getText(
				driver.findElement(By.xpath("//tbody[@class='p-datatable-tbody']/tr/td[3]")));
		if (dataTableClassTopic.equalsIgnoreCase(applnClassTopic)) {
			return true;
		}

		return false;

	}

	public boolean verifySearhBox() {
		if (searchBar.isEnabled()) {
			return true;
		}
		return false;
	}

	public boolean verifyPopupTextField() {

		if (classTopic.isDisplayed() && noOfClasses.isDisplayed() && classDates.isDisplayed()) {
			return true;
		}
		return false;
	}

	public void addNewBtnClick() {
		click(addNewBtn);
	}

	public boolean verifyPopup() {
		if (isDisplayed(cancelBtn) && isDisplayed(saveBtn) && isDisplayed(closeBtn)) {
			return true;
		}
		return false;
	}

	public void classBtnClick() {
		click(classLink);

	}

	public boolean verifyPageTitle(String expected) {

		if (getText(pageTitle).equals(expected)) {
			return true;
		}

		return false;
	}

	public boolean verifyHeaderTitle(String expected) {

		if (getText(headerTitle).equals(expected)) {
			return true;
		}

		return false;
	}

	public String validateAddNewPopupTitle() {
		if (addNewClassPopup.isDisplayed()) {
			String poptitle = PopupTitle.getText();
			System.out.println("popup title text is: " + poptitle);
			return PopupTitle.getText();
		} else {
			return "";
		}
	}

	public boolean validateAddNewPopup() {

		return addNewClassPopup.isDisplayed();

	}

}
