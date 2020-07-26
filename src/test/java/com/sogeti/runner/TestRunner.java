package com.sogeti.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = {"com.sogeti.stepDefinitions"}, features = "src/test/resources",
        plugin = {"pretty", "html:target/site/cucumber-pretty", "json:target/cucumber.json"})
public class TestRunner {

}
