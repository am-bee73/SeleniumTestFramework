package com.example.selenium.web.testing.utils;

import java.util.HashMap;
import java.util.Map;

public class CucumberUtils {

    public static Map<String, String> processNullsAndEmpties(Map<String, String> map) {
        Map<String, String> modifiableMap = new HashMap<>(map);
        modifiableMap.entrySet()
            .stream()
            .filter(e -> e.getValue() == null)
            .forEach(fe -> fe.setValue(""));

        modifiableMap.entrySet()
            .stream()
            .filter(e -> e.getValue().equals("null"))
            .forEach(fe -> fe.setValue(null));

        return modifiableMap;
    }
}