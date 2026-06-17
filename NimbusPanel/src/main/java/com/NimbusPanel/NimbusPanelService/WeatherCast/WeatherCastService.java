package com.NimbusPanel.NimbusPanelService.WeatherCast;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherCastService {
	
	private final WeatherCastRepository weathercastRepository;
	
	@Autowired
	public WeatherCastService(WeatherCastRepository weathercastRepository) {
		this.weathercastRepository = weathercastRepository;
	}

	public List<WeatherCast> getWeatherCast() {
		return weathercastRepository.findAll();
	}
	
}
