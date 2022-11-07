package com.example.selenium.web.testing.log;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class LogUtil {

    public static LogEntries getLogs(WebDriver driver) {
        return driver
            .manage()
            .logs()
            .get(LogType.BROWSER);
    }
}
