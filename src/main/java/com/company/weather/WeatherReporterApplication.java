package com.company.weather;

import com.company.weather.helpers.CommentHandler;
import com.company.weather.resilience.Fallback;
import com.company.weather.services.ReportReader;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class WeatherReporterApplication {

	@Autowired
	private Environment environment;
	public static void main(String[] args) {
		SpringApplication.run(WeatherReporterApplication.class, args);
	}
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	@Bean
	public ReportReader getReportReader() {
		return new ReportReader();
	}
	@Bean
	public CommentHandler getCommentHandler() {
		return new CommentHandler();
	}
	@Bean
	public Fallback getFallback() {
		return new Fallback();
	}
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
	@Bean
	public InternalResourceViewResolver defaultViewResolver() {
		return new InternalResourceViewResolver();
	}

	@Bean(name = "jasyptStringEncryptor")
	public StringEncryptor stringEncryptor() {

		final SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(environment.getProperty("jasypt.encryptor.password"));
		config.setAlgorithm(environment.getProperty("jasypt.encryptor.algorithm"));
		config.setKeyObtentionIterations(environment.getProperty("jasypt.encryptor.key-obtention-iterations"));
		config.setPoolSize(environment.getProperty("jasypt.encryptor.pool-size"));
		config.setProviderName(environment.getProperty("jasypt.encryptor.provider-name"));
		config.setSaltGeneratorClassName(environment.getProperty("jasypt.encryptor.salt-generator-classname"));
		config.setIvGeneratorClassName(environment.getProperty("jasypt.encryptor.iv-generator-classname"));
		config.setStringOutputType(environment.getProperty("jasypt.encryptor.string-output-type"));

		final PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setConfig(config);

		return encryptor;
	}

}
