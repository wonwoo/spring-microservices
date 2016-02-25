package spring.microservices.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.microservices.auth.model.Account;

/**
 * Created by wonwoo on 2016. 2. 25..
 */
@Repository
public interface UserRepository extends JpaRepository<Account,Long>{

    Account findByUsername(String username);
}
