package com.company.weather.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
    private String dt;
    private Main main;
    private Weather[] weather;
    private Clouds clouds;
    private Wind wind;
    private String visibility;
    private String pop;
    private Sys sys;
    private String dt_txt;
}
