package com.asac.demo16Security.Repositries;

import com.asac.demo16Security.Models.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    AppUser findByUsername(String username);

}
