package com.automation.weather.runner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.automation.weather.Base;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/com/automation/weather/features"
		,glue= {"com.automation.weather.stepDefinitions"}
		,plugin= {"pretty","json:target/cucumber.json", "junit:target/cucumber.xml"}
		,monochrome= true)

public class TestRunner extends Base{
	
	@BeforeClass
	public static void saveConfig() {
		new Base();
	}
	
	@AfterClass
	public static void generateReport() {
		File reportOutputDirectory = new File("target");
		List<String> jsonFiles = new ArrayList<String>();
		jsonFiles.add("target/cucumber.json");
		String projectName = "Bluestack-weather";
		String buildNumber = "1.0";

		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		configuration.setBuildNumber(buildNumber);
		configuration.addClassifications("User", "Shubanker");
		configuration.addClassifications("Branch", "master");
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		reportBuilder.generateReports();
	}
}