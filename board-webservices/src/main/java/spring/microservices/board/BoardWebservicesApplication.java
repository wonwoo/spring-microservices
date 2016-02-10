package spring.microservices.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class BoardWebservicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(BoardWebservicesApplication.class, args);
	}
}
