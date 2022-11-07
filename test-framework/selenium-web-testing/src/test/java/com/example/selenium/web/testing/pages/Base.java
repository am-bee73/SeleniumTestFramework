package com.example.selenium.web.testing.pages;

import com.example.selenium.web.testing.log.LogUtil;
import javax.annotation.PostConstruct;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Getter
public class Base {

    @Lazy
    @Autowired
    protected LogUtil logUtil;

    @Autowired
    WebDriver driver;

    @PostConstruct
    public void initializePage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

}