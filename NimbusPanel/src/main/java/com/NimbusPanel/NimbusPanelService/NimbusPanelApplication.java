package com.NimbusPanel.NimbusPanelService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NimbusPanelApplication {

	public static void main(String[] args) {
		SpringApplication.run(NimbusPanelApplication.class, args);
	}
}



//@Component
//class UserCommandLineRunner implements CommandLineRunner{
//
//	@Override
//	public void run(String... args) throws Exception {
//		for (User u : this.userRepository.findAll())
//			System.out.print(u.toString());
//	}
//	
//	@Autowired UserRepository userRepository;
//
//}
//
//
//interface UserRepository extends JpaRepository<User, Long>{
//	
//	Collection<User> findByName(String name);
//}



