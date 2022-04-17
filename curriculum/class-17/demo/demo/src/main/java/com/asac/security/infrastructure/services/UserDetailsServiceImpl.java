package com.asac.security.infrastructure.services;

import com.asac.security.domain.Account;
import com.asac.security.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = userRepository.findUserByUsername(username);

        if (account == null) {
            System.out.print("Username not found");
            throw new UsernameNotFoundException((username + " not found"));
        }

        return account;
    }
}
