package com.sogeti.pojo.countryPostalcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Postalcode {

    @JsonProperty("post code")
    private String postCode;

    @JsonProperty("country abbreviation")
    private String countryAbbreviation;

    private String country;
    private List<Place> places;

    @Override
    public String toString() {
        return "ClassPojo [postCode = " + postCode + ", country = " + country + ", countryAbbreviation = " + countryAbbreviation + ", places = " + places + "]";
    }
}
