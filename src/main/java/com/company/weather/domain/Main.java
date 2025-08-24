package com.company.weather.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Main {
    private String temp;
    private String feels_like;
    private String temp_min;
    private String temp_max;
    private String pressure;
    private String sea_level;
    private String grnd_level;
    private String humidity;
    private String temp_kf;
}
