package com.project.shopapp.models.feature;

import com.project.shopapp.models.user_management.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "notifications")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "notification_type", nullable = false)
    @Size(max = 100)
    private String notificationType;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @Column(name = "is_read", nullable = false)
    private boolean isRead;
}
