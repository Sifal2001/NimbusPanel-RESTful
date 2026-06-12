//package com.NimbusPanel.NimbusPanelService.user;
//
//
//import java.util.List;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.autoconfigure.AutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
////@AutoConfiguration
//public class UserConfig {
//	@Bean
//	CommandLineRunner commandLineRunner(UserRepository repository) {
//		return args ->{
//			User sifal = new User(
//				"Sifal",
//				"Yakoubi",
//				"sifalyak@gmail.com",
//				"sifalsifal"
//					);
//			User moh = new User (
//				"Moh",
//				"Ham",
//				"mohham@gmail.com",
//				"mohammoham"
//					);
//			repository.saveAll(
//					List.of(sifal, moh)
//					);
//		};
//		
//	}
//}
