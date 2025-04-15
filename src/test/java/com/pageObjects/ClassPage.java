package com.lms.pageObjects;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Comparator;
import java.util.Date;
import com.lms.utilities.LMSUIConstants;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import static com.lms.utilities.LMSUIConstants.applicationData;

import java.util.HashMap;
import java.util.List;

import com.lms.driverManager.WebDriverFactory;

public class ClassPage extends BasePage {
	String batchNameRow;
	String batchNamePopUpValue;
	int rowSize;
	Map<String, String> fetchRowMap;
//	WebDriverWait wait;

	@FindBy(xpath = "//span[text()='Class']/..")
	WebElement classLink;
	@FindBy(xpath = "//div[contains(@role, 'dialog')]")
	WebElement addNewPopup;
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
	@FindBy(xpath = "//span[text()='Cancel']")
	WebElement cancelBtn;
	@FindBy(xpath = "//span[text()='Save']")
	WebElement saveBtn;
	@FindBy(xpath = "//span[contains(@class,'p-dialog-header-close-icon ng-tns')]")
	WebElement closeBtn;
	@FindBy(xpath = "//div[contains(@class,'p-dialog-header')]")
	WebElement dialogWindow;
	@FindBy(xpath = "//span[@class='p-button-icon pi pi-calendar']")
	WebElement calenderIcon;
	@FindBy(xpath = "//input[@id='classTopic']")
	WebElement classTopic;
	@FindBy(xpath = "//input[@id='classNo']")
	WebElement noOfClasses;
	@FindBy(xpath = "//input[@id='icon']")
	WebElement classDates;

	@FindBy(id = "deleteProgram")
	WebElement deletebtn;
//	@FindBy(id="filterGlobal")WebElement searchbtn;
	@FindBy(xpath = "//span[text()='Confirm']")
	WebElement confirmlabel;
	@FindBy(xpath = "//span[text()='Yes']")
	WebElement yesBtn;
	@FindBy(xpath = "//span[text()='No']")
	WebElement noBtn;
	@FindBy(xpath = "//*[contains(text(),'Are you sure you want to delete')]")
	WebElement contentTxt;
//	@FindBy(xpath="//span[@class='pi pi-times ng-tns-c133-4']")WebElement closeBtn;
	@FindBy(xpath = "//div[contains(@class,'p-toast-summary')]")
	WebElement successPopupTitle;
	@FindBy(xpath = "//div[contains(@class,'p-toast-detail')]")
	WebElement successPopupContent;

	@FindBy(xpath = "//button[@class='p-button-rounded p-button-danger p-button p-component ng-star-inserted']")
	WebElement cancelbtn;

//	@FindBy(xpath="//span[@class='p-dialog-header-close-icon ng-tns-c132-3 pi pi-times']")WebElement crossbtn;
	@FindBy(xpath = "//*[contains(text(),'Successful Program Created')]")
	WebElement pgmPopSuccessTxt;

	@FindBy(xpath = "//input[@id='username']")
	WebElement userName;

	@FindBy(xpath = "//input[@id='password']")
	WebElement password;

	@FindBy(xpath = "//button[@id='login']")
	WebElement login;

	@FindBy(xpath = "//span[text()='Class']/..")
	WebElement classPage;

	@FindBy(xpath = "//div[contains(@class, 'cdk-overlay-backdrop')]")
	WebElement classBckDrp;

	@FindBy(xpath = "//tbody[@class='p-datatable-tbody']/tr[1]")
	WebElement firstRow;

	@FindBy(xpath = "//p-dropdown[@id='batchName']//div/div/input[@type='text']")
	WebElement batchNamePopUp;

	@FindBy(xpath = "//input[@id='classDescription']")
	WebElement classDescrip;

	@FindBy(xpath = "//input[@id='icon']")
	WebElement classDate;

	@FindBy(xpath = "//button[@id='saveClass']")
	WebElement saveButton;

	@FindBy(xpath = "//p-dropdown[@id='staffId']/div/input")
	WebElement staffName;

	@FindBy(xpath = "//p-radiobutton[@ng-reflect-input-id='Inactive']")
	WebElement statusInactBtn;

	@FindBy(xpath = "//input[@id='classComments']")
	WebElement comments;

