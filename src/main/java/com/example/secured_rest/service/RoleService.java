package com.example.secured_rest.service;

import com.example.secured_rest.models.ERole;
import com.example.secured_rest.models.Role;
import com.example.secured_rest.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByName(ERole name) throws RoleNotFoundException {
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isPresent()) {
            return role.get();
        }
        throw new RoleNotFoundException();
    }
}
