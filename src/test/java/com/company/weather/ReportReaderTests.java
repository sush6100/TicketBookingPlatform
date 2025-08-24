package com.company.weather;

import com.company.weather.domain.*;
import com.company.weather.domain.dtos.TemperatureDate;
import com.company.weather.helpers.CommentHandler;
import com.company.weather.domain.ReportConstants;
import com.company.weather.resilience.Fallback;
import com.company.weather.services.ReportReader;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = WeatherReporterApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ReportReaderTests {

	@Value("${request.url}")
	private String url;
	@Value("${app.id}")
	private String appId;
	@Mock
	private RestTemplate restTemplate;
	@Mock
	private ReportConstants constants;
	@Mock
	private CommentHandler commentHandler;
	@Mock
	private Fallback fallback;
	@Mock
	private Forecast forecast;
	@Mock
	private WeatherResponse weatherResponse;
	@Mock
	private Main main;
	@Mock
	private Wind wind;
	@Mock
	private Clouds clouds;
	@InjectMocks
	private ReportReader reportReader = new ReportReader();
	private String location = "bangalore";
	private int count = 1;
	private int  st = 22;
	private int  ht = 40;
	private int  tt = 500;
	private int  wt = 10;
	private double  lt = 70;
	private double  hrt = 75;
	private int  mf = 11;
	private double  kf = 273.15;
	private void setUp() {
		when(constants.getAppId()).thenReturn(appId);
		when(constants.getRequestUrl()).thenReturn(formatUrl());
		when(constants.getStormThreshold()).thenReturn(st);
		when(constants.getHeatThreshold()).thenReturn(ht);
		when(constants.getThunderThreshold()).thenReturn(tt);
		when(constants.getWindThreshold()).thenReturn(wt);
		when(constants.getLightRainThreshold()).thenReturn(lt);
		when(constants.getHeavyRainThreshold()).thenReturn(hrt);
		when(constants.getDayMultiplyFactor()).thenReturn(mf);
		when(constants.getKelvinCelsiusFactor()).thenReturn(kf);
		when(restTemplate.getForObject(formatUrl(), Forecast.class)).thenReturn(forecast);
		when(forecast.getList()).thenReturn(new WeatherResponse[]{weatherResponse});
		when(weatherResponse.getMain()).thenReturn(main);
		when(main.getTemp()).thenReturn("30");
		when(main.getTemp_max()).thenReturn("35");
		when(main.getTemp_min()).thenReturn("5");
		when(weatherResponse.getWind()).thenReturn(wind);
		when(wind.getSpeed()).thenReturn("9");
		when(wind.getGust()).thenReturn("21");
		when(weatherResponse.getClouds()).thenReturn(clouds);
		when(clouds.getAll()).thenReturn("69");
		when(weatherResponse.getDt_txt()).thenReturn("2023-02-21 12:00:00");
	}
	private String formatUrl() {
		return String.format(url, "bangalore", appId, count);
	}
	@Test
	void readReport_verify_with_count() {
		setUp();

		final List<TemperatureDate> temperatureDates = reportReader.readReport(location, count);

		assertEquals(count, temperatureDates.size());
	}
	@Test
	void readReport_verify_with_dayRange() {
		setUp();
		when(wind.getSpeed()).thenReturn("11");
		when(main.getTemp_max()).thenReturn("35");

		final List<TemperatureDate> temperatureDates = reportReader.readReportWithDayRange(location, count);

		assertEquals(count, temperatureDates.size());
	}

}
