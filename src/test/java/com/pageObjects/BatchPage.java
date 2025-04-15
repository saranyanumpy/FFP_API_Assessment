package com.lms.pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.poi.util.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.util.Strings;

import com.beust.jcommander.JCommander.ProgramName;
import com.lms.utilities.LMSUIConstants;

public class BatchPage extends BasePage {

	@FindBy(xpath = "//*[text()='Batch']/..")
	WebElement batchBtn;
	
	@FindBy(xpath = "//span[text()='Batch']/..") WebElement batchLink;

	@FindBy(className = "box")
	WebElement heading;

	@FindBy(xpath = "//mat-toolbar/span[1]")
	WebElement title;

	@FindBy(id = "filterGlobal")
	WebElement searchBox;

	@FindBy(className = "mat-menu-item")
	WebElement menuItem;

	@FindBy(className = "p-dialog-title")
	WebElement popupWindowTitle;

	@FindBy(xpath = "//mat-card-title//button[@icon='pi pi-trash']")
	WebElement topDeletebtn;

	@FindBy(xpath = "//button[@icon='pi pi-pencil']")
	WebElement editIcon;
	
	@FindBy(xpath = "//td//button[@icon='pi pi-trash']")
	WebElement deleteIcon;
	
	@FindBy(className = "p-dropdown-label")
	WebElement dropdownLabel;

	@FindBy(className = "p-dropdown-trigger")
	WebElement dropdownTrigger;

	@FindBy(id = "batchName")
	WebElement batchName;

	@FindBy(id = "batchProg")
	WebElement batchProg;

	@FindBy(className = "p-invalid")
	WebElement errorMessage;

	@FindBy(id = "batchDescription")
	WebElement batchDescription;

	@FindBy(id = "batchStatus")
	WebElement batchStatus;

	@FindBy(id = "batchNoOfClasses")
	WebElement batchNoOfClasses;
	
	@FindBy(id = "programName")
	WebElement programName;

	@FindBy(xpath = "//*[@ng-reflect-value = 'ACTIVE']")
	WebElement activeStatusInput;
	@FindBy(xpath = "//*[@ng-reflect-value = 'INACTIVE']")
	WebElement inactiveStatusInput;
	
	@FindBy(xpath="//*[text()='Cancel']/..")WebElement cancelBtn;
	@FindBy(xpath="//*[text()='Save']/..")WebElement saveBtn;
	@FindBy(xpath="//span[contains(@class,'p-dialog-header-close-icon ng-tns')]/..") WebElement closeBtn;
	
	@FindBy(xpath = "//div[contains(@role, 'alert')]") WebElement saveBatchPopup; 
	@FindBy(xpath = "//div[contains(@class, 'p-toast-summary')]") WebElement saveBatchPopupTitle;
	@FindBy(xpath = "//div[contains(@class, 'p-toast-detail')]") WebElement saveBatchPopupMessage;
	
	
	//delete program
	@FindBy(xpath="//*[text()='No']/..") WebElement noBtn;
	@FindBy(xpath="//*[text()='Yes']/..") WebElement yesBtn;
	@FindBy(xpath="//button[contains(@class, 'p-dialog-header-close')]") WebElement deleteCloseBtn;
	@FindBy(id="deleteProgram") WebElement firstProgDeleteBtn;
	@FindBy (xpath="//span[text()='Confirm']") WebElement deleteConfirmPopup;
	@FindBy(id="logout") WebElement logout;

	
	public void openHomePage() {
		openPage("batch");
	}

	public void logout() {
		click(logout);
	}
	
	public boolean isLoginPage() {
		if(driver.getCurrentUrl().contains("login")) {
			return true;
		}
		return false;
	}

	
	public void clickOnBatchBtn() {
		click(batchBtn);
	}
	
    public void closeSubMenu() {
  	   ((JavascriptExecutor)driver).executeScript("arguments[0].click();", batchBtn);
    }
	
	public boolean isOnBatchPage() {
		if(driver.getCurrentUrl().contains("batch")) {
			return true;
		}
		return false;
	}

	public String getHeading() {
		return heading.getText();

	}

	public String getTitle() {
		return title.getText();
	}

	public void clickOnMenuItem() {
		click(menuItem);
	}

	public String getMenuItemText() {
		return menuItem.getText();
	}

	public String getPopUpWindowTitle() {
		driver.switchTo().activeElement();
		return getText(popupWindowTitle);
	}
	
	public boolean isPopupWindowDispalyed() {
		if(getText(popupWindowTitle, 1).equals("Batch Details")) {
			return true;
		}
		return false;
	}

	public String getText(WebElement element, long waitTime) {
		try {
			WebElement textElement = new WebDriverWait(driver, Duration.ofSeconds(waitTime))
				.until(ExpectedConditions.visibilityOf(element));
			return textElement.getText();
		} catch (Exception e) {
			return "";
		}
	} 
	public boolean isTopDeleteButtonEnabled() {
		return topDeletebtn.isEnabled();
	}

	public boolean isEditIconsPresentInEachRow() {
		boolean isFlag = true;
		//Perform row lookup
		List<WebElement> rows = driver.findElements(By.xpath("//mat-card-content//table/tbody/tr"));

		//Verify if edit button is displayed
		for (int i = 0; i < rows.size(); i++) {
			WebElement editButton = rows.get(i).findElement(By.xpath("//button[@icon='pi pi-pencil']"));
			if (editButton.isDisplayed()) {
				System.out.println("Edit button is present in row " + (i + 1));
			} else {
				isFlag = false;
			}
		}

		return isFlag;
	}

