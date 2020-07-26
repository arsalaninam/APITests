package com.sogeti.pojo.countryPostalcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Place {

    @JsonProperty("place name")
    private String placeName;

    @JsonProperty("state abbreviation")
    private String stateAbbreviation;

    private String longitude;
    private String state;
    private String latitude;

    @Override
    public String toString() {
        return "ClassPojo [placeName = " + placeName + ", longitude = " + longitude + ", state = " + state + ", stateAbbreviation = " + stateAbbreviation + ", latitude = " + latitude + "]";
    }
}
