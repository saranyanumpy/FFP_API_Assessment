package com.lms.pageObjects;

import java.util.Map;
import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.event.SpellChecker;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.openqa.selenium.support.Color;



//import org.apache.commons.lang.StringUtils;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.lms.utilities.LMSUIConstants.*;

public class LoginPage extends BasePage {
DashboardPage dp=new DashboardPage();
	Map<String, String> loginData;

	@FindBy(xpath = "//form//button[@id='login']")
	WebElement loginBtn;
	@FindBy(xpath = "//div//input[@id='username']")
	WebElement userTextLogin;
	@FindBy(xpath = "//div//input[@id='password']")
	WebElement passwordTextLogin;
	@FindBy (xpath  = "//div[@class='image-container']") WebElement logo;
	@FindBy (xpath  = "//p[text()='Please login to LMS application']") WebElement signInText ;	
	@FindBy (xpath  = "//div//input[@id='username']") WebElement userTextBox;
	@FindBy (xpath  = "//div//input[@id='password']") WebElement passTextBox;
	 @FindBy (xpath  = "//span[text()='User']") WebElement userWordtext;
	 @FindBy (xpath  = "//span[text()='Password']") WebElement passWordtext;
	 @FindBy (xpath  = "//span[text()=' *'][@class='mat-placeholder-required mat-form-field-required-marker ng-tns-c78-0 ng-star-inserted']") WebElement userastrik;
	 @FindBy (xpath  = "//span[@class='mat-placeholder-required mat-form-field-required-marker ng-tns-c78-1 ng-star-inserted'][text()=' *']") WebElement passwordastrik;
	// @FindBy (xpath  = "//div//button[@id='login']") WebElement loginBtn;
	public void openPage() {
		driver.get(BASE_URL + "login");
	}

//	public void login(String testcase) {
//		loginData = applicationData.getData("Login", testcase);
//		openPage("login");
//		sendKeys(userTextLogin,loginData.get("Username"));
//		sendKeys(passwordTextLogin,loginData.get("Password"));
//		System.out.println("Logging in with Username: " + loginData.get("Username") + " and Password: " + loginData.get("Password"));
//		click(loginBtn);
//		applicationData.setLoggedIn(true);
//		
//	}
	public boolean login(String testcase) {
		 loginData = applicationData.getData("Login", testcase);
		    openPage("login");
		    sendKeys(userTextLogin, loginData.get("Username"));
		    sendKeys(passwordTextLogin, loginData.get("Password"));
		    System.out.println("Logging in with Username: " + loginData.get("Username") + " and Password: " + loginData.get("Password"));
		    click(loginBtn);
		    
		    // Check if login was successful by verifying the presence of a dashboard element
		    try {
		        return dp.isDashboardLoaded(); // Replace with actual dashboard locator
		    } catch (NoSuchElementException e) {
		        return false; // Login failed
		    }
		}
	public String spellChecking() throws Exception { // Extract text content from the web page
		 String PageTexts = driver.findElement(By.tagName("body")).getText(); // Create a SpellChecker instance SpellDictionary dictionary = null;
		 SpellDictionaryHashMap dictionary= new SpellDictionaryHashMap();
		 SpellChecker spellChecker = new SpellChecker(dictionary); // Tokenize the web page text into words 
		 String[] words = StringUtils.split(PageTexts);
		 StringBuffer misSpelledWords = null; // Check the spelling of each word
		 for (String word : words) {
		 if (!spellChecker.isCorrect(word)) 
		 { if (misSpelledWords == null) 
		 { misSpelledWords = new StringBuffer();
		 }
		 misSpelledWords.append(word); 
		 misSpelledWords.append(","); // System.out.println("Misspelled word: " + word); }
		 }
		 if (misSpelledWords != null) { return misSpelledWords.toString(); }
		 return null;
		 }
		return null;
	}
	
