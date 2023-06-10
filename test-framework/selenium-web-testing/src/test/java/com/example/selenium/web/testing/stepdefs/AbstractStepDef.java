package com.example.selenium.web.testing.stepdefs;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractStepDef {

    protected static WebDriver webDriver;

    private static long WAIT_TIME = 100;

    private WebDriverWait wait;

    void waitVisibility(WebElement element) {
        wait = new WebDriverWait(webDriver, Duration.ofMillis(WAIT_TIME));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    void waitUntilElementIsClickable(WebElement element) {
        wait = new WebDriverWait(webDriver, Duration.ofMillis(WAIT_TIME));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void implicitlyWait() {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(WAIT_TIME));
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean>
            expectation = driver -> ((JavascriptExecutor) Objects.requireNonNull(driver)).executeScript(
                "return document.readyState")
            .toString()
            .equals("complete");
        try {
            TimeUnit.MILLISECONDS.sleep(WAIT_TIME);
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofMillis(WAIT_TIME));
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
}
