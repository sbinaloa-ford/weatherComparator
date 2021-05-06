package com.automation.weather.pageObjects;

import java.util.Map;

import org.junit.Assert;

import com.automation.weather.Base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestLogic extends Base{

	Response response;
	
	public void getCityWeather() {
		 RequestSpecification request = RestAssured.given();
		 request.baseUri(getWeatherRestUrl());
		 request.basePath("/weather");
		 response = request.queryParam("q", getCityName()) 
		                    .queryParam("appid", getApiKey()) 
		                    .get();
		 
		 String jsonString = response.asString();
		 Assert.assertEquals(jsonString.contains(getCityName()), true);
		 Assert.assertEquals(response.getStatusCode(),200);
	}
	
	public void getTemp() {
		 Map<String, String> main = response.jsonPath().getMap("main");
		 restTemp = Float.parseFloat(String.valueOf(main.get("temp")));
		 restTemp = (float) (restTemp - 273.15);
	}
	
	public void comparator() {
		double variance = getVariance()/100;
		double diff = variance * restTemp;
		double maxValue = restTemp + diff;
		double minValue = restTemp - diff;
		
		Assert.assertTrue("Difference in Temperatures is more than variance",minValue <= weatherUiTemp && weatherUiTemp <= maxValue);
	}

}
