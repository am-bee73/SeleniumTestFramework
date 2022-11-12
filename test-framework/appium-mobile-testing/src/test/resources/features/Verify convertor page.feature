Feature: Verify convertor page

  Scenario Outline: Verify convertor page is displayed
    Given launch the application
    When click on '<Button>' button
    Then page '<Activity>' is displayed
    Examples:
      | Button    | Activity  |
      | Converter | Converter |