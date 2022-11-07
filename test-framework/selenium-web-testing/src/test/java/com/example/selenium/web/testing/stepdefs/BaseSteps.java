package com.example.selenium.web.testing.stepdefs;

import com.example.selenium.web.testing.utils.ScenarioContext;
import lombok.Getter;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Getter
public class BaseSteps extends AbstractStepDef {

    @Lazy
    @Autowired
    private ScenarioContext scenarioContext;

    protected SoftAssertions softAssert() {
        return getScenarioContext().getSoftAssertions();
    }

}
