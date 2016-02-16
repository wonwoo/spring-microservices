package spring.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.audit.listener.AuditListener;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import spring.microservices.model.Board;
import spring.microservices.model.User;

import java.security.Principal;
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

    public List<User> getUsersFallback(@AuthenticationPrincipal Principal principal) {
        //OAuth2ProxyAutoConfiguration
//        System.out.println(o);
        return Collections.emptyList();
    }
    @Autowired
    ApiService apiService;

    //    @HystrixCommand(fallbackMethod = "getUsersFallback")
    @RequestMapping
    public List<User> getUsers( @AuthenticationPrincipal Principal principal) {
        return apiService.getList();
    }

}

@Service
class ApiService{

    @Autowired
    @LoadBalanced
    @Qualifier("loadBalancedOauth2RestTemplate")
    private OAuth2RestOperations restTemplate;

    public List<User> getList(){
//        OAuth2RestTemplate
//        OAuth2ClientAuthenticationProcessingFilter
        ParameterizedTypeReference<Collection<User>> ptr = new ParameterizedTypeReference<Collection<User>>() {
        };
//        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//        headers.add("authorization",request.getHeader("authorization"));
//        HttpEntity httpEntity = new HttpEntity(headers);

//        System.out.println(restTemplate.getAccessToken());

        restTemplate.getAccessToken();
        ResponseEntity<Collection<User>> responseEntity = this.restTemplate.exchange("http://users-webservices/user", HttpMethod.GET, null, ptr);
        return responseEntity
                .getBody()
                .stream()
                .collect(toList());
    }

}