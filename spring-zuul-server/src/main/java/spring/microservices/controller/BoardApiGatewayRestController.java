package spring.microservices.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import spring.microservices.model.Board;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.*;

/**
 * Created by wonwoo on 2016. 2. 11..
 */
@RestController
@RequestMapping("/board")
public class BoardApiGatewayRestController {

    public List<Board> getBoardFallback() {
        return Collections.emptyList();
    }

    //loadBalancedRestTemplate,loadBalancedOauth2RestTemplate
    @Autowired
    @LoadBalanced
    private OAuth2RestOperations restTemplate;


    @HystrixCommand(fallbackMethod = "getBoardFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000") })
    @RequestMapping
    public List<Board> getBoards() {
        ParameterizedTypeReference<Collection<Board>> ptr = new ParameterizedTypeReference<Collection<Board>>() {
        };

        ResponseEntity<Collection<Board>> responseEntity = this.restTemplate.exchange("http://board-webservices/board", HttpMethod.GET, null, ptr);

        return responseEntity
                .getBody()
                .stream()
                .collect(toList());
    }

}
