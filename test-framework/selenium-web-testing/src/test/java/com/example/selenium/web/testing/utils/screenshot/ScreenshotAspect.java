package com.example.selenium.web.testing.utils.screenshot;

import java.io.IOException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ScreenshotAspect {

    @Autowired
    private ScreenshotUtils screenshotUtils;

    @After("@annotation(takeScreenshot)")
    public void after(JoinPoint joinPoint, TakeScreenshot takeScreenshot) throws IOException {
        this.screenshotUtils.takeScreenshot(joinPoint.getSignature().getName());
    }
}
