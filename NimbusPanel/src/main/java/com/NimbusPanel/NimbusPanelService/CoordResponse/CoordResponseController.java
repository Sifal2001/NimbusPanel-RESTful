package com.NimbusPanel.NimbusPanelService.CoordResponse;

import com.NimbusPanel.NimbusPanelService.Config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


import com.google.gson.Gson;

@RestController
public class CoordResponseController {

    private final WebClient.Builder webClientBuilder;
    private final CoordResponseRepository coordResponseRepository;
    
    @Autowired
    public CoordResponseController(WebClient.Builder webClientBuilder, CoordResponseRepository coordResponseRepository) {
		this.webClientBuilder = webClientBuilder;
		this.coordResponseRepository = coordResponseRepository;
    }
    
    @GetMapping(path = "/api/v1/castLocation")
    public String LocationRequest(@RequestParam("q") String city) {
    	
    	String API_KEY = Config.getApiKey(); 
    	
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" +city+ "&APPID=" + API_KEY; 
        String responseBody = webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
        		.block();
        
        Gson gson = new Gson();
        CoordResponse responseArray = gson.fromJson(responseBody, CoordResponse.class);   
        coordResponseRepository.save(responseArray);
        
        Coord coord = responseArray.getCoord();
        
        double fieldToPrint2 = coord.getLat();
        System.out.println("Field to print: " + fieldToPrint2);
        return responseBody;
    }
}
