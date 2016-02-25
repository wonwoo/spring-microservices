package spring.microservices.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by wonwoo on 2016. 2. 14..
 */
@RestController
public class UserController {

    @RequestMapping("/me")
    public Principal getCurrentLoggedInUser(Principal user) {
        return user;
    }
}
