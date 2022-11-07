package com.example.selenium.web.testing.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"json:target/jsonReports/cucumber.json","io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
    features = "src/test/resources/features",
    glue = {"com.example.selenium.web.testing"},
    tags = "@Run",
    monochrome = true
)
public class TestDebugRunner extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
