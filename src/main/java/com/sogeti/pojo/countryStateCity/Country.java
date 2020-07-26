package com.sogeti.pojo.countryStateCity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Country {

    @JsonProperty("country abbreviation")
    private String countryAbbreviation;

    @JsonProperty("place name")
    private String placeName;

    @JsonProperty("state abbreviation")
    private String stateAbbreviation;

    private String country;
    private String state;
    private List<Place> places;

    @Override
    public String toString() {
        return "ClassPojo [countryAbbreviation = " + countryAbbreviation + ", country = " + country + ", placeName = " + placeName + ", state = " + state + ", stateAbbreviation = " + stateAbbreviation + ", places = " + places + "]";
    }
}
