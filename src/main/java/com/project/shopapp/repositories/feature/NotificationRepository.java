package com.project.shopapp.repositories.feature;

import com.project.shopapp.models.feature.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
