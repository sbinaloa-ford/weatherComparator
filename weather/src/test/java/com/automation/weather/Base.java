package com.automation.weather;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

	public static Properties properties;
	private final String propertyFilePath= "src/test/java/com/automation/weather/global.properties";
	public static WebDriver driver;
	public static Float weatherUiTemp;
	public static Float restTemp;
	
	public Base(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
	
	public static void getDriver() {
		switch(properties.getProperty("driver")) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", properties.getProperty("chromeDriverPath"));
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", properties.getProperty("firefoxDriverPath"));
			driver = new FirefoxDriver();
			break;
		}	
	}
	
	public static String getWeatherUIUrl() {
		return properties.getProperty("weatherUIUrl");		
	}
	
	public static String getWeatherRestUrl() {
		return properties.getProperty("weatheRestUrl");		
	}
	
	public static String getApiKey() {
		return properties.getProperty("apiKey");		
	}
	
	public static String getInputJson() {
		return properties.getProperty("inputJson");		
	}
	
	public static String getCityName() {
		 JSONParser jsonParser = new JSONParser();
		 JSONArray obj = new JSONArray() ;
         
	        try (FileReader reader = new FileReader(getInputJson()))
	        {
	          JSONObject object = (JSONObject) jsonParser.parse(reader);
	          obj = (JSONArray) object.get("City");
	 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return (String) obj.get(0);
	}
	
	public static double getVariance() {
		 JSONParser jsonParser = new JSONParser();
		 JSONObject obj = null ;
        
	        try (FileReader reader = new FileReader(getInputJson()))
	        {
	          obj = (JSONObject) jsonParser.parse(reader);
	 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return (long) obj.get("Variance");
	}
	
}
