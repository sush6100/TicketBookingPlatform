package xyx.platform;

import xyx.platform.resilience.Fallback;
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

@SpringBootApplication
public class PlatformApplication {

	@Autowired
	private Environment environment;
	public static void main(String[] args) {
		SpringApplication.run(PlatformApplication.class, args);
	}
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	@Bean
	public Fallback getFallback() {
		return new Fallback();
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