	@FindBy(xpath = "//input[@id='classNotes']")
	WebElement notes;

	@FindBy(xpath = "//input[@id='classRecordingPath']")
	WebElement recording;

//	@FindBy(xpath = "//p-toast//p-toastitem")
//	WebElement saveSuccessMsgPop;
	
	@FindBy(xpath = "//div[contains(@class,'p-toast-summary')]")
	WebElement saveSuccessMsgPop;

	@FindBy(xpath = "//span[text()='Cancel']/..")
	WebElement cancelButton;

	@FindBy(xpath = "//input[@id='classTopic']")
	WebElement classTopicPopUp;

	@FindBy(xpath = "//input[@id='filterGlobal']")
	WebElement search;

	@FindBy(xpath = "//small[text()='Class Date is required.']")
	WebElement invalDateErrMsg;

	@FindBy(xpath = "//mat-card-title[text()='Class Comments']/following-sibling::mat-card-subtitle")
	WebElement commentsRow;

	@FindBy(xpath = "//mat-card-title[text()='Class Notes']/following-sibling::mat-card-subtitle")
	WebElement notesRow;

	@FindBy(xpath = "//mat-card-title[text()='Class Recording Path']/following-sibling::mat-card-subtitle")
	WebElement recordingRow;

	@FindBy(xpath = "//tbody[@class='p-datatable-tbody']") // Example: adjust based on your actual table structure
	WebElement dataTable;

	@FindBy(xpath = "//button[contains(@class,'p-paginator-next')]")
	WebElement nextPageLink;

	@FindBy(xpath = "//button[contains(@class,'p-paginator-last')]")
	WebElement lastPageLink;

	@FindBy(xpath = "//button[contains(@class,'p-paginator-first')]")
	WebElement startPageLink;

	@FindBy(xpath = "//button[contains(@class,'p-paginator-prev')]")
	WebElement prevPageLink;

	@FindBy(xpath = "//button[@icon='pi pi-pencil']")
	WebElement editLink;
	
	@FindBy(xpath = "//span[text()='Close']/..")
	WebElement closePopUp;
	
	@FindBy(xpath = "//thead//tr//th[2]")
	WebElement clickSortBatchName;

	@FindBy(xpath = "//thead//tr//th[3]")
	WebElement clickSortClassTopic;

	@FindBy(xpath = "//thead//tr//th[4]")
	WebElement clickSortClassDescription;
	
	@FindBy(xpath = "//thead//tr//th[5]")
	WebElement clickSortStatus;
	
	@FindBy(xpath = "//thead//tr//th[6]")
	WebElement clickSortClassDate;
	
	@FindBy(xpath = "//thead//tr//th[7]")
	WebElement clickSortStaffName;
	
	
	@FindBy(xpath = "//tr//td[2]")
	List<WebElement> sortBatchName;

	@FindBy(xpath = "//tr//td[3]")
	List<WebElement> sortClassTopic;

	@FindBy(xpath = "//tr//td[4]")
	List<WebElement> sortClassDescription;
	
	@FindBy(xpath = "//tr//td[5]")
	List<WebElement> sortStatus;
	
	@FindBy(xpath = "//tr//td[6]")
	List<WebElement> sortClassDate;
	
	@FindBy(xpath = "//tr//td[7]")
	List<WebElement> sortStaffName;
	
	
	@FindBy(xpath = "//button[@id='logout']")
	WebElement logOut;
	
	@FindBy(xpath = "//p[text()='Please login to LMS application']")
	WebElement loginPage;
	

	Map<String, String> classData;
//
//	public ClassPage() {
//		this.wait = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT));
//	}

	public void openPage() {
		driver.get(BASE_URL + "session");

	}

	public boolean isOnClassPage() {
		if (driver.getCurrentUrl().contains("session")) {
			return true;
		}
		return false;
	}

	public void validInputMandatoryFields(String testcase) {

		classData = LMSUIConstants.applicationData.getData("Class", testcase);

		sendKeys(classTopic, classData.get("ClassTopic"));
		sendKeys(noOfClasses, classData.get("NoofClasses"));
		sendKeys(classDates, classData.get("SelectClassDates"));

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
		classBckDrp.click();
		// click(classBckDrp);

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
		if (addNewPopup.isDisplayed()) {
			String poptitle = PopupTitle.getText();
			System.out.println("popup title text is: " + poptitle);
			return PopupTitle.getText();
		} else {
			return "";
		}
	}

