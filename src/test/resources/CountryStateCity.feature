Feature: Write API Test for http://api.zippopotam.us/{country}/{state}/{city}


  Scenario Outline: Verify that the response status code is 200 and content type is JSON
    Given city api endpoint is up
    When user sends a get request to city api endpoint with following details
      | country | <country> |
      | state   | <state>   |
      | city    | <city>    |
    Then verify response <statusCode> and <contentType> from city api endpoint response
    Examples:
      | country | state | city      | statusCode | contentType        |
      | de      | bw    | stuttgart | 200        | "application/json" |


  Scenario Outline: Verify that the response time is below 1s.
    Given city api endpoint is up
    When user sends a get request to city api endpoint with following details
      | country | <country> |
      | state   | <state>   |
      | city    | <city>    |
    Then verify response time is below 1 second of city api endpoint response
    Examples:
      | country | state | city      |
      | de      | bw    | stuttgart |


  Scenario Outline: Verify From Response - Country name and State name
    Given city api endpoint is up
    When user sends a get request to city api endpoint with following details
      | country | <country> |
      | state   | <state>   |
      | city    | <city>    |
    Then verify country name and state name from city api endpoint response
      | countryName | <countryName> |
      | stateName   | <stateName>   |
    Examples:
      | country | state | city      | countryName | stateName         |
      | de      | bw    | stuttgart | Germany     | Baden-Württemberg |


  Scenario Outline: Verify in Response - Post code and Place name
    Given city api endpoint is up
    When user sends a get request to city api endpoint with following details
      | country | <country> |
      | state   | <state>   |
      | city    | <city>    |
    Then Verify post code and place name from city api endpoint response
      | postCode  | <postCode>  |
      | placeName | <placeName> |
    Examples:
      | country | state | city      | postCode | placeName           |
      | de      | bw    | stuttgart | 70597    | Stuttgart Degerloch |