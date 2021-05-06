Feature: List of Scenarios to get the current weather data from two sources and then compare them 

 Scenario: Collect the weather data for the city from the weather site
	Given user is on weather Home page
	And user is able to see search field
	When user enters city name in the search field
	And user selects the searched city from the list
	Then user collects the weather data
	Then close the web page
	
 Scenario: Collect the weather data for the city from the openWeather url
	When user makes the weather api for the selected city and validate the response
	Then collect the weather data from the response
	
 Scenario: Compare the temperature info from UI and api
 	Then verify the temp data from both phases