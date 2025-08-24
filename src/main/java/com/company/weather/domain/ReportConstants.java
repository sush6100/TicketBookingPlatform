package com.company.weather.domain;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ReportConstants {

    @Value("${app.id}")
    private String appId;
    @Value("${request.url}")
    private String requestUrl;
    @Value("${storm.threshold}")
    private int stormThreshold;
    @Value("${thunder.threshold}")
    private int thunderThreshold;
    @Value("${wind.threshold}")
    private int windThreshold;
    @Value("${heat.threshold}")
    private int heatThreshold;
    @Value("${multiply.factor}")
    private int dayMultiplyFactor;
    @Value("${kelvin.celsius.converter}")
    private double kelvinCelsiusFactor;
    @Value("${lightRain.threshold}")
    private double lightRainThreshold;
    @Value("${heavyRain.threshold}")
    private double heavyRainThreshold;
}