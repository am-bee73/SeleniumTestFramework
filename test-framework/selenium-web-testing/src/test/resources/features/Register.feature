Feature: Verify register functionality

  Scenario Outline: Verify registration <ErrorMessage> warning message
    Given click register button
    And register page displayed

    When register with data
      | UserName        | <UserName>        |
      | Password        | <Password>        |
      | ConfirmPassword | <ConfirmPassword> |
      | Email           | <Email>           |
    Then warning message displayed
    And error message '<ErrorMessage>' is displayed
    Examples:
      | UserName | Password | ConfirmPassword | Email             | ErrorMessage                                    |
      | Dumitru  | A123456a | A123456a        | test@gmail.com    | EXISTING_USERNAME_ERROR                         |
      | Alland   | A123456a | A123456a        | email@gmail.com   | EXISTING_EMAIL_ERROR                            |
      | All      | A123456a | A123456a        | test@gmail.com    | USERNAME_AT_LEAST_6_CHARACTERS_LONG             |
      | Alland   | 123      | 123             | test@gmail.com    | PASSWORD_AT_LEAST_6_CHARACTERS_LONG             |
      | Alland   | A123456a | A123456a        |                   | EMAIL_IS_REQUIRED                               |
      |          | A123456a | A123456a        | dumitrd@gmail.com | USERNAME_IS_REQUIRED                            |
      | Alland   |          |                 | dumitrd@gmail.com | PASSWORD_IS_REQUIRED                            |
      | Alland   | A123456a | A123456a        | dumitrd@gmail.com | PASSWORD_AND_CONFIRMATION_PASSWORD_DO_NOT_MATCH |
      | Alland   |          |                 | dumitrd@gmail.com | EXISTING_USERNAME_ERROR                         |
      | Alland   | A123456a | A123456a        | dumitrd@gmail.com | EXISTING_USERNAME_ERROR                         |


  Scenario: Verify registration with valid credentials
    Given click register button
    And register page displayed

    When register with data
      | UserName        | Dumitrd           |
      | Password        | Bee723ee#         |
      | ConfirmPassword | Bee723ee#         |
      | Email           | dumitrd@gmail.com |
    Then profile page displayed