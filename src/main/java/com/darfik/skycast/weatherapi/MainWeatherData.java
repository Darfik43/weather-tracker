package com.darfik.skycast.weatherapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;


@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainWeatherData {

    @JsonProperty("temp")
    private Double temp;

    @JsonProperty("feels_like")
    private Double feelsLike;
}
