package com.sogeti.stepDefinitions;

import com.sogeti.businesslayer.product.countryStateCity.CountryStateCityBusinessLogic;
import com.sogeti.pojo.countryStateCity.Country;
import com.sogeti.util.PropertyReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.sogeti.constant.ScenarioNameConstant.*;
import static com.sogeti.constant.ServiceConstant.BASE_URL;
import static io.restassured.RestAssured.given;

public class CountryStateCityStepDefinition extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(CountryStateCityStepDefinition.class);

    private RequestSpecification requestSpecification;
    private Response response;
    private Country countryResponsePojo;

    @Given("city api endpoint is up")
    public void cityApiEndpointIsUp() {
        log.info(BASE_SETUP);
        requestSpecification = new RequestSpecBuilder().
                setBaseUri(prop.getProperty(BASE_URL)).
                build();
    }

    @When("user sends a get request to city api endpoint with following details")
    public void userSendsGetRequestToCityApiEndpoint(Map<String, String> data) {
        log.info(HIT_GET_ENDPOINT);
        String countryName = data.get("country");
        String stateName = data.get("state");
        String cityName = data.get("city");

        String pathParameters = countryName + "/" + stateName + "/" + cityName;
        response = given().
                spec(requestSpecification).
                when().
                get(pathParameters);
        countryResponsePojo = CountryStateCityBusinessLogic.getCountryDetails(pathParameters);
    }

    @Then("verify response {int} and {string} from city api endpoint response")
    public void verifyResponseCodeAndContentTypeOfCityApiEndpoint(int expectedStatusCode, String expectContentType) {
        log.info(VERIFY_STATUS_CODE_200_AND_CONTENT_TYPE_JSON);
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
        Assert.assertEquals(expectContentType, response.getContentType());
    }

    @Then("verify response time is below {int} second of city api endpoint response")
    public void verifyResponseTimeOfCityApiEndpoint(int responseTime) {
        log.info(VERIFY_RESPONSE_TIME_IS_BELOW_1_SECOND);
        long responseTimeInMilliSeconds = responseTime * 1000;
        response.then().time(Matchers.lessThan(responseTimeInMilliSeconds));
    }

    @Then("verify country name and state name from city api endpoint response")
    public void verifyCountryNameAndStateNameFromCityApiEndpointResponse(Map<String, String> data) {
        log.info(VALIDATE_RESPONSE_BODY);
        String expectedCountryName = data.get("countryName");
        String expectedStateName = data.get("stateName");
        Assert.assertEquals(expectedCountryName, countryResponsePojo.getCountry());
        Assert.assertEquals(expectedStateName, countryResponsePojo.getState());
    }

    @Then("Verify post code and place name from city api endpoint response")
    public void verifyPostCodeAndPlaceNameFromCityApiEndpointResponse(Map<String, String> data) {
        log.info(VALIDATE_RESPONSE_BODY);
        String expectedPostCode = data.get("postCode");
        String expectedPlaceName = data.get("placeName");

        List<String> placeNameList = new ArrayList<>();

        for (int i = 0; i < countryResponsePojo.getPlaces().size(); i++) {
            if (countryResponsePojo.getPlaces().get(i).getPostCode().equalsIgnoreCase(expectedPostCode)) {
                placeNameList.add(countryResponsePojo.getPlaces().get(i).getPlaceName());
            }
        }
        Assert.assertTrue(placeNameList.contains(expectedPlaceName));
    }

}
