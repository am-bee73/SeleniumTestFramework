package com.example.appium.mobile.testing.service;

import javax.annotation.PostConstruct;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppiumService implements AppiumServiceInterface {

    @Value("${device.name}")
    private String deviceName;
    @Value("${device.platform}")
    private String devicePlatform;
    @Value("${application.package}")
    private String applicationPackage;
    @Value("${application.activity}")
    private String applicationActivity;

    private final DesiredCapabilities capabilities = new DesiredCapabilities();

    @PostConstruct
    public void setup() {
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformName", devicePlatform);
        capabilities.setCapability("appPackage", applicationPackage);
        capabilities.setCapability("appActivity", applicationActivity);
    }

    @Override
    public DesiredCapabilities getCapabilities() {
        return capabilities;
    }
}
