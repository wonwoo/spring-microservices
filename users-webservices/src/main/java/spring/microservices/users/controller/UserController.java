package spring.microservices.users.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.microservices.users.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wonwoo on 2016. 2. 9..
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    public List<User> getUsers() {

        return Arrays.asList(new User(1L,"wonwoo","123123"),new User(2L,"kebin","8888"));
    }

}
