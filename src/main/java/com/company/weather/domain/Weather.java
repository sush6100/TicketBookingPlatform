package com.company.weather.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather {
    private String id;
    private String main;
    private String description;
    private String icon;

}
