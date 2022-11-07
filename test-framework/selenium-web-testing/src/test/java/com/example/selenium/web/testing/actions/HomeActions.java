package com.example.selenium.web.testing.actions;

import com.example.selenium.web.testing.pages.HomePage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HomeActions {

    @Autowired
    HomePage homePage;

    public void clickRegisterButton() {
        homePage.getRegisterLink().click();
    }
}
