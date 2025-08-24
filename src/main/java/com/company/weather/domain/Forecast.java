package com.company.weather.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Forecast {
    private String cod;
    private Integer message;
    private Integer cnt;
    private WeatherResponse[] list;
    private City city;
}