	public void verifyLogo() {
	    boolean logoPresent = logo.isDisplayed();
	    Assert.assertTrue(logoPresent, "Logo is not displayed on the page.");
	}
	public void verifyApplicationTitle() throws TesseractException {
		String Title = " LMS - Learning Management System";
		File imageFile=new File ("C:\\Users\\sridh\\git\\Team4_SeleniumSquad_LMSUI\\src\\test\\resources\\testData\\Logo_Screen.png");
		ITesseract image = new Tesseract();
		String imageText = image.doOCR(imageFile);
		System.out.println(imageText);
		String lines[]= imageText.split("\\r?\\n");                              //Extract lines
		String AppTitle=lines[0].replaceAll("[^a-zA-Z\\s\\-]" ,"");               //remove all special characters
		System.out.println("Line[0]" + AppTitle);
		try {
		Assert.assertEquals(AppTitle,Title );
		}
		catch (Exception e) {
			String message = e.getMessage();
		}
	}
	
	public void verifyCompanyName() throws TesseractException {
		String CompanyName = " NumpyNinja";
		File imageFile=new File ("C:\\Users\\sridh\\git\\Team4_SeleniumSquad_LMSUI\\src\\test\\resources\\testData\\Logo_Screen.png");
		ITesseract image = new Tesseract();
		String imageText = image.doOCR(imageFile);
		System.out.println(imageText);
		String lines[]= imageText.split("\\r?\\n");
		String compName=lines[1].replaceAll("[^a-zA-Z\\s\\-]" ,"");
		System.out.println("Line[1]" + compName);
		
		try {
			Assert.assertEquals(CompanyName, compName);
			}
			catch (Exception e) {
				String message = e.getMessage();
			}
	}
	
	public void verifyPleaseLoginTitle() {
		String loginText = signInText.getText();
		String pleaselogin = "Please login to LMS application";
		Assert.assertEquals(loginText,pleaselogin);
	}
	public void TextFieldsDisplayed(int a) {
		
			List<WebElement> textfields = driver.findElements(By.id("username or password "));
			int size = textfields.size();
			if(size==a) {
				System.out.println("2 Text feilds are there");
			}
			else {
				System.out.println("Text feilds are not there");
			}
		}
	
	public void usertext(String string) {
	  	  String expectedmsg = string;
			String actualmsg = userWordtext.getText();
			Assert.assertEquals(expectedmsg,actualmsg);
			 System.out.println(actualmsg);
	    }
	public void astrikuser(String string) {
	  	  String expectedmsg = string;
			String actualmsg = userastrik.getText();
			Assert.assertEquals(expectedmsg,actualmsg);
			 System.out.println(actualmsg);
	    }
	public void passwordtext(String string) {
	  	  String expectedmsg = string;
			String actualmsg = passWordtext.getText();
			Assert.assertEquals(expectedmsg,actualmsg);
			 System.out.println(actualmsg);
	    }
	
	 public void astrikpassword(String string) {
	   	  String expectedmsg = string;
	 		String actualmsg = passwordastrik.getText();
	 		Assert.assertEquals(expectedmsg,actualmsg);
	 		 System.out.println(actualmsg);
	     }
	public void inputFieldAlign() {
		
	     WebElement inputField = userTextBox;

	     // location and dimensions of the input field
	     int inputFieldX = inputField.getLocation().getX();
	     int inputFieldWidth = inputField.getSize().getWidth();

	     // Get the dimensions 
	     int windowWidth = driver.manage().window().getSize().getWidth();

	     // Calculate the center position
	     int inputFieldCenter = inputFieldX + (inputFieldWidth / 2);
	     int windowCenter = windowWidth / 2;

	     // Assert that the input field is approximately centered
	     int tolerance = 20; // Allow a small tolerance for alignment
	     Assert.assertTrue(Math.abs(windowCenter - inputFieldCenter) <= tolerance, 
	             "Input field is not centered on the page");
	 }
	
