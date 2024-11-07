package com.nutrisense.web.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.nutrisense.web.steps",
    plugin = {"pretty", "html:target/cucumber-html-report.html"},
    monochrome = true
)
public class TestRunner {
}
