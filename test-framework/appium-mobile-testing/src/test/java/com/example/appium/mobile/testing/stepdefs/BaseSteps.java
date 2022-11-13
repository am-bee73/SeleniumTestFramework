package com.example.appium.mobile.testing.stepdefs;

import com.example.appium.mobile.testing.MobileTestingApplication;
import com.example.appium.mobile.testing.service.AppiumService;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = MobileTestingApplication.class)
public class BaseSteps {

    @Value("${appium.server}")
    protected String appiumServer;
    @Value("${appium.wait}")
    protected Long appiumWait;
    @Value("${appium.timeout}")
    private Long appiumTimeout;
    @Value("${device.platform}")
    protected String devicePlatform;

    @Autowired
    private AppiumService appiumService;
    protected AppiumDriver driver;

    public AppiumDriver getDriver() throws MalformedURLException {
        if (driver == null) {
            switch (devicePlatform) {
                case "Android":
                    driver = new AndroidDriver(new URL(appiumServer), appiumService.getCapabilities());
                    break;
                case "iOS":
                    driver = new IOSDriver(new URL(appiumServer), appiumService.getCapabilities());
                    break;
            }
            Objects.requireNonNull(driver).manage().timeouts().implicitlyWait(Duration.ofSeconds(appiumWait));
        }
        return driver;
    }

    public WebElement waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(appiumTimeout));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    DesiredCapabilities getCapabilities() {
        return appiumService.getCapabilities();
    }
}
