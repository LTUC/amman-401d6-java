package com.asac.security.infrastructure.services;

import com.asac.security.domain.Role;

public interface SecurityService {

    Role findRoleById(Long roleId);
    Role findRoleByName(String name);
}
