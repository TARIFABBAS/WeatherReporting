Feature: Weather Report Comparator

  Scenario Outline: Compare weather report from two sources
    Given user open <browser> browser
    And user navigate to "https://www.accuweather.com"
    When user search for location <location>
    Then user verify search result location <location>
    And user fetch all the weather details
    And user fetch weather details from "http://api.openweathermap.org" for location <location>
    Then user compares weather info from UI and API on basis of temperature with threshold value <temp variance> <humidity variance>

    Examples:
      | browser | location  | temp variance | humidity variance |
      | CHROME  | bangalore | 2             | 10                |
      | FIREFOX | bangalore | 4             | 10                |