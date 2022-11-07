package com.example.selenium.web.testing.stepdefs;

import com.example.selenium.web.testing.actions.HomeActions;
import com.example.selenium.web.testing.actions.RegisterActions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;


public class HomeStepDefs {

    @Autowired
    private HomeActions homeActions;

    @Autowired
    private RegisterActions registerActions;

    @When("click register button")
    public void clickSignUpButton() {
        homeActions.clickRegisterButton();
    }

    @When("when login with username '{}' and password '{}'")
    public void whenLoginWithUsernameAndPassword(String username, String password) {
    }

    @Then("register page displayed")
    public void profilePageDisplayed() {
        Assert.assertTrue(registerActions.registerPageDisplayed());
    }
}
