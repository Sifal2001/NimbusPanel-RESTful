package com.NimbusPanel.NimbusPanelService.WeatherCast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/weathercast")
public class WeatherCastController {
	
	private final WeatherCastService weathercastService;

	@Autowired
	public WeatherCastController(WeatherCastService weathercastService) {
		this.weathercastService = weathercastService;
	}
}


