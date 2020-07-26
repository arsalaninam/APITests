package com.sogeti.businesslayer.product.countryStateCity;

import com.sogeti.pojo.countryPostalcode.Postalcode;
import com.sogeti.util.PropertyReader;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sogeti.constant.ServiceConstant.BASE_URL;
import static io.restassured.RestAssured.when;

public class CountryPostalcodeBusinessLogic extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(CountryPostalcodeBusinessLogic.class);

    /**
     * Extract data as Response object
     * Populate Single Postalcode Response POJO
     * e.g. http://api.zippopotam.us/{country}/{postal-code}
     *
     */
    public static Postalcode getPostalCodeDetails(String pathParameters) {
        String baseUrl = prop.getProperty(BASE_URL);
        String url = baseUrl + pathParameters;
        log.info("URL to be hit : " + url);

        Response response = when().get(url);
        Postalcode postalcode = response.getBody().as(Postalcode.class);
        log.info("Info : " + postalcode);
        return postalcode;
    }
}
