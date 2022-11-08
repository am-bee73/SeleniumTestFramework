package com.example.selenium.web.testing.enums;

public enum ErrorMessages {

    EXISTING_USERNAME_ERROR("Name %s is already taken."),
    EXISTING_EMAIL_ERROR("Email '%s' is already taken."),
    USERNAME_AT_LEAST_6_CHARACTERS_LONG("The UserName must be at least 6 characters long."),
    PASSWORD_AT_LEAST_6_CHARACTERS_LONG("The Password must be at least 6 characters long."),
    EMAIL_IS_REQUIRED("The Email field is required."),
    USERNAME_IS_REQUIRED("The UserName field is required."),
    PASSWORD_IS_REQUIRED("The Password field is required."),
    PASSWORD_AND_CONFIRMATION_PASSWORD_DO_NOT_MATCH("The password and confirmation password do not match."),
    ;
    private final String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
