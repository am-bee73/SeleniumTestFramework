package com.example.selenium.web.testing.pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ProfilePage extends Base{

    @FindBy(how = How.XPATH, using = "//body/div[1]/div[1]/div[2]/form[1]/ul[1]")
    WebElement rightNavBar;


}
