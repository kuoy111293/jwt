package com.oneworld.security.app1.repository;

import com.oneworld.security.app1.models.ERole;
import com.oneworld.security.app1.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
