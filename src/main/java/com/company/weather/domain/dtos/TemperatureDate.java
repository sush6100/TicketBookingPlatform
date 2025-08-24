package com.company.weather.domain.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TemperatureDate {
    private String temp;
    private String maxTemp;
    private String minTemp;
    private String date;
    private String comments;
}
