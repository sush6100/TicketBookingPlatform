package com.company.weather.services;

import com.company.weather.domain.Forecast;
import com.company.weather.domain.WeatherResponse;
import com.company.weather.domain.dtos.TemperatureDate;
import com.company.weather.helpers.CommentHandler;
import com.company.weather.domain.ReportConstants;
import com.company.weather.resilience.Fallback;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ReportReader {
    @Autowired
    private CommentHandler commentHandler;
    @Autowired
    private ReportConstants constants;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Fallback fallback;

    @Retry(name = "fetchRetry", fallbackMethod = "fallback")
    public List<TemperatureDate> readReport(@RequestParam String location, @RequestParam Integer count)
    {
        final Forecast forecast = restTemplate.getForObject(formatUrl(location, count), Forecast.class);
        final List<TemperatureDate> temperatureDates = filterAndCollectTemperatureDates(forecast);
        log.info("List length:{} and response status: {}.", forecast.getList().length, forecast.getCod());

        return temperatureDates;
    }
    @Retry(name = "fetchRetry", fallbackMethod = "fallback")
    //@CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "fallback")
    public List<TemperatureDate> readReportWithDayRange(@RequestParam String location, @RequestParam Integer days)
    {
        log.info("At readReportWithDayRange: trying to fetch temperature details from external site:");
        final int count = days * constants.getDayMultiplyFactor();
        final Forecast forecast = restTemplate.getForObject(formatUrl(location, count), Forecast.class);
        final List<TemperatureDate> temperatureDates = filterAndCollectTemperatureDates(forecast);

        log.info("List length:{} and response status: {}.", forecast.getList().length, forecast.getCod());

        return temperatureDates;
    }

    private List<TemperatureDate> fallback(String location, Integer days, Throwable e) {

        return fallback.fallback(location, days, e);
    }

    private List<TemperatureDate> filterAndCollectTemperatureDates(Forecast forecast) {
        return Arrays.stream(forecast.getList())
                .map(weatherResponse -> processResponse(weatherResponse))
                .collect(Collectors.toList());
    }
    private String formatUrl(String location, Integer count) {
        return String.format(constants.getRequestUrl(), location, constants.getAppId(), count);
    }
    private TemperatureDate processResponse(WeatherResponse weatherResponse) {
        final TemperatureDate temperatureDate = new TemperatureDate();
        temperatureDate.setTemp(toCelsius(weatherResponse.getMain().getTemp()));
        temperatureDate.setMaxTemp(toCelsius((weatherResponse.getMain().getTemp_max())));
        temperatureDate.setMinTemp(toCelsius(weatherResponse.getMain().getTemp_min()));
        temperatureDate.setDate(weatherResponse.getDt_txt());
        commentHandler.updateComments(weatherResponse, temperatureDate);

        return temperatureDate;
    }
    private String toCelsius(String kelvin) {
        return String.valueOf(Double.valueOf(kelvin) - constants.getKelvinCelsiusFactor());
    }
}
