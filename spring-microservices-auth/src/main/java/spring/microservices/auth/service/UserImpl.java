package spring.microservices.auth.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import spring.microservices.auth.model.Account;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by wonwoo on 2016. 2. 14..
 */
public class UserImpl extends User {
    /**
     *
     */
    private static final long serialVersionUID = 2144213171926616839L;

    public UserImpl(Account users) {
        super(users.getUsername(), users.getPassword(), authorities());
    }

    private static Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(() -> "ROLE_ADMIN");
    }
}