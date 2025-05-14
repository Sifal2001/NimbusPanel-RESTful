package com.NimbusPanel.NimbusPanelService.WeatherCast;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.NimbusPanel.NimbusPanelService.user.User;
import com.NimbusPanel.NimbusPanelService.user.UserRepository;

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
