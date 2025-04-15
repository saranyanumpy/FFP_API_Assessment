package com.lms.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "pretty",
		"html:target/Report.html" }, monochrome = false, tags = "", features = {
				"src/test/resources/features" }, glue = { "com.lms.stepDefinition", "com.lms.hooks" })

public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}

}