	public void manageClassPage() {

		classPage.click();
		classBckDrp.click();

	}

//	public void waitForFirstRowToContain(String expectedValue) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until((ExpectedCondition<Boolean>) driver -> {
//            WebElement thirdCell = driver.findElement(By.xpath("//tbody[@class='p-datatable-tbody']/tr[1]/td[3]"));
//            System.out.println("thirdCell.getText() -> " + thirdCell.getText() + " " + expectedValue);
//            return thirdCell.getText().contains(expectedValue);
//        });
//    }

	public void classPopUpWindow() {
		search.clear();
		search.sendKeys("testtopic 8");
		search.sendKeys(Keys.RETURN);
		// WebElement firstRowExplicitWait =
		// driver.findElement(By.xpath("//tbody[@class='p-datatable-tbody']/tr[1]"));
		// batchNameRow =
		// firstRowExplicitWait.findElement(By.xpath("./td[2]")).getText();
		// System.out.println("batchNameRow-->" + batchNameRow);
		// waitForDataTableToLoad();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("//tbody[@class='p-datatable-tbody']/tr[1]/td[3]"), "testtopic 8"));
		// waitForFirstRowToContain("Javascript1");
		// firstRowExplicitWait.findElement(By.xpath("//button[@icon='pi
		// pi-pencil']")).click();
		// WebElement editLinkEle =
		// dataTable.findElement(By.xpath("./tr[1]//button[@icon='pi pi-pencil']"));
		WebElement editLinkEle = driver
				.findElement(By.xpath("//tbody[@class='p-datatable-tbody']/tr[1]//button[@icon='pi pi-pencil']"));

		editLinkEle.click();
		/// batchNamePopUpValue = batchNamePopUp.getAttribute("ng-reflect-model");
		// System.out.println("batchNamePopUpValue" + batchNamePopUpValue);

	}

	public void validateClassPopUp() {
		Assert.assertEquals(batchNamePopUpValue, batchNameRow);
	}

	public boolean isBatchNameDisabled() {
		return !batchNamePopUp.isEnabled();

	}

	public boolean isClassTopicDisplayed() {
		return !classTopicPopUp.isEnabled();

	}

//	public WebElement waitForElementToBeClickable(By locator) {
//		return wait.until(ExpectedConditions.elementToBeClickable(locator));
//	}

//	 public void waitForDialogToClose(WebElement dialogLocator) {
//		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	        wait.until(ExpectedConditions.invisibilityOf(dialogLocator));
//	    }

