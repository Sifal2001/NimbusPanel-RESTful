package com.NimbusPanel.NimbusPanelService.WeatherCast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.NimbusPanel.NimbusPanelService.Config.Config;
import com.google.gson.Gson;

@RestController
public class WeatherCastRequest {
	
    private final WebClient.Builder webClientBuilder;
    private final WeatherCastRepository weatherCastRepository;
    private final WeatherConditionRepository weatherConditionRepository;
    private final CurrentWeatherRepository currentWeatherRepository;
    private final DailyWeatherRepository dailyWeatherRepository;
    
    @Autowired
    public WeatherCastRequest(WebClient.Builder webClientBuilder, WeatherCastRepository weatherCastRepository, 
    		WeatherConditionRepository weatherConditionRepository, CurrentWeatherRepository currentWeatherRepository, 
    		DailyWeatherRepository dailyWeatherRepository) {
		this.webClientBuilder = webClientBuilder;
		this.weatherCastRepository = weatherCastRepository;
		this.weatherConditionRepository = weatherConditionRepository;
		this.currentWeatherRepository = currentWeatherRepository;
		this.dailyWeatherRepository = dailyWeatherRepository;
    }
    
    @GetMapping(path = "/api/v1/castRequest")
    public String makeGetRequest(
    		@RequestParam("lat") String lat,
            @RequestParam("lon") String lon) {
    	String API_KEY = Config.getApiKey();
        String url = "https://api.openweathermap.org/data/3.0/onecall?lat=" +lat+ "&lon=" +lon+ "&units=metric&dt=1709744400&APPID=" + API_KEY; 
        String responseBody = webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
        		.block();
        
        Gson gson = new Gson();
        WeatherCast responseArray = gson.fromJson(responseBody, WeatherCast.class);
        
        currentWeatherRepository.save(responseArray.getCurrent());

        // Save DailyWeather objects
        for (DailyWeather dailyWeather : responseArray.getDaily()) {
            dailyWeatherRepository.save(dailyWeather);
        }

        // Save WeatherCondition objects
        for (DailyWeather dailyWeather : responseArray.getDaily()) {
            for (WeatherCondition weatherCondition : dailyWeather.getWeather()) {
                weatherCondition.setDailyWeather(dailyWeather);
                weatherConditionRepository.save(weatherCondition);
            }
        }
        
        weatherCastRepository.save(responseArray);
        double fieldToPrint2 = responseArray.getCurrent().getTemp();
        
        System.out.println("Field to print: " + fieldToPrint2);
        return responseBody;
    }
}

