package spring.microservices.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import spring.microservices.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.*;

/**
 * Created by wonwoo on 2016. 2. 11..
 */
@RestController
@RequestMapping("/user")
public class UsersApiGatewayRestController {

    public List<User> getUsersFallback() {
        return Collections.emptyList();
    }

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getUsersFallback")
    @RequestMapping
    public List<User> getUsers() {
        ParameterizedTypeReference<Collection<User>> ptr = new ParameterizedTypeReference<Collection<User>>() {
        };

        ResponseEntity<Collection<User>> responseEntity = this.restTemplate.exchange("http://users-webservices/user", HttpMethod.GET, null, ptr);

        return responseEntity
                .getBody()
                .stream()
                .collect(toList());
    }

}