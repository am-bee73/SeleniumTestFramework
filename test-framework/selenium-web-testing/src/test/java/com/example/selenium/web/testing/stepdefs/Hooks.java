package com.example.selenium.web.testing.stepdefs;

import com.example.selenium.web.testing.log.LogUtil;
import com.example.selenium.web.testing.utils.screenshot.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;

@Slf4j
public class Hooks extends AbstractStepDef {

    @Lazy
    @Autowired
    private ApplicationContext applicationContext;

    @Lazy
    @Autowired
    private ScreenshotUtils screenshotUtils;

    @Value("${application.url}")
    private String appUrl;


    @Before
    public void initDriver() {
        WebDriver driver = this.applicationContext.getBean(WebDriver.class);
        driver.navigate().to(appUrl);
        driver.manage().window().maximize();
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
    public void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.attach(this.screenshotUtils.getScreenshot(), "image/png", scenario.getName());
            log.info("Screenshot is attached");
        }
    }

    @AfterStep(order = 1)
    public void logging(Scenario scenario) {
        if (scenario.isFailed()) {
            WebDriver driver = this.applicationContext.getBean(WebDriver.class);
            scenario.attach(String.valueOf(LogUtil.getLogs(driver).toJson()), "json", scenario.getName());
            log.info("Log file is attached");
        }
    }

//    @After(order = 2)
//    public void tearDown() {
//        if (((RemoteWebDriver) this.applicationContext.getBean(WebDriver.class)).getSessionId() != null) {
//            this.applicationContext.getBean(WebDriver.class).quit();
//            this.applicationContext.getBean(WebDriver.class).close();
//            log.info("Close driver.");
//        }
//    }

    private String getScenarioLocation(Scenario scenario) {
        return scenario.getUri().toString().replaceAll("^.*features/(?=.*.feature)", "");
    }
}