	public void loginBtnDisplay() {
		if(loginBtn.isDisplayed()) 
		{
		    System.out.println("Login button is Displayed");
		}
		else
		    System.out.println("Show more is not there");
	}
	 public void loginBtnLocation() {
		 int winHeight = driver.manage().window().getSize().getHeight();     //dimension of browser window
		 int winWidth = driver.manage().window().getSize().getWidth();       //dimension of browser window
		 int xPos = loginBtn.getLocation().getX();
			int yPos = loginBtn.getLocation().getY();
		 
		 Dimension dimuser = loginBtn.getSize();
         
        int heightuser= dimuser.height;
        int widthuser=dimuser.width;
        System.out.println("Winheight"+winHeight);
        System.out.println("winWidth"+winWidth);
        System.out.println("heightuser"+heightuser);
        System.out.println("widthuser"+widthuser);
        System.out.println("xPos"+xPos);
        System.out.println("yPos"+yPos);
        
        if (xPos>700 & xPos<800 & yPos>400 & yPos<600  ) {
       	System.out.println("Login button in the centre");
        }  
     }
	 
	 
	 public void userTextFieldColorCheck() {
		 String color = userTextBox.getCssValue("color");
   	  String hex = Color.fromString(color).asHex();
   	  
   	  if (hex=="#808080" ) {
   		System.out.println("User String is in grey color");
   	  }
   	  else {
   		System.out.println("User String is not in grey color");
   	  }
     }
	 public void userPassFieldColorCheck() {
		 String color = passTextBox.getCssValue("color");
	   	  String hex = Color.fromString(color).asHex();
	   	  
	   	  if (hex=="#808080" ) {
	   		System.out.println("User String is in grey color");
	   	  }
	   	  else {
	   		System.out.println("User String is not in grey color");
	   	  }
	     }	
	 
	 public void loginBtnClick() {
		 loginBtn.click();
	 }
	 
	 public void loginBtnKeyboardAction(String testcase) {
		// Assuming this is part of your login method
		 String testcase1 = "ValidCredentials"; // Use appropriate test case name for the admin

		 // Retrieve login data and open the login page
		 loginData = applicationData.getData("Login", testcase1);
		 openPage("login");

		 // Enter username and password
		 sendKeys(userTextLogin, loginData.get("Username"));
		 sendKeys(passwordTextLogin, loginData.get("Password"));

		 // Submit the login form using keyboard actions
		 passwordTextLogin.sendKeys(Keys.TAB); // Move focus if needed
		 passwordTextLogin.sendKeys(Keys.ENTER); // Submit the form

		 // Wait for the dashboard to load, with a timeout
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.urlContains("https://lms-frontend-hackathon-oct24-173fe394c071.herokuapp.com/login")); // Wait for a unique part of the URL

		 // Optional: Assert to confirm login success
		 String actualTitle = driver.getTitle();
		 System.out.println("Actual title after login: " + actualTitle); // Log the actual title for debugging
		 String expectedTitle = "LMS"; // Adjust according to your dashboard page title

		 // Use assertEquals to check if the expected title matches the actual title
		 try {
		     Assert.assertEquals(actualTitle, expectedTitle);
		 } catch (AssertionError e) {
		     System.err.println("Assertion failed: Expected title: " + expectedTitle + ", but found: " + actualTitle);
		     throw e; // Re-throw to fail the test properly
		 }

		 // Mark user as logged in
		 applicationData.setLoggedIn(true);

	}
	 public void loginBtnMouseAction(String testcase) {    driver.manage().deleteAllCookies();
	    
	 loginData = applicationData.getData("Login", testcase); // Use the provided testcase
     openPage("login");

     // Enter username and password
     sendKeys(userTextLogin, loginData.get("Username"));
     sendKeys(passwordTextLogin, loginData.get("Password"));

     // Use Actions class to perform mouse click on the login button
     Actions actions = new Actions(driver);
     actions.moveToElement(loginBtn).click().perform(); // Move to the button and click it

     // Wait for the dashboard to load, with a timeout
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     wait.until(ExpectedConditions.urlContains("https://lms-frontend-hackathon-oct24-173fe394c071.herokuapp.com/login"));
     		// Adjust this to a unique part of the dashboard URL

     // Optional: Assert to confirm login success
     String actualTitle = driver.getTitle();
     System.out.println("Actual title after login: " + actualTitle); // Log the actual title for debugging
     String expectedTitle = "LMS"; // Adjust according to your dashboard page title

     // Use assertEquals to check if the expected title matches the actual title
     try {
         Assert.assertEquals(actualTitle, expectedTitle);
     } catch (AssertionError e) {
         System.err.println("Assertion failed: Expected title: " + expectedTitle + ", but found: " + actualTitle);
         throw e; // Re-throw to fail the test properly
     }

     // Mark user as logged in
     applicationData.setLoggedIn(true);
 }
}
