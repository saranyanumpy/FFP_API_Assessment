package com.lms.pageObjects;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;


import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.testng.Assert;



public class DashboardPage extends BasePage{
	private static final int DEFAULT_TIMEOUT_MS = 5000;
	String LinkURL = "";
	 HttpURLConnection huc = null;
   int respCode = 200;

	@FindBy(xpath = "//mat-toolbar[@class='mat-toolbar mat-primary mat-toolbar-single-row ng-star-inserted']") WebElement headerBar;
	@FindBy(xpath = "//span[text()=' LMS - Learning Management System ']") WebElement headerTitle;
	
	public boolean verifyHeaderTitle(String expected) {
		
		if (getText(headerTitle).equals(expected)) {
			return true;
		}

		return false;
	}
	public boolean isDashboardLoaded() {
	    Assert.assertTrue(  headerBar.isDisplayed(), "Dashboard menu bar is not displayed as expected.");
	    return true;
	}

	public void navigateToUrlAndVerifyTitle(String url, String expectedTitle, Integer timeoutMs) {
	    int timeout = (timeoutMs != null) ? timeoutMs : DEFAULT_TIMEOUT_MS;

	    try {
	        // Navigate to the URL
	        driver.navigate().to(url);
	        
	        // Create a WebDriverWait instance for the entire timeout period
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout / 1000));

	        // Wait until the URL is loaded
	        boolean isUrlLoaded = wait.until(ExpectedConditions.urlToBe(url));
	        if (!isUrlLoaded) {
	            Assert.fail("Failed to load URL: " + url + " within " + timeout + " milliseconds.");
	        }

	        // Wait for the LMS title to be visible
	        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//span[text()=' LMS - Learning Management System ']")));

	        // Check if the title matches the expected title
	        Assert.assertEquals(titleElement.getText(), expectedTitle,
	                "Expected title: '" + expectedTitle + "' but found: '" + titleElement.getText() + "'");

	        System.out.println("Navigation to " + url + " and title verification completed successfully.");

	    } catch (TimeoutException e) {
	        // Log details about the failure
	        Assert.fail("Navigation to " + url + " or visibility of LMS title failed within " + timeout + " milliseconds. " +
	                    "Error: " + e.getMessage());
	    }
	}



	public void broken_links() {
		//WebElement Link = driver.findElement(By.tagName("a"));
        List<WebElement> links = driver.findElements(By.tagName("a"));
        
        Iterator<WebElement> it = links.iterator();
        
        while(it.hasNext()){
            
        	LinkURL = it.next().getAttribute("href");
            
            System.out.println(LinkURL);
		//String LinkURL = Link.getAttribute("href");
		
		if (LinkURL==null || LinkURL.isEmpty()) {
			System.out.println("URL is either not configurerd for anchor tag or it is empty");
			//LoggerLoad.info("URL is either not configurerd for anchor tag or it is empty");
		}
		
		try {
            huc = (HttpURLConnection)(new URL(LinkURL).openConnection());
            
            huc.setRequestMethod("HEAD");
            
            huc.connect(); 
            
            respCode = huc.getResponseCode();
            
            if(respCode >= 400){
                System.out.println(LinkURL+" is a broken link");
            }
            else{
                System.out.println(LinkURL+" is a valid link");
            }
		} catch (MalformedURLException e) {
          
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
	}
	}
	
	public void titleCheck(String expectedTitle) {
		Point location = headerTitle.getLocation();
		Assert.assertTrue(isNearTopLeftCorner(location), "Title is not in the top left corner.");
 
	}
	private boolean isNearTopLeftCorner(Point location) {
	    int xThreshold = 50; // Define a threshold for x-axis
	    int yThreshold = 50; // Define a threshold for y-axis
	    return (location.getX() <= xThreshold && location.getY() <= yThreshold);
	}
	public void expectedTitleCheck(String expectedTitle) {
		
		String actualTitle = headerTitle.getText();
		Assert.assertEquals(actualTitle, expectedTitle, "Title does not match.");
		 System.out.println("The title '" + actualTitle + "' matches the expected title '" + expectedTitle + "'.");
		
	}
	public void NavigationBarText() {
       
        
		List<WebElement> buttons = headerBar.findElements(By.xpath("//div[@class='ng-star-inserted']//button"));
		    Assert.assertTrue(buttons.size() > 0, "Navigation buttons are not present in the navbar");
		    for (WebElement button : buttons) {
		        String buttonText = button.getText().trim();
		        Assert.assertFalse(buttonText.isEmpty(), "Navigation button text is missing for one of the buttons.");
		        System.out.println("Button text: " + buttonText);
		    }
}
	public void correctSpellingAndSpacingInTitle() {
        String actualTitle = headerTitle.getText();
        String expectedTitle = "LMS - Learning Management System";
       Assert.assertEquals(actualTitle, expectedTitle, "The title does not match the expected spelling or spacing.");
    }
	//Single button position checking
//	 public void home() {
//		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		    List<WebElement> buttons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
//		        By.xpath("//div[@class='ng-star-inserted']//button")));
//		    
//		    if (!buttons.isEmpty()) {
//		        String firstButtonText = buttons.get(0).getText();
//		        Assert.assertEquals(firstButtonText, "Program", "The first button is not 'Program'.");
//		    } else {
//		        System.out.println("No buttons found. Expected 'Program' button in the 1st position.");
//		    }
//		}
//	 
	 public void verifyButtonOrder() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    List<WebElement> buttons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
		        By.xpath("//div[@class='ng-star-inserted']//button")));

		   
		    List<String> expectedOrder = Arrays.asList("Program", "Batch", "Class", "Logout");

		    
		    for (int i = 0; i < expectedOrder.size(); i++) {
		        if (i < buttons.size()) {
		            String actualButtonText = buttons.get(i).getText();
		            Assert.assertEquals(actualButtonText, expectedOrder.get(i), 
		                "Button at position " + (i + 1) + " is not as expected.");
		        } else {
		            Assert.fail("Expected more buttons but found only " + buttons.size());
		        }
		    }
		}
}