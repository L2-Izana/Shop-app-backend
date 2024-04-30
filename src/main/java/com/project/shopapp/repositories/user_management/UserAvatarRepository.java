package com.project.shopapp.repositories.user_management;

import com.project.shopapp.models.user_management.UserAvatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAvatarRepository extends JpaRepository<UserAvatar, Long> {
}
