package com.automation.weather.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.weather.Base;

public class Locators extends Base{

	By weatherPageTitle = By.xpath("//*[@name='twc-logo']");
	By searchField = By.id("LocationSearch_input");
	By locationList = By.id("LocationSearch_listbox");
	By selectedCityTemperature = By.xpath("//*[@class='CurrentConditions--tempValue--3KcTQ']");
	By selectedCity = By.id("LocationSearch_listbox-0");
	
	public WebElement getWeatherPageTitle() {
		return driver.findElement(weatherPageTitle);
	}
	
	public WebElement getSearchField() {
		return driver.findElement(searchField);
	}
	
	public WebElement getLocationList() {
		return driver.findElement(locationList);
	}
	
	public WebElement getSelectedCityTemperature() {
		return driver.findElement(selectedCityTemperature);
	}
	
	public WebElement getselectedCity() {
		return driver.findElement(selectedCity);
	}
	
	public void waitForSearchElement() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(searchField));
	}
}
