package com.example.service.role;

import com.example.entity.Role;

import com.example.repository.RoleRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class RoleServiceImpl implements RoleService {
	
	@Autowired
    RoleRepository roleRepository;
    @Override
    public Role findByName(String name) {
        Role role = roleRepository.findRoleByName(name);
        return role;
    }
}
