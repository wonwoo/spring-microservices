package spring.microservices.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UsersWebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersWebservicesApplication.class, args);
	}
}
