package com.NimbusPanel.NimbusPanelService.CoordResponse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AutoConfiguration
public class CoordResponseConfig {
	@Bean
	CommandLineRunner commandLineRunner(CoordResponseRepository repository) {
		return args ->{
		};
		
	}
}