	public boolean isDeleteIconsPresentInEachRow() {
		boolean isFlag = true;
		List<WebElement> rows = driver.findElements(By.xpath("//mat-card-content//table/tbody/tr"));

		for (int i = 0; i < rows.size(); i++) {
			WebElement trashButton = rows.get(i).findElement(By.xpath("//button[@icon='pi pi-trash']"));
			if (trashButton.isDisplayed()) {
				System.out.println("Delete button is present in row " + (i + 1));
			} else {
				isFlag = false;
			}
		}

		return isFlag;
	}

	public boolean isCheckBoxPresentInEachRow() {
		boolean isFlag = true;
		List<WebElement> rows = driver.findElements(By.xpath("//mat-card-content//table/tbody/tr"));

		for (int i = 0; i < rows.size(); i++) {
			WebElement checkBox = rows.get(i).findElement(By.xpath("//div[@role='checkbox']"));
			if (checkBox.isDisplayed()) {
				System.out.println("CheckBox button is present in row " + (i + 1));
			} else {
				isFlag = false;
			}
		}

		return isFlag;
	}

	public boolean verifyTableHeaders(String headerTitle) {
		WebElement header = driver
				.findElement(By.xpath("//mat-card-content//table/thead/tr//th[text()='" + headerTitle + "']"));

		if (header.isDisplayed()) {
			System.out.println("Element " + headerTitle);
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyCheckBoxInHeader() {
		WebElement checkbox = driver
				.findElement(By.xpath("//mat-card-content//table/thead/tr//th//div[@role='checkbox']"));
		if (checkbox.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifySortIconInHeaders(String headerTitle) {
		WebElement sortIcon = driver.findElement(
				By.xpath("//mat-card-content//table/thead/tr//th[text()='" + headerTitle + "']//p-sorticon"));

		if (sortIcon.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isOnBatchPopupPage() {
		return "Batch Details".equalsIgnoreCase(getPopUpWindowTitle());
	}

	public void inputBatchFields(String testcase) {
		//Read data from excel
		Map<String, String> batchTestData = LMSUIConstants.applicationData.getData("Batch", testcase);
		System.out.println("batchTestData:"+ batchTestData);

		//If value for field is given then set it to popup
		String programName = batchTestData.get("ProgramName");
		if (!Strings.isNullOrEmpty(programName)) {
			dropDownSelect(dropdownTrigger, batchTestData.get("ProgramName"));
		}

		String batchNamePrefix = batchTestData.get("BatchNamePrefix");
		if (!Strings.isNullOrEmpty(batchNamePrefix)) {
			batchProg.sendKeys(batchNamePrefix);
		}

		String batchNameTxt = batchTestData.get("BatchName");
		if (!Strings.isNullOrEmpty(batchNameTxt)) {
			if(testcase.equals("validInputMandatory")) {
				sendKeys(batchName, String.valueOf(new Random().nextInt(99999)));
			} else {
				sendKeys(batchName, batchNameTxt);
			}
		}

		String description = batchTestData.get("Description");
		if (!Strings.isNullOrEmpty(description)) {
			sendKeys(batchDescription, description);
		}

		String status = batchTestData.get("Status");

		if (!Strings.isNullOrEmpty(status)) {
			if (status.equalsIgnoreCase("ACTIVE")) {
				click(activeStatusInput);
			} else {
				click(inactiveStatusInput);
			}
		}

		String numberOfClasses = batchTestData.get("NumberOfClasses");
		if (numberOfClasses != null) {
			sendKeys(batchNoOfClasses, numberOfClasses);
		}

	}

	public String getSelectedProgramName() {
		return dropdownLabel.getAttribute("value");
	}

	public String getErrorForField() {
		return errorMessage.getText();
	}

	public String getBatchNamePefix() {
		return batchProg.getText();
	}
	
	public void clickSaveBtn() {
    	click(saveBtn);
    }
    
    public void clickCancelBtn() {
    	click(cancelBtn);
    }
    
    public void clickCloseBtn() {
//    	click(closeBtn);
   	   ((JavascriptExecutor)driver).executeScript("arguments[0].click();", closeBtn);

    }
    
    public String saveActionPopup() {
    	if (isDisplayed(saveBatchPopup)) {
	        return getText(saveBatchPopupTitle);
	    } else {
	        return "No Popup Displayed";
	    	}
	}
	
    
    public boolean isSaveBatchPopupDisplayed() {
    	return isDisplayed(saveBatchPopup);
    }
	
    
    public void search(String batchName) {
		sendKeys(searchBox,batchName);
	}
    
    public void clickEditBatch() {
		click(editIcon);
	}
    
    public void clickDeleteBatch() {
		click(deleteIcon);
	}
    
    public boolean validateDeleteConfirmPopup() {
		boolean popup = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.invisibilityOf(deleteConfirmPopup));
	    if (popup) {
	        return false;
	    } 
	    return true;
	}
    
    public boolean isProgramNameEditable(){
    	return programName.isEnabled();
    }
    
    public boolean isBatchNameEditable(){
    	return batchName.isEnabled();
    }
    
    public List<String> getBatchNamesForAllRows() {
    	List<String> list = new ArrayList();
    	List<WebElement> rows = driver.findElements(By.xpath("//mat-card-content//table/tbody/tr"));
    	 for (WebElement row : rows) {
    		 list.add(row.findElement(By.xpath("td[2]")).getText());
    	 }
        return list;

    }
    
    public String getBatchName(String testData) {
    	Map<String, String> batchTestData = LMSUIConstants.applicationData.getData("Batch", testData);
		System.out.println("batchTestData:"+ batchTestData);
		return batchTestData.get("BatchName");
    }
    
    public void clickNoBtn() {
    	click(noBtn);
    }
    
    public void clickYesBtn() {
    	System.out.println("YEssss");
    	click(yesBtn);
    }
    
    public void clickCloseIcon() {
    	click(deleteCloseBtn);
    }
}

