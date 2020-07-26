package com.sogeti.pojo.countryStateCity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Place {

    @JsonProperty("place name")
    private String placeName;

    @JsonProperty("post code")
    private String postCode;

    private String longitude;
    private String latitude;


    @Override
    public String toString() {
        return "ClassPojo [placeName = " + placeName + ", longitude = " + longitude + ", postCode = " + postCode + ", latitude = " + latitude + "]";
    }
}
