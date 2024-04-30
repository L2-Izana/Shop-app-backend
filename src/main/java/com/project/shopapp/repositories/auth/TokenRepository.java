package com.project.shopapp.repositories.auth;

import com.project.shopapp.models.auth.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
