Feature: Verify valid register functionality

  Scenario: Verify registration with valid credentials
    Given click register button
    And register page displayed

    When register with data
      | UserName        | Dumitrd           |
      | Password        | Bee723ee#         |
      | ConfirmPassword | Bee723ee#         |
      | Email           | dumitrd@gmail.com |
    Then profile page displayed