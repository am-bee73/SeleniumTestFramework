package com.example.selenium.web.testing.utils.screenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Lazy
@Component
public class ScreenshotUtils {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${screenshot.path}")
    private Path path;

    public void takeScreenshot(String testName) throws IOException {
        File sourceFile = this.applicationContext.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.FILE);
        FileCopyUtils.copy(sourceFile, this.path.resolve(testName + ".png").toFile());
    }

    public byte[] getScreenshot() {
        return this.applicationContext.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.BYTES);
    }
}
