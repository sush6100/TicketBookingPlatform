package com.company.weather.resilience;

import com.company.weather.domain.dtos.TemperatureDate;
import com.company.weather.exceptions.ReportFetchException;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class Fallback {

    public List<TemperatureDate> fallback(String location, Integer days, Throwable e) {
        try {
            log.info("At Fallback, showing offline records as the live temperature source is not reachable.");

            //The below is only dummy details which are to be fetched from the offline json files which are received from the website
            final TemperatureDate temperatureDate = new TemperatureDate();
            temperatureDate.setComments("From offline records: Heavy Rains, Carry umbrella");
            temperatureDate.setTemp("3.4399999999999977");
            temperatureDate.setMinTemp("3.4399999999999977");
            temperatureDate.setMaxTemp("4.57000000000005");
            temperatureDate.setDate("2023-03-05 06:00:00");

            return Arrays.asList(temperatureDate);
        } catch (Throwable th) {
            throw new ReportFetchException(th.getMessage());
        }
    }
}
