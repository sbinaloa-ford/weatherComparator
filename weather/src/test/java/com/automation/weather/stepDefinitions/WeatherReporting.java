package com.automation.weather.stepDefinitions;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import com.automation.weather.Base;
import com.automation.weather.pageObjects.Locators;
import com.automation.weather.pageObjects.RestLogic;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WeatherReporting extends Base{
	
	Locators locators = new Locators();
	RestLogic logic = new RestLogic();
	
	@Given("user is on weather Home page")
	public void user_is_on_weather_Home_page() {
		getDriver();
	    driver.get(getWeatherUIUrl());
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
	    assertTrue(driver.getTitle().contentEquals("National and Local Weather Radar, Daily Forecast, Hurricane and information from The Weather Channel and weather.com"));
	    assertTrue(locators.getWeatherPageTitle().isDisplayed());
	}

	@Given("user is able to see search field")
	public void user_is_able_to_see_search_field() {
	    assertTrue(locators.getSearchField().isDisplayed());
	}

	@When("user enters city name in the search field")
	public void user_enters_city_name_in_the_search_field() {
		locators.waitForSearchElement();
		locators.getSearchField().click();
	    locators.getSearchField().sendKeys(getCityName());
	}

	@When("user selects the searched city from the list")
	public void user_selected_the_searched_city() {
	    if(locators.getLocationList().isDisplayed()) {
	    	locators.getselectedCity().click();
	    }
	}

	@Then("user collects the weather data")
	public void user_collects_the_weather_data() {
	    if(locators.getSelectedCityTemperature().isDisplayed()){
	    	String temp = locators.getSelectedCityTemperature().getText();
	    	weatherUiTemp = Float.parseFloat(temp.substring(0, temp.length()-1));
	    }
	}
	
	@Then("close the web page")
	public void close_the_web_page() {
	    driver.close();
	}

	@When("user makes the weather api for the selected city and validate the response")
	public void user_makes_the_weather_api_for_the_selected_city() {
		logic.getCityWeather();
	}

	@Then("collect the weather data from the response")
	public void validate_the_response_status_code() {
	    logic.getTemp();
	}

	@Then("verify the temp data from both phases")
	public void verify_the_temp_data_from_both_phases() {
	    logic.comparator();
	}
}

