package com.lms.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lms.driverManager.WebDriverFactory;
import com.lms.utilities.ConfigReader;

public class BasePage {

	protected WebDriver driver = WebDriverFactory.getInstance().getDriver();
	protected String BASE_URL = ConfigReader.getProp("baseUrl");

	protected static final long IMPLICIT_WAIT = 10;

	@FindBy(xpath = "//table/tbody/tr//div[@role='checkbox']")
	List<WebElement> rows;
	@FindBy(xpath = "//table/tbody/tr//button[contains(@icon, 'pi-pencil')]")
	List<WebElement> editicons;
	@FindBy(xpath = "//table/tbody/tr//button[contains(@icon, 'pi-trash')]")
	List<WebElement> deleteIcons;
	@FindBy(xpath="//button[contains(@class,'p-toast-icon-close')]")
	WebElement closeToastIcon;

	// success popup

	@FindBy(xpath = "//div[contains(@class,'p-toast-summary')]")
	WebElement successPopupTitle;
	@FindBy(xpath = "//div[contains(@class,'p-toast-detail')]")
	WebElement successPopupContent;

	public BasePage() {
		PageFactory.initElements(driver, this);
	}

	public void initElements() {
		PageFactory.initElements(driver, this);
	}

	public void openPage(String pagename) {
		driver.get(BASE_URL + pagename);

	}

	public boolean verifyPopup(String expmsg) {
		boolean retVal = false;
		retVal =  verifyText(expmsg, successPopupTitle);
//		try {
//			click(closeToastIcon);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		return retVal;
		
	}
	
	public void wait(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean dropDownSelect(WebElement element) {

		try {
			WebElement eleDD = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT))
					.until(ExpectedConditions.visibilityOf(element));

			if (eleDD.isEnabled()) {

				Select ddObjSelect = new Select(element);
				ddObjSelect.selectByIndex(1);
				return true;
			} else {
				throw new IllegalStateException("Element is not enabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	
	public void dropDownSelect(WebElement dropdown, String desiredOption) {
         click(dropdown);
         
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT));
         wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[role='listbox'] li")));

         try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 List<WebElement> options = driver.findElements(By.cssSelector("ul[role='listbox'] li"));
        
        for (WebElement option : options) {
            if (option.getText().equals(desiredOption)) {
                click(option);
                break;
            }
        }
	 }

	public boolean click(WebElement element) {
		try {
			WebElement eleToClick = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT))
					.until(ExpectedConditions.visibilityOf(element));

			if (eleToClick.isEnabled()) {
				eleToClick.click();
				return true;
			} else {
				throw new IllegalStateException("Element is not enabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean sendKeys(WebElement element, String text) {
		try {
			WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT))
					.until(ExpectedConditions.visibilityOf(element));

			if (ele.isEnabled()) {

				ele.clear();
				ele.sendKeys(text);
				return true;
			} else {
				throw new Exception("Element is not enabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isDisplayed(WebElement element) {
		try {
			WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT))
					.until(ExpectedConditions.visibilityOf(element));

			return ele.isEnabled();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isViewable(WebElement element) {
		try {
			WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT))
					.until(ExpectedConditions.visibilityOf(element));

			return ele.isDisplayed();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	public String getText(WebElement element) {

		WebElement textElement = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT))
				.until(ExpectedConditions.visibilityOf(element));
		return textElement.getText();
	}

	public boolean verifyText(String expectedMsg, WebElement element) {

		if (getText(element).equals(expectedMsg)) {
			return true;
		}

		return false;
	}

	public String ActualPageTitle() {
		return driver.getTitle();
	}

	public boolean clickElementByText(String text) {
		try {
			driver.findElement(By.xpath("//span[text()='" + text + "']/..")).click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean ValidateCheckboxes() {
		boolean allCheckboxesDisplayed = true;
		for (WebElement row : rows) {
			if (!row.isDisplayed()) {
				System.out.println("Checkbox not present in row: " + row.getText());
				allCheckboxesDisplayed = false;
			}
		}
		return allCheckboxesDisplayed;
	}


	public void singleDelete() {
		
		

	}

	public void multipleDelete() {

	}

}
