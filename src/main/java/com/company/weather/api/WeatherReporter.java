package com.company.weather.api;

import com.company.weather.domain.dtos.TemperatureDate;
import com.company.weather.services.ReportReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/weather_forecast")
@Slf4j
public class WeatherReporter {

    @Autowired
    private ReportReader reportReaderService;

    @GetMapping(value = "/with_count")
    public ResponseEntity<List<TemperatureDate>> temperatureDates(@RequestParam String location, @RequestParam Integer count) {

        final List<TemperatureDate> temperatureDates = reportReaderService.readReport(location, count);
        log.info("The temperature date details: {}", temperatureDates);

        return ResponseEntity.ok(temperatureDates);
    }

    @GetMapping(value = "/with_day_range")
    public ResponseEntity<List<TemperatureDate>> dayRangeForecast(@RequestParam String location, @RequestParam Integer dayRange) {

        final List<TemperatureDate> temperatureDates = reportReaderService.readReportWithDayRange(location, dayRange);
        log.info("The temperature date details: {}", temperatureDates);

        return ResponseEntity.ok(temperatureDates);
    }
}
