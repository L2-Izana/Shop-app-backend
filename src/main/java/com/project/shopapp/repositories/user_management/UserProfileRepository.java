package com.project.shopapp.repositories.user_management;

import com.project.shopapp.models.user_management.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
