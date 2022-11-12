package com.example.appium.mobile.testing.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
    plugin = {"json:target/jsonReports/cucumber.json", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
    features = "src/test/resources/features",
    extraGlue = {"com.example.appium.mobile.testing"},
    tags = "not @ignore"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    public static Integer scenarios;

    @Override
    @Test(dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        super.runScenario(pickleWrapper, featureWrapper);
    }

    @DataProvider
    @Override
    public Object[][] scenarios() {
        scenarios = super.scenarios().length;
        return super.scenarios();
    }

    public TestRunner() {}
}
