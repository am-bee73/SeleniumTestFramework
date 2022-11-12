package com.example.appium.mobile.testing;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;


@CucumberContextConfiguration
@SpringBootTest(classes = {MobileTestingApplication.class})
public class CucumberSpringConfiguration {

}
