package com.example.selenium.web.testing;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;


@CucumberContextConfiguration
@SpringBootTest(classes = {WebTestingApplication.class})
public class CucumberSpringConfiguration {

}
