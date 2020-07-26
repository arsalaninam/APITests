package com.sogeti.businesslayer.product.countryStateCity;

import com.sogeti.pojo.countryStateCity.Country;
import com.sogeti.util.PropertyReader;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sogeti.constant.ServiceConstant.BASE_URL;
import static io.restassured.RestAssured.when;

public class CountryStateCityBusinessLogic extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(CountryStateCityBusinessLogic.class);

    /**
     * Extract data as Response object
     * Populate Single Country Response POJO
     * e.g. http://api.zippopotam.us/{country}/{state}/{city}
     *
     */
    public static Country getCountryDetails(String pathParameters) {
        String baseUrl = prop.getProperty(BASE_URL);
        String url = baseUrl + pathParameters;
        log.info("URL to be hit : " + url);

        Response response = when().get(url);
        Country countryPojo = response.getBody().as(Country.class);
        log.info("Info : " + countryPojo);
        return countryPojo;
    }
}
