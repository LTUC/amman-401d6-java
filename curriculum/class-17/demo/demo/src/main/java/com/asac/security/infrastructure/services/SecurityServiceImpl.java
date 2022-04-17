package com.asac.security.infrastructure.services;

import com.asac.security.domain.Role;
import com.asac.security.infrastructure.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow();
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name).orElseThrow();
    }
}
