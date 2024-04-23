package com.project.shopapp.repositories.user_management;

import com.project.shopapp.models.user_management.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByPhoneNumber(String phoneNumber);
}