	public String waitForToastAndGetText() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement toast = wait.until(ExpectedConditions.visibilityOf(saveSuccessMsgPop));
		return toast.getText();

	}

	public void updateData(String testcase) {

		fetchRowMap = applicationData.getData(LMSUIConstants.CLASS_SHEET_NAME, testcase);
		fetchRowMap.forEach((key, value) -> {
			if (value != null && !key.equals("testcase")) {
				updateInputData(key, value);
				// waitForElementToBeVisible(By.xpath("//button[@id='saveClass']"));

			}
		});
		saveButton.click();
		System.out.println("save clicked");
	}

	public void updateInputData(String key, String value) {

		switch (key) {
		case "classDescription":
			classDescrip.clear();
			classDescrip.sendKeys(value);
			// saveBtn.click();
			break;

		case "selectClassDates":
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(classDate));
			classDate.clear();
			classDate.sendKeys(value + Keys.ENTER);
			// saveBtn.click();
			break;

		case "staffName":
			staffName.clear();
			staffName.sendKeys(value);
			// saveBtn.click();
			break;

		case "status":
			 rowSize = getTableBodyRowCount();
			statusInactBtn.click();

			break;

		case "comments":
			comments.clear();
			comments.sendKeys(value);
			break;

		case "notes":
			notes.clear();
			notes.sendKeys(value);
			break;

		case "recording":
			recording.clear();
			recording.sendKeys(value);
			break;

		default:
			System.out.println(key.toString() + " key is unknown."); // This handles the "not part" case
			break;

		}
	}

	public void validateUpdatedRowDetails() {
		System.out.println("validateUpdatedRowDetails");
		for (String key : fetchRowMap.keySet()) {
			String value = fetchRowMap.get(key);
			if (value != null && !key.equals("testcase")) {
				chkUpdatedRow(key, value);
			}
		}

	}
	
	 public int getTableBodyRowCount() {
	        
	        WebElement table = driver.findElement(By.xpath("//tbody[@class='p-datatable-tbody']")); 
	       List<WebElement> rows = table.findElements(By.tagName("tr"));
	        return rows.size(); 
	    }
	 
	 public static String convertDateFormat(String dateString) {
	        SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
	        SimpleDateFormat targetFormat = new SimpleDateFormat("MM/dd/yy");

	        
	            // Parse the original date string to a Date object
	           Date date = null;
			try {
				date = originalFormat.parse(dateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	            // Format the Date object to the new format
	            return targetFormat.format(date);
	        } 
	 
	 
	public void chkUpdatedRow(String key, String value) {

		// TODO - added for the
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// waitForFirstRowToContain("Javascript1");

		if (!key.equalsIgnoreCase("status")) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.textToBePresentInElementLocated(
					By.xpath("//tbody[@class='p-datatable-tbody']/tr[1]/td[3]"), "testtopic 8"));
		}
//		WebElement firstRowExplicitWait = wait.until(
//				ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody[@class='p-datatable-tbody']/tr[1]")));

		// WebElement firstRowExplicitWait =
		// driver.findElement(By.xpath("//tbody[@class='p-datatable-tbody']/tr[1]"));
		switch (key) {
		case "classDescription":
			String classDescripRow = firstRow.findElement(By.xpath("./td[4]")).getText();
			System.out.println(classDescripRow + "--" + value);
			Assert.assertEquals(classDescripRow, value);
			break;

		case "selectClassDates":

			String classDateRow = firstRow.findElement(By.xpath("./td[6]")).getText();
			
			System.out.println(classDateRow + "--" + convertDateFormat(value));
			Assert.assertTrue(classDateRow.contains(convertDateFormat(value)), "Class Date should contain the substring");
			break;

		case "staffName":

			String staffNameRow = firstRow.findElement(By.xpath("./td[7]")).getText();
			System.out.println(staffNameRow + "--" + value);
			Assert.assertEquals(staffNameRow, value);
			break;

		case "status":
            int rowSizeInactive = getTableBodyRowCount();
			//WebElement statusRow = firstRow.findElement(By.xpath("./td[5]"));
			//wait.until(ExpectedConditions.invisibilityOf(By.xpath("//tbody[@class='p-datatable-tbody']/tr[1]/td[3]"), "testtopic"));
			//System.out.println(statusRow + "--" + value);
			Assert.assertEquals(rowSize-1, rowSizeInactive);
			break;
			
		case "comments":

			firstRow.click();
			String commentText = commentsRow.getText();
			System.out.println(commentText + "--" + value);
			Assert.assertEquals(commentText, value);
			closePopUp.click();
			break;

		case "notes":
			// WebElement firstRowWaitelee =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody[@class='p-datatable-tbody']/tr[1]")));
			// firstRowExplicitWait.click();
			firstRow.click();
			String noteText = notesRow.getText();
			System.out.println(noteText + "--" + value);
			Assert.assertEquals(noteText, value);
			closePopUp.click();
			break;

		case "recording":
			// WebElement firstRowWaitele =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody[@class='p-datatable-tbody']/tr[1]")));
			// firstRowExplicitWait.click();
			firstRow.click();
			String recordText = recordingRow.getText();
			System.out.println(recordText + "--" + value);
			Assert.assertEquals(recordText, value);
			closePopUp.click();
			// TODO: add close dialog here
			break;

		default:
			System.out.println(key.toString() + " key is unknown."); // This handles the "not part" case
			break;

		}
	}

	public void ValSuccessToastMsg(String expectedSuccessmsg) {
		System.out.println("ValSuccessToastMsg");
		String successMsg = waitForToastAndGetText();
		System.out.println("successMsg-->" + successMsg);
		Assert.assertTrue(successMsg.contains(expectedSuccessmsg), "The message should contain the substring");

	}
	
	public void validateToastMessageNotDisplayed() {
	    System.out.println("Validating that the success toast message is not displayed.");
	    
	    // Get the success message from the toast
	    String successMsg = waitForToastAndGetText(); 

	    System.out.println("Success Message: " + successMsg);

	    // Assert that the success message is either empty or null
	    Assert.assertTrue(successMsg == null || successMsg.isEmpty(), 
	        "No success message to be displayed");
	}


	public void ClkCancelBtn(String testcase) {
		fetchRowMap = applicationData.getData(LMSUIConstants.CLASS_SHEET_NAME, testcase);
		fetchRowMap.forEach((key, value) -> {
			if (value != null && !key.equals("testcase")) {
				updateInputData(key, value);
				// waitForElementToBeVisible(By.xpath("//button[@id='saveClass']"));

			}
		});
		cancelButton.click();
		System.out.println("cancel clicked");

	}
	
	public void validateCancelBtn() {
		 int rowSizeInactive = getTableBodyRowCount();
		 Assert.assertEquals(rowSize, rowSizeInactive);
		
	}

	public void validateErrMsg(String expectedErrMsg) {
		String actualErrMsg = invalDateErrMsg.getText();
		Assert.assertTrue(actualErrMsg.contains(expectedErrMsg), "The message should contain the substring");

	}
	
	//// pagination

	public void waitForDataTableToLoad() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(dataTable));
	}

	public void clearFilter() {
		search.clear();
	}
	
	public void clickNextPagelink() {

		waitForDataTableToLoad();
		click(nextPageLink);
	}

	public void clickLastPageLink() {
		waitForDataTableToLoad();
		click(lastPageLink);
	}

	public void clickStrtPageLink() {
		waitForDataTableToLoad();
		click(startPageLink);
	}

	public void clickPrevPageLink() {
		waitForDataTableToLoad();
		click(prevPageLink);
	}

	public boolean NextPageLinkEnabled() {

		if (nextPageLink.isEnabled()) {
			return true;
		} else {
			return false;
		}

	}

	public boolean PrevPageLinkEnabled() {

		if (prevPageLink.isEnabled()) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<WebElement> getSortBatchName() {
		return sortBatchName;
			
	}
	
	public void clickSortBatchName() {
		click(clickSortBatchName);
		
	}
	
	public List<WebElement> getSortClassTopic() {
		return sortClassTopic;
			
	}
	
	public void clickSortClassTopic() {
		click(clickSortClassTopic);
		
	}
	
	public List<WebElement> getSortClassDescription() {
		return sortClassDescription;
			
	}
	
	public void clickSortClassDescription() {
		click(clickSortClassDescription);
		
	}
	
	public List<WebElement> getSortStatus() {
		return sortStatus;
			
	}
	
	public void clickSortStatus() {
		click(clickSortStatus);
		
	}
	
	public List<WebElement> getSortClassDate() {
		return sortClassDate;
			
	}
	
	public void clickSortClassDate() {
		click(clickSortClassDate);
		
	}
	
	public List<WebElement> getSortStaffName() {
		return sortStaffName;
			
	}
	
	public void clickSortStaffName() {
		click(clickSortStaffName);
		
	}
	
	
	
	public void SortAscendingOrder(List<WebElement> list) {
		List<String> appSort = list.stream().map(s -> s.getText()).collect(Collectors.toList());
		System.out.println(appSort);

		List<String> SortedList = appSort.stream().sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList());
		System.out.println(SortedList);
		Assert.assertTrue(appSort.equals(SortedList));

	}
	
	public void sortDescendingOrder(List<WebElement> list) {
		List<String> appSort = list.stream().map(s -> s.getText()).collect(Collectors.toList());
		System.out.println(appSort);

		List<String> sortedList = appSort.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(sortedList);

		Assert.assertTrue(appSort.equals(sortedList));
	
	
	}
	
	public void logout()
	{
		logOut.click();
	}
public void vallogout(String string) {
	String valLogin = loginPage.getText();
	Assert.assertEquals(valLogin, string);
}


}

