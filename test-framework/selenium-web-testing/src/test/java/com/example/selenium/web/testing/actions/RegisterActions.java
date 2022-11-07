package com.example.selenium.web.testing.actions;

import com.example.selenium.web.testing.pages.ProfilePage;
import com.example.selenium.web.testing.pages.RegisterPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RegisterActions {

    @Autowired
    private RegisterPage registerPage;

    public boolean registerPageDisplayed() {
        boolean displayed = registerPage.getCreateNewAccount().isDisplayed();
        log.info("Register page displayed: " + displayed);
        return displayed;
    }

    public boolean warningsDisplayed() {
        boolean displayed = registerPage.getWarningList().isDisplayed();
        log.info("Warning list displayed: " + displayed);
        return displayed;
    }

    public String getRegisterErrorMessages() {
        return registerPage.getWarningList().getText();
    }

    public void registerUser(String username, String password, String confirmPassword, String email) {
        registerPage.getUsername().sendKeys(username);
        registerPage.getPassword().sendKeys(password);
        registerPage.getConfirmPassword().sendKeys(confirmPassword);
        registerPage.getEmail().sendKeys(email);

        registerPage.getRegisterButton().click();
    }
}
