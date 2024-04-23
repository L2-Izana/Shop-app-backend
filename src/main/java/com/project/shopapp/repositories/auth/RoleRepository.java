package com.project.shopapp.repositories.auth;

import com.project.shopapp.models.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
