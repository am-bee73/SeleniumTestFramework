package com.example.appium.mobile.testing.stepdefs;

import com.example.appium.mobile.testing.service.AppiumService;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

@Slf4j
public class Hooks extends BaseSteps {

    @Autowired
    private AppiumService appiumService;

    @Value("${screenshot.path}")
    private Path path;

    private AppiumDriver driver;

    @Before
    public void driverInit() throws MalformedURLException {
        if (driver == null) {
            driver = new AndroidDriver(new URL(appiumServer), appiumService.getCapabilities());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(appiumWait));
        }
    }

    @After
    public void driverQuit() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Before
    public void loggingBeforeScenarioDetails(Scenario scenario) {
        log.info(String.format("Starting scenario [%s] from [%s]", scenario.getName(), getScenarioLocation(scenario)));
    }

    @After
    public void loggingAfterScenarioDetails(Scenario scenario) {
        log.info(String.format("Ending scenario [%s] from [%s]", scenario.getName(), getScenarioLocation(scenario)));
    }

    @AfterStep(order = 0)
    public void takeScreenshot(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            LocalDateTime localDateTime = LocalDateTime.now();
            String time = StringUtils.replace(String.valueOf(localDateTime), ":", "-");
            byte[] screenshotData = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            File sourceFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            FileCopyUtils.copy(sourceFile, this.path.resolve(scenario.getName() + "[" + time + "].png").toFile());

            scenario.attach(screenshotData, "image/png", scenario.getName());
            log.info("Screenshot is attached");
        }
    }

    private String getScenarioLocation(Scenario scenario) {
        return scenario.getUri().toString().replaceAll("^.*features/(?=.*.feature)", "");
    }
}
