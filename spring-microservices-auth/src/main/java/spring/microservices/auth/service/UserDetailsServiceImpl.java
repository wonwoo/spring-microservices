package spring.microservices.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import spring.microservices.auth.model.Account;
import spring.microservices.auth.repository.UserRepository;

/**
 * Created by wonwoo on 2016. 2. 14..
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {Account users = userRepository.findByUsername(username);
        return new UserImpl(users);
    }
}