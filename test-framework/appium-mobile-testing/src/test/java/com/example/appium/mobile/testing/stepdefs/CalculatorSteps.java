package com.example.appium.mobile.testing.stepdefs;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.net.MalformedURLException;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;

@Slf4j
public class CalculatorSteps extends BaseSteps {

    @Value("${appium.sleep}")
    private Long timeToSleep;

    private AppiumDriver driver;
    private WebElement element;

    @When("launch the application")
    public void shouldLaunchTheApplication() throws MalformedURLException {
        log.info("Running: launch the application at " + new Date());
        driver = getDriver();
    }

    @When("click on '{}' button")
    public void shouldClickInCategory(String buttonName) throws InterruptedException {
        log.info("Running: click on " + buttonName + " " + new Date());
        switch (buttonName) {
            case "Converter":
                element = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_handle_btn_converter"));
                break;
            case "0":
                element = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_00"));
                break;
            case "1":
                element = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_01"));
                break;
            case "2":
                element = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_02"));
                break;
            case "3":
                element = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_03"));
                break;
            case "4":
                element = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_04"));
                break;
            case "5":
                element = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_05"));
                break;
            case "6":
                element = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_06"));
                break;
            case "7":
                element = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_07"));
                break;
            case "8":
                element = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_08"));
                break;
            case "9":
                element = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_09"));
                break;
            case "+":
                element = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_add"));
                break;
            case "+/-":
                element = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_plusminus"));
                break;
            case "()":
                element = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_parenthesis"));
                break;
            case "=":
                element = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_equal"));
                break;
            default:
                throw new NoSuchElementException("No such element defined.");
        }
        waitForElement(element).click();
        Thread.sleep(timeToSleep);
    }

    @Then("page '{}' is displayed")
    public void pageDisplayed(String pageName) {
        switch (pageName) {
            case "Converter":
                element = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/converter_text_title"));
                waitForElement(element);
                break;
            default:
                throw new NoSuchElementException("No such page defined.");
        }
        Assert.assertTrue(element.isDisplayed());
    }

    @Then("the result is '{}'")
    public void resultIs(String result) {
        element = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_edt_formula"));
        waitForElement(element);
        Assert.assertEquals(element.getText(), result);
    }
}
