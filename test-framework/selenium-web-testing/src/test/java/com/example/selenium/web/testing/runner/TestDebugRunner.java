package com.example.selenium.web.testing.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
    plugin = {"json:target/jsonReports/cucumber.json","io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
    features = "src/test/resources/features",
    extraGlue = {"com.example.selenium.web.testing"},
    tags = "@Run"
)
public class TestDebugRunner extends AbstractTestNGCucumberTests {

}
