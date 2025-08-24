package com.company.weather.helpers;

import com.company.weather.domain.ReportConstants;
import com.company.weather.domain.WeatherResponse;
import com.company.weather.domain.dtos.TemperatureDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentHandler {

    @Autowired
    private ReportConstants constants;
    public void updateComments(WeatherResponse weatherResponse, TemperatureDate temperatureDate) {
        if(isWindy(weatherResponse))
            temperatureDate.setComments("It’s too windy, watch out!");
        else if(isThunderStorm(weatherResponse))
            temperatureDate.setComments("Don’t step out! A storm is brewing!");

        else if(isHot(weatherResponse))
            temperatureDate.setComments("Use sunscreen lotion!");
        else if(isLightRainPredicted(weatherResponse) && !isHeavyRainPredicted(weatherResponse))
            temperatureDate.setComments("Light Rains, Carry umbrella");
        else if(!isLightRainPredicted(weatherResponse) && isHeavyRainPredicted(weatherResponse))
            temperatureDate.setComments("Heavy Rains, Carry umbrella");
        else
            temperatureDate.setComments("All is well!");
    }

    private boolean isThunderStorm(WeatherResponse weatherResponse) {
        return Double.valueOf(weatherResponse.getWind().getGust()) > constants.getStormThreshold()
                && Double.valueOf(weatherResponse.getClouds().getAll()) > constants.getThunderThreshold();
    }
    private boolean isWindy(WeatherResponse weatherResponse) {
        return Double.valueOf(weatherResponse.getWind().getSpeed()) > constants.getWindThreshold();
    }
    private boolean isHot(WeatherResponse weatherResponse) {
        return Double.valueOf(toCelsius(weatherResponse.getMain().getTemp_max())) > constants.getHeatThreshold();
    }
    private boolean isLightRainPredicted(WeatherResponse weatherResponse) {
        final Double aDouble = Double.valueOf(weatherResponse.getClouds().getAll());
        return aDouble > constants.getLightRainThreshold() && aDouble <constants.getHeavyRainThreshold();
    }
    private boolean isHeavyRainPredicted(WeatherResponse weatherResponse) {
        return Double.valueOf(weatherResponse.getClouds().getAll()) >= constants.getHeavyRainThreshold();
    }
    private String toCelsius(String kelvin) {
        return String.valueOf(Double.valueOf(kelvin) - constants.getKelvinCelsiusFactor());
    }
}
