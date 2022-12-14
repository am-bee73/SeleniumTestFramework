package com.example.appium.mobile.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-${platform:android}.properties")
public class MobileTestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileTestingApplication.class, args);
    }
}
