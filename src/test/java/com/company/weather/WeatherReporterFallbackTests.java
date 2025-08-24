package com.company.weather;

import com.company.weather.domain.*;
import com.company.weather.exceptions.ReportFetchException;
import com.company.weather.domain.ReportConstants;
import com.company.weather.resilience.Fallback;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WeatherReporterApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class WeatherReporterFallbackTests {

	@Value("${request.url}")
	private String url;
	@Value("${app.id}")
	private String appId;
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
	@MockBean
	private Fallback fallback;
	@MockBean
	private RestTemplate restTemplate;
	@Autowired
	private MockMvc mockMvc;
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
		when(constants.getRequestUrl()).thenReturn(formatUrl(1));
		when(constants.getStormThreshold()).thenReturn(st);
		when(constants.getHeatThreshold()).thenReturn(ht);
		when(constants.getThunderThreshold()).thenReturn(tt);
		when(constants.getWindThreshold()).thenReturn(wt);
		when(constants.getLightRainThreshold()).thenReturn(lt);
		when(constants.getHeavyRainThreshold()).thenReturn(hrt);
		when(constants.getDayMultiplyFactor()).thenReturn(mf);
		when(constants.getKelvinCelsiusFactor()).thenReturn(kf);
		when(restTemplate.getForObject(formatUrl(1), Forecast.class)).thenThrow(new ReportFetchException("External API not reachable"));
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
	private String formatUrl(int overrideCount) {
		return String.format(url, "bangalore", appId, overrideCount);
	}
	@Test
	void readReport_verify_temperatureDates_verify_retry() throws Exception {
		setUp();
		when(constants.getRequestUrl()).thenReturn(formatUrl(1));
		when(wind.getSpeed()).thenReturn("11");
		when(main.getTemp_max()).thenReturn("35");
		mockMvc.perform(get("/weather_forecast/with_count")
						.contentType(MediaType.APPLICATION_JSON).param("location","bangalore").param("count","1"))
				.andExpect(status().is(200))
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

		verify(restTemplate, times(2)).getForObject(anyString(), any());
	}
	@Test
	void readReport_verify_temperatureDates_verify_fallback() throws Exception {
		setUp();
		when(constants.getRequestUrl()).thenReturn(formatUrl(1));
		when(wind.getSpeed()).thenReturn("11");
		when(main.getTemp_max()).thenReturn("35");
		mockMvc.perform(get("/weather_forecast/with_count")
						.contentType(MediaType.APPLICATION_JSON).param("location","bangalore").param("count","1"))
				.andExpect(status().is(200))
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

		verify(fallback, times(1)).fallback(anyString(), any(), any());
	}
	@Test
	void readReport_verify_dayRange_verify_retry() throws Exception {
		setUp();
		when(constants.getRequestUrl()).thenReturn(formatUrl(1));
		when(wind.getSpeed()).thenReturn("11");
		when(main.getTemp_max()).thenReturn("35");
		mockMvc.perform(get("/weather_forecast/with_day_range")
						.contentType(MediaType.APPLICATION_JSON).param("location","bangalore").param("dayRange","1"))
				.andExpect(status().is(200))
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

		verify(restTemplate, times(2)).getForObject(anyString(), any());
	}
	@Test
	void readReport_verify_dayRange_verify_fallback() throws Exception {
		setUp();
		when(constants.getRequestUrl()).thenReturn(formatUrl(1));
		when(wind.getSpeed()).thenReturn("11");
		when(main.getTemp_max()).thenReturn("35");
		mockMvc.perform(get("/weather_forecast/with_day_range")
						.contentType(MediaType.APPLICATION_JSON).param("location","bangalore").param("dayRange","1"))
				.andExpect(status().is(200))
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

		verify(fallback, times(1)).fallback(anyString(), any(), any());
	}
}
