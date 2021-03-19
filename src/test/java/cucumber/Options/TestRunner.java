package cucumber.Options;

import org.junit.Before;
import org.junit.runner.RunWith;

import apitest.Utility.Init;
import io.cucumber.junit.CucumberOptions;
import io.restassured.RestAssured;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/apitest/cucumber/features" , glue = "apitest.cucumber.stepdefinition" , plugin = "json:target/jsonReports/cucumber-report.json")
public class TestRunner extends Init{

	
}
 