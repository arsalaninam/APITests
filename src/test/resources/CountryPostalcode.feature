Feature: Write API Test for http://api.zippopotam.us/{country}/{postal-code}


  Scenario Outline: Verify that the response status code is 200 and content type is JSON.
    Given postalCode api endpoint is up
    When user sends a get request to postalcode endpoint with following details
      | country    | <country>    |
      | postalCode | <postalCode> |
    Then verify response <statusCode> and <contentType> from postalcode endpoint response
    Examples:
      | country | postalCode | statusCode | contentType        |
      | us      | 90210      | 200        | "application/json" |
      | us      | 12345      | 200        | "application/json" |
      | ca      | B2R        | 200        | "application/json" |


  Scenario Outline: Verify that the response time is below 1s
    Given postalCode api endpoint is up
    When user sends a get request to postalcode endpoint with following details
      | country    | <country>    |
      | postalCode | <postalCode> |
    Then verify response time of postalcode endpoint is below 1 second
    Examples:
      | country | postalCode |
      | us      | 90210      |
      | us      | 12345      |
      | ca      | B2R        |


  Scenario Outline: Verify in Response - Place Name for each input Country and Postal Code
    Given postalCode api endpoint is up
    When user sends a get request to postalcode endpoint with following details
      | country    | <country>    |
      | postalCode | <postalCode> |
    Then verify placeName for input country and postalcode from postalcode endpoint response
      | placeName | <placeName> |
    Examples:
      | country | postalCode | placeName     |
      | us      | 90210      | Beverly Hills |
      | us      | 12345      | Schenectady   |
      | ca      | B2R        | Waverley      |