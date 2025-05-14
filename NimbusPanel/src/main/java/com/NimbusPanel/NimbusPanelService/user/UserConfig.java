package com.NimbusPanel.NimbusPanelService.user;


import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

@Configuration
@AutoConfiguration
public class UserConfig {
	@Bean
	CommandLineRunner commandLineRunner(UserRepository repository) {
		return args ->{
			User sifal = new User(
					"sifal"
					);
			User moh = new User (
					"moh"
					);
			repository.saveAll(
					List.of(sifal, moh)
					);
		};
		
	}
}
