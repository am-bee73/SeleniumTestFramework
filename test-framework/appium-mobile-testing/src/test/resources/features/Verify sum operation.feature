Feature: Verify sum operation


  Scenario Outline: Verify sum of two positive digits <First digit> + <Second digit>
    Given launch the application
    When click on '<First digit>' button
    And click on '+' button
    And click on '<Second digit>' button
    And click on '=' button
    Then the result is '<Result>'
    Examples:
      | First digit | Second digit | Result |
      | 1           | 2            | 3      |
      | 5           | 5            | 10     |
      | 51          | 5            | 56     |


  Scenario Outline: Verify sum of one negative -<First digit> and one positive <Second digit> digit
    Given launch the application
    When click on '+/-' button
    When click on '<First digit>' button
    When click on '()' button
    And click on '+' button
    And click on '<Second digit>' button
    And click on '=' button
    Then the result is '<Result>'
    Examples:
      | First digit | Second digit | Result |
      | 1           | 2            | 1      |
      | 5           | 5            | 0      |
      | 8           | 5            | -8     |
