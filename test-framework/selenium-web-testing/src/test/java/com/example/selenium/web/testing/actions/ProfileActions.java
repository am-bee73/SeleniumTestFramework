package com.example.selenium.web.testing.actions;

import com.example.selenium.web.testing.pages.ProfilePage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProfileActions {

    @Autowired
    private ProfilePage profilePage;

    public WebElement getRightNavBar() {
        return profilePage.getRightNavBar();
    }
}