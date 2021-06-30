package com.hititproje;

import com.hititproje.model.User;
import com.hititproje.repository.UserRepository;
import com.hititproje.model.UsersCar;
import com.hititproje.repository.UsersCarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class HititProjeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HititProjeApplication.class, args);
	}

	@Bean
	CommandLineRunner createInitialUsers(UserRepository userRepository, UsersCarRepository usersCarRepository){
		return (args) -> {
			User user1 = new User();
			user1.setUserName("Godemen");
			user1.setFirstName("Egemen");
			user1.setLastName("Gödeoğlu");
			user1.setAge(25);
			User user2 = new User();
			user2.setUserName("Mbtozak");
			user2.setFirstName("Buğse");
			user2.setLastName("Tozak");
			user2.setAge(18);
			userRepository.save(user1);
			userRepository.save(user2);
			UsersCar car1 = new UsersCar();
			car1.setCarName("BMW");
			car1.setCarModel("A180");
			car1.setCarAge(2);
			car1.setUser(user1);
			UsersCar car2 = new UsersCar();
			car2.setCarName("Mercedes");
			car2.setCarModel("C200");
			car2.setCarAge(1);
			car2.setUser(user2);
			usersCarRepository.save(car1);
			usersCarRepository.save(car2);
		};
	}
}
