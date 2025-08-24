package com.company.weather.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wind {
    private String speed;
    private String deg;
    private String gust;
}
