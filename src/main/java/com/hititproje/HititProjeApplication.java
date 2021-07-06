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
		return args -> {
			User user1 = new User();
			user1.setUserName("Godemen34");
			user1.setFirstName("Egemen");
			user1.setLastName("Gödeoğlu");
			user1.setAge(25);
			User user2 = new User();
			user2.setUserName("Mb35");
			user2.setFirstName("Miraç");
			user2.setLastName("Buğse");
			user2.setAge(18);
			User user3 = new User();
			user3.setUserName("brkblbn20");
			user3.setFirstName("Burak");
			user3.setLastName("Bilgin");
			user3.setAge(24);
			User user4 = new User();
			user4.setUserName("fthfrkn24");
			user4.setFirstName("Fatih Furkan");
			user4.setLastName("Yılmaz");
			user4.setAge(32);
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
			userRepository.save(user4);
			UsersCar car1 = new UsersCar();
			car1.setCarName("BMW");
			car1.setCarModel("A180");
			car1.setCarAge(5);
			car1.setUser(user1);
			UsersCar car2 = new UsersCar();
			car2.setCarName("Mercedes");
			car2.setCarModel("C200");
			car2.setCarAge(1);
			car2.setUser(user2);
			UsersCar car3 = new UsersCar();
			car3.setCarName("Audi");
			car3.setCarModel("A8");
			car3.setCarAge(0);
			car3.setUser(user1);
			UsersCar car4 = new UsersCar();
			car4.setCarName("Kia");
			car4.setCarModel("Sportage");
			car4.setCarAge(0);
			car4.setUser(user2);
			UsersCar car5 = new UsersCar();
			car5.setCarName("Skoda");
			car5.setCarModel("Octavia");
			car5.setCarAge(7);
			car5.setUser(user3);
			UsersCar car6 = new UsersCar();
			car6.setCarName("Honda");
			car6.setCarModel("Civic");
			car6.setCarAge(4);
			car6.setUser(user4);
			usersCarRepository.save(car1);
			usersCarRepository.save(car2);
			usersCarRepository.save(car3);
			usersCarRepository.save(car4);
			usersCarRepository.save(car5);
			usersCarRepository.save(car6);
		};
	}
}
