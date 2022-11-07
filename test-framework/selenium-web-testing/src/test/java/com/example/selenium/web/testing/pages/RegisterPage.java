package com.example.selenium.web.testing.pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Getter
@Component
public class RegisterPage extends Base {

    @FindBy(how = How.XPATH, using = "//h4[contains(text(),'Create a new account.')]")
    private WebElement createNewAccount;

    @FindBy(how = How.ID, using = "UserName")
    private WebElement username;

    @FindBy(how = How.ID, using = "Password")
    private WebElement password;

    @FindBy(how = How.ID, using = "ConfirmPassword")
    private WebElement confirmPassword;

    @FindBy(how = How.ID, using = "Email")
    private WebElement email;

    @FindBy(how = How.XPATH, using = "//body/div[2]/form[1]/div[6]/div[1]/input[1]")
    private WebElement registerButton;

    @FindBy(how = How.XPATH, using = "//body[1]/div[2]/form[1]/div[1]/ul[1]")
    private WebElement warningList;

}