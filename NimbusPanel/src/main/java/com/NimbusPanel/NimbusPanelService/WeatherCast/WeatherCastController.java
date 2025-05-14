package com.NimbusPanel.NimbusPanelService.WeatherCast;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;


@RestController
@RequestMapping(path = "/api/v1/weathercast")
public class WeatherCastController {
	
	private final WeatherCastService weathercastService;

	@Autowired
	public WeatherCastController(WeatherCastService weathercastService) {
		this.weathercastService = weathercastService;
	}
}


