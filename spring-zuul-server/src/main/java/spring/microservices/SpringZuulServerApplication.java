package spring.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SpringZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringZuulServerApplication.class, args);
	}
}
