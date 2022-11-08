package com.example.selenium.web.testing.stepdefs;

import static com.example.selenium.web.testing.enums.ErrorMessages.EXISTING_EMAIL_ERROR;
import static com.example.selenium.web.testing.enums.ErrorMessages.EXISTING_USERNAME_ERROR;
import static com.example.selenium.web.testing.utils.CucumberUtils.processNullsAndEmpties;

import com.example.selenium.web.testing.actions.ProfileActions;
import com.example.selenium.web.testing.actions.RegisterActions;
import com.example.selenium.web.testing.enums.ErrorMessages;
import com.example.selenium.web.testing.enums.StorageKey;
import com.example.selenium.web.testing.utils.screenshot.TakeScreenshot;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

@Slf4j
public class RegisterStepDefs extends BaseSteps {

    @Autowired
    private RegisterActions registerActions;

    @Autowired
    private ProfileActions profileActions;

    @TakeScreenshot
    @When("register with data")
    public void registerWithData(DataTable dataTable) {
        Map<String, String> dataTableMap = dataTable.asMap(String.class, String.class);
        Map<String, String> registrationData = processNullsAndEmpties(dataTableMap);

        registerActions.registerUser(
            registrationData.get("UserName"),
            registrationData.get("Password"),
            registrationData.get("ConfirmPassword"),
            registrationData.get("Email")
        );

        getScenarioContext().addToStorage(String.class, registrationData.get("UserName"));
        getScenarioContext().addToStorage(StorageKey.EMAIL, registrationData.get("Email"));
        getScenarioContext().addToStorage(StorageKey.USERNAME, registrationData.get("UserName"));
    }

    @TakeScreenshot
    @Then("profile page displayed")
    public void profilePageDisplayed() {
        String username = getScenarioContext().getLastFromStorage(String.class);
        log.info(String.format("Profile page for %s user should be displayed", username));

        WebElement rightNavBar = profileActions.getRightNavBar();
        String expectedXpath = String.format("//a[contains(text(),'Hello %s!')]", username);

        Assert.assertNotNull(rightNavBar, "Nav bar element not found");

        softAssert()
            .assertThat(rightNavBar.findElement(By.xpath(expectedXpath)).isDisplayed())
            .describedAs("Profile page is not displayed")
            .isTrue();
    }

    @Then("warning message displayed")
    public void warningMessageDisplayed() {
        Assert.assertTrue(
            registerActions.warningsDisplayed(), "Warnings are not displayed");
        registerActions.warningsDisplayed();
    }

    @Then("error message '{}' is displayed")
    public void errorMessageIsDisplayed(ErrorMessages error) {
        String expectedErrorMessage;

        if (error.equals(EXISTING_USERNAME_ERROR)) {
            String username = (String) getScenarioContext().getFromStorage(StorageKey.USERNAME);
            expectedErrorMessage = String.format(error.getErrorMessage(), username);
        } else if (error.equals(EXISTING_EMAIL_ERROR)) {
            String email = (String) getScenarioContext().getFromStorage(StorageKey.EMAIL);
            expectedErrorMessage = String.format(error.getErrorMessage(), email);
        } else {
            expectedErrorMessage = error.getErrorMessage();
        }

        String actualErrorMessage = registerActions.getRegisterErrorMessages();

//        softAssert()
//            .assertThat(expectedErrorMessage)
//            .describedAs("Error messages are not displayed correctly.")
//            .isEqualTo(actualErrorMessage);

        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}
