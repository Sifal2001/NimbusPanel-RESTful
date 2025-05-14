package com.NimbusPanel.NimbusPanelService.WeatherCast;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfiguration
public class WeatherCastConfig {
	@Bean
	CommandLineRunner commandLineRunner(WeatherCastRepository repository) {
//		CurrentWeather forecast = new CurrentWeather(4.2,4.6,6.9);
//		List<WeatherForecast> list = new ArrayList<>();
//		list.add(new WeatherForecast(4.6,4.6,8.0));
		return args ->{
		};
		
	}
}