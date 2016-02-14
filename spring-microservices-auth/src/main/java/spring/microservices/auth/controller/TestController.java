package spring.microservices.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by wonwoo on 2016. 2. 14..
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public Principal getCurrentLoggedInUser(Principal user) {
        return user;
    }
}
