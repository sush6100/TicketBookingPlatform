package com.company.weather;

import com.company.weather.domain.*;
import com.company.weather.domain.dtos.TemperatureDate;
import com.company.weather.helpers.CommentHandler;
import com.company.weather.domain.ReportConstants;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = WeatherReporterApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class CommentHandlerTests {

	@Mock
	private ReportConstants constants;
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
	private CommentHandler commentHandler = new CommentHandler();
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
		when(constants.getStormThreshold()).thenReturn(st);
		when(constants.getHeatThreshold()).thenReturn(ht);
		when(constants.getThunderThreshold()).thenReturn(tt);
		when(constants.getWindThreshold()).thenReturn(wt);
		when(constants.getLightRainThreshold()).thenReturn(lt);
		when(constants.getHeavyRainThreshold()).thenReturn(hrt);
		when(constants.getDayMultiplyFactor()).thenReturn(mf);
		when(constants.getKelvinCelsiusFactor()).thenReturn(kf);
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
	@Test
	void readReport_fetchReport() {
		setUp();

		final TemperatureDate temperatureDate = new TemperatureDate();
		commentHandler.updateComments(weatherResponse, temperatureDate);

		assertTrue(temperatureDate.getComments().contains("well"));

	}
	@Test
	void readReport_verify_windy() {
		setUp();
		when(wind.getSpeed()).thenReturn("11");
		when(main.getTemp_max()).thenReturn("35");

		final TemperatureDate temperatureDate = new TemperatureDate();
		commentHandler.updateComments(weatherResponse, temperatureDate);

		assertTrue(temperatureDate.getComments().contains("windy"));
	}
	@Test
	void readReport_verify_storm() {
		setUp();
		when(wind.getGust()).thenReturn("23");
		when(clouds.getAll()).thenReturn("501");

		final TemperatureDate temperatureDate = new TemperatureDate();
		commentHandler.updateComments(weatherResponse, temperatureDate);

		System.out.println(temperatureDate.getComments());
		assertTrue(temperatureDate.getComments().contains("storm"));
	}
	@Test
	void readReport_verify_hot() {
		setUp();
		when(main.getTemp_max()).thenReturn("320");

		final TemperatureDate temperatureDate = new TemperatureDate();
		commentHandler.updateComments(weatherResponse, temperatureDate);

		System.out.println(temperatureDate.getComments());
		assertTrue(temperatureDate.getComments().contains("sunscreen"));
	}
	@Test
	void readReport_verify_umbrellaLightRainfall() {
		setUp();
		when(clouds.getAll()).thenReturn("71");

		final TemperatureDate temperatureDate = new TemperatureDate();
		commentHandler.updateComments(weatherResponse, temperatureDate);

		System.out.println(temperatureDate.getComments());
		assertTrue(temperatureDate.getComments().contains("Light"));
	}
	@Test
	void readReport_verify_umbrellaHeavyRainfall() {
		setUp();
		when(clouds.getAll()).thenReturn("76");

		final TemperatureDate temperatureDate = new TemperatureDate();
		commentHandler.updateComments(weatherResponse, temperatureDate);

		System.out.println(temperatureDate.getComments());
		assertTrue(temperatureDate.getComments().contains("Heavy"));
	}
}
