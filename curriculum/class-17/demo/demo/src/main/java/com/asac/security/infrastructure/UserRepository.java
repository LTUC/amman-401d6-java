package com.asac.security.infrastructure;

import com.asac.security.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {
    Account findUserByUsername(String username);
}
