Assignment for UI Test Automation

Dependency: Java,Maven

Libraries used: Selenium, Cucumber, MasterThought  Reporting, Rest Assured, Json-simple

Run  from cmd:
Go to root folder of project : weather
And then run command mvn test

If there is a problem from cmd:
Go to weather/src/test/java/com/automation/weather/runner/TestRunner.java and run as junit



For reporting, go to target folder, see html , json or xml reporting. Open the html report in browser


Also to check for any other country , you need to go to com.automation.resoucres folder and update the city name in json
If you want to run for multiple cities, then you can go for scenario_outline and need to tweak  little bit
