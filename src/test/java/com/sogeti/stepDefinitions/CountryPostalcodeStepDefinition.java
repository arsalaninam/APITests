package com.sogeti.stepDefinitions;

import com.sogeti.businesslayer.product.countryStateCity.CountryPostalcodeBusinessLogic;
import com.sogeti.pojo.countryPostalcode.Postalcode;
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

public class CountryPostalcodeStepDefinition extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(CountryPostalcodeStepDefinition.class);

    private RequestSpecification requestSpecification;
    private Response response;
    private Postalcode postalcode;

    @Given("postalCode api endpoint is up")
    public void postalCodeApiEndpointIsUp() {
        log.info(BASE_SETUP);
        requestSpecification = new RequestSpecBuilder().
                setBaseUri(prop.getProperty(BASE_URL)).
                build();
    }

    @When("user sends a get request to postalcode endpoint with following details")
    public void userSendsGetRequestToPostalCodeEndpoint(Map<String, String> data) {
        log.info(HIT_GET_ENDPOINT);
        String country = data.get("country");
        String postalCode = data.get("postalCode");
        String pathParameters = country + "/" + postalCode;

        response = given().
                spec(requestSpecification).
                when().
                get(pathParameters);

        postalcode = CountryPostalcodeBusinessLogic.getPostalCodeDetails(pathParameters);
    }

    @Then("verify response {int} and {string} from postalcode endpoint response")
    public void verifyResponseCodeAndContentTypeFromPostalCodeEndpoint(int expectedStatusCode, String expectedContentType) {
        log.info(VERIFY_STATUS_CODE_200_AND_CONTENT_TYPE_JSON);
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
        Assert.assertEquals(expectedContentType, response.getContentType());
    }

    @Then("verify response time of postalcode endpoint is below {int} second")
    public void verifyResponseTimeOfPostalCodeEndpoint(int responseTime){
        log.info(VERIFY_RESPONSE_TIME_IS_BELOW_1_SECOND);
        long responseTimeInMilliSeconds = responseTime * 1000;
        response.then().time(Matchers.lessThan(responseTimeInMilliSeconds));
    }

    @Then("verify placeName for input country and postalcode from postalcode endpoint response")
    public void verifyPlaceNameForInputCountryAndPostalCodeFromPostalCodeEndpoint(Map<String,String> data){
        log.info(VALIDATE_RESPONSE_BODY);

        String expectedPlaceName = data.get("placeName");

        List<String> placeNameList = new ArrayList<>();

        for (int i = 0; i < postalcode.getPlaces().size(); i++) {
            if (postalcode.getPlaces().get(i).getPlaceName().equalsIgnoreCase(expectedPlaceName)) {
                placeNameList.add(postalcode.getPlaces().get(i).getPlaceName());
            }
        }
        Assert.assertTrue(placeNameList.contains(expectedPlaceName));
    }
}
