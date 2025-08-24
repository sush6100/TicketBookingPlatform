package com.company.weather.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class City {
    private String id;
    private String name;
    private Coord coord;
    private String country;
    private String population;
    private String timszone;
    private String sunrise;
    private String sunset;
}
