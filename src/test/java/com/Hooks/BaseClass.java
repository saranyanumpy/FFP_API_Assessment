package com.lms.hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.lms.driverManager.WebDriverFactory;
import com.lms.utilities.ApplicationData;
import com.lms.utilities.ConfigReader;
import com.lms.utilities.ExcelReader;
import static com.lms.utilities.LMSUIConstants.*;

import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks {

	WebDriverFactory wd = WebDriverFactory.getInstance();

	@BeforeAll
	public static void before_all() {
		ApplicationData appData = new ApplicationData();
		ConfigReader.load_prop();
		appData.setModuleNameTestDataMap(ExcelReader.loadExcelData());
		applicationData = appData;
	}

	@Before
	public void setUp() {

		if (wd.getDriver() == null) {
			wd.initializeDriver(ConfigReader.getProp("browser"));
			System.out.println("driver details --->" + wd.getDriver().toString());
		}
	}

	@AfterStep
	public void addScreenShot(Scenario scenario) {
		if (scenario.isFailed()) {
			String ScreenShotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) wd.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", ScreenShotName);
		}
	}

	@AfterAll
	public static void after() {

		// TestDataCleanup();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverFactory.getInstance().closeDriver();
		System.out.println("driver closed");

		// private void TestDataCleanup(){
		// }

	}
}
