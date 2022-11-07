package com.example.selenium.web.testing.pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Getter
@Component
public class HomePage extends Base {

    @FindBy(how = How.ID, using = "registerLink")
    private WebElement registerLink;

    @FindBy(how = How.ID, using = "loginLink")
    private WebElement loginButton;

}
