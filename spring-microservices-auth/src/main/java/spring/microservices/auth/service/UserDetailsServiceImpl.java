package spring.microservices.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by wonwoo on 2016. 2. 14..
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    private AccountsRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return new UserImpl();
    }
